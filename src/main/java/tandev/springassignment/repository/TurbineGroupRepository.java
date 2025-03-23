package tandev.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tandev.springassignment.entity.TurbineGroup;

@Repository
public interface TurbineGroupRepository extends JpaRepository<TurbineGroup, String> {
} 