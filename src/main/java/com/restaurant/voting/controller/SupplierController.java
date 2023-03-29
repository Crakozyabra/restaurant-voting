package com.restaurant.voting.controller;

import com.restaurant.voting.dto.supplier.ProductSupplierDto;
import com.restaurant.voting.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = SupplierController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {

    public static final String REST_URL = "/admin/suppliers";

    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ProductSupplierDto create(@RequestBody ProductSupplierDto productSupplierDto) {
        Assert.isNull(productSupplierDto.getId(),"must be null");
        return supplierService.create(productSupplierDto);
    }

    @GetMapping("/{id}")
    public ProductSupplierDto get(@PathVariable Integer id) {
        return supplierService.get(id);
    }

    @GetMapping("/start-with")
    public Set<ProductSupplierDto> findAllByNameStartingWith(@RequestParam String startWith) {
        return supplierService.getByNameStartingWith(startWith);
    }

    @GetMapping("/page/{start}/limit/{limit}/end-with")
    public Set<ProductSupplierDto> getAllByNameEndingWith(@RequestParam String endWith, @PathVariable Integer start,
                                                          @PathVariable Integer limit) {
        return supplierService.getAllByNameEndingWithPaged(endWith, start, limit);
    }

    @GetMapping
    public Set<ProductSupplierDto> getAll() {
        return supplierService.getAll();
    }

    @PutMapping
    public ProductSupplierDto update(@RequestBody ProductSupplierDto productSupplierDto) {
        Assert.notNull(productSupplierDto.getId(),"must not be null");
        return supplierService.create(productSupplierDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        supplierService.delete(id);
    }
}
