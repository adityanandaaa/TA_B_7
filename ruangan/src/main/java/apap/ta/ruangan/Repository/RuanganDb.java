package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuanganDb extends JpaRepository<RuanganModel,Long> {

}
