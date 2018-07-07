package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Dept;
import repos.DeptRepository;

@RestController
@RequestMapping( value="/")
public class DeptController {

	@Autowired
	private DeptRepository repo;
	
	@GetMapping(value="dept",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dept> getdeptbyloc(@RequestParam(name="loc") String loc){
		System.out.println("getdeptbyloc " + loc);
		return repo.findByLoc(loc);
	}
	
	@GetMapping()
	public String m(){
		return "<h1>In M1 </h1>";
		
	}
}
