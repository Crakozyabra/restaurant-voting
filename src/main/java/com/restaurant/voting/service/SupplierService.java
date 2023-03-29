package com.restaurant.voting.service;

import com.restaurant.voting.dto.supplier.ProductSupplierDto;
import com.restaurant.voting.model.ProductSupplier;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.repository.SupplierRepository;
import com.restaurant.voting.util.DtoUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class SupplierService {

    private SupplierRepository supplierRepository;

    private RestaurantRepository restaurantRepository;

    public SupplierService(SupplierRepository supplierRepository, RestaurantRepository restaurantRepository) {
        this.supplierRepository = supplierRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public ProductSupplierDto create(ProductSupplierDto productSupplierDto) {
        return save(productSupplierDto);
    }

    public ProductSupplierDto get(int id) {
        return supplierRepository.findById(id).map(DtoUtil::productSupplierToProductSupplierDto)
                .orElse(new ProductSupplierDto());
    }

    public Set<ProductSupplierDto> getByNameStartingWith(String startWith) {
        return DtoUtil.productSuppliersToProductSupplierDtos(supplierRepository.findAllByNameStartingWith(startWith));
    }

    public Set<ProductSupplierDto> getAllByNameEndingWithPaged(String endWith, int start, int limit) {
        return DtoUtil.productSuppliersToProductSupplierDtos(
                supplierRepository.getAllByNameEndingWith(endWith, PageRequest.of(start, limit)));
    }

    public Set<ProductSupplierDto> getAll() {
        return DtoUtil.productSuppliersToProductSupplierDtos(supplierRepository.findAll());
    }

    @Transactional
    public ProductSupplierDto update(ProductSupplierDto productSupplierDto) {
       return save(productSupplierDto);
    }

    private ProductSupplierDto save(ProductSupplierDto productSupplierDto) {
        ProductSupplier productSupplier = new ProductSupplier(
                productSupplierDto.getId(), productSupplierDto.getName(), null);
        Set<Restaurant> restaurants = new HashSet<>();
        for (Integer id : productSupplierDto.getRestaurantIds()) {
            restaurants.add(restaurantRepository.getReferenceById(id));
        }
        productSupplier.setRestaurants(restaurants);
        ProductSupplier saved = supplierRepository.save(productSupplier);
        return DtoUtil.productSupplierToProductSupplierDto(saved);
    }

    @Transactional
    public void delete(int id) {
        supplierRepository.deleteById(id);
    }
}
