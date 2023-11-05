package de.ait.app.controllers;

import de.ait.app.dto.AccountResponseDTO;
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
    public List<AccountResponseDTO> getAccounts() {
        if (!accountService.getAllAccounts().isEmpty()) {
            return accountService.getAllAccounts();
        } else
            throw new NullPointerException();
    }

    @PostMapping("/create")
    public AccountResponseDTO createAccount(@RequestBody Account account) {
        if (account.getBalance() > 0) {
            return accountService.save(account);

        } else
            throw new IllegalArgumentException();
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

    @PutMapping("/{id}/withdraw")
    public AccountResponseDTO withdraw(@RequestBody Double sum, @PathVariable long id) {
        if (sum > 0) {
            sum = -sum;
        }
        return accountService.update(id, sum);
    }

    @PutMapping("/{id}/deposit")
    public AccountResponseDTO deposit(@RequestBody Double sum, @PathVariable long id) {
        if (sum < 0) {
            sum = -sum;
        }
        return accountService.update(id, sum);
    }
}