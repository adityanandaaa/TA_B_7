package apap.ta.ruangan.Service;

import java.util.List;

import apap.ta.ruangan.Model.RoleModel;

public interface RoleService{
    List<RoleModel> findAll();
    RoleModel findById(Long id);
}