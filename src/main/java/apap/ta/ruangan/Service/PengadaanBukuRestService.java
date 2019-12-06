package apap.ta.ruangan.Service;

import apap.ta.ruangan.Rest.Base;
import apap.ta.ruangan.Rest.PengadaanBukuDetail;
import org.json.JSONException;
import reactor.core.publisher.Mono;

public interface PengadaanBukuRestService {
    Mono<Base> postStatus(PengadaanBukuDetail pengadaanBuku) throws JSONException;
}
