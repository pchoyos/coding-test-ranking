package com.idealista.infrastructure.api.repository;

import com.idealista.infrastructure.api.model.entity.Ad;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdRepository extends CrudRepository<Ad, Integer> {

    List<Ad> findAll();

    List<Ad> findAll(Specification specs);
}