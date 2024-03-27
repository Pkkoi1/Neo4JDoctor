package iuh.fit.entity;


import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Treatment {
    @ToString.Exclude
    private Doctor doctor;
    @ToString.Exclude
    private Patient patient;
    private LocalDate startOfDate;
    private LocalDate endDate;
    private String diagnosis;
}
