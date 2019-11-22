package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Rest.PengajuanSurat;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PeminjamanRuanganRestService {

    PeminjamanRuanganModel createPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel);
    List<PeminjamanRuanganModel> getPeminjamanRuanganList();
}
