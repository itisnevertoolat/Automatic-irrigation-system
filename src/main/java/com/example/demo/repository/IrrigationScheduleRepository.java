package com.example.demo.repository;

import com.example.demo.entity.IrrigationSchedules;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IrrigationScheduleRepository extends JpaRepository<IrrigationSchedules, Integer> {
    @Query("select schedule from IrrigationSchedules schedule where schedule.status = :status")
    List<IrrigationSchedules> findByStatus(Status status);
}
