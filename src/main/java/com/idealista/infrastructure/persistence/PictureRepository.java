package com.idealista.infrastructure.persistence;

import com.idealista.infrastructure.api.model.entity.Picture;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Integer> {

    List<Picture> findAll();

    List<Picture> findAll(Specification specs);
}