package com.bancoDDLS.springboot.app.services;

import java.util.List;

import com.bancoDDLS.springboot.app.models.entity.Cliente;

public interface IClienteService {

	public Cliente getById(Long id, List<Cliente> clientes);
	
}
