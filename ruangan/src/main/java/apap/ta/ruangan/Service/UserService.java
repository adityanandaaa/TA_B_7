package apap.ta.ruangan.Service;

import java.util.List;

import apap.ta.ruangan.Model.UserModel;

public interface UserService {
    UserModel createUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    boolean checkPassword(String password);
    List<UserModel> getAllUsers();
    UserModel getUserByUSername(String name);
    UserModel findByUsername(String username);
    UserModel findByuuid(String id);

}
