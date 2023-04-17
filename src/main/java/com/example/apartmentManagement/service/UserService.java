package com.example.apartmentManagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.apartmentManagement.modal.User;
import com.example.apartmentManagement.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public void saveMyUser(User user) {
        userRepository.save(user);
    }

    public List<User> showAllUsers(){
        List<User> users = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    public List<User> showRoleUsers(String role) {
        List<User> users = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            if(user.getRole().equals(role)) {
                users.add(user);
            }
        }
        return users;
    }

    public List<User> showNameUser(String name) {
        return userRepository.findByName(name);
    }

    public List<User> showIdUser(Integer id) {
        List<User> users = new ArrayList<>();
        users.add(userRepository.findOne(id));
        return users;
    }

    public void deleteMyUser(int id) {
        userRepository.delete(id);
    }

    public User editUser(int id) {
        return userRepository.findOne(id);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
