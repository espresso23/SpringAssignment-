package tandev.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tandev.springassignment.entity.EipMPost;

@Repository
public interface EipMPostRepository extends JpaRepository<EipMPost, Long> {
} 