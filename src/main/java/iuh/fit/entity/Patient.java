package iuh.fit.entity;
import lombok.*;
@ToString
@Getter

@NoArgsConstructor

enum Gender{
    m, f;
}

public class Patient extends Person{
    private Gender gender;
    private String dateOfBirth;
    private String address;

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Patient(String personID, String name, String phone, Gender gender, String dateOfBirth, String address) {
        super(personID, name, phone);
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Patient(Gender gender, String dateOfBirth, String address) {
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
