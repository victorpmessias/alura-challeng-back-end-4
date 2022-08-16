package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.revenues.dto.RevenueDto;
import com.victor.noloosecoins.models.revenues.forms.NewRevenueForm;
import com.victor.noloosecoins.services.RevenueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("/revenues")
public class RevenuesController {

    private final RevenueService service;

    public RevenuesController(RevenueService service) {
        this.service = service;
    }

    @GetMapping
    public Page<RevenueDto> getAll(Pageable pageable, @RequestParam(required = false) String description) {
        if(description != null){
            return service.listAllByDescription(pageable, description);
        }else{
        return service.listAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public RevenueDto getExpenseById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpenseById(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<RevenueDto> registerNewExpense(@RequestBody @Valid NewRevenueForm form) {
        RevenueDto revenue = service.registerNewExpense(form);
        return ResponseEntity.created(URI.create("/revenues/"+revenue.getId())).body(revenue);
    }

    @PutMapping("/{id}")
    @Transactional
    public RevenueDto updateExpenseRegistry(@RequestBody @Valid NewRevenueForm form, @PathVariable Long id) {
        return service.updateRegistry(form, id);
    }

    @GetMapping("/{year}/{month}")
    public Page<RevenueDto> getRevenueByMonth(@PathVariable int year, @PathVariable int month, Pageable pageable){
        return service.searchRevenueByMonth(year, month, pageable);
    }
}
