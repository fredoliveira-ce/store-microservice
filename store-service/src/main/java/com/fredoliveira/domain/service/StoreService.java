package com.fredoliveira.domain.service;

import com.fredoliveira.data.entity.StoreEntity;
import com.fredoliveira.data.repository.StoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Optional<StoreEntity> findOneById(Long id) {
        return storeRepository.findById(id);
    }

    public StoreEntity save(StoreEntity store) {
        return storeRepository.save(store);
    }

    public StoreEntity update(StoreEntity store, StoreEntity currentStore) {
        BeanUtils.copyProperties(store, currentStore);

        return storeRepository.save(currentStore);
    }
}
