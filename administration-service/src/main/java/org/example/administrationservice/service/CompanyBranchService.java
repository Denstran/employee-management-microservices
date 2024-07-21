package org.example.administrationservice.service;

import org.example.administrationservice.exception.ResourceNotFoundException;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.repository.CompanyBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyBranchService {
    private final CompanyBranchRepository companyBranchRepository;

    @Autowired
    public CompanyBranchService(CompanyBranchRepository companyBranchRepository) {
        this.companyBranchRepository = companyBranchRepository;
    }

    @Transactional
    public void save(CompanyBranch companyBranch) {
        companyBranch.createCompanyBranch();
        companyBranchRepository.save(companyBranch);
    }

    @Transactional
    public void update(CompanyBranch companyBranch) {
        CompanyBranch oldCompanyBranch = getById(companyBranch.getId());
        if (budgetEquals(oldCompanyBranch, companyBranch)) companyBranchRepository.save(companyBranch);

        Money budgetDiff = getBudgetDiff(oldCompanyBranch, companyBranch);

        if (Money.isPositive(budgetDiff))
            companyBranch.reduceBudget(budgetDiff);
        else
            companyBranch.adjustBudget(Money.abs(budgetDiff));

        companyBranchRepository.save(oldCompanyBranch);
    }

    private Money getBudgetDiff(CompanyBranch first, CompanyBranch second) {
        return Money.subtract(first.getBudget(), second.getBudget());
    }

    private boolean budgetEquals(CompanyBranch first, CompanyBranch second) {
        return first.getBudget().equals(second.getBudget());
    }

    public CompanyBranch getById(Long companyBranchId) {
        return companyBranchRepository.findById(companyBranchId).orElseThrow(
                () -> new ResourceNotFoundException("Филиал с указанным id не существует!"));
    }

    @Transactional
    public void reduceBudget(CompanyBranch companyBranch, Money reduceAmount) {
        companyBranch.reduceBudget(reduceAmount);

        companyBranchRepository.save(companyBranch);
    }

    @Transactional
    public void adjustBudget(CompanyBranch companyBranch, Money budgetAdjustment) {
       companyBranch.adjustBudget(budgetAdjustment);

       companyBranchRepository.save(companyBranch);
    }
}
