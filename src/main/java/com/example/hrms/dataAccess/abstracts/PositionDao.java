package com.example.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrms.entities.concretes.Position;


public interface PositionDao extends JpaRepository<Position, Integer> {
	
	List<Position> findByName(String name);

}
