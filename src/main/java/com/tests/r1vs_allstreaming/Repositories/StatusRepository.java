package com.tests.r1vs_allstreaming.Repositories;

import com.tests.r1vs_allstreaming.Models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
