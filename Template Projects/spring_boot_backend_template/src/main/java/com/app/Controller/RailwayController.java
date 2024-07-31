package com.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.CustomException.InvalidException;
import com.app.DTO.RailwayDTO;
import com.app.Services.IRailwayService;
import com.app.entity.Category;
import com.app.entity.Railway;

@RestController
@RequestMapping("/railway")
public class RailwayController {
	
	@Autowired
	private IRailwayService service;
	@GetMapping
	public List<Railway> getAllRailway()
	{
		return (service.getallRailway());
	}
	@PostMapping
	public Railway Insert(@RequestBody Railway r)
	{
		Railway r1=null;
		try {
			r1=service.insert(r);
		}catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return r1;
	}
//	@DeleteMapping("/{id}") 
//	public String Delete(@PathVariable Long id) {
//		
//		return service.Delete(id);
//	}
//	@PutMapping
//	public String Update(@RequestBody Railway r)
//	{
//		return service.Update(r);
//	}
	@DeleteMapping("/{id}")
	public Optional<Railway> delete(@PathVariable Long id){
		return service.Delete(id);
	}
//	@DeleteMapping("/{name}")
//	public String Delete(@PathVariable String name)
//	{
//		return( service.Delete(name));
//	}
	@GetMapping("/{category}")
	public List<Railway> findAllByCategory(@PathVariable Category category)
	{
		return (service.findAllByCategory(category));
	}
	@GetMapping("/Railway/{name}")
	public List<Railway> findAllByname(@PathVariable String name)
	{
		return(service.findAllByname(name));
	}
	@DeleteMapping("/Railway/{name}")
	public void DeleteByName(@PathVariable String name) {
		service.deleteByName(name);
	}
	@GetMapping("/railwayDTO")
	public List<RailwayDTO> GetRailway(){
		return service.GetRailway();
	}
	
	@GetMapping("/sortByName")
	public List<Railway> sortByName(){
		return service.sortByName();
	}
}
