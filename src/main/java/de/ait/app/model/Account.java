package de.ait.app.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
@EqualsAndHashCode(exclude = {"iban", "balance"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iban;
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }
}
