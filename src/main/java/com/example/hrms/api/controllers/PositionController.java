package com.example.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.PositionService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Position;

@RestController
@RequestMapping("hrms/api/positions")
public class PositionController {
	
	PositionService positionService;
	
	
	@Autowired
	public PositionController(PositionService positionService) {
		super();
		this.positionService = positionService;
	}


    @GetMapping("/getall")
	ResponseEntity<DataResult<List<Position>>> getAll(){
		
		return new ResponseEntity<>(positionService.getAll(),HttpStatus.OK);
	
	}
    
    @PostMapping("/add")
    ResponseEntity<Result> add(@RequestBody Position position) {
    	
    	return new ResponseEntity<> (positionService.add(position), HttpStatus.OK);
    }

}
