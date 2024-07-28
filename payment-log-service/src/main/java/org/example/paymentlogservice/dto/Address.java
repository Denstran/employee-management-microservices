package org.example.paymentlogservice.dto;

import lombok.*;

import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;
    private String zipCode;
    private String street;
    private int buildingNumber;
    private String country;

    @Override
    public String toString() {
        if (zipCode == null || zipCode.isEmpty())
            return String.format("%s, %s, %s", country, city, street);

        return String.format("%s, %s, %s, %s", country, city, street, zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return city.equals(address.city) && zipCode.equals(address.zipCode) && street.equals(address.street)
                && country.equals(address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, zipCode, street, country);
    }
}
