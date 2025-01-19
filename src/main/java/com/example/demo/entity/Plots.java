package com.example.demo.entity;

import com.example.demo.enums.SoilType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
public class Plots {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int plotId;

    private String name;

    @Enumerated(EnumType.STRING)
    private SoilType soilType;

    @OneToMany(mappedBy = "plots", cascade = CascadeType.ALL)
    private List<IrrigationSchedules> irrigationSchedules;

    public int getPlotId() {
        return plotId;
    }

    public void setPlotId(int plotId) {
        this.plotId = plotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public List<IrrigationSchedules> getIrrigationSchedules() {
        return irrigationSchedules;
    }

    public void setIrrigationSchedules(List<IrrigationSchedules> irrigationSchedules) {
        this.irrigationSchedules = irrigationSchedules;
    }

    @Override
    public String toString() {
        return "Plots{" +
                "plotId=" + plotId +
                ", name='" + name + '\'' +
                ", soilType=" + soilType +
                ", irrigationSchedules=" + irrigationSchedules +
                '}';
    }
}
