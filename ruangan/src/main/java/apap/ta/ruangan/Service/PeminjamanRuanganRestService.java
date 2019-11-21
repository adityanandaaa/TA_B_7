package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.PeminjamanRuanganModel;
import apap.ta.ruangan.Rest.PengajuanSurat;
import reactor.core.publisher.Mono;

public interface PeminjamanRuanganRestService {

    PeminjamanRuanganModel createPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel);
    Mono<PengajuanSurat> retrieveListSurat();
}
