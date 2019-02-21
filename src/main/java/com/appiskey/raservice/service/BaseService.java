package com.appiskey.raservice.service;

import com.appiskey.raservice.model.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by suraksha-pnc on 2/7/19.
 */
public interface BaseService<T> {
    public T insert(T item);
    public Iterable<T> findAll();
    public T findById(UUID id);
    public void delete(T item);
    public T update(T item);

}
