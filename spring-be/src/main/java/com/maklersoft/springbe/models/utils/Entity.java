package com.maklersoft.springbe.models.utils;

import com.maklersoft.springbe.models.exceptions.BeforeSaveException;

public interface Entity {
    void beforeSave(String accountId) throws BeforeSaveException;
}
