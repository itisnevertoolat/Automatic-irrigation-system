package com.example.demo.service.Impl;

import com.example.demo.entity.Plots;
import com.example.demo.repository.PlotRepository;
import com.example.demo.service.PlotService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.demo.exception.*;
@Service
@Transactional
public class PlotServiceImpl implements PlotService {
    private final PlotRepository plotRepository;

    public PlotServiceImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Override
    public void addPlot(Plots plot) throws ParameterNotFoundException {
        validatePlotData(plot);
        plotRepository.save(plot);
    }

    @Override
    public Plots editPlot(Integer plotId, Plots plot) throws ParameterNotFoundException, ResourceNotFoundException {
        Plots storedPlot = plotRepository.findById(plotId)
                .orElseThrow(() -> new ResourceNotFoundException("Plot"));

        validatePlotData(plot);
        updatePlotData(plot, storedPlot);

        return plotRepository.save(storedPlot);
    }

    @Override
    public List<Plots> getPlots() {
        return plotRepository.findAll();
    }

    private void validatePlotData(Plots plot) throws ParameterNotFoundException {
        if(Optional.ofNullable(plot.getSoilType()).isEmpty())
            throw new ParameterNotFoundException("soilType");

    }
    private void updatePlotData(Plots plot, Plots existingPlot) {
        existingPlot.setName(plot.getName());
        existingPlot.setSoilType(plot.getSoilType());
    }
}
