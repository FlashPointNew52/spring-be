package com.maklersoft.springbe.models.utils;

import lombok.Data;

import java.util.List;

@Data
public class KladrResponse {
    private List<Object> result;
    private Object searchContext;
}
