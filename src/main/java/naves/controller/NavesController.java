package naves.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import naves.dto.NaveDTO;
import naves.kafka.NavesKafkaProducer;
import naves.service.NaveService;

@RestController
@RequestMapping("/api")
public class NavesController {
	
	@Autowired
	private NaveService naveService;
	
	@Autowired
    private NavesKafkaProducer naveKafkaProduder;
	
	@GetMapping("/{pageNumber}/{pageSize}")
	@Operation(summary = "Consultar todas las naves utilizando paginación", description = "Devuelve una lista de naves segun el numero de pagina y el tama;o de apgina especificado")
	public List<NaveDTO> getAll(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize) {
		
		return naveService.getAll(pageNumber, pageSize);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Consultar una única nave por id")
	public NaveDTO getById(@PathVariable("id") Long id) throws Exception {
		return naveService.getById(id);
	}
	
	@GetMapping("/filter/{name}")
	@Operation(summary = "Filtrar naves por nombre", description = "Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en la petición")
	public List<NaveDTO> filter(@PathVariable("name") String name) {
		
		return naveService.filterByName(name);
	}
	
	@PostMapping("/")
	@Operation(summary = "Crear una nueva nave", description = "Devuelve la nave creada")
	public NaveDTO create(@RequestBody NaveDTO nave) {
		
		return naveService.create(nave);
	}
	
	@PatchMapping("/")
	@Operation(summary = "Modificar una nave")
	public NaveDTO modifyById(@RequestBody NaveDTO nave) {
		
		return naveService.modifyById(nave);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una nave", description = "Elimina una nave segun el ID")
	public void deleteById(@PathVariable("id") Long id) {
		naveService.deleteById(id);
	}
	
	@GetMapping("/kafka")
	@Operation(summary = "Envia mensaje de kafka al tópico 'naves'")
    public void sendMessage(@RequestParam("mensaje") String message) {
		naveKafkaProduder.sendMessage(message);
    }
}
