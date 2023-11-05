package de.ait.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"balance"})
@Builder
public class AccountDTO {
    private String iban;
    private Double balance;
}
