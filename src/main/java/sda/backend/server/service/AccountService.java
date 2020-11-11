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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AvatarRepository avatarRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AvatarRepository avatarRepository) {
        this.accountRepository = accountRepository;
        this.avatarRepository = avatarRepository;
    }

    private DTOAccount accountToDTOAcount(Account account) {
        DTOAccount dtoAccount = DTOAccount.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .password(account.getPassword())
                .username(account.getUsername())
                .displayName(account.getDisplayName())
                .avatar(account.getAvatar())
                .accountStatus(account.getAccountStatus())
                .accountType(account.getAccountType())
                .cratedAccount(account.getCratedAccount())
                .build();
        return dtoAccount;
    }

    private Account DTOAccountToAcount(DTOAccount dtoAccount) {
        Account account = Account.builder()
                .accountId(dtoAccount.getAccountId())
                .email(dtoAccount.getEmail())
                .password(dtoAccount.getPassword())
                .username(dtoAccount.getUsername())
                .displayName(dtoAccount.getDisplayName())
                .avatar(dtoAccount.getAvatar())
                .accountStatus(dtoAccount.getAccountStatus())
                .accountType(dtoAccount.getAccountType())
                .cratedAccount(dtoAccount.getCratedAccount())
                .build();
        return account;
    }

    public ResponseEntity<?> saveAccount(DTOAccount account) {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Account newAccount = DTOAccountToAcount(account);
        newAccount.setCratedAccount(LocalDateTime.now());
        Avatar avatar = new Avatar();
        avatar.setPath(account.getAvatar().getPath());
        account.setAvatar(avatar);
        avatar.setAccount(newAccount);
        accountRepository.save(newAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> login(DTOAccount account) {
        boolean isAccountValid = accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword()).isPresent();
        if (!isAccountValid) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAccountById(Long id) {
        if (idExists(id)) {
            DTOAccount account = accountToDTOAcount(accountRepository.findById(id).get());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> getAccountByUsername(String username) {
        if (usernameExists(username)) {
            DTOAccount account = accountToDTOAcount(accountRepository.findByUsername(username).get());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public boolean usernameExists(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }

    public boolean idExists(Long id) {
        return accountRepository.findById(id).isPresent();
    }

/*
    public ResponseEntity<?> updateUserById(DTOAccount dtoAccount, Long id) {
        if(idExists(id)&&usernameExists(dtoAccount.getUsername())){
            Account
            return
        }
    }
*/
}
