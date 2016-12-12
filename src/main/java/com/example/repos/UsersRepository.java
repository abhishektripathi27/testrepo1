package com.example.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {
	Page<Users> findAll(Pageable pageable);

}
