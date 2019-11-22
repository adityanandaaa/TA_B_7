package apap.ta.ruangan.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import apap.ta.ruangan.Model.RoleModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import apap.ta.ruangan.Rest.BaseResponse;
import apap.ta.ruangan.Rest.GuruResponse;
import apap.ta.ruangan.Rest.Setting;
import apap.ta.ruangan.Rest.SiswaResponse;
import io.netty.handler.codec.json.JsonObjectDecoder;
import reactor.core.publisher.Mono;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    @Autowired
    private UserDb userDb;

    
    // public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
    //     this.webClient = webClientBuilder.baseUrl(Setting.baseUrl).build();
    // }

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

    @Override
    public UserModel createUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        RoleModel role = user.getRole();
        user.setPassword(pass);
        user.setRole(role);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public boolean checkPassword(String password) {
        if (password.matches("^.*[A-Za-z].*")) {
            if (password.matches(".*[0-9].*")) {
                if (password.length() >= 8) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Mono<GuruResponse> postStatusGuru(GuruResponse guru) throws JSONException{
        JSONObject data = new JSONObject();
        guru.createNig();
        data.put("idUser", guru.getIdUser());
        data.put("nig", guru.getNig());
        data.put("nama", guru.getNama());
        data.put("tempatLahir", guru.getTempat_lahir());
        data.put("tanggalLahir", guru.getTanggal_lahir());
        data.put("alamat", guru.getAlamat());
        data.put("telepon", guru.getTelepon());
        return this.webClient.post().uri("/api/teachers").contentType(MediaType.APPLICATION_JSON).syncBody(data.toString()).retrieve().bodyToMono(GuruResponse.class);
    }

    @Override
    public Mono<SiswaResponse> postStatusSiswa(SiswaResponse siswa) throws JSONException{
        JSONObject data = new JSONObject();
        siswa.createNis();
        data.put("idUser", siswa.getIdUser());
        data.put("nis", siswa.getNis());
        data.put("nama", siswa.getNama());
        data.put("tempatLahir", siswa.getTempat_lahir());
        data.put("tanggalLahir", siswa.getTanggal_lahir());
        data.put("alamat", siswa.getAlamat());
        data.put("telepon", siswa.getTelepon());
        return this.webClient.post().uri("/api/students").contentType(MediaType.APPLICATION_JSON).syncBody(data.toString()).retrieve().bodyToMono(SiswaResponse.class);
    }

    @Override
    public Map<String, String> getUserProfile(String role, String uuid) {
        return this.webClient.get().uri("/api/"+role+"/"+uuid).retrieve().bodyToMono(Map.class).block();
    }
    @Override
    public Map<String, Object> getUsers(String role) {
        return this.webClient.get().uri("/api/"+role).retrieve().bodyToMono(Map.class).block();
    }
}
