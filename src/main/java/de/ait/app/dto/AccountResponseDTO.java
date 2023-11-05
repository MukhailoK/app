package de.ait.app.dto;

import de.ait.app.model.Account;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String iban;
    private double balance;


    public static AccountResponseDTO from(Account account) {
        return new AccountResponseDTO(account.getId(),
                account.getIban(),
                account.getBalance());
    }

    public static List<AccountResponseDTO> from(List<Account> accounts) {
        return accounts.stream().map(AccountResponseDTO::from).toList();
    }
}
