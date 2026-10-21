package com.devcoresolutions.thepixelvault.converters;

import com.devcoresolutions.thepixelvault.dtos.UserAddress;
import tools.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class UserAddressConverter implements AttributeConverter<UserAddress, String> {

    private static final tools.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserAddress attribute) {
        if (attribute == null) return null;
        try {
            // Convierte el record a un String JSON para MySQL
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al serializar UserAddress a JSON", e);
        }
    }

    @Override
    public UserAddress convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        try {
            // Convierte el texto JSON de MySQL de vuelta al record UserAddress
            return objectMapper.readValue(dbData, UserAddress.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al deserializar JSON a UserAddress", e);
        }
    }
}
