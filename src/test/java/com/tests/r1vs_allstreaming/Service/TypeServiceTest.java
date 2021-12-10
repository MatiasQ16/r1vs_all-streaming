package com.tests.r1vs_allstreaming.Service;

import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Repositories.TypeRepository;
import com.tests.r1vs_allstreaming.Services.TypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeServiceImpl typeService;

    @Test
    void ifCallGetTypesAndExistTypesReturnList() {
        List<Type> typeList = new ArrayList<>();
        typeList.add(new Type("Streaming1"));
        typeList.add(new Type("Streaming2"));

        when(typeRepository.findAll()).thenReturn(typeList);
        List<Type> result;

        // Act
        result = typeService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(typeList.size(), result.size());
        assertAll("result",
                () -> assertEquals("Streaming1", result.get(0).getType()),
                () -> assertEquals("Streaming2", result.get(1).getType())
        );
    }

    @Test
    void ifCallSaveReturnNewType() {
        Type type = new Type("Streaming");

        when(typeRepository.save(type)).thenReturn(type);
        Type result;

        // Act
        result = typeService.save(type);

        // Assert
        assertNotNull(result);
        assertEquals(result.getType(), type.getType());
    }
}
