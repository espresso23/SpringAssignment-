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
        // Kiểm tra các trường bắt buộc
        if (userDTO.getLoginName() == null || userDTO.getLoginName().isEmpty()) {
            throw new RuntimeException("Login name is required");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            throw new RuntimeException("First name is required");
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            throw new RuntimeException("Last name is required");
        }

        // Kiểm tra xem login_name đã tồn tại chưa
        if (userRepository.existsByLoginName(userDTO.getLoginName())) {
            throw new RuntimeException("Login name already exists");
        }

        // Tạo mới user
        TurbineUser user = new TurbineUser();
        user.setLoginName(userDTO.getLoginName());
        user.setPasswordValue(userDTO.getPassword()); // Mã hóa mật khẩu
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // Thiết lập giá trị mặc định cho migrateVersion
        user.setMigrateVersion(0); // Giá trị mặc định

        // Các trường không bắt buộc
        user.setFirstNameKana(userDTO.getFirstNameKana()); // Có thể NULL
        user.setLastNameKana(userDTO.getLastNameKana()); // Có thể NULL
        user.setEmail(userDTO.getEmail()); // Có thể NULL
        user.setOutTelephone(userDTO.getOutTelephone()); // Có thể NULL
        user.setInTelephone(userDTO.getInTelephone()); // Có thể NULL
        user.setCellularPhone(userDTO.getCellularPhone()); // Có thể NULL
        user.setCellularMail(userDTO.getCellularMail()); // Có thể NULL
        user.setPhoto(userDTO.getPhoto()); // Có thể NULL
        user.setPositionId(userDTO.getPositionId()); // Có thể NULL
        user.setIsAdmin(userDTO.getIsAdmin()); // Có thể NULL

        // Thiết lập thời gian tạo và cập nhật
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setDisabled("F"); // Mặc định là "F" (không bị vô hiệu hóa)

        // Lưu user vào cơ sở dữ liệu
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
        user.setPhoto(userDTO.getPhoto()); // photo là String, không cần chuyển đổi
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

        // Xử lý photo
        if (user.getPhoto() != null) {
            dto.setPhoto(user.getPhoto());
        } else {
            dto.setPhoto(null);
        }

        // Xử lý departmentName
        if (user.getPosition() != null) {
            dto.setDepartmentName(user.getPosition().getPositionName());
        } else {
            dto.setDepartmentName("N/A"); // Hoặc một giá trị mặc định khác
        }

        dto.setIsAdmin(user.getIsAdmin());

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
                (user.getEmail() != null && user.getEmail().toLowerCase().contains(keyword)) ||
                (user.getPosition() != null && user.getPosition().getPositionName().toLowerCase().contains(keyword));
    }
} 