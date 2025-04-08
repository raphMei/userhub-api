package com.raphmei.userhub.service;


import com.raphmei.userhub.dto.RegisterRequest;
import com.raphmei.userhub.dto.UserDTO;
import com.raphmei.userhub.entity.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(RegisterRequest registerRequest);
    UserDTO getUserById(Long id);
    User updateUser(Long id, UserDTO user);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();


}
