package pruebaAdeA.Services.Users;

import pruebaAdeA.Dtos.Request.UserRequestDto;
import pruebaAdeA.Dtos.Response.UserResponseDto;
import pruebaAdeA.Models.StatusUserModel;

import java.util.List;

public interface UserService {
    // Create user
    UserResponseDto createUser(UserRequestDto request);

    // Update user
    UserResponseDto updateUser(Integer id, UserRequestDto request);

    // Delete User
    void DeleteUser(Integer id);

    // Get User by id
    UserResponseDto getUserById(Integer id);

    // List users by status
    List<UserResponseDto> listUserByStatus(StatusUserModel status);

    // Validate unique login
    boolean validateLogin(String login, Integer id);

    // Validate unique email
    boolean validateEmail(String email, Integer id);

    // login
    UserResponseDto login(String loginEmail, String password);

}
