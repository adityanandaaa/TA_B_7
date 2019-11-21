package apap.ta.ruangan.Service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserRestService {
    Map<String, String> getUserProfile(String role, String uuid);
    Map<String, Object> getUsers(String role);
}
