package com.example.hrms.mapper;

import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.dtos.JobAdvertDto;

public class JobAdvertMapperImpl implements JobAdvertMapper {

	@Override
    public JobAdvertDto toDto(JobAdvert jobAdvert) {
        if (jobAdvert == null) {
            return null;
        }

        JobAdvertDto jobAdvertDto = new JobAdvertDto();
        jobAdvertDto.setEmployerName(jobAdvert.getEmployer() != null ? jobAdvert.getEmployer().getCompanyName() : null);
        jobAdvertDto.setPositionName(jobAdvert.getPosition() != null ? jobAdvert.getPosition().getName() : null);
        jobAdvertDto.setCreatedDate(jobAdvert.getCreatedDate());
        jobAdvertDto.setApplicationDate(jobAdvert.getApplicationDeadline());
        jobAdvertDto.setNumberOfOpenPosition(jobAdvert.getNumberOfOpenPosition());

        return jobAdvertDto;
    }

    @Override
    public JobAdvert toEntity(JobAdvertDto jobAdvertDto) {
        if (jobAdvertDto == null) {
            return null;
        }

        JobAdvert jobAdvert = new JobAdvert();
        // İşveren ve pozisyonun manuel olarak ayarlanması gerekebilir. Entity bulma işlemi burada yapılabilir.
        jobAdvert.setCreatedDate(jobAdvertDto.getCreatedDate());
        jobAdvert.setApplicationDeadline(jobAdvertDto.getApplicationDate());
        jobAdvert.setNumberOfOpenPosition(jobAdvertDto.getNumberOfOpenPosition());

        return jobAdvert;
    }

}
