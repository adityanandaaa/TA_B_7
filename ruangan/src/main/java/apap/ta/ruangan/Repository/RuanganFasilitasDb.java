package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Model.RuanganFasilitasModel;
import apap.ta.ruangan.Model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuanganFasilitasDb extends JpaRepository<RuanganFasilitasModel,Long> {
    List<RuanganFasilitasModel> findByRuanganModel(RuanganModel ruanganModel);
    RuanganFasilitasModel findByRuanganModelAndFasilitasModel(RuanganModel ruang, FasilitasModel fasilitas);
    Optional<RuanganFasilitasModel> findById(Long id);
    List<RuanganFasilitasModel> findByFasilitasModel(FasilitasModel fasilitasModel);
}
