package de.ait.app.dto;

import de.ait.app.model.Account;
import de.ait.app.model.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class AccountResponseDTO implements Function<Account, AccountDTO> {

    @Override
    public AccountDTO apply(Account account) {
        return new AccountDTO(account.getIban(),
                account.getBalance());
    }

    public List<AccountDTO> apply(List<Account> accounts) {
        return accounts.stream().map(this).toList();
    }
}
