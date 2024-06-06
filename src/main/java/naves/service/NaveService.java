package naves.service;

import java.util.List;

import naves.dto.NaveDTO;

public interface NaveService {

	List<NaveDTO> getAll(Integer pageNumber, Integer pageSize);
	
	NaveDTO getById(Long id);
	
	List<NaveDTO> filterByName(String name);
	
	NaveDTO create(NaveDTO nave);
	
	NaveDTO modifyById(NaveDTO nave);
	
	void deleteById(Long id);
}
