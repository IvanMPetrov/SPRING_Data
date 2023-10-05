package com.example.json.Services;

import com.example.json.entities.users.User;
import com.example.json.entities.users.UserDTO;
import com.example.json.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();

    List<UserDTO> getUsersWithSoldProductsOrderByCount();
}
