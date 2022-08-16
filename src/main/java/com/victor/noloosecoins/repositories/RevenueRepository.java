package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.revenues.Revenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    Page<Revenue> findAllByDescriptionContains(String description, Pageable pageable);
}
