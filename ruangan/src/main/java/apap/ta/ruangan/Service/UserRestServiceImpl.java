package apap.ta.ruangan.Service;

import apap.ta.ruangan.Rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
    private final WebClient webClient;
    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
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
