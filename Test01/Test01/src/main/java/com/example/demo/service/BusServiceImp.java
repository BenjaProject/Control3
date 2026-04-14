package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BusEntity;
import com.example.demo.interfaces.IBusService;
import com.example.demo.repository.BusRepository;

@Service
public class BusServiceImp implements IBusService {
	
	@Autowired
	private BusRepository busRepository;

	@Override
	public List<BusEntity> findAll() {
		Iterable<BusEntity> buses = busRepository.findAll();
		return (List<BusEntity>) buses;
	}

	@Override
	public BusEntity findById(Long id) {
		
		return busRepository.findById(id).orElseThrow();
	}

	@Override
	public BusEntity save(BusEntity bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public void deleteById(Long id) {
		busRepository.deleteById(id);
	}
	
	
	
}
