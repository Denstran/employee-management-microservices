package org.example.administrationservice.controller;

import org.example.administrationservice.service.CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/companyBranches")
public class CompanyBranchController {
    private final CompanyBranchService companyBranchService;

    @Autowired
    public CompanyBranchController(CompanyBranchService companyBranchService) {
        this.companyBranchService = companyBranchService;
    }
}
