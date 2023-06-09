package com.lingua.market.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lingua.market.web.dto.ApiResponse;
import com.lingua.market.web.dto.UserRegistrationDTO;
import com.lingua.market.web.exception.UserAlreadyExistsException;

import jakarta.validation.Valid;

import com.lingua.market.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

    @GetMapping
    public ResponseEntity<List<UserRegistrationDTO>> getAllUsers() {
        return ResponseEntity.ok().body(this.userService.getAllUsers());
    }

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
		try {
            userService.registerUser(userRegistrationDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
        }
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserRegistrationDTO> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.userService.getUserById(id));
	}
    
	@PutMapping("/{id}")
	public ResponseEntity<UserRegistrationDTO> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO userDTO){
		return ResponseEntity.ok().body(this.userService.updateUser(id, userDTO));
	}
	
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		this.userService.deleteUser(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
