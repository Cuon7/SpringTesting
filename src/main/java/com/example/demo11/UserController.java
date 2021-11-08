package com.example.demo11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

	@PostMapping("/request")
	public String hello(@RequestBody User user) {	
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String realTime = now.format(formatter);
		System.out.println("Hello, " + user.toString() + ". Time now is: " +realTime + ". hello " + user.getName() + " - ");
		return "Hello, " + user + "!";

	}
	
    @Autowired
    private UserRepository userRepository;

    
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {			
			List<User> userData = userRepository.findByUserName(user.getUserName());
			if (!userData.isEmpty()) {
				throw new Exception("Username already existed");
			}
			

			User _user = userRepository
					.save(new User(user.getUserName(), user.getPhoneNumber(), user.getName()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user  = userData.get();
			_user.setUserName(user.getUserName());
			_user.setPhoneNumber(user.getPhoneNumber());
			_user.setName(user.getName());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> userList = userRepository.findAll();			
			
			if (userList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else
				
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/log")
	public ResponseEntity<HttpServletRequest> getLog(HttpServletRequest request) {
		return null;
		
	}

}

