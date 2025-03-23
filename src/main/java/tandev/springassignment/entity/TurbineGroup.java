package tandev.springassignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing a group in the system.
 * This class maps to the turbine_group table in the database.
 * Groups are used to organize users and associate them with specific posts/positions.
 */
@Data
@Entity
@Table(name = "turbine_group")
public class TurbineGroup {
    /**
     * Group ID serves as the primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    /**
     * Group name serves as the primary key
     * Unlike other entities, this uses a String identifier instead of an auto-generated ID
     */
    @Column(name = "group_name", nullable = false, length = 99, unique = true)
    private String groupName;

    /**
     * Object data associated with the group
     */
    @Column(name = "objectdata")
    private byte[] objectdata;

    /**
     * ID of the user who owns/manages this group
     */
    @Column(name = "owner_id")
    private Long ownerId;

    /**
     * Display name or alias for the group
     * This can be different from the groupName which is used as the identifier
     */
    @Column(name = "group_alias_name", length = 99)
    private String groupAliasName;

    /**
     * Public flag for the group
     */
    @Column(name = "public_flag", length = 1)
    private String publicFlag;

    /**
     * ID of the user who created this record
     */
    @Column(name = "created_user_id")
    private Long createdUserId;

    /**
     * ID of the user who last modified this record
     */
    @Column(name = "modified_user_id")
    private Long modifiedUserId;

    /**
     * Timestamp of record creation
     */
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Timestamp of last modification
     */
    @Column(name = "modified")
    private LocalDateTime modified;

    /**
     * One-to-One relationship with Post entity
     * Each group is associated with exactly one post/position
     */
    @OneToOne(mappedBy = "group")
    private EipMPost post;
} 