package com.devcoresolutions.thepixelvault.dtos;

public record RegisterRequest(
    String email,
    String password,
    String userName,
    String userMiddleName,
    String userPhone,
    String userLastNameFirst,
    Byte status,
    String userLastNameSecond,
    UserAddress userAddress
) {
}
