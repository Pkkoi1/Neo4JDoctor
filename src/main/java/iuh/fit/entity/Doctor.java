package iuh.fit.entity;
import lombok.*;



public class Doctor extends Person{
    private String speciality;
    private String deptID;

    public Doctor(String personID, String name, String phone, String speciality, String deptID) {
        super(personID, name, phone);
        this.speciality = speciality;
        this.deptID = deptID;
    }

    public Doctor(String personID, String name, String phone) {
        super(personID, name, phone);
    }

    public Doctor() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                ", deptID='" + deptID + '\'' +
                ", personID='" + personID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
