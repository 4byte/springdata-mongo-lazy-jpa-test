package com.example.demo.repository;

import com.example.demo.entity.Child;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChildRepository extends MongoRepository<Child,String> {
}
