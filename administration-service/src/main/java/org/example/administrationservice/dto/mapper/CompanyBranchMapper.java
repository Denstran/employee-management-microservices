package org.example.administrationservice.dto.mapper;


import org.example.administrationservice.dto.CompanyBranchDTO;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.dtomapping.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of AbstractMapper for CompanyBranch and CompanyBranchDTO
 */
@Component
public class CompanyBranchMapper extends AbstractMapper<CompanyBranch, CompanyBranchDTO> {

    @Autowired
    public CompanyBranchMapper(ModelMapper mapper) {
        super(CompanyBranch.class, CompanyBranchDTO.class, mapper);
    }
}
