package tandev.springassignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Entity class representing a position/role in the company.
 * This class maps to the eip_m_position table in the database.
 * The prefix 'eip_m_' indicates this is a master table in the EIP (Enterprise Information Portal) system.
 */
@Data
@Entity
@Table(name = "eip_m_position")
public class EipMPosition {
    /**
     * Unique identifier for the position
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    /**
     * Name/title of the position (e.g., "Manager", "Developer", etc.)
     */
    @Column(name = "position_name", nullable = false, length = 64)
    private String positionName;

    /**
     * Sort order of the position
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * Date of record creation
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * Timestamp of last modification
     */
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * One-to-Many relationship with User entity
     * A position can be held by multiple users
     */
    @OneToMany(mappedBy = "position")
    private List<TurbineUser> users;
} 