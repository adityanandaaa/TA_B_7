package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.UserModel;

public interface UserService {

    UserModel getUserByUSername(String name);
    UserModel findByUsername(String username);
    UserModel findByuuid(String id);

}
