package com.auyamatech.services;

import com.auyamatech.commands.UnitOfMeasureCommand;
import com.auyamatech.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.auyamatech.repositories.UnitOfMeasureReposirory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    UnitOfMeasureReposirory unitOfMeasureReposirory;
    UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReposirory unitOfMeasureReposirory,
                                    UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureReposirory = unitOfMeasureReposirory;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAllUoms() {
        return StreamSupport.stream(unitOfMeasureReposirory.findAll().spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toSet());
    }
}
