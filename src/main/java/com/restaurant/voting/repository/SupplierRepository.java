package com.restaurant.voting.repository;

import com.restaurant.voting.model.ProductSupplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SupplierRepository extends JpaRepository<ProductSupplier, Integer> {

    public List<ProductSupplier> findAllByNameStartingWith(String startWith);

    public List<ProductSupplier> getAllByNameEndingWith(String endWith, Pageable pageable);
}
