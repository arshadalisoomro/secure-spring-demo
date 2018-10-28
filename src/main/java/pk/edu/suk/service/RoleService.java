package pk.edu.suk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.suk.model.Role;
import pk.edu.suk.repository.RoleRepository;
import pk.edu.suk.service.util.CrudOperation;

import java.util.List;

/**
 * Created by Arshay on 10/27/2018.
 */

@Service
public class RoleService implements CrudOperation<Role>{

    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public boolean save(Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getRoleId()).isPresent();
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).isPresent() ? roleRepository.findById(id).get() : null;
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
