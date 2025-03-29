package com.example.spring_jwt.repository;

import com.example.spring_jwt.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
