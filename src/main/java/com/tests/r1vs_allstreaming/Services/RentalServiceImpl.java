package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Rental;
import com.tests.r1vs_allstreaming.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    @Transactional
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}
