package sda.backend.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.model.Account;
import sda.backend.server.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // TODO: 10/24/2020
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

    // TODO: 10/24/2020
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
        return accountToDTOAcount(accountRepository.findByEmail(email));
    }

    public List<DTOAccount> getAllAccounts() {
       return accountRepository.findAll().stream()
               .map(this::accountToDTOAcount)
               .collect(Collectors.toList());
    }
}
