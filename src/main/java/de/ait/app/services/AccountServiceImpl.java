package de.ait.app.services;

import de.ait.app.dto.AccountResponseDTO;
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


    private String generateIban() {
        return country + ++num;

    }

    @Autowired
    AccountRepository accountRepository;

    public List<AccountResponseDTO> getAllAccounts() {

        return new ArrayList<>(AccountResponseDTO.from(accountRepository.findAll()));
    }

    public AccountResponseDTO getAccountById(Long id) {
        return AccountResponseDTO.from(accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found")));
    }

    public AccountResponseDTO save(Account account) {
        account.setIban(generateIban());
        return AccountResponseDTO.from(accountRepository.save(account));
    }

    public AccountResponseDTO update(Long id, Double sum) {
        Account account = (accountRepository.findById(id).orElseThrow(() -> new RuntimeException("not found")));
        account.setBalance(account.getBalance() + sum);
        return AccountResponseDTO.from(accountRepository.save(account));
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
