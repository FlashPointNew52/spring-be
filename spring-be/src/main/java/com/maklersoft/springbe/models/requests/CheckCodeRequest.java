package com.maklersoft.springbe.models.requests;

import lombok.Data;

@Data
public class CheckCodeRequest {
    String userId;
    String phone;
    String checkCode;
    String password;
}
