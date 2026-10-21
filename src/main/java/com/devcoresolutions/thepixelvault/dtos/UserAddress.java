package com.devcoresolutions.thepixelvault.dtos;

public record UserAddress(
        String city,
        String state,
        String number,
        String sector,
        String street,
        String country,
        String zipCode
) {

}
