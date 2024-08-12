package com.example.hrms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.JobAdvertDto;

@Mapper(componentModel="spring")
public interface JobAdvertMapper {
	
	JobAdvertMapper INSTANCE = Mappers.getMapper(JobAdvertMapper.class);
	
	@Mapping(source = "employer.companyName", target = "employerName")
    @Mapping(source = "position.name", target = "positionName")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "applicationDeadline", target = "applicationDate")
	@Mapping(source = "numberOfOpenPosition", target = "numberOfOpenPosition")
    JobAdvertDto toDto(JobAdvert jobAdvert);
	
	

    @Mapping(source = "employerName", target = "employer.companyName")
    @Mapping(source = "positionName", target = "position.name")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "applicationDate", target = "applicationDeadline")
    @Mapping(source = "numberOfOpenPosition", target = "numberOfOpenPosition")
    JobAdvert toEntity(JobAdvertDto jobAdvertDto);
	
	

}
