package com.lingua.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lingua.market.web.dto.UserRegistrationDTO;
import com.lingua.market.persistence.dao.UserRepository;
import com.lingua.market.persistence.model.User;
import com.lingua.market.web.exception.ResourceNotFoundException;
import com.lingua.market.web.exception.UserAlreadyExistsException;

@Service
public class UserService {
    
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserRegistrationDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserRegistrationDTO.class))
                .collect(Collectors.toList());
    }

    public UserRegistrationDTO registerUser(UserRegistrationDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use"); 
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserRegistrationDTO.class);
    }

    public UserRegistrationDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        
        return modelMapper.map(user, UserRegistrationDTO.class);
    }

    public UserRegistrationDTO updateUser(Long id, UserRegistrationDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
	
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
	
		User updatedEmployee = userRepository.save(user);
        return modelMapper.map(updatedEmployee, UserRegistrationDTO.class);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        
        userRepository.delete(user);
    }

}
