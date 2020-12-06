package com.maklersoft.springbe.models.requests;

import lombok.Data;

@Data
public class TaskListRequest {
    boolean isOrg;
    String orgName;
    String phone;
    String email;
    String userName;
    String userLastName;
}
