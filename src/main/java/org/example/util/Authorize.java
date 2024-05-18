package org.example.util;

import org.example.Entities.Role;

import java.util.Set;

public class Authorize {

    public static boolean isAutorize(Set<Role>roles){

        for(Role role:roles){
            if(role.getRoleName().equalsIgnoreCase("Normal")||
                    role.getRoleName().equals("Admin")){
                return true;
            }
        }

        return false;
    }
}
