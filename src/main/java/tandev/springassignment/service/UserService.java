package tandev.springassignment.service;

import tandev.springassignment.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    UserDTO getUserById(Long userId);
    UserDTO getUserByLoginName(String loginName);
    List<UserDTO> getAllUsers();
    List<UserDTO> searchUsers(String keyword);
    void enableUser(Long userId);
    void disableUser(Long userId);
    boolean isLoginNameExists(String loginName);
} 