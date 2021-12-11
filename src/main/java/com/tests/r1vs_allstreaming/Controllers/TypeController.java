package com.tests.r1vs_allstreaming.Controllers;

import com.tests.r1vs_allstreaming.Models.Account;
import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Services.AccountService;
import com.tests.r1vs_allstreaming.Services.TypeService;
import com.tests.r1vs_allstreaming.Utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private AccountService accountService;

    /**
     * The getTypes method obtain the types from database
     * @return ResponseEntity<?> With the information and HttpStatus
     */
    @GetMapping("")
    public ResponseEntity<?> getTypes() {
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }

    /**
     * The createType method is used for create a type on database
     * @param type
     * @return ResponseEntity<?> With the information and HttpStatus
     */
    @PostMapping("")
    public ResponseEntity<Type> createType(@Valid @RequestBody Type type) {
        return new ResponseEntity<Type>(typeService.save(type), HttpStatus.CREATED);
    }

    /**
     * The updateType method update a type on database
     * @param type
     * @param typeId
     * @return ResponseEntity<?> With the information and HttpStatus
     */
    @PutMapping("/{typeId}")
    public ResponseEntity<?> updateType(@Valid @RequestBody Type type, @PathVariable Long typeId) {
        Type actualType = typeService.findById(typeId);
        if (actualType == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("The type doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        actualType.setType(type.getType());
        return new ResponseEntity<Type>(typeService.save(actualType), HttpStatus.OK);
    }

    /**
     * The deleteType method delete a type on database
     * @param typeId
     * @return ResponseEntity<?> With informations and HttpStatus
     */
    @DeleteMapping("/{typeId}")
    public ResponseEntity<?> deleteType(@PathVariable Long typeId){
        Type type = typeService.findById(typeId);

        if (type == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Type doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        List<Account> typeAccounts = accountService.findAllByType(type.getId());

        if (typeAccounts.size() > 0) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("The type has accounts associated, it can't be deleted"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(typeService.delete(typeId), HttpStatus.OK);
    }

}
