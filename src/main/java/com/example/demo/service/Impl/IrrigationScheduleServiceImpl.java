package com.example.demo.service.Impl;

import com.example.demo.entity.IrrigationSchedules;
import com.example.demo.entity.Plots;
import com.example.demo.enums.Status;
import com.example.demo.exception.*;
import com.example.demo.repository.IrrigationScheduleRepository;
import com.example.demo.repository.PlotRepository;
import com.example.demo.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class IrrigationScheduleServiceImpl implements com.example.demo.service.IrrigationScheduleService {

    private final IrrigationScheduleRepository irrigationScheduleRepository;
    private final PlotRepository plotRepository;
    private final SensorService sensorService;
    private Integer countOfFailedSchedulers = 0;

    public IrrigationScheduleServiceImpl(IrrigationScheduleRepository irrigationScheduleRepository, PlotRepository plotRepository, SensorService sensorService) {
        this.irrigationScheduleRepository = irrigationScheduleRepository;
        this.plotRepository = plotRepository;
        this.sensorService = sensorService;
    }

    @Override
    public IrrigationSchedules createIrrigationSchedule(Integer plotId, IrrigationSchedules irrigationSchedule) throws ResourceNotFoundException, ParameterNotFoundException {
        System.out.println(plotId+" "+irrigationSchedule.toString());
        Plots plot = plotRepository.findById(plotId).orElseThrow(() -> new ResourceNotFoundException("Plot"));
        irrigationSchedule.setPlots(plot);
        validateIrrigationSchedulesData(irrigationSchedule);
        irrigationSchedule.setStatus(Status.PENDING);
        return irrigationScheduleRepository.save(irrigationSchedule);
    }

    @Override
    public IrrigationSchedules updateIrrigationSchedule(Integer scheduleId, IrrigationSchedules updatedSchedule) throws ResourceNotFoundException, ParameterNotFoundException {
        IrrigationSchedules existingSchedule = getScheduleById(scheduleId);
        validateIrrigationSchedulesData(updatedSchedule);
        updateIrrigationScheduleData(updatedSchedule , existingSchedule);

        return irrigationScheduleRepository.save(existingSchedule);
    }

    @Override
    public IrrigationSchedules getScheduleById(Integer scheduleId) throws ResourceNotFoundException {
        IrrigationSchedules schedules = irrigationScheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule"));
        return schedules;
    }


    public String triggerPendingIrrigations(Integer maxRetryAttempts) {
        List<IrrigationSchedules> pendingSchedules = irrigationScheduleRepository.findByStatus(Status.PENDING);
        Integer totalPendingSchedules = pendingSchedules.size();
        for (IrrigationSchedules schedule : pendingSchedules) {
            try {
                sensorService.sendIrrigationCommand(schedule);
                schedule.setStatus(Status.SENT);
            } catch (Exception e) {
                schedule.setRetryCount(schedule.getRetryCount() + 1);
                if(Objects.equals(schedule.getRetryCount(), maxRetryAttempts)){
                    System.err.printf("The schedule with %d has exceeded the maximum Retry Attempts\n", schedule.getScheduleId());
                    countOfFailedSchedulers++;
                    schedule.setStatus(Status.FAILED);
                }
            }
            irrigationScheduleRepository.save(schedule);
        }
        return String.format("The Total count of sending schedulers %d\n " +
                "The count of failed schedulers %d\n " +
                "The count of successful schedulers %d", totalPendingSchedules, countOfFailedSchedulers, totalPendingSchedules - countOfFailedSchedulers);
    }


    private void validateIrrigationSchedulesData(IrrigationSchedules irrigationSchedule) throws ParameterNotFoundException {

        if(Optional.ofNullable(irrigationSchedule.getStartTime()).isEmpty())
            throw new ParameterNotFoundException("startTime");
        if(Optional.ofNullable(irrigationSchedule.getDuration()).isEmpty())
            throw new ParameterNotFoundException("duration");
        if(Optional.ofNullable(irrigationSchedule.getWaterAmount()).isEmpty())
            throw new ParameterNotFoundException("waterAmount");

    }
    private void updateIrrigationScheduleData(IrrigationSchedules irrigationSchedule, IrrigationSchedules existingIrrigationSchedule) {

        existingIrrigationSchedule.setStartTime(irrigationSchedule.getStartTime());
        existingIrrigationSchedule.setDuration(irrigationSchedule.getDuration());
        existingIrrigationSchedule.setWaterAmount(irrigationSchedule.getWaterAmount());
    }
}