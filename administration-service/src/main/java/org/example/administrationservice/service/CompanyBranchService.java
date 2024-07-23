package org.example.administrationservice.service;

import org.example.administrationservice.exception.ResourceNotFoundException;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.Address;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.repository.CompanyBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyBranchService {
    private final CompanyBranchRepository companyBranchRepository;

    @Autowired
    public CompanyBranchService(CompanyBranchRepository companyBranchRepository) {
        this.companyBranchRepository = companyBranchRepository;
    }

    @Transactional
    public void create(CompanyBranch companyBranch) {
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

    public boolean existsByAddress(Address address) {
        return companyBranchRepository.existsByCompanyBranchAddress(address);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return companyBranchRepository.existsByPhoneNumber(phoneNumber);
    }

    public Optional<CompanyBranch> getByAddress(Address address) {
        return companyBranchRepository.findCompanyBranchByCompanyBranchAddress(address);
    }

    public Optional<CompanyBranch> getByPhoneNumber(String phoneNumber) {
        return companyBranchRepository.findCompanyBranchByPhoneNumber(phoneNumber);
    }

    public CompanyBranch getReference(Long companyBranchId) {
        if (companyBranchId == null || companyBranchId < 1)
            throw new IllegalArgumentException("Выбран несуществующий отдел!");
        return companyBranchRepository.getReferenceById(companyBranchId);
    }

    public List<CompanyBranch> getAll() {
        return companyBranchRepository.findAll();
    }
}
