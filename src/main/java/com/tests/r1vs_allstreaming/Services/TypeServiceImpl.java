package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    @Transactional
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Type findById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Type delete(Long id) {
        Type type = typeRepository.getById(id);
        typeRepository.deleteById(id);
        return type;
    }


}
