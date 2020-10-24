package sda.backend.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.backend.server.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByEmail(String email);

    Account findById(long id);

    Account findByUsername(String username);

    Account findByDisplayName(String displayName);

    List<Account> findAll();
}
