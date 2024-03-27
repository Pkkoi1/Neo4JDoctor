package iuh.fit.entity;
import lombok.*;


public abstract class Person {
    protected String personID;
    protected String name;
    protected String phone;

    public Person(String personID, String name, String phone) {
        this.personID = personID;
        this.name = name;
        this.phone = phone;
    }

    public Person() {
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID='" + personID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
