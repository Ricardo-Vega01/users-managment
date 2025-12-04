package pruebaAdeA.Controllers;

import org.springframework.web.bind.annotation.*;
import pruebaAdeA.Dtos.Request.UserRequestDto;
import pruebaAdeA.Dtos.Response.UserResponseDto;
import pruebaAdeA.Models.StatusUserModel;
import pruebaAdeA.Services.Users.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    // constructor
    public UserController(UserService userService){
        this.userService = userService;
    }

    // create user
    @PostMapping(value = "/basic", consumes = "application/json", produces = "application/json")
    public UserResponseDto createUser(@RequestBody UserRequestDto request){
        return userService.createUser(request);
    }

    // create Full user
    @PostMapping(value = "/full", consumes = "application/json", produces = "application/json")
    public  UserResponseDto createFullUser(@RequestBody UserRequestDto request){
        return  userService.createFullUser(request);
    }

    // update user
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Integer id, @RequestBody UserRequestDto request){
        return userService.updateUser(id, request);
    }

    // delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.DeleteUser(id);
    }

    // get id
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id){
            return  userService.getUserById(id);
    }

    // list users

    /*@GetMapping
    public List<UserResponseDto> listUsers(@RequestParam(required = false) String status){
        StatusUserModel statusEnum = status != null  ? StatusUserModel.valueOf(status.toUpperCase()):null;
        return statusEnum != null ? userService.listUserByStatus(statusEnum) : userService.listUserByStatus(StatusUserModel.A);
    }
    */
    @GetMapping
    public List<UserResponseDto> allUser(){
        return userService.listAllUser();
    }
}
