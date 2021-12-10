package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Status;
import com.tests.r1vs_allstreaming.Repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    @Transactional(readOnly = true)
    public Status findById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }
}
