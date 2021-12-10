package com.tests.r1vs_allstreaming.Services;

import com.tests.r1vs_allstreaming.Models.Type;

import java.util.List;

public interface TypeService {
    public List<Type> findAll();

    public Type save(Type type);

    public Type findById(Long id);

    public Type delete(Long id);

}
