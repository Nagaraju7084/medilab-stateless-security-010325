package com.medilab.preclinic.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medilab.preclinic.model.MedilabUser;
import com.medilab.preclinic.model.UserRole;
import com.medilab.preclinic.model.UserRoleToPermission;
import com.medilab.preclinic.repo.MediUserRepository;

@Component
public class CustomUserpermissions implements PermissionEvaluator {
	
	@Autowired
	private MediUserRepository medilabUserRepository;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		String loggedInUser = authentication.getName();
		System.out.println("username :\t" + loggedInUser);
		
		String moduleName = (String)targetDomainObject;
		System.out.println("moduleName :\t" + moduleName);
		
		String userAuthority = (String)permission;
		System.out.println("usreAuthority :\t" + userAuthority);
		
		MedilabUser databaseUser = medilabUserRepository.findUserByEmail(loggedInUser);
		if(databaseUser != null && null != databaseUser.getRole()) {
			UserRole userRole = databaseUser.getRole();
			String databaseRole = userRole.getName();
			if(moduleName.equalsIgnoreCase(databaseRole)) {
				for(UserRoleToPermission userRoleToPermission : userRole.getPermissionsSet()) {
					String dbUserPermissionName = userRoleToPermission.getUserPermission().getName();
					if(userAuthority.equalsIgnoreCase(dbUserPermissionName)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
