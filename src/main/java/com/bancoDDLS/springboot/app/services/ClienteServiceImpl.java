package com.bancoDDLS.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoDDLS.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	private List<Cliente> lista;
	
	public ClienteServiceImpl() {
		
	}
	
	@Override
	public Cliente getById(Long id, List<Cliente> clientes) {
		
		this.lista = clientes;
		
		Cliente clienteResult = null;
		
		for(Cliente cliente : this.lista) {
			if(id == cliente.getIdCliente()) {
				clienteResult = cliente;
				break;
			}
		}
		
		return clienteResult;
		
	}

}
