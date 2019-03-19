package com.fredoliveira.web;

import com.fredoliveira.data.entity.StoreEntity;
import com.fredoliveira.domain.service.StoreService;
import com.fredoliveira.domain.exception.StoreException;
import com.fredoliveira.domain.exception.StoreNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class StoreResource {

    private final StoreService storeService;

    @Autowired public StoreResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @ApiOperation("Save a new store.")
    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Valid @RequestBody StoreEntity store) throws Exception {
        return ofNullable(storeService.save(store))
                .map(currentStore -> ok(storeService.findOneById(currentStore.getId())))
                .orElseThrow(Exception::new);
    }

    @ApiOperation("Update a store by id.")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody StoreEntity store) throws Exception {
        Optional<StoreEntity> currentStore = storeService.findOneById(id);

        if (!currentStore.isPresent()) {
            throw new StoreNotFoundException();
        }

        return ofNullable(storeService.update(store, currentStore.get()))
                .map(updatedStore -> ok(storeService.findOneById(updatedStore.getId())))
                .orElseThrow(Exception::new);
    }

    @ApiOperation("Find a store by id.")
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity findOneById(@PathVariable Long id) throws StoreException {
        return storeService.findOneById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(StoreNotFoundException::new);
    }

}
