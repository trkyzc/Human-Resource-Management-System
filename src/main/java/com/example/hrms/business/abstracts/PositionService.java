package com.example.hrms.business.abstracts;

import java.util.List;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Position;

public interface PositionService {
	
	DataResult<List<Position>> getAll();
	Result add(Position position);

}
