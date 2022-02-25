package com.bancoDDLS.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoDDLS.springboot.app.models.entity.Cuenta;

@Service
public class CuentaServiceImpl implements ICuentaService {

	private List<Cuenta> lista;
	
	public CuentaServiceImpl() {
		
	}
	
	@Override
	public Cuenta getById(Long id, List<Cuenta> lista) {
		this.lista = lista;
		Cuenta cuentaResult = null;
		
		for(Cuenta cuenta : this.lista) {
			if(id == cuenta.getIdCuenta()) {
				cuentaResult = cuenta;
				break;
			}
		}
		return cuentaResult;
	}

}
