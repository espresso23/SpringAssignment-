package tandev.springassignment.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserDTO {

    @NotBlank(message = "Login name is required")
    private String loginName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    private String passwordConfirm;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String firstNameKana;
    private String lastNameKana;

    @Email(message = "Invalid email format")
    private String email;

    private String outTelephone;
    private String inTelephone;
    private String cellularPhone;
    private String cellularMail;
    private String photo;
    private Long positionId;
    private String departmentName;
    private Boolean isAdmin;

    private String fullName;
    private String department;
    
    public String getFullName() {
        return lastName + " " + firstName;
    }
} 