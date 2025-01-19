package com.example.demo.service.Impl;

import com.example.demo.entity.IrrigationSchedules;
import com.example.demo.service.SensorService;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {

    @Override
    public void sendIrrigationCommand(IrrigationSchedules schedule) {

        System.out.println("Sending irrigation command for specific schedule " + schedule);
    }
}