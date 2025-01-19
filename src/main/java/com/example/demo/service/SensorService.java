package com.example.demo.service;

import com.example.demo.entity.IrrigationSchedules;

public interface SensorService {
    void sendIrrigationCommand(IrrigationSchedules schedule);
}
