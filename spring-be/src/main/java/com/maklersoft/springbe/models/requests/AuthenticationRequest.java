package com.maklersoft.springbe.models.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String phone;
    String password;
}
