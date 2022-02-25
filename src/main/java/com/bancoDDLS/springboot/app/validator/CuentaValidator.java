package com.bancoDDLS.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.bancoDDLS.springboot.app.models.entity.Cuenta;

@Component
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cuenta cuenta = (Cuenta)target;
		
		if (cuenta.getSaldoActual() < 100) {
			errors.rejectValue("saldoActual", "MinRequerido.cuenta.saldoActual");
		}
		
		if(!Double.toString(cuenta.getSaldoActual()).matches("[0-9]+(.?[0-9]+)?")) {
			errors.rejectValue("saldoActual", "Format.cuenta.saldoActual");
		}
		
		if (cuenta.getCreacion() == null) {
			errors.rejectValue("creacion", "typeMismatch.cuenta.creacion");
		}

	}

}
