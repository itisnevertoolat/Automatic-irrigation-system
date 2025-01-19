package com.example.demo.controller;

import com.example.demo.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PlotService;
import java.util.List;
import com.example.demo.entity.Plots;

@RestController
@RequestMapping("/api/plots")
public class PlotController {
    private final PlotService plotService;

    public PlotController(PlotService plotService){
        this.plotService = plotService;
    }


    @PostMapping
    public ResponseEntity<String> addPlot(@RequestBody Plots plot) throws ParameterNotFoundException {
        plotService.addPlot(plot);
        return ResponseEntity.status(HttpStatus.OK).body("The Plot created Successfully");
    }

    @PostMapping("{id}")
    public ResponseEntity<Plots> editPlot(@PathVariable Integer id, @RequestBody Plots plot) throws ParameterNotFoundException, ResourceNotFoundException {
        Plots updatedPlot = plotService.editPlot(id, plot);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPlot);
    }
    @GetMapping
    public ResponseEntity<List<Plots>> getAllPlots(){
        List<Plots> plots = plotService.getPlots();
        return ResponseEntity.status(HttpStatus.OK).body(plots);
    }
}
