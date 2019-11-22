package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.UserModel;

public interface UserService {
    UserModel createUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    boolean checkPassword(String password);

    UserModel getUserByUSername(String name);
    UserModel findByUsername(String username);
    UserModel findByuuid(String id);

}
