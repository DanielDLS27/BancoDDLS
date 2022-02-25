package com.bancoDDLS.springboot.app.models.dao;

import java.util.List;

import com.bancoDDLS.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDao {

	public List<Tarjeta> findAll();
	
	public Tarjeta findOne(Long id);
	
	public Tarjeta findByNumeroTarjeta(String numeroTarjeta);
	
	public List<Tarjeta> findByIdCuenta(Long idCuenta);
	
	public void save(Tarjeta tarjeta);
	
	public void delete(Long id);
}
