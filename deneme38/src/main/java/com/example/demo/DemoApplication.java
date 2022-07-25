package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

	}

	public interface userService{
		User getUserById(Long id);
	}
	@Service
	public class UserServiceImpl implements userService{
		@Override
		public User getUserById(Long id) {
			return null;
		}
	}
	@RestController
	@RequestMapping("/api")

	public class userController{
		@Autowired
		private userService userService;
		@GetMapping("/api/{id}")
		public User getUserById(@PathVariable Long id){
			return userService.getUserById(id);
		}
	}


}
