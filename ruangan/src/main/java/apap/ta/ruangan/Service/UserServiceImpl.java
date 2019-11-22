package apap.ta.ruangan.Service;


import apap.ta.ruangan.Model.UserModel;
import apap.ta.ruangan.Repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDb userDb;

    @Override
    public UserModel getUserByUSername(String name) {
        return userDb.findByUsername(name);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public UserModel findByuuid(String id){
        return userDb.findById(id);
    }
}


