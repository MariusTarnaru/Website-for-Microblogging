package sda.backend.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.backend.server.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();

    Optional<Account> findByEmailAndPassword(String email, String password);

    Optional<Account> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
