package com.tests.r1vs_allstreaming.DataJPA;

import com.tests.r1vs_allstreaming.Repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountControllerTestDataJPA {

    @Autowired
    private AccountRepository accountRepository;


}
