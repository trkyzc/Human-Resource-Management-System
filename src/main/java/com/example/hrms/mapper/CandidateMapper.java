package com.example.hrms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.concretes.School;
import com.example.hrms.entities.dtos.CandidateRequestDto;
import com.example.hrms.entities.dtos.SchoolDto;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateRequestDto toDto(Candidate candidate);

    @Mapping(target = "school", source = "schoolDto", qualifiedByName = "toSchool")
    Candidate toEntity(CandidateRequestDto candidateRequestDto);

    @Named("toSchool")
    default School toSchool(SchoolDto schoolDto) {
        if (schoolDto == null) {
            return null;
        }
        School school = new School();
        school.setName(schoolDto.getName());
        school.setDepartment(schoolDto.getDepartment());
        school.setStartAt(schoolDto.getStartAt());
        school.setEndAt(schoolDto.getEndAt());
        return school;
    }
}