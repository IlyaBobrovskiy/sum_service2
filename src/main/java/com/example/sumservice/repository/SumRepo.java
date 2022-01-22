package com.example.sumservice.repository;

import com.example.sumservice.entity.SumEntity;
import org.springframework.data.repository.CrudRepository;

public interface SumRepo extends CrudRepository<SumEntity, String> {

    SumEntity findByName(String name);
}
