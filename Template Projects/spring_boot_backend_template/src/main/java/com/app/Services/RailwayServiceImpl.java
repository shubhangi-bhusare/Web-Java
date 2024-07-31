package com.app.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.CustomException.InvalidException;
import com.app.DTO.RailwayDTO;
import com.app.Repository.RailwayRepository;
import com.app.entity.Category;
import com.app.entity.Railway;


@Service
@Transactional
public class RailwayServiceImpl implements IRailwayService {
	@Autowired
	private RailwayRepository repo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Railway> getallRailway() {
		return (repo.findAll().stream().collect(Collectors.toList()));
	}
	
	public void ValidateDateTime(Railway r) throws InvalidException {
		if(r.getStartDate().isAfter(r.getEndDate()))
		{
			throw new InvalidException("Time should be correct!!");
		}
	}
	public void ValidateName(Railway r) throws InvalidException {
		if(r.getName()=="")
		{
			throw new InvalidException("Name should not blank!!!");
		}
	}
	public void validateEnum(Railway r) throws InvalidException {
		if(r.getCategory()!=Category.AC|| r.getCategory()!= Category.EXPRESS||r.getCategory()!=Category.METRO||r.getCategory()!=Category.SHATABDI)
		{
			throw new InvalidException("Enum should be Match!!!");
		}
	}
	@Override
	public Railway insert(Railway r) throws InvalidException  {
		ValidateDateTime(r);
		ValidateName(r);
		ValidateDateTime(r);
		return (repo.save(r));
	}



	@Override
	public Optional<Railway> Delete(Long id)  {
		 Optional<Railway> i = null;
		try {
		   i=repo.findById(id);
		 }catch(Exception e) {
			System.out.println("Invalid id!!!"); 
		 }
		repo.deleteById(id);
		 return i;
		 
	}

	@Override
	public String Delete1(Long id) {
		
		if(repo.existsById(id)) 
		{
			repo.deleteById(id);
			return "railway is deleted!!!";
		}
		return "deleting railway is failed...invalid id!!";
		 
	}
//
	@Override
	public String Update(Railway r) {
		if(repo.existsById(r.getId())) {
			 repo.save(r);
			 return "Updated successfully!!!";
		}
		return "fail to update!!";
		
	}
	@Override
	public List<Railway> findAllByCategory(Category category)
	{
		return repo.findAllByCategory(category);
	}
	@Override
	public List<Railway> findAllByname(String name){
		return repo.findAllByname(name);
	}

	@Override
	public void deleteByName(String name) {
		Railway l1=repo.findAllByName(name);
		 repo.delete(l1);
		 
		 
	}

	@Override
	public List<RailwayDTO> GetRailway() {
		
		return (repo.findAll().stream()
		.map(m->mapper.map(m, RailwayDTO.class))
		.collect(Collectors.toList()));
	
	}

//	@Override
//	public List<Railway> sortByName(String name) {
////		List<Railway> l=repo.findAllByName(name);
////		l.stream().sorted().collect(Collectors.toList());
//		return l;
//	}
//	
	public List<Railway> sortByName(){
		return repo.findAllByOrderByNameDesc();
	}

	

}
