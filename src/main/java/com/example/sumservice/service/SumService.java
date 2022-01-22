package com.example.sumservice.service;

import com.example.sumservice.entity.SumEntity;
import com.example.sumservice.exception.SumNotFoundException;
import com.example.sumservice.model.SumModel;
import com.example.sumservice.repository.SumRepo;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    private final SumRepo sumRepo;

    public SumService(final SumRepo sumRepo) {
        this.sumRepo = sumRepo;
    }

    public SumModel getByName(String name) throws SumNotFoundException {
        SumEntity entity = sumRepo.findByName(name);
        if (entity == null) {
            throw new SumNotFoundException("Значение не найдено");
        }
        return SumModel.toModel(entity);
    }

    public void add(String name, Integer value) {
        SumEntity entity = new SumEntity();
        entity.setName(name);
        entity.setValue(value);
        sumRepo.save(entity);
    }

    public void delete(String name) throws SumNotFoundException {
        SumEntity entity = sumRepo.findByName(name);
        if (entity != null) {
            sumRepo.delete(entity);
        } else {
            throw new SumNotFoundException("Значение не найдено");
        }
    }
}

