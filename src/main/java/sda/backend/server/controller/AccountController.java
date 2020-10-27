package sda.backend.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.service.AccountService;

import java.util.List;

@RestController
@Transactional
@Validated
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public boolean login(@RequestBody DTOAccount account) {
        return account.getEmail().equals("email") && account.getPassword().equals("password");
    }

    @GetMapping("/accounts")
    public List<DTOAccount> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{username}")
    public DTOAccount getByUsername(@PathVariable String username) {
        return accountService.getAccountByUsername(username);
    }

    @GetMapping("accounts/{id}")
    public DTOAccount getById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/addAccount")
    public void saveAccount(@RequestBody DTOAccount account) {
        accountService.saveAccount(account);
    }


}
