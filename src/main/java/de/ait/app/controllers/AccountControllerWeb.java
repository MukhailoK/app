package de.ait.app.controllers;

import de.ait.app.model.Account;
import de.ait.app.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountControllerWeb {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/register_acc")
    public String blablabla() {
        return "accountRegister";
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestParam("balance") double balance) {
        accountService.saveOrUpdate(new Account(balance));
        return "createAccountSuccess";
    }

    @GetMapping("/accounts")
    public String getAllUsers(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accountsList";
    }

    @DeleteMapping("/delete_acc/{id}")
    public String deleteAccById(@PathVariable long id) {
        accountService.delete(id);
        return "accountDeletedSuccessfully";
    }
}
