package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();

    public Account save(Account account);

    public List<Account> findAllByType(Long typeId);
}
