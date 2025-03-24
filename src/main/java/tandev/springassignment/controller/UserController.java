package tandev.springassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tandev.springassignment.dto.PositionDTO;
import tandev.springassignment.dto.UserDTO;
import tandev.springassignment.service.PositionService;
import tandev.springassignment.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseEntity.ok("User added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/enable")
    public ResponseEntity<?> enableUser(@PathVariable Long userId) {
        try {
            userService.enableUser(userId);
            return ResponseEntity.ok("User enabled successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/positions")
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        List<PositionDTO> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }

    @PostMapping("/{userId}/disable")
    public ResponseEntity<?> disableUser(@PathVariable Long userId) {
        try {
            userService.disableUser(userId);
            return ResponseEntity.ok("User disabled successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-login-name")
    public ResponseEntity<Boolean> checkLoginName(@RequestParam String loginName) {
        boolean exists = userService.isLoginNameExists(loginName);
        return ResponseEntity.ok(exists);
    }
}