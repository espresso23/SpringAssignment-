package tandev.springassignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tandev.springassignment.dto.UserDTO;
import tandev.springassignment.entity.TurbineUser;
import tandev.springassignment.repository.TurbineUserRepository;
import tandev.springassignment.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private TurbineUserRepository userRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirm())) {
            throw new RuntimeException("Password confirmation does not match");
        }

        if (userRepository.existsByLoginName(userDTO.getLoginName())) {
            throw new RuntimeException("Login name already exists");
        }

        TurbineUser user = new TurbineUser();
        user.setLoginName(userDTO.getLoginName());
        user.setPasswordValue(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setFirstNameKana(userDTO.getFirstNameKana());
        user.setLastNameKana(userDTO.getLastNameKana());
        user.setEmail(userDTO.getEmail());
        user.setOutTelephone(userDTO.getOutTelephone());
        user.setInTelephone(userDTO.getInTelephone());
        user.setCellularPhone(userDTO.getCellularPhone());
        user.setCellularMail(userDTO.getCellularMail());
        user.setPhoto(userDTO.getPhoto());
        user.setPositionId(userDTO.getPositionId());
        user.setIsAdmin(userDTO.getIsAdmin());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setDisabled("F");

        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        TurbineUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setFirstNameKana(userDTO.getFirstNameKana());
        user.setLastNameKana(userDTO.getLastNameKana());
        user.setEmail(userDTO.getEmail());
        user.setOutTelephone(userDTO.getOutTelephone());
        user.setInTelephone(userDTO.getInTelephone());
        user.setCellularPhone(userDTO.getCellularPhone());
        user.setCellularMail(userDTO.getCellularMail());
        user.setPhoto(userDTO.getPhoto().getBytes());
        user.setPositionId(userDTO.getPositionId());
        user.setIsAdmin(userDTO.getIsAdmin());
        user.setModified(LocalDateTime.now());

        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        TurbineUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserByLoginName(String loginName) {
        TurbineUser user = userRepository.findByLoginName(loginName);
        return user != null ? convertToDTO(user) : null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> searchUsers(String keyword) {
        // Implement search logic here
        return userRepository.findAll().stream()
                .filter(user -> matchesKeyword(user, keyword))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void enableUser(Long userId) {
        TurbineUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDisabled("F");
        userRepository.save(user);
    }

    @Override
    public void disableUser(Long userId) {
        TurbineUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDisabled("T");
        userRepository.save(user);
    }

    @Override
    public boolean isLoginNameExists(String loginName) {
        return userRepository.existsByLoginName(loginName);
    }

    private UserDTO convertToDTO(TurbineUser user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setLoginName(user.getLoginName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setFirstNameKana(user.getFirstNameKana());
        dto.setLastNameKana(user.getLastNameKana());
        dto.setEmail(user.getEmail());
        dto.setOutTelephone(user.getOutTelephone());
        dto.setInTelephone(user.getInTelephone());
        dto.setCellularPhone(user.getCellularPhone());
        dto.setCellularMail(user.getCellularMail());
        dto.setPhoto(user.getPhoto());
        dto.setPositionId(user.getPositionId());
        dto.setIsAdmin(user.getIsAdmin());
        
        if (user.getPosition() != null) {
            dto.setDepartmentName(user.getPosition().getPositionName());
        }
        
        return dto;
    }

    private boolean matchesKeyword(TurbineUser user, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true;
        }
        
        keyword = keyword.toLowerCase();
        return (user.getLoginName() != null && user.getLoginName().toLowerCase().contains(keyword)) ||
               (user.getFirstName() != null && user.getFirstName().toLowerCase().contains(keyword)) ||
               (user.getLastName() != null && user.getLastName().toLowerCase().contains(keyword)) ||
               (user.getEmail() != null && user.getEmail().toLowerCase().contains(keyword));
    }
} 