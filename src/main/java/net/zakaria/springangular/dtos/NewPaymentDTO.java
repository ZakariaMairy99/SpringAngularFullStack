package net.zakaria.springangular.dtos;

import lombok.*;
import net.zakaria.springangular.entities.PaymentType;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class NewPaymentDTO {
    private double amount;
    private PaymentType type;
    private LocalDate date;
    private String studentCode;
}
