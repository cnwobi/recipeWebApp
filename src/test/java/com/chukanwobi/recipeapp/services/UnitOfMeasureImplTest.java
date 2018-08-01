package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureImplTest {
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureService service;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
      service = new UnitOfMeasureImpl(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);

    }

    @Test
    public void testFindAllUnitOfMeasure() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasures.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(3L);
        unitOfMeasures.add(unitOfMeasure2);


        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        Set<UnitOfMeasureCommand> commands = service.findAllUnitOfMeasure();

        assertEquals(2,commands.size());
        verify(unitOfMeasureRepository,times(1)).findAll();
    }
}