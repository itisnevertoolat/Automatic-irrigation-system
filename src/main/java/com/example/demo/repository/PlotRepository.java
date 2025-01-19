package com.example.demo.repository;

import com.example.demo.entity.Plots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plots, Integer> {
}
