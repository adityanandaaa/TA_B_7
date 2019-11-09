package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuanganFasilitasDb extends JpaRepository<RuanganFasilitasModel,Long> {
    List<RuanganFasilitasModel> findByRuanganModel(RuanganModel ruanganModel);
}
