package de.ait.app.services;

import de.ait.app.dto.AccountResponseDTO;
import de.ait.app.model.Account;
import de.ait.app.model.AccountDTO;
import de.ait.app.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {

    private static final String country = "DE ";
    private long num = 1000000000000L;


    private String generateIban() {
        return country + ++num;

    }

    @Autowired
    private final AccountRepository accountRepository;

    private final AccountResponseDTO accountResponseDTO;

    public List<AccountDTO> getAllAccounts() {
        return new ArrayList<>(accountResponseDTO.apply(accountRepository.findAll()));
    }

    public AccountDTO getAccountById(Long id) {
        return accountResponseDTO.apply(accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found")));
    }

    public AccountDTO save(Account account) {
        account.setIban(generateIban());
        return accountResponseDTO.apply(accountRepository.save(account));
    }

    public AccountDTO update(Long id, Double sum) {
        Account account = (accountRepository.findById(id).orElseThrow(() -> new RuntimeException("not found")));
        account.setBalance(account.getBalance() + sum);
        return accountResponseDTO.apply(accountRepository.save(account));
    }

    public Boolean delete(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
