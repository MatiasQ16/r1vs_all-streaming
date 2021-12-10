package com.tests.r1vs_allstreaming.Controllers;

import com.tests.r1vs_allstreaming.Models.Account;
import com.tests.r1vs_allstreaming.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<List<Account>>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/t={typeId}")
    public ResponseEntity<List<Account>> getAccountsByType(@PathVariable Long typeId) {
        return new ResponseEntity<>(accountService.findAllByType(typeId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
    }

}
