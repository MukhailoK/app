package de.ait.app.services;

import de.ait.app.model.Account;
import de.ait.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try {

            return accountRepository.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrUpdate(Account account) {
        try {
            account.setIban(getIban());
            accountRepository.save(account);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) {
        try {
            accountRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getById(long id){
        return accountRepository.getById(id);
    }
}
