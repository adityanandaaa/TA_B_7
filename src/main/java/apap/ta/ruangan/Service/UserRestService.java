package apap.ta.ruangan.Service;

import org.json.JSONException;

import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Rest.BaseResponse;
import apap.ta.ruangan.Rest.GuruResponse;
import apap.ta.ruangan.Rest.SiswaResponse;
import reactor.core.publisher.Mono;


import java.util.Map;
public interface UserRestService {
    UserModel createUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    boolean checkPassword(String password);
    Mono<GuruResponse> postStatusGuru(GuruResponse guru) throws JSONException;
    Mono<SiswaResponse> postStatusSiswa(SiswaResponse siswa) throws JSONException;
    Map<String, String> getUserProfile(String role, String uuid);
    Map<String, Object> getUsers(String role);
}
