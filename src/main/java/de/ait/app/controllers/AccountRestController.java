package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public List<Account> getAccounts() {
        if (!accountService.getAllAccounts().isEmpty()) {
            return accountService.getAllAccounts();
        } else
            throw new RuntimeException(new NullPointerException());
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        if (!accountService.getAllAccounts().contains(account) &&
                account.getBalance() >= 0) {
            accountService.saveOrUpdate(account);
            return account;
        } else
            throw new RuntimeException(new IllegalArgumentException());
    }

    @DeleteMapping
    public void deleteAccount(@RequestBody Long id) {
        if (accountService.getById(id) != null) {
            accountService.delete(id);
        } else
            throw new RuntimeException(new ChangeSetPersister.NotFoundException());
    }

    @PutMapping("/withdraw")
    public Account withdraw(@RequestBody Account account) {
        if (accountService.getAllAccounts().contains(account)) {
            if (account.getBalance() < 0) {
                account.setBalance(-account.getBalance());
            }
            double balance = accountService.getById(account.getId()).getBalance() - account.getBalance();
            account.setBalance(balance);
            accountService.saveOrUpdate(account);
            return accountService.getById(account.getId());
        } else
            throw new RuntimeException(new ChangeSetPersister.NotFoundException());

    }

    @PutMapping("/toAdd")
    public Account toAdd(@RequestBody Account account) {
        if (accountService.getAllAccounts().contains(account)) {
            if (account.getBalance() < 0) {
                account.setBalance(-account.getBalance());
            }
            double balance = accountService.getById(account.getId()).getBalance() + account.getBalance();
            account.setBalance(balance);
            accountService.saveOrUpdate(account);
            return accountService.getById(account.getId());
        } else
            throw new RuntimeException(new ChangeSetPersister.NotFoundException());

    }
}
