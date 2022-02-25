package com.bancoDDLS.springboot.app.models.dao;

import java.util.List;

import com.bancoDDLS.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public Cliente findOne(Long id);
	
	public Cliente findById(Long id);
	
	public List<Cliente> findByTelefono(String telefono);
	
	public void save(Cliente cliente);
	
	public void delete(Long id);
	
}
