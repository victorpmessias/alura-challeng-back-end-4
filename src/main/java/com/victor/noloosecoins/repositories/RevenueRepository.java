package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.revenues.Revenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    Page<Revenue> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
    List<Revenue> findByDateBetween(LocalDate start, LocalDate end);
    Page<Revenue> findAllByDescriptionContains(String description, Pageable pageable);
    boolean existsByDateBetweenAndDescription(LocalDate startDate, LocalDate endDate, String description);
}
