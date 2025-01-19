package com.example.demo.entity;

import com.example.demo.enums.Status;
import jakarta.persistence.*;
import java.time.*;

@Entity
public class IrrigationSchedules {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "plot_id")
    private Plots plots;
    private LocalTime startTime;
    private LocalTime duration;
    private Double waterAmount;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer retryCount;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setPlots(Plots plots) {
        this.plots = plots;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public String toString() {
        return "IrrigationSchedules{" +
                "scheduleId=" + scheduleId +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", waterAmount=" + waterAmount +
                ", status=" + status +
                ", retryCount=" + retryCount +
                '}';
    }
}
