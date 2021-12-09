package com.tests.r1vs_allstreaming.Repositories;

import com.tests.r1vs_allstreaming.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
