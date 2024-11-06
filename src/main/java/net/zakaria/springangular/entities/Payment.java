package net.zakaria.springangular.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter @ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private PaymentType type;
    private PaymentStatus status;
    private double amount;
    private String file;
    @ManyToOne
    private Student student;
}
