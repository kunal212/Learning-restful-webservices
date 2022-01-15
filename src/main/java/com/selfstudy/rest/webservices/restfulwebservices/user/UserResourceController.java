package com.selfstudy.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResourceController {

	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userrepo;

	@GetMapping("/allusers")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// get specific details
	@GetMapping("/allusers/{id}")
	public User retrieveUserusingPathvariable(@PathVariable int id) {

		User user = service.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}

	// get the userdetails with headerId
	@GetMapping("/userswithcurrentURL/{id}")
	public User retrieveUserusingPathvariableWithURL(@PathVariable int id) {

		User user = service.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserusingHateos(@PathVariable int id) {
		User user = service.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}

	// create users
	@PostMapping("/users")
	public String createUser(@Valid @RequestBody User user) {

		if (user.getBirthDate() == null && user.getId() == null) {
			throw new UserNotFoundException("User " + user);
		}
		User savedUser = service.save(user);
		System.out.println("Created User details " + savedUser);
		return "Created";
	}

	// create Users with header
	@PostMapping("/userswithcurrentURL")
	public ResponseEntity<Object> createUserwithcurrentURL(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		// CREATED
		// /user/{id} savedUser.getId()

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build(); 

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}

}
