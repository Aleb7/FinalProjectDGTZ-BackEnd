package it.Digitazon.finalProject.presentation.controllers;

import it.Digitazon.finalProject.persistence.entities.User;
import it.Digitazon.finalProject.presentation.dto.UserDTO;
import it.Digitazon.finalProject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping
    public List<UserDTO> getUser() {
        return userService.getAll()
                .stream()
                .map(this::convertToDto)
                .toList();

    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable long id) {
        return convertToDto(userService.getById(id));
    }


    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO newUser) {
        User user = convertToEntity(newUser);
        user = userService.create(user);
        return convertToDto(user);
    }


    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO updateUser) {
        User user = convertToEntity(updateUser);
        user = userService.update(id, user);
        return convertToDto(user);
    }

    @DeleteMapping("/delete/{id}")
    public UserDTO deleteUser(@PathVariable long id) {
        return convertToDto(userService.delete(id));
    }

    private UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    private User convertToEntity(UserDTO dto){
        return modelMapper.map(dto,User.class );
    }

}