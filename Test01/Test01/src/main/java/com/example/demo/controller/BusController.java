package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BusEntity;
import com.example.demo.interfaces.IBusService;
@RestController
@RequestMapping("/api/v1/entities/buses")
public class BusController {

	@Autowired
	private IBusService busService;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			return ResponseEntity.ok(busService.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(busService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body("Bus no encontrado");
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BusEntity bus){
		try {
			return ResponseEntity.ok(busService.save(bus));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(e);
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BusEntity bus){
		try {
			BusEntity busEncontrado = busService.findById(id);
			if(busEncontrado==null) {
				return ResponseEntity.status(404).body("Bus no encontrado");
			}
			busEncontrado.setPatente(bus.getPatente());
			busEncontrado.setMarca(bus.getMarca());
			busEncontrado.setKm(bus.getKm());
			busEncontrado.setAnio(bus.getAnio());
			busEncontrado.setCombustible(bus.getCombustible());
			busEncontrado.setRevisionAlDia(bus.isRevisionAlDia());
			return ResponseEntity.ok(busService.save(busEncontrado));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try {
			busService.deleteById(id);
			return ResponseEntity.ok("Bus Eliminado correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(e);
		}
		
	}
	
	
	
	
}
