package org.example.administrationservice.model.companyBranch;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.administrationservice.event.companyBranch.CompanyBranchBudgetAdjusted;
import org.example.administrationservice.event.companyBranch.CompanyBranchBudgetReduced;
import org.example.administrationservice.event.companyBranch.CompanyBranchCreated;
import org.example.administrationservice.model.Money;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
@Table(name = "COMPANY_BRANCH", uniqueConstraints = {
        @UniqueConstraint(name = "AddressUniqueConstraint", columnNames = {"COMPANY_BRANCH_CITY",
                "COMPANY_BRANCH_ZIP_CODE", "COMPANY_BRANCH_STREET", "COMPANY_BRANCH_COUNTRY",
                "COMPANY_BRANCH_BUILDING_NUMBER"})
})
@SQLDelete(sql = "UPDATE COMPANY_BRANCH SET DELETED = true WHERE id=?")
@Where(clause = "DELETED=false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBranch extends AbstractAggregateRoot<CompanyBranch> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "COMPANY_BRANCH_BUDGET"))
    @NotNull
    private Money budget;

    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                column = @Column(name = "COMPANY_BRANCH_CITY")),
            @AttributeOverride(name = "zipCode",
                column = @Column(name = "COMPANY_BRANCH_ZIP_CODE")),
            @AttributeOverride(name = "street",
                column = @Column(name = "COMPANY_BRANCH_STREET")),
            @AttributeOverride(name = "country",
            column = @Column(name = "COMPANY_BRANCH_COUNTRY")),
            @AttributeOverride(name = "buildingNumber",
                    column = @Column(name = "COMPANY_BRANCH_BUILDING_NUMBER"))
    })
    private Address companyBranchAddress;

    @NotNull
    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Неверный формат номера!")
    @Column(name = "COMPANY_BRANCH_PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "DELETED")
    private boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyBranch that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PostPersist
    public void createCompanyBranch() {
        registerEvent(new CompanyBranchCreated(this.id, this.budget));
    }

    public void adjustBudget(Money adjustmentAmount) {
        this.budget = Money.sum(this.budget, new Money(adjustmentAmount));
        registerEvent(new CompanyBranchBudgetAdjusted(this.id, new Money(adjustmentAmount)));
    }
    public void reduceBudget(Money reduceAmount) {
        this.budget = Money.subtract(this.getBudget(), new Money(reduceAmount));
        registerEvent(new CompanyBranchBudgetReduced(this.id, Money.abs(reduceAmount)));
    }
}