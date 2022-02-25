package com.bancoDDLS.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoDDLS.springboot.app.models.dao.IClienteDao;
import com.bancoDDLS.springboot.app.services.IClienteService;

@Component
public class ClientePropertyEditor extends PropertyEditorSupport{

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		
		try {
			Long id = Long.parseLong(idStr);
			this.setValue(clienteService.getById(id, clienteDao.findAll()));
		}catch(Exception e) {
			System.out.println("Hubo un error al asignar el objeto cuenta a la tarjeta");
		}
		
	}
	
}
