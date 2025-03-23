package tandev.springassignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Entity class representing a company in the system.
 * This class maps to the eip_m_company table in the database.
 * The prefix 'eip_m_' indicates this is a master table in the EIP (Enterprise Information Portal) system.
 */
@Data
@Entity
@Table(name = "eip_m_company")
public class EipMCompany {
    /**
     * Unique identifier for the company
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    /**
     * Official name of the company
     */
    @Column(name = "company_name", nullable = false, length = 64)
    private String companyName;

    /**
     * Company name in Kana (Japanese phonetic characters)
     */
    @Column(name = "company_name_kana", length = 64)
    private String companyNameKana;

    /**
     * Postal code of the company's address
     */
    @Column(name = "zipcode", length = 8)
    private String zipcode;

    /**
     * Physical address of the company
     */
    @Column(name = "address", length = 64)
    private String address;

    /**
     * Main telephone number
     */
    @Column(name = "telephone", length = 15)
    private String telephone;

    /**
     * Fax number
     */
    @Column(name = "fax_number", length = 15)
    private String faxNumber;

    /**
     * Company's website URL
     */
    @Column(name = "url", length = 99)
    private String url;

    /**
     * IP address of the company
     */
    @Column(name = "ipaddress", length = 99)
    private String ipaddress;

    /**
     * Port of the company's website
     */
    @Column(name = "port")
    private Integer port;

    /**
     * Internal IP address of the company
     */
    @Column(name = "ipaddress_internal", length = 99)
    private String ipaddressInternal;

    /**
     * Internal port of the company's website
     */
    @Column(name = "port_internal")
    private Integer portInternal;

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
     * A company can have multiple users
     */
    @OneToMany(mappedBy = "company")
    private List<TurbineUser> users;

    /**
     * One-to-Many relationship with Post entity
     * A company can have multiple posts/positions
     */
    @OneToMany(mappedBy = "company")
    private List<EipMPost> posts;
} 