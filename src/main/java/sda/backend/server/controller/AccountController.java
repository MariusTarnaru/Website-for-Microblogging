package sda.backend.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.service.AccountService;

import java.util.List;


@RestController
@Transactional
@Validated
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public DTOAccount loginByEmail(String email) {
        return accountService.getAccountByEmail(email);
    }

    @GetMapping("/accounts")
    public List<DTOAccount> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
