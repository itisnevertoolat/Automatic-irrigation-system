package com.example.demo.service;

import com.example.demo.entity.Plots;
import com.example.demo.exception.ParameterNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface PlotService {
    public void addPlot(Plots plot) throws ParameterNotFoundException;
    public Plots editPlot(Integer plotId, Plots plot) throws ParameterNotFoundException, ResourceNotFoundException;
    public List<Plots> getPlots();
}
