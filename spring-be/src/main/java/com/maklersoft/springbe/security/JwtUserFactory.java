package com.maklersoft.springbe.security;

import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.models.enums.StateUser;

public final class JwtUserFactory {
    public JwtUserFactory(){}
    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getAccountId(),
                user.getName(),
                user.getLastName(),
                user.getPassword(),
                user.getPhones(),
                user.getEmails(),
                null,
                user.getStateCode() != StateUser.ARCHIVE,
                user.getAssignDate()

        );
    }
}
