package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Slf4j
@Service
@Transactional
public class UnitOfMeasureImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAllUnitOfMeasure() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false).map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());

    }

    @Override
    public UnitOfMeasureCommand findById(Long L) {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findById(L);
        if (!unitOfMeasure.isPresent()) {

            new RuntimeException("Unit of measure not found");
        }

        return unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure.get());
    }
}
