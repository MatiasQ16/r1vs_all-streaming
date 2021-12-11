package com.tests.r1vs_allstreaming.Controllers;

import com.tests.r1vs_allstreaming.Models.Account;
import com.tests.r1vs_allstreaming.Models.Rental;
import com.tests.r1vs_allstreaming.Models.Status;
import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Services.AccountService;
import com.tests.r1vs_allstreaming.Services.RentalService;
import com.tests.r1vs_allstreaming.Services.StatusService;
import com.tests.r1vs_allstreaming.Services.TypeService;
import com.tests.r1vs_allstreaming.Utils.EnumStatus;
import com.tests.r1vs_allstreaming.Utils.ErrorResponse;
import net.bytebuddy.utility.RandomString;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.RandomStringUtils;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private RentalService rentalService;

    /**
     * The getAccounts method obtain the accounts from database and return them.
     * @return ResponseEntity<?> With the information and HttpStatus.
     */
    @GetMapping("")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<List<Account>>(accountService.findAll(), HttpStatus.OK);
    }

    /**
     *The getAccountsByType method obtain the accounts filtered by type of account
     * from database and return them.
     * @param typeId
     * @return ResponseEntity<?> With the information and HttpStatus.
     */
    @GetMapping("/t={typeId}")
    public ResponseEntity<?> getAccountsByType(@PathVariable Long typeId) {
        Type type = typeService.findById(typeId);

        if (type == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("The type doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.findAllByType(typeId), HttpStatus.OK);
    }

    /**
     * The createAccount method save an account on database
     * @param account
     * @return ResponseEntity<?> With the information and HttpStatus
     */
    @PostMapping("")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
    }

    /**
     * The setStatus method, change the status of an account and update it on database.
     * @param accountId
     * @param statusId
     * @return ResponseEntity<?> With the information and HttpStatus
     */
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

    /**
     * The leaseAccount method create a rental for an account and do the "lease" process, updating
     * the password and status for the account.
     * If the status is 'in use' or 'blocked' then can't lease
     * @param accountId
     * @param time
     * @return
     */
    @PostMapping("/lease/{accountId}/t={time}")
    public ResponseEntity<?> leaseAccount(@PathVariable Long accountId, @PathVariable String time) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("The account doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        // Validation of lease
        if (account.getStatus().getId() == EnumStatus.IN_USE.getStatusId()){
            return new ResponseEntity<>(new ErrorResponse("The account is been used"), HttpStatus.BAD_REQUEST);
        }

        if(account.getStatus().getId() == EnumStatus.BLOCKED.getStatusId()){
            return new ResponseEntity<>(new ErrorResponse("The account is blocked"), HttpStatus.UNAUTHORIZED);
        }

        account.setPassword(RandomStringUtils.randomAlphanumeric(12));

        Rental rental = new Rental();

        LocalDateTime now = LocalDateTime.now();
        rental.setDate(now);

        rental.setExpiration(time.equalsIgnoreCase("day") ? now.plusHours(24) : now.plusHours(1));

        rental.setAccount(account);
        account.getRentals().add(rental);

        Status inUse = statusService.findById(EnumStatus.IN_USE.getStatusId());
        account.setStatus(inUse);

        rentalService.save(rental);

        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);

    }


}
