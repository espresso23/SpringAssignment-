package tandev.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tandev.springassignment.entity.EipMPosition;

@Repository
public interface EipMPositionRepository extends JpaRepository<EipMPosition, Long> {
} 