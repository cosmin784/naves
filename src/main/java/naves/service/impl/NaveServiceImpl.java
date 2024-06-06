package naves.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import naves.dto.NaveDTO;
import naves.entity.Nave;
import naves.repository.NaveRepository;
import naves.service.NaveService;

@Service
public class NaveServiceImpl implements NaveService {

	@Autowired
	private NaveRepository naveRepository;

	@Override
	public List<NaveDTO> getAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Nave> naves = naveRepository.findAll(pageable).getContent();
		
		List<NaveDTO> respuesta = new ArrayList<>();
		
		naves.forEach(x -> respuesta.add(new NaveDTO(x)));
		
		return respuesta;
	}

	@Override
	@Cacheable("naves")
	public NaveDTO getById(Long id) {
		NaveDTO respuesta = null;
		Optional<Nave> nave = naveRepository.findById(id);
		if(nave.isPresent()) {
			respuesta = new NaveDTO(nave.get());
		}
		
		return respuesta;
	}

	@Override
	public List<NaveDTO> filterByName(String name) {
		List<Nave> naves = naveRepository.findByNombreContaining(name);
		List<NaveDTO> respuesta = new ArrayList<>();
		
		naves.forEach(x -> respuesta.add(new NaveDTO(x)));
		
		return respuesta;
	}
	
	@Override
	public NaveDTO create(NaveDTO nave) {
		return new NaveDTO(naveRepository.save(new Nave(nave)));
	}

	@Override
	@CachePut(value = "naves", key = "#nave.id")
	public NaveDTO modifyById(NaveDTO nave) {
		NaveDTO respuesta = null;
		Optional<Nave> naveDBOpt = naveRepository.findById(nave.getId());
		if(naveDBOpt.isPresent()) {
			Nave naveBD = naveDBOpt.get();
			naveBD.setNombre(nave.getNombre());
			naveBD.setColor(nave.getColor());
			naveBD.setTamano(nave.getTamano());
			
			respuesta = new NaveDTO(naveRepository.save(naveBD));
		}
		
		return respuesta;
	}

	@Override
	@CacheEvict("naves")
	public void deleteById(Long id) {
		naveRepository.deleteById(id);
	}
}
