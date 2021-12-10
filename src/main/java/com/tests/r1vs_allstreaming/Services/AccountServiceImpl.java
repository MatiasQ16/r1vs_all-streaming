package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Account;
import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Repositories.AccountRepository;
import com.tests.r1vs_allstreaming.Repositories.StatusRepository;
import com.tests.r1vs_allstreaming.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        account.setType(typeRepository.getById(account.getType().getId()));
        account.setStatus(statusRepository.getById(account.getStatus().getId()));
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAllByType(Long typeId) {
        return accountRepository.findAll().stream().filter((account) -> account.getType().getId() == typeId).collect(Collectors.toList());
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
}
