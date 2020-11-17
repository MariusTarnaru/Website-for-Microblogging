package sda.backend.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.model.Account;
import sda.backend.server.model.Avatar;
import sda.backend.server.repository.AccountRepository;
import sda.backend.server.repository.AvatarRepository;
import sda.backend.server.util.AccountMapper;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarRepository avatarRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AvatarRepository avatarRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.avatarRepository = avatarRepository;
        this.accountMapper = accountMapper;
    }

    private DTOAccount accountToDTOAccount(Account account) {
        return DTOAccount.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .password(account.getPassword())
                .username(account.getUsername())
                .displayName(account.getDisplayName())
                .avatar(account.getAvatar())
                .accountStatus(account.getAccountStatus())
                .accountType(account.getAccountType())
                .createdAccount(account.getCreatedAccount())
                .build();
    }

    private Account DTOAccountToAccount(DTOAccount dtoAccount) {
        return Account.builder()
                .accountId(dtoAccount.getAccountId())
                .email(dtoAccount.getEmail())
                .password(dtoAccount.getPassword())
                .username(dtoAccount.getUsername())
                .displayName(dtoAccount.getDisplayName())
                .avatar(dtoAccount.getAvatar())
                .accountStatus(dtoAccount.getAccountStatus())
                .accountType(dtoAccount.getAccountType())
                .createdAccount(dtoAccount.getCreatedAccount())
                .build();
    }

    public ResponseEntity<?> saveAccount(DTOAccount account) {
        if (!emailExist(account.getEmail())) {
            Account newAccount = DTOAccountToAccount(account);
            newAccount.setCreatedAccount(LocalDateTime.now());
            Avatar avatar = new Avatar();
            avatar.setPath(account.getAvatar().getPath());
            account.setAvatar(avatar);
            accountRepository.save(newAccount);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<?> login(DTOAccount account) {
        if(!emailExist(account.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        boolean isAccountValid = accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword()).isPresent();
        if (!isAccountValid) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity<?> getAccountById(Long id) {
        if (idExists(id)) {
            DTOAccount account = accountToDTOAccount(accountRepository.findById(id).get());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> getAccountByUsername(String username) {
        if (usernameExists(username)) {
            DTOAccount account = accountToDTOAccount(accountRepository.findByUsername(username).get());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> updateUserById(DTOAccount dtoAccount, Long id) {
        if (idExists(id)) {
            Account account = accountRepository.findById(id).get();
            accountMapper.updateAccount(dtoAccount, account);
            accountRepository.save(account);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteUserById(Long id) {
        if (idExists(id)) {
            accountRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();
    }

    public boolean idExists(Long id) {
        return accountRepository.existsById(id);
    }

    private boolean emailExist(String email) {
        return accountRepository.existsByEmail(email);
    }

    public boolean usernameExists(String username) {
        return accountRepository.existsByUsername(username);
    }

}
