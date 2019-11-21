package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.UserModel;

public interface UserService {
    UserModel findByUsername(String username);
}
