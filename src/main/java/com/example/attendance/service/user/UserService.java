package com.example.attendance.service.user;

import com.example.attendance.model.User;
import com.example.attendance.repository.UserRepository;
import com.example.attendance.security.principle.UserPrinciple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Optional<User> currentUser, User user) {
        user.setId(currentUser.get().getId());
        if (user.getUsername() == null || user.getUsername().trim().equals("")) {
            user.setUsername(currentUser.get().getUsername());
        }
        if (user.getPassword() == null || user.getPassword().trim().equals("")) {
            user.setPassword(currentUser.get().getPassword());
        }
        if (user.getAddress() == null || user.getAddress().trim().equals("")) {
            user.setAddress(currentUser.get().getAddress());
        }
        if (user.getFirstname() == null || user.getFirstname().trim().equals("")) {
            user.setFirstname(currentUser.get().getFirstname());
        }
        if (user.getLastname() == null || user.getLastname().trim().equals("")) {
            user.setLastname(currentUser.get().getLastname());
        }
        if (user.getAge() == 0) {
            user.setAge(currentUser.get().getAge());
        }
        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException(username);
        return UserPrinciple.build(user.get());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
