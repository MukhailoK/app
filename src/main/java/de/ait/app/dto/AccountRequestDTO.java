package de.ait.app.dto;

import de.ait.app.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDTO {
    private Long id;
    private String iban;
    private double balance;

    public static List<Account> toAccount(List<AccountRequestDTO> accounts) {
        return accounts.stream().map(AccountRequestDTO::toAccount).collect(Collectors.toList());
    }


    public static Account toAccount(AccountRequestDTO account) {
        return new Account(null, account.getIban(), account.getBalance());
    }
}
