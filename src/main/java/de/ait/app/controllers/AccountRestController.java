package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new NullPointerException();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        if (!accountService.getAllAccounts().contains(account) &&
                account.getBalance() > 0) {
            accountService.save(account);
            return account;
        } else
            throw new IllegalArgumentException();
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

    @PutMapping("/{id}/withdraw")
    public Account withdraw(@RequestBody double sum, @PathVariable long id) {
        if (sum > 0) {
            sum = -sum;
        }
        accountService.update(id, sum);
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}/deposit")
    public Account deposit(@RequestBody double sum, @PathVariable long id) {
        if (sum < 0) {
            sum = -sum;
        }
        accountService.update(id, sum);
        return accountService.getAccountById(id);
    }
}
