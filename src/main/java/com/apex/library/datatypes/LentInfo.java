package com.apex.library.datatypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class LentInfo {
    UUID uuid;
    long lentAt;
    long expiredIn;
    LentType type;

    public boolean isExpired() {
        return System.currentTimeMillis() >= expiredIn;
    }
}
