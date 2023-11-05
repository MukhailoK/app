package de.ait.app.repositories;

import de.ait.app.dto.AccountRequestDTO;
import de.ait.app.dto.AccountResponseDTO;
import de.ait.app.model.Account;
import de.ait.app.model.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
 }
