package tandev.springassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tandev.springassignment.dto.UserDTO;
import tandev.springassignment.service.UserService;
import tandev.springassignment.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"/", "/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @GetMapping
    public String listUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String keyword, 
                            @RequestParam(required = false) String searchType,
                            Model model) {
        List<UserDTO> users = userService.searchUsers(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        return "user/list";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("positions", positionService.getAllPositions());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, 
                         BindingResult result, 
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("positions", positionService.getAllPositions());
            return "user/add";
        }

        try {
            userService.createUser(userDTO);
            return "redirect:/users";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("positions", positionService.getAllPositions());
            return "user/add";
        }
    }

    @PostMapping("/{userId}/enable")
    @ResponseBody
    public ResponseEntity<?> enableUser(@PathVariable Long userId) {
        try {
            userService.enableUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/disable")
    @ResponseBody
    public ResponseEntity<?> disableUser(@PathVariable Long userId) {
        try {
            userService.disableUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-login-name")
    @ResponseBody
    public ResponseEntity<Boolean> checkLoginName(@RequestParam String loginName) {
        boolean exists = userService.isLoginNameExists(loginName);
        return ResponseEntity.ok(exists);
    }
}