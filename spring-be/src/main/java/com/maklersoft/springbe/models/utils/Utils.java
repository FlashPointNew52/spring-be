package com.maklersoft.springbe.models.utils;

import com.maklersoft.springbe.models.responses.GeoObjectsResponse;
import com.maklersoft.springbe.security.JwtUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static JwtUser getSessionUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user = (auth != null) ? auth.getPrincipal() :  null;

        if (user instanceof JwtUser) {
            return (JwtUser) user;
            //get details from model object
        }
        return null;
    }

}
