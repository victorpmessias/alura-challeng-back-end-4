package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.revenues.dto.RevenueDto;
import com.victor.noloosecoins.models.revenues.forms.NewRevenueForm;
import com.victor.noloosecoins.services.RevenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/revenues")
@Tag(name = "Revenues Controller", description = "URIs to manage, register and retrieve revenues registries")
public class RevenuesController {

    private final RevenueService service;

    public RevenuesController(RevenueService service) {
        this.service = service;

    }

    @Operation(description = "Return a list of all registered revenues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a page with list of revenues found"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RevenueDto> getAll(Pageable pageable, @RequestParam(required = false) String description) {
        if(description != null){
            return service.listAllByDescription(pageable, description);
        }else{
        return service.listAll(pageable);
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RevenueDto getExpenseById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteExpenseById(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RevenueDto> registerNewExpense(@RequestBody @Valid NewRevenueForm form) {
        RevenueDto revenue = service.registerNewExpense(form);
        return ResponseEntity.created(URI.create("/revenues/"+revenue.getId())).body(revenue);
    }

    @PutMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public RevenueDto updateExpenseRegistry(@RequestBody @Valid NewRevenueForm form, @PathVariable Long id) {
        return service.updateRegistry(form, id);
    }

    @GetMapping(path ="/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
    })
    public Page<RevenueDto> getRevenueByMonth(@PathVariable int year, @PathVariable int month, Pageable pageable){
        return service.searchRevenueByMonth(year, month, pageable);
    }
}
