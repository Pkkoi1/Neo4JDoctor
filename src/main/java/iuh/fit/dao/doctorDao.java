package iuh.fit.dao;

import iuh.fit.entity.Doctor;
import iuh.fit.entity.Person;
import iuh.fit.utils.AppUtils;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class doctorDao {
    private Driver driver;

    private SessionConfig session;

    public doctorDao(Driver driver, String dbName) {
        this.driver = driver;
        session = SessionConfig
                .builder()
                .withDefaultAccessMode(AccessMode.WRITE)
                .withDatabase(dbName)
                .build();
    }

    public void close() {
        driver.close();
    }

    public boolean addDoctor(Doctor doctor)
    {
        String query = "CREATE (d:Doctor {personID: $doctorID, name: $name, phone: $phone, speciality: $speciality, deptID : $depID})";
        Map<String, Object> params = Map.of("doctorID", doctor.getPersonID(), "name", doctor.getName(), "phone", doctor.getPhone(), "speciality", doctor.getSpeciality(), "depID", doctor.getDeptID());
        try(Session ses = driver.session(session))
        {
            return ses.executeWrite(tx -> {
                tx.run(query, params).consume();
                System.out.println("Add doctor successfully");
                return true;
            });
        }
    }
    public Map<String, Long> getNoOfDoctorsBySpe(String key)
    {
        String query = "MATCH (d:Doctor) - [:HAVE] -> (dept:Department) WHERE dept.name = $key RETURN d.speciality, count(d) as number";
        Map<String, Object> params = Map.of("key", key);
        try(Session ses = driver.session(session))
        {
            return ses.executeRead(tx -> {
                return tx.run(query, params).stream()
                        .collect(
                                Collectors.toMap(
                                        record -> record.get("d.speciality").asString(),
                                        record -> record.get("number").asLong(),
                                        (a, b) -> a,
                                        LinkedHashMap::new

                                )
                        );
            });
        }
    }
    public List<Doctor> listDoctor()
    {
        String query = "MATCH (d:Doctor) RETURN d";
        Map<String, Object> params = Map.of();
        try(Session ses = driver.session(session))
        {
            return ses.executeRead(tx -> {
             Result result = tx.run(query, params);
                return result.stream()
                        .map(record -> record.get("d").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Doctor.class))
                        .toList();
            });
        }
    }
    public List<Doctor> listDoctorBySpecially(String key) {
        String query = "CALL db.index.fulltext.queryNodes('text_index_dSpecially', $key) YIELD node RETURN node as d";
        Map<String, Object> params = Map.of("key", key);
        List<Doctor> doctors = new ArrayList<>();

        try (Session ses = driver.session(session)) {
            return ses.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result.stream()
                        .map(record -> record.get("d").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Doctor.class))
                        .toList();
            });

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
