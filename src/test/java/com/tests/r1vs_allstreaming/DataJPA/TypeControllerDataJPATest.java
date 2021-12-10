package com.tests.r1vs_allstreaming.DataJPA;

import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Repositories.TypeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TypeControllerDataJPATest {

    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void testCreateType() {
        Type type = new Type("Streaming");
        Type newType = typeRepository.save(type);

        Assertions.assertNotNull(newType);
        Assertions.assertEquals(type.getType(), newType.getType());
    }

    @Test
    public void testGetTypes() {
        List<Type> types = new ArrayList<>();
        Type type1 = new Type("Streaming1");
        Type type2 = new Type("Streaming2");

        types.add(type1);
        types.add(type2);

        typeRepository.save(type1);
        typeRepository.save(type2);

        List<Type> newTypes = typeRepository.findAll();

        Assertions.assertNotNull(newTypes);
        Assertions.assertEquals(types.size(), newTypes.size());
        Assertions.assertEquals(types.get(0).getType(), newTypes.get(0).getType());
        Assertions.assertEquals(types.get(1).getType(), newTypes.get(1).getType());
    }


}
