package com.example.apartmentManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.apartmentManagement.modal.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);
    public Optional<User> findById(int id);
    public List<User> findByName(String name);
}