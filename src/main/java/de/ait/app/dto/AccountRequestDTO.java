package de.ait.app.dto;

import de.ait.app.model.Account;
import de.ait.app.model.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AccountRequestDTO implements Function<AccountDTO, Account> {

    @Override
    public Account apply(AccountDTO account) {
        return new Account(null, account.getIban(), account.getBalance());
    }

    public List<Account> apply(List<AccountDTO> accounts) {
        return accounts.stream().map(this).collect(Collectors.toList());
    }
}
