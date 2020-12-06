package com.maklersoft.springbe.models.utils;

import lombok.Data;

import java.util.Objects;

@Data
public class Confirmation {
    Object key;
    Object value;

    public Confirmation(Object key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Confirmation that = (Confirmation) o;
        return key.equals(that.key) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
