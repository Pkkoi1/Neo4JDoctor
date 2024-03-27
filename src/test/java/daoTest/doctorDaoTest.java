package daoTest;

import iuh.fit.dao.doctorDao;
import iuh.fit.entity.Doctor;
import iuh.fit.entity.Person;
import iuh.fit.utils.AppUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;

public class doctorDaoTest {
    static String dbName = "khoi21042951";
    static doctorDao doctorDao  ;

    @BeforeAll
    static void setup() {
         doctorDao = new doctorDao(AppUtils.driver(), dbName);
    }
    @AfterAll
    static void close() {
        doctorDao.close();
    }

    @Test
    void testAddDoctor() {
         Doctor doctor = new Doctor("11", "Khoi", "123456789", "Cardiology", "2");
            doctorDao.addDoctor(doctor);
    }
    @Test
    void testList() {
        doctorDao.getNoOfDoctorsBySpe("City General").forEach((k,v)-> System.out.println(k + " có " + v + " bác sĩ"));
    }
    @Test
    void testListBySpecially() {
        doctorDao.listDoctorBySpecially("Cardiology").forEach(System.out::println);
    }
@Test
    void testListDoc()
{
    doctorDao.listDoctor().forEach(System.out::println);}

}
