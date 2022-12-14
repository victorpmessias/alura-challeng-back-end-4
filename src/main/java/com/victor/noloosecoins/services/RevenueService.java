package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.revenues.Revenue;
import com.victor.noloosecoins.models.revenues.dto.RevenueDto;
import com.victor.noloosecoins.models.revenues.forms.NewRevenueForm;
import com.victor.noloosecoins.repositories.RevenueRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RevenueService {

    private final RevenueRepository repository;

    public RevenueService(RevenueRepository repository) {
        this.repository = repository;
    }

    public Page<RevenueDto> listAll(Pageable pageable){
        Page<Revenue> expenses = repository.findAll(pageable);
        return expenses.map(RevenueDto::new);
    }

    public RevenueDto registerNewExpense(NewRevenueForm form) {
        Revenue revenue = form.convertToExpenseEntity();
        revenue = repository.save(revenue);
        return new RevenueDto(revenue);
    }

    public RevenueDto updateRegistry(NewRevenueForm form, Long id) {
        Revenue revenue = getRevenueById(id);
        revenue.setValue(form.getValue());
        revenue.setDescription(form.getDescription());
        revenue.setDate(form.getDate());

        return new RevenueDto(revenue);
    }

    private Revenue getRevenueById(Long id){
        return repository.findById(id).orElseThrow(() -> {return new EntityNotFoundException("Can't find a expense entity with id: " + id);
        });
    }

    public RevenueDto getById(Long id) {
        Revenue revenue = getRevenueById(id);
        return new RevenueDto(revenue);
    }

    public void deleteExpense(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Can't find a expense entity with id: " + id);
        }
    }
}
