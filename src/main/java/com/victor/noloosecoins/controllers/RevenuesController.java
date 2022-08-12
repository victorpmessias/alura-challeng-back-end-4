package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.Revenue;
import com.victor.noloosecoins.repositories.RevenueRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/revenues")
public class RevenuesController {


    private final RevenueRepository repository;

    public RevenuesController(RevenueRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Revenue> getAll(){
        return repository.findAll();
    }

}
