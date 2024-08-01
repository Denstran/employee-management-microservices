package org.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    @Override
    public String toString() {
        if (middleName == null || middleName.isEmpty() || middleName.equals("ОТСУТСТВУЕТ"))
            return String.format("%s %s", firstName, lastName);

        return String.format("%s %s %s", firstName, lastName, middleName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return firstName.equals(name.firstName) && middleName.equals(name.middleName)
                && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }
}
