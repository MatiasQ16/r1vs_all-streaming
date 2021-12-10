package com.tests.r1vs_allstreaming.Controllers;

import com.tests.r1vs_allstreaming.Models.Account;
import com.tests.r1vs_allstreaming.Models.Status;
import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Services.AccountService;
import com.tests.r1vs_allstreaming.Services.StatusService;
import com.tests.r1vs_allstreaming.Services.TypeService;
import com.tests.r1vs_allstreaming.Utils.ErrorResponse;
import org.apache.coyote.Response;
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

    @Autowired
    private TypeService typeService;

    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<List<Account>>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/t={typeId}")
    public ResponseEntity<?> getAccountsByType(@PathVariable Long typeId) {
        Type type = typeService.findById(typeId);

        if (type == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("The type doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.findAllByType(typeId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}/s={statusId}")
    public ResponseEntity<?> setStatus(@PathVariable Long accountId, @PathVariable Long statusId) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("The account doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        Status status = statusService.findById(statusId);
        if (status == null) {
            return new ResponseEntity<>(new ErrorResponse("The status that are trying to set to account doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        account.setStatus(status);
        return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
    }

}
