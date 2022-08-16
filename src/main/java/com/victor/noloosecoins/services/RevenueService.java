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
import java.time.LocalDate;

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
        return repository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Can't find a expense entity with id: " + id)
        );
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


    public Page<RevenueDto> searchRevenueByMonth(int year, int month, Pageable pageable) {
        LocalDate initialDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.ofEpochDay(initialDate.toEpochDay()).plusMonths(1).withDayOfMonth(1).minusDays(1);
        Page<Revenue> revenue = repository.findByDateBetween(initialDate, endDate, pageable);
        return revenue.map(RevenueDto::new);

    public Page<RevenueDto> listAllByDescription(Pageable pageable, String description) {
        Page<Revenue> revenues = repository.findAllByDescriptionContains(description, pageable);
        return revenues.map(RevenueDto::new);

    }
}
