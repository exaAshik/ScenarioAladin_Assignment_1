package org.example.util;

import org.example.Entities.Role;

import java.util.Set;

public class Authorize {

    public static boolean isAutorize(Set<Role>roles){
        System.out.println(roles);
        for(Role role:roles){
            if(role.getRoleName().equalsIgnoreCase("Lead")||
                    role.getRoleName().equals("Admin")){
                return true;
            }
        }

        return false;
    }
}
