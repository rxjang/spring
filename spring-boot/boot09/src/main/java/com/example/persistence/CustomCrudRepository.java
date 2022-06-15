package com.example.persistence;

import com.example.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository  extends CrudRepository<WebBoard, Long>, CustomWebBoard {
}
