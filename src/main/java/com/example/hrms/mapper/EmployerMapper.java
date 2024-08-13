package com.example.hrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.dtos.EmployerRequestDto;

@Mapper(componentModel="spring")
public interface EmployerMapper {
	
	EmployerMapper INSTANCE = Mappers.getMapper(EmployerMapper.class);
	
	EmployerRequestDto toDto(Employer employer);
	
	Employer toEntity(EmployerRequestDto employerRequestDto);

}
