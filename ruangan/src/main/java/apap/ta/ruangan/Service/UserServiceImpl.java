package apap.ta.ruangan.Service;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel findByUsername(String username) {
        return userDb.findByUsername(username);
    }
}

