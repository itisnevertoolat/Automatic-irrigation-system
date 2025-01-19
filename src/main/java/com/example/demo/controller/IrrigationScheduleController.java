package com.example.demo.controller;

import com.example.demo.exception.*;
import com.example.demo.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.IrrigationSchedules;

@RestController
@RequestMapping("/api/irrigation")
public class IrrigationScheduleController {

    private final IrrigationScheduleService irrigationScheduleService;

    public IrrigationScheduleController(IrrigationScheduleService irrigationScheduleService) {
        this.irrigationScheduleService = irrigationScheduleService;
    }

    @PostMapping("/{plotId}")
    public ResponseEntity<IrrigationSchedules> createIrrigationSchedule(@PathVariable Integer plotId, @RequestBody IrrigationSchedules irrigationSchedule)
            throws ResourceNotFoundException, ParameterNotFoundException {

        IrrigationSchedules createdSchedule = irrigationScheduleService.createIrrigationSchedule(plotId, irrigationSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<IrrigationSchedules> updateIrrigationSchedule(@PathVariable Integer scheduleId, @RequestBody IrrigationSchedules updatedSchedule)
            throws ResourceNotFoundException, ParameterNotFoundException {
        IrrigationSchedules updatedScheduleEntity = irrigationScheduleService.updateIrrigationSchedule(scheduleId, updatedSchedule);
        return ResponseEntity.ok(updatedScheduleEntity);
    }



    @PostMapping("/{scheduleId}/trigger")
    public ResponseEntity<String> triggerIrrigation(@PathVariable Integer scheduleId) throws ResourceNotFoundException {
        IrrigationSchedules schedule = irrigationScheduleService.getScheduleById(scheduleId);
        return ResponseEntity.ok("Irrigation triggered for schedule " + schedule);
    }

    @PostMapping("/retry/{maxRetryAttempts}")
    public ResponseEntity<String> retryPendingSchedulers(@PathVariable Integer maxRetryAttempts){
        return ResponseEntity.ok(irrigationScheduleService.triggerPendingIrrigations(maxRetryAttempts));
    }
}