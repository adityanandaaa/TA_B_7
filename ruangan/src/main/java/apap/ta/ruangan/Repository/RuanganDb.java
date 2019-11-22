package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuanganDb extends JpaRepository<RuanganModel,Long> {
    RuanganModel findByNama(String nama);
    Optional<RuanganModel> findById(Long id);

}
