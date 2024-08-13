package com.example.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobAdvertService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.JobAdvertDao;
import com.example.hrms.entities.concretes.City;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.JobAdvert;
import com.example.hrms.entities.concretes.Position;
import com.example.hrms.entities.dtos.JobAdvertCreateRequest;
import com.example.hrms.entities.dtos.JobAdvertDto;
import com.example.hrms.mapper.JobAdvertMapper;
//import com.example.hrms.mapper.JobAdvertMapperImpl;

import jakarta.persistence.EntityManager;


@Service
public class JobAdvertManager implements JobAdvertService {
	
	private JobAdvertDao jobAdvertDao;
	
	@Autowired
	private EntityManager entityManager;
	
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
	}

	@Override
	public Result addAdvert(JobAdvertCreateRequest jobAdvertCreateRequest) {
		
		JobAdvert jobAdvert = JobAdvertMapper.INSTANCE.toEntity(jobAdvertCreateRequest);
		
		City city = entityManager.getReference(City.class, jobAdvertCreateRequest.getCityId());
		Position position = entityManager.getReference(Position.class, jobAdvertCreateRequest.getPositionId());
		Employer employer = entityManager.getReference(Employer.class, jobAdvertCreateRequest.getEmployerId());
		jobAdvert.setCity(city);
		jobAdvert.setEmployer(employer);
		jobAdvert.setPosition(position);

		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan başarıyla eklendi.");
	}

	@Override
	public DataResult<List<JobAdvertDto>> getByActiveJobAdverts() {
		
		//List<JobAdvertDto> jobAdverts = jobAdvertDao.getByIsActiveTrue().stream().map(jobAdvert->new JobAdvertDto(jobAdvert)).collect(Collectors.toList()); // my mapper
		
		List<JobAdvertDto> jobAdverts = jobAdvertDao.getByIsActiveTrue().stream().map(JobAdvertMapper.INSTANCE::toDto).collect(Collectors.toList());
		
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdverts,"İlanlar başarıyla görüntülendi.");
	}

	@Override
	public DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateAsc() {
		
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdvertDao.getAllByOrderByApplicationDateAsc(),"İlanlar başarıyla görüntülendi.");
	}

	@Override
	public DataResult<List<JobAdvertDto>> getAllByOrderByApplicationDateDesc() {
		
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdvertDao.getAllByOrderByApplicationDateDesc(),"İlanlar başarıyla görüntülendi.");
	}

	@Override
	public DataResult<List<JobAdvertDto>> getAllByEmployer(int employerId) {
		
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdvertDao.getAllByEmployer(employerId),"İlanlar başarıyla görüntülendi.");
	}

	@Override
	public Result updateJobAdvertStatus(int id) {
		
		JobAdvert jobAdvert = jobAdvertDao.getById(id);
		jobAdvert.setActive(false);
		jobAdvertDao.save(jobAdvert);
		//jobAdvertDao.updateJobAdvertStatus(id);
		return new SuccessResult();
	}
	
	

}
