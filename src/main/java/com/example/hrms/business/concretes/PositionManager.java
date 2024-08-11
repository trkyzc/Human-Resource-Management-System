
package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.PositionService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.PositionDao;
import com.example.hrms.entities.concretes.Position;

@Service
public class PositionManager implements PositionService {
	
	PositionDao positionDao;

	@Autowired
	public PositionManager(PositionDao positionDao) {
		super();
		this.positionDao = positionDao;
	}

	@Override
	public DataResult<List<Position>> getAll() {
		
		return new SuccessDataResult<List<Position>>( positionDao.findAll(),"Pozisyonlar başarıyla listelendi.");	
		
	}

	@Override
	public Result add(Position position) {
		
		if(!positionDao.findByName(position.getName()).isEmpty()) {
			
			return new ErrorResult("This position has already exist.");		
		}
		else {
			
			positionDao.save(position);
			return new SuccessResult("Position has been added.");
		}
		
	}

}
