package com.app.prayer.serviceImp;

import com.app.prayer.entity.Role;
import com.app.prayer.repository.RoleRepository;
import com.app.prayer.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp  implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
