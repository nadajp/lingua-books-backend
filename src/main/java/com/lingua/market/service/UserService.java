package com.lingua.market.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lingua.market.persistence.dao.UserRepository;
import com.lingua.market.persistence.model.User;
import com.lingua.market.web.dto.UserDTO;
import com.lingua.market.web.exception.ResourceNotFoundException;
import com.lingua.market.web.exception.UserAlreadyExistsException;

@Service
public class UserService {
    
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        Optional<User> existingUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        Optional<User> existingUserByAuthUserId = userRepository.findByAuthUserId(userDTO.getAuthUserId());
    
        if (existingUserByEmail.isPresent() || existingUserByAuthUserId.isPresent()) {
            throw new UserAlreadyExistsException("A user with this email or authentication ID already exists.");
        }
        User user = modelMapper.map(userDTO, User.class);        
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDTO.class);
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
	
		if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getDefaultLanguages() != null) {
            user.setDefaultLanguages(userDTO.getDefaultLanguages());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
	
		User updatedEmployee = userRepository.save(user);
        return modelMapper.map(updatedEmployee, UserDTO.class);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        
        userRepository.delete(user);
    }

}

