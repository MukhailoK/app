package de.ait.app.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
@EqualsAndHashCode(exclude = {"balance", "id"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iban;
    private Double balance;

    public Account(Double balance) {
        this.balance = balance;
    }
}
