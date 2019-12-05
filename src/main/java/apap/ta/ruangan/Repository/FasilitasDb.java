package apap.ta.ruangan.Repository;

import apap.ta.ruangan.Model.FasilitasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FasilitasDb extends JpaRepository<FasilitasModel,Long> {
    FasilitasModel findFasilitasModelById(Long id);
    FasilitasModel findFasilitasModelByNama(String nama);
}
