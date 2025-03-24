package tandev.springassignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing a user in the system.
 * This class maps to the turbine_user table in the database.
 */
@Data//auto generated getters/setters
@Entity
@Table(name = "turbine_user")
public class TurbineUser {
    /**
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * User's login name (username). Must be unique in the system.
     */
    @Column(name = "login_name", nullable = false, length = 32, unique = true)
    private String loginName;

    /**
     * Hashed password value
     */
    @Column(name = "password_value", nullable = false, length = 200)
    private String passwordValue;

    /**
     * User's first name in regular characters
     */
    @Column(name = "first_name", nullable = false, length = 99)
    private String firstName;

    /**
     * User's last name in regular characters
     */
    @Column(name = "last_name", nullable = false, length = 99)
    private String lastName;

    /**
     * Primary email address
     */
    @Column(name = "email", length = 99)
    private String email;

    /**
     * Confirmation value
     */
    @Column(name = "confirm_value", length = 99)
    private String confirmValue;

    /**
     * Timestamp of last modification
     */
    @Column(name = "modified")
    private LocalDateTime modified;

    /**
     * Timestamp of record creation
     */
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Timestamp of user's last login
     */
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    /**
     * Account status flag ('T' for disabled, 'F' for enabled)
     */
    @Column(name = "disabled", length = 1)
    private String disabled;

    /**
     * Object data
     */
    @Column(name = "objectdata")
    private byte[] objectdata;

    /**
     * Timestamp of last password change
     */
    @Column(name = "password_changed")
    private LocalDateTime passwordChanged;

    /**
     * ID of user's company
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * ID of user's position in the company
     */
    @Column(name = "position_id")
    private Long positionId;

    /**
     * Internal telephone extension
     */
    @Column(name = "in_telephone", length = 15)
    private String inTelephone;

    /**
     * External telephone number
     */
    @Column(name = "out_telephone", length = 15)
    private String outTelephone;

    /**
     * Mobile phone number
     */
    @Column(name = "cellular_phone", length = 15)
    private String cellularPhone;

    /**
     * Mobile email address
     */
    @Column(name = "cellular_mail", length = 99)
    private String cellularMail;

    /**
     * Cellular UID
     */
    @Column(name = "cellular_uid", length = 99)
    private String cellularUid;

    /**
     * User's first name in Kana (Japanese phonetic characters)
     */
    @Column(name = "first_name_kana", length = 99)
    private String firstNameKana;

    /**
     * User's last name in Kana (Japanese phonetic characters)
     */
    @Column(name = "last_name_kana", length = 99)
    private String lastNameKana;

    /**
     * Path or URL to user's profile photo
     */
    @Column(name = "photo")
    private String photo;

    /**
     * Flag indicating whether user has a photo
     */
    @Column(name = "has_photo", length = 1, columnDefinition = "varchar(1) default 'F'")
    private String hasPhoto;

    /**
     * Timestamp of photo modification
     */
    @Column(name = "photo_modified")
    private LocalDateTime photoModified;

    /**
     * Photo for smartphone
     */
    @Column(name = "photo_smartphone")
    private byte[] photoSmartphone;

    /**
     * Flag indicating whether user has a smartphone photo
     */
    @Column(name = "has_photo_smartphone", length = 1, columnDefinition = "varchar(1) default 'F'")
    private String hasPhotoSmartphone;

    /**
     * Timestamp of smartphone photo modification
     */
    @Column(name = "photo_modified_smartphone")
    private LocalDateTime photoModifiedSmartphone;

    /**
     * Flag indicating whether user has tutorial forbidden
     */
    @Column(name = "tutorial_forbid", length = 1, columnDefinition = "varchar(1) default 'F'")
    private String tutorialForbid;

    /**
     * Migration version
     */
    @Column(name = "migrate_version", nullable = false, columnDefinition = "integer default 0")
    private Integer migrateVersion = 0; // Khởi tạo giá trị mặc định

    /**
     * ID of the user who created this record
     */
    @Column(name = "created_user_id")
    private Long createdUserId;

    /**
     * ID of the user who last modified this record
     */
    @Column(name = "updated_user_id")
    private Long updatedUserId;

    /**
     * Many-to-One relationship with Company entity
     * Lazy loading is used to improve performance
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private EipMCompany company;

    /**
     * Many-to-One relationship with Position entity
     * Lazy loading is used to improve performance
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    private EipMPosition position;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}