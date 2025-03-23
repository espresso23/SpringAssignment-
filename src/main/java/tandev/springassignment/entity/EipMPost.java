package tandev.springassignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity class representing a post/job position in the company.
 * This class maps to the eip_m_post table in the database.
 * The prefix 'eip_m_' indicates this is a master table in the EIP (Enterprise Information Portal) system.
 * Posts represent specific job positions within a company and are associated with groups.
 */
@Data
@Entity
@Table(name = "eip_m_post")
public class EipMPost {
    /**
     * Unique identifier for the post
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    /**
     * ID of the company this post belongs to
     */
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    /**
     * Name/title of the post
     */
    @Column(name = "post_name", nullable = false, length = 64)
    private String postName;

    /**
     * Zipcode associated with the post
     */
    @Column(name = "zipcode", length = 8)
    private String zipcode;

    /**
     * Address associated with the post
     */
    @Column(name = "address", length = 64)
    private String address;

    /**
     * Inbound telephone number associated with the post
     */
    @Column(name = "in_telephone", length = 15)
    private String inTelephone;

    /**
     * Outbound telephone number associated with the post
     */
    @Column(name = "out_telephone", length = 15)
    private String outTelephone;

    /**
     * Fax number associated with the post
     */
    @Column(name = "fax_number", length = 15)
    private String faxNumber;

    /**
     * Name of the group associated with this post
     */
    @Column(name = "group_name", length = 99, unique = true)
    private String groupName;

    /**
     * Sort order of the post
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
     * Many-to-One relationship with Company entity
     * Each post belongs to one company
     * Lazy loading is used to improve performance
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private EipMCompany company;

    /**
     * One-to-One relationship with Group entity
     * Each post is associated with exactly one group
     * The relationship is mapped by the group_name field
     */
    @OneToOne
    @JoinColumn(name = "group_name", referencedColumnName = "group_name", insertable = false, updatable = false)
    private TurbineGroup group;
} 