package org.example.administrationservice.dto.mapper;

import jakarta.annotation.PostConstruct;
import org.example.administrationservice.dto.DepartmentInfoDTO;
import org.example.administrationservice.model.department.CompanyBranchDepartmentPK;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.example.administrationservice.service.CompanyBranchService;
import org.example.administrationservice.service.DepartmentService;
import org.example.dtomapping.AbstractMapperWithSpecificFields;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DepartmentInfoMapper extends AbstractMapperWithSpecificFields<DepartmentInfo, DepartmentInfoDTO> {

    private final CompanyBranchService companyBranchService;
    private final DepartmentService departmentService;

    public DepartmentInfoMapper(ModelMapper mapper, CompanyBranchService companyBranchService,
                                DepartmentService departmentService) {
        super(DepartmentInfo.class, DepartmentInfoDTO.class, mapper);
        this.companyBranchService = companyBranchService;
        this.departmentService = departmentService;
    }

    @PostConstruct
    @Override
    public void setupMapper() {
        mapper.createTypeMap(DepartmentInfo.class, DepartmentInfoDTO.class)
                .addMappings(m -> {
                    m.skip(DepartmentInfoDTO::setDepartmentId);
                    m.skip(DepartmentInfoDTO::setCompanyBranchId);
                    m.skip(DepartmentInfoDTO::setDepartmentName);
                }).setPostConverter(toDtoConverter());

        mapper.createTypeMap(DepartmentInfoDTO.class, DepartmentInfo.class)
                .addMappings(m -> m.skip(DepartmentInfo::setPk)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFieldsForDto(DepartmentInfo source, DepartmentInfoDTO destination) {
        if (Objects.isNull(source) || Objects.isNull(source.getPk())) {
            destination.setDepartmentId(null);
            destination.setCompanyBranchId(null);
            destination.setDepartmentName(null);
        }else {
            destination.setDepartmentId(source.getPk().getDepartment().getId());
            destination.setCompanyBranchId(source.getPk().getCompanyBranch().getId());
            destination.setDepartmentName(source.getPk().getDepartment().getDepartmentName());
        }
    }

    @Override
    protected void mapSpecificFieldsForEntity(DepartmentInfoDTO source, DepartmentInfo destination) {
        if (Objects.isNull(source) || Objects.isNull(source.getDepartmentId()) ||
                Objects.isNull(source.getCompanyBranchId())) destination.setPk(null);
        else {
            CompanyBranch cbRef = companyBranchService.getReference(source.getCompanyBranchId());
            Department depRef = departmentService.getReference(source.getDepartmentId());

            CompanyBranchDepartmentPK pk = new CompanyBranchDepartmentPK(cbRef, depRef);
            destination.setPk(pk);
        }
    }
}
