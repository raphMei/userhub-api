package com.raphmei.userhub.service;


import com.raphmei.userhub.dto.UserDTO;
import com.raphmei.userhub.entity.User;

import java.util.List;

public interface UserService {

    void createUser(UserDTO user);
    UserDTO getUserById(Long id);
    User updateUser(Long id, UserDTO user);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();


}
