package sda.backend.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.exception.EmailAlreadyUsedException;
import sda.backend.server.exception.UserNotFoundException;
import sda.backend.server.model.Account;
import sda.backend.server.model.Avatar;
import sda.backend.server.repository.AccountRepository;
import sda.backend.server.repository.AvatarRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public DTOAccount getAccountByEmail(String email) {
        Account accountFromDB = accountRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

        return accountToDTOAcount(accountRepository.findByEmail(email).get());
    }


    public List<DTOAccount> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::accountToDTOAcount)
                .collect(Collectors.toList());
    }

    public DTOAccount getAccountById(Long id) {
        return accountToDTOAcount(accountRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
    }

    public DTOAccount getAccountByUsername(String username) {
        return accountToDTOAcount(accountRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException()));
    }

    public DTOAccount saveAccount(DTOAccount account) {

        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        Account newAccount = DTOAccountToAcount(account);
        newAccount.setCratedAccount(LocalDateTime.now());
        Avatar avatar = new Avatar();
        avatar.setPath(account.getAvatar().getPath());
        account.setAvatar(avatar);
        avatar.setAccount(newAccount);
        accountRepository.save(newAccount);
        return accountToDTOAcount(accountRepository.findByEmail(newAccount.getEmail()).get());
    }

    public boolean accountExists(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }
}
