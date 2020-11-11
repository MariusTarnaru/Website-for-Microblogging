package sda.backend.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sda.backend.server.dto.DTOAccount;
import sda.backend.server.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Transactional
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveAccount(@RequestBody DTOAccount account) {
            return accountService.saveAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DTOAccount account) {
        return accountService.login(account);
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAccountByUsername(@PathVariable String username){
        return accountService.getAccountByUsername(username);
    }

/*
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> updateUserById(DTOAccount dtoAccount, @PathVariable Long id){
        return accountService.updateUserById(dtoAccount, id);
    }
*/

}
