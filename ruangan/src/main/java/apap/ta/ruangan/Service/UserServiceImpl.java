package apap.ta.ruangan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.ta.ruangan.Model.RoleModel;
import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDb userDb;

    @Override
    public UserModel createUser(UserModel user){
        String pass = encrypt(user.getPassword());
        RoleModel role = user.getRole();
        user.setPassword(pass);
        user.setRole(role);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
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
        if(password.matches("^.*[A-Za-z].*")){
            if(password.matches(".*[0-9].*")){
             if(password.length() >= 8){
               return true;
             }
           }
        }
       return false;
    } 

}