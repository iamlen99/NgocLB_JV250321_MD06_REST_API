package ra.edu.service;

import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Roles;

public interface RoleService {
    Roles findByRoleName(RoleName roleName);
}
