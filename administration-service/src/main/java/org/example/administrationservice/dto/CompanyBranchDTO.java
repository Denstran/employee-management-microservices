package org.example.administrationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.Address;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyBranchDTO {
    private Long id;

    @NotNull
    @Valid
    private Money budget;

    @NotNull
    @Valid
    private Address companyBranchAddress;

    @NotNull
    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Неверный формат номера!")
    private String phoneNumber;
}
