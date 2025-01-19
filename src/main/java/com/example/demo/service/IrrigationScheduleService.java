package com.example.demo.service;

import com.example.demo.entity.IrrigationSchedules;
import com.example.demo.exception.ParameterNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;

public interface IrrigationScheduleService {
    IrrigationSchedules createIrrigationSchedule(Integer plotId, IrrigationSchedules irrigationSchedule) throws ResourceNotFoundException, ParameterNotFoundException;

    IrrigationSchedules updateIrrigationSchedule(Integer scheduleId, IrrigationSchedules updatedSchedule) throws ResourceNotFoundException, ParameterNotFoundException;

    IrrigationSchedules getScheduleById(Integer scheduleId) throws ResourceNotFoundException;
    String triggerPendingIrrigations(Integer maxRetryAttempts);
}
