package com.bancoDDLS.springboot.app.models.dao;

import java.util.List;

import com.bancoDDLS.springboot.app.models.entity.Cuenta;

public interface ICuentaDao {
	
	public List<Cuenta> findAll();

	public void save(Cuenta cuenta);
	
	public Cuenta findOne(Long id);
	
	public void delete(Long id);
	
	public Cuenta findById(Long id);
}
