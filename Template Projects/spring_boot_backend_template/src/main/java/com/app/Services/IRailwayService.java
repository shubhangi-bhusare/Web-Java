package com.app.Services;
import java.util.List;
import java.util.Optional;

import com.app.CustomException.InvalidException;
import com.app.DTO.RailwayDTO;
import com.app.entity.*;
public interface IRailwayService {
		List<Railway> getallRailway();
		//insert
		Railway insert(Railway r) throws InvalidException;
		//Delete
		String Delete1(Long id);
		String Update(Railway r);
//		 String Delete(String name);
		 List<Railway> findAllByCategory(Category category);
		 List<Railway> findAllByname(String name);
		 void deleteByName(String name);
		 List<RailwayDTO> GetRailway();
		 Optional<Railway> Delete(Long id);
		 List<Railway> sortByName();
}
