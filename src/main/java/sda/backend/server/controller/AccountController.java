package sda.backend.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.service.AccountService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DTOAccount account) {
        DTOAccount dtoAccount = accountService.getAccountByEmail(account.getEmail());
        return new ResponseEntity<>(dtoAccount,HttpStatus.ACCEPTED);
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

    @PostMapping("/register")
    public ResponseEntity saveAccount(@RequestBody @Valid DTOAccount account) {

            DTOAccount dtoAccountFromDB = accountService.saveAccount(account);
            return new ResponseEntity<>(dtoAccountFromDB,HttpStatus.CREATED);

    }


}
