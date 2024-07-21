package org.example.administrationservice.service;

import org.example.administrationservice.exception.ResourceNotFoundException;
import org.example.administrationservice.model.CompanyBranchDepartmentPK;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.example.administrationservice.repository.DepartmentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentInfoService {
    private final DepartmentInfoRepository repository;

    @Autowired
    public DepartmentInfoService(DepartmentInfoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createDepartmentInfo(DepartmentInfo departmentInfo) {
        departmentInfo.registerDepartmentInfo();
        repository.save(departmentInfo);
    }

    @Transactional
    public void updateDepartmentInfo(DepartmentInfo departmentInfo) {
        DepartmentInfo departmentInfoFromDB = getById(departmentInfo.getPk());
        if (!budgetsEquals(departmentInfoFromDB, departmentInfo))
            repository.save(departmentInfo);

        Money budgetDiff = getBudgetDiff(departmentInfoFromDB, departmentInfo);

        if (Money.isPositive(budgetDiff))
            departmentInfo.reduceBudget(budgetDiff);
        else
            departmentInfo.adjustBudget(Money.abs(budgetDiff));

        repository.save(departmentInfo);
    }

    private boolean budgetsEquals(DepartmentInfo first, DepartmentInfo second) {
        return first.getDepartmentBudget().equals(second.getDepartmentBudget());
    }

    private Money getBudgetDiff(DepartmentInfo first, DepartmentInfo second) {
        return Money.subtract(first.getDepartmentBudget(), second.getDepartmentBudget());
    }

    public DepartmentInfo getById(CompanyBranchDepartmentPK pk) {
        return repository.findById(pk).orElseThrow(
                () -> new ResourceNotFoundException("Такой отдел не существует!"));
    }
}
