package com.tests.r1vs_allstreaming.Repositories;

import com.tests.r1vs_allstreaming.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
