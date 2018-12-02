package com.auyamatech.services;

import com.auyamatech.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAllUoms();

}
