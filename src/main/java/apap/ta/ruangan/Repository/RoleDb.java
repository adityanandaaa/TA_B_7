package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDb extends JpaRepository<RoleModel,Long> {
    Optional<RoleModel> findById(Long id);
}
