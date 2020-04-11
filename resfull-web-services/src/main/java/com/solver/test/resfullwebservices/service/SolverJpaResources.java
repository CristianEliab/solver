package com.solver.test.resfullwebservices.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//Controller
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SolverJpaResources {
	
	@Autowired
	private RegistrosService registroService;
	
	@Autowired
	private RegistroJpaRepository registroJpaRepository;
	
//	@GetMapping(path="/registros")
//	public RegistrosService enviarRespuesta() {
//		return new RegistrosService("Respuesta");
//	}
	
	@GetMapping("/jpa/registros/all")
	public List<Registro> getAllTodos(){
		return registroJpaRepository.findAll();
	}
	
	@DeleteMapping("/jpa/registros/all/{id}")
	public ResponseEntity<Void> deleteRegistro(@PathVariable long id) {
		registroJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/jpa/registros/all/{id}")
	public ResponseEntity<Registro> updateRegistro(@PathVariable long id, @RequestBody Registro registro){
		Registro regis = registroJpaRepository.save(registro);
		return new ResponseEntity<Registro>(regis, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/registros/all")
	public ResponseEntity<Void> updateRegistro( @RequestBody Registro registro){
		Registro regis = registroJpaRepository.save(registro);
		//Get current 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("registros/all").buildAndExpand(regis.getNumero()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
