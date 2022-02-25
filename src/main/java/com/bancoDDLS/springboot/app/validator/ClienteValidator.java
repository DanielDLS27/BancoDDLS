package com.bancoDDLS.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoDDLS.springboot.app.models.entity.Cliente;

@Component
public class ClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Cliente cliente = (Cliente)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.cliente.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.cliente.apellido");

		if (!cliente.getNombre().matches("[a-z,A-Z]{1,15}?[ ]?[a-z,A-Z]{1,15}")) {
			errors.rejectValue("nombre", "Format.cliente.nombre");
		}
		
		if (!cliente.getApellido().matches("[a-z,A-Z]{1,15}?[ ]?[a-z,A-Z]{1,15}")) {
			errors.rejectValue("apellido", "Format.cliente.apellido");
		}
		
		if (!cliente.getNumeroTelefonico().matches("[0-9]{10}")) {
			errors.rejectValue("numeroTelefonico", "Format.cliente.numerotelefonico");
		}
		
		if(!cliente.getEmail().matches("[0-9A-Za-z]+@[A-Za-z]+[.]?[A-Za-z]+")) {
			errors.rejectValue("email", "Format.cliente.email");
		}
		
	}

}
