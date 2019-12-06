package apap.ta.ruangan.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ta.ruangan.Model.RoleModel;
import apap.ta.ruangan.Repository.RoleDb;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDb roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel findById(Long id) {
        return roleDb.findById(id).get();
    }

}
