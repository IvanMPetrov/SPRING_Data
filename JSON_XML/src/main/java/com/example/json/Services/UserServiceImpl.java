package com.example.json.Services;

import com.example.json.entities.users.User;
import com.example.json.entities.users.UserDTO;
import com.example.json.entities.users.UserWithSoldProductsDTO;
import com.example.json.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUsersWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        return allWithSoldProducts
                .stream()
                .map(user -> this.mapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDTO> getUsersWithSoldProductsOrderByCount() {
        List<User> users = this.userRepository.findAllWithSoldProductsOrderByCount();

        return users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setAge(user.getAge());
                    userDTO.setFirst_name(user.getFirstName());
                    userDTO.setLast_name(user.getLastName());
                    return userDTO;
                })
                .collect(Collectors.toList());
    }
}
