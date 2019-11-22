package apap.ta.ruangan.Service;

import apap.ta.ruangan.Rest.Base;
import apap.ta.ruangan.Rest.PengadaanBukuDetail;
import apap.ta.ruangan.Rest.Setting;
import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.json.JSONObject;

import javax.transaction.Transactional;
import java.awt.*;

@Service
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService {

    private final WebClient webClient;


    public PengadaanBukuRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.perpustakaanUrl)
                .build();
    }

    @Override
    public Mono<Base> postStatus(PengadaanBukuDetail pengadaanBuku) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("judul", pengadaanBuku.getJudul());
        data.put("pengarang", pengadaanBuku.getPengarang());
        data.put("penerbit", pengadaanBuku.getPenerbit());
        data.put("jumlah", Integer.toString(pengadaanBuku.getJumlah()));
        data.put("harga", Integer.toString(pengadaanBuku.getHarga()));
        data.put("userId", pengadaanBuku.getUserId());
        return this.webClient
                .post()
                .uri("/api/pengadaan/add")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString())
                .retrieve()
                 .bodyToMono(Base.class);
    }
}
