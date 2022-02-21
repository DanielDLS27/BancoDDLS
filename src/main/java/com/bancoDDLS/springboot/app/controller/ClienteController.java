package com.bancoDDLS.springboot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoDDLS.springboot.app.models.dao.IClienteDao;
import com.bancoDDLS.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {

	@Autowired
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/clientes-lista", method = RequestMethod.GET)
	public String clienteLista(Model model, Map<String, Object> modelCliente) {
		Cliente cliente = new Cliente();
		modelCliente.put("cliente", cliente);
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "clientes-lista";
	}
	
	@RequestMapping(value = "/buscar-cliente-por-IdTelefono", method = RequestMethod.POST)
	public String buscarClientePorIdOTelefono(Cliente cliente, RedirectAttributes flash) {
		if(clienteDao.findById(Long.parseLong(cliente.getNumeroTelefonico())) != null) {
			flash.addFlashAttribute("clientesEncontrados", clienteDao.findById(Long.parseLong(cliente.getNumeroTelefonico())));
			flash.addFlashAttribute("mensajeSuccess", "Se encontro al cliente");
		}
		else if(!clienteDao.findByTelefono(cliente.getNumeroTelefonico()).isEmpty()) {
			flash.addFlashAttribute("clientesEncontrados", clienteDao.findByTelefono(cliente.getNumeroTelefonico()));
			flash.addFlashAttribute("mensajeSuccess", "Se encontro al cliente");
		}
		else {
			flash.addFlashAttribute("mensajeError", "No se encontro ningun registro");
		}
		return "redirect:/clientes-lista";
	}
	
}
