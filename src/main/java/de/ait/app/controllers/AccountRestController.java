package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.model.AccountDTO;
import de.ait.app.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Autowired
    private AccountServiceImpl accountService;


    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return !accountService.getAllAccounts().isEmpty() ?
                ResponseEntity.status(HttpStatus.FOUND).body(accountService.getAllAccounts()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account) {
        if (account.getBalance() > 0) {
            AccountDTO saveResult = accountService.save(account);
            return saveResult != null ?
                    ResponseEntity.ok(saveResult) :
                    ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO foundResult = accountService.getAccountById(id);
        return foundResult.equals(new RuntimeException("Account not found")) ?
                ResponseEntity.status(HttpStatus.FOUND).body(foundResult) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return accountService.delete(id) ?
                ResponseEntity.ok("account with " + id + " deleted") :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@RequestBody Double sum, @PathVariable long id) {
        if (sum >= 0) {
            return ResponseEntity.badRequest().build();
        }

        AccountDTO account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        AccountDTO updatedAccount = accountService.update(id, sum);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAccount);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@RequestParam Double sum, @PathVariable long id) {
        if (sum <= 0) {
            return ResponseEntity.badRequest().build();
        }

        AccountDTO account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        AccountDTO updatedAccount = accountService.update(id, sum);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedAccount);
    }
}
