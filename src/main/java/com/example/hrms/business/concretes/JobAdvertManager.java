package com.example.hrms.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobAdvertService;
import com.example.hrms.cache.CacheableConfig;
import com.example.hrms.cache.CacheableConfig.CacheTarget;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorDataResult;
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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class JobAdvertManager implements JobAdvertService {
	
	private JobAdvertDao jobAdvertDao;
	private JobAdvertMapper jobAdvertMapper;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CacheManager cacheManager;
	
	public JobAdvertManager(JobAdvertDao jobAdvertDao, JobAdvertMapper jobAdvertMapper) {
		super();
		this.jobAdvertDao = jobAdvertDao;
		this.jobAdvertMapper = jobAdvertMapper;
	}

	@Override
	//@CacheableConfig(cacheTarget = CacheTarget.SHARED)
	@Caching(evict = { @CacheEvict(value = "jobAdverts", allEntries = true, cacheResolver = "cacheResolver"),
            @CacheEvict(value = "activeJobAdverts", allEntries = true, cacheResolver = "cacheResolver"),
            @CacheEvict(value = "jobAdvertsByEmployerId", key ="#jobAdvertCreateRequest.getEmployerId()" , cacheResolver = "cacheResolver")})
	public Result addAdvert(JobAdvertCreateRequest jobAdvertCreateRequest) {
		
		JobAdvert jobAdvert = jobAdvertMapper.toEntity(jobAdvertCreateRequest);
		
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
	@Transactional  //Hibernate oturumunun açık kalmasını sağlar. Lazy loading yapılabilir.
	@CacheableConfig(cacheTarget = CacheTarget.SHARED)
	@Cacheable(value = "activeJobAdverts", key = "'activeJobAdvertsCache'",cacheResolver = "cacheResolver") //value = cache adı, key = cache anahtarı
	public DataResult<List<JobAdvertDto>> getByActiveJobAdverts() {
		
		//List<JobAdvertDto> jobAdverts = jobAdvertDao.getByIsActiveTrue().stream().map(jobAdvert->new JobAdvertDto(jobAdvert)).collect(Collectors.toList()); // my mapper
		
		List<JobAdvertDto> jobAdverts = jobAdvertDao.getByIsActiveTrue().stream().map(jobAdvertMapper::toDto).collect(Collectors.toList());
		
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
	@CacheableConfig(cacheTarget = CacheTarget.MEMORY_AND_SHARED)
	@Cacheable(value = "jobAdvertsByEmployerId", key = "#employerId", cacheResolver = "cacheResolver")
	public DataResult<List<JobAdvertDto>> getAllByEmployer(int employerId) {
		
		List<JobAdvertDto> jobAdverts = jobAdvertDao.getAllByEmployer(employerId);
		if(jobAdverts.isEmpty()) {
			throw new EntityNotFoundException("İşverene ait ilan bulunamadı.");
		}
		else {
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdverts,"İlanlar başarıyla görüntülendi.");
		}
	}

	@Override
	@CacheEvict(value = "activeJobAdverts", allEntries = true)
	public Result updateJobAdvertStatus(int id) {
		
		JobAdvert jobAdvert = jobAdvertDao.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("İlan bulunamadı."));
		jobAdvert.setActive(!jobAdvert.isActive());
		jobAdvertDao.save(jobAdvert);
		
		//employerId'ye göre cache temizleme
		if (jobAdvert.getEmployer() != null) {
	        cacheManager.getCache("jobAdvertsByEmployerId")
	                    .evict(jobAdvert.getEmployer().getId()); 
	    }

		
		return new SuccessResult("İlan başarıyla güncellendi.");
	}

	@Override
	@Transactional
	@CacheableConfig(cacheTarget = CacheTarget.SHARED)
	@Cacheable(value = "jobAdverts", key = "'allJobAdverts'", cacheResolver = "cacheResolver",unless = "#result == null")
	public DataResult<List<JobAdvertDto>> getAll() {
		
		List<JobAdvertDto> jobAdverts = jobAdvertDao.findAll().stream().map(jobAdvertMapper::toDto).collect(Collectors.toList());
		return new SuccessDataResult<List<JobAdvertDto>>(jobAdverts, "İlanlar başarıyla görüntülendi.");
    }

	@Override
	@Caching(evict = { @CacheEvict(value = "jobAdverts", allEntries = true, cacheResolver = "cacheResolver"),
			@CacheEvict(value = "jobAdvertsByEmployerId", allEntries = true, cacheResolver = "cacheResolver"),
			@CacheEvict(value = "activeJobAdverts", allEntries = true, cacheResolver = "cacheResolver")})
	public Result deleteJobAdvert(int id) {
		    
        Optional<JobAdvert> jobAdvert = jobAdvertDao.findById(id);
		if (jobAdvert == null) {
			throw new EntityNotFoundException("İlan bulunamadı.");
		}
		jobAdvertDao.deleteById(id);
		return new SuccessResult("İlan başarıyla silindi.");
	}
	
	
	

}
