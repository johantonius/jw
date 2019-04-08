package com.jurnaliswarga.project.repository;

import com.jurnaliswarga.project.model.Videos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends CrudRepository<Videos, Long> {
    List<Videos> findByStatus(Boolean status);

}
    