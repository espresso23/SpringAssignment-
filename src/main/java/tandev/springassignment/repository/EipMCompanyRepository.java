package tandev.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tandev.springassignment.entity.EipMCompany;

@Repository
public interface EipMCompanyRepository extends JpaRepository<EipMCompany, Long> {
} 