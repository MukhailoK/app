package de.ait.app.services;

import de.ait.app.model.Account;
import de.ait.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl {

    private static final String country = "DE ";
    private long num = 1000000000000L;

    private String getIban() {
        return country + ++num;

    }

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }

    private Account getAccountById(long id) {
        return accountRepository.findById(id).get();
    }

    public void saveOrUpdate(Account account) {
        account.setIban(getIban());
        accountRepository.save(account);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
