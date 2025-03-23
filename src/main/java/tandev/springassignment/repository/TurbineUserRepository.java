package tandev.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tandev.springassignment.entity.TurbineUser;

@Repository
public interface TurbineUserRepository extends JpaRepository<TurbineUser, Long> {
    TurbineUser findByLoginName(String loginName);
    boolean existsByLoginName(String loginName);
} 