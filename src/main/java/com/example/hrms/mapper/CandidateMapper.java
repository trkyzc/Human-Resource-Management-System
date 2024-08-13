package com.example.hrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.dtos.CandidateRequestDto;

@Mapper(componentModel="spring")
public interface CandidateMapper {
	
	CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);
	
	CandidateRequestDto toDto(Candidate candidate);
	
	Candidate toEntity(CandidateRequestDto candidateDto);

}
