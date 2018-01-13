package com.data.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.google.common.collect.ImmutableMap;


import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum UserStatusEnum {

    ACTIVE("ACTIVE");

    @Getter
    private final String state;

    private static final Map<String, UserStatusEnum> userStateMap;

    static {
        Map<String, UserStatusEnum> stringUserStatusEnumMap = EnumSet.allOf(UserStatusEnum.class).stream().collect(Collectors.toMap(UserStatusEnum::getState, userStatusEnum -> userStatusEnum));
        userStateMap = ImmutableMap.copyOf(stringUserStatusEnumMap);
    }

    public static UserStatusEnum of(String state) {
        if (userStateMap.containsKey(state)) {
            return userStateMap.get(state);
        }
        throw new IllegalArgumentException("Unknown enum value " + state);
    }
}
