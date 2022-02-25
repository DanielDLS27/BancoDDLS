package com.bancoDDLS.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoDDLS.springboot.app.models.dao.IClienteDao;
import com.bancoDDLS.springboot.app.models.entity.Cliente;

@Controller
@SessionAttributes("cliente")
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
	
	@RequestMapping(value = "/form-cliente")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Nuevo cliente, llene los datos");
		return "form-cliente";
	}
	
	@RequestMapping(value = "form-cliente/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteDao.findOne(id);
		}
		else {
			return "redirect:/clientes-lista";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Edite el cliente");
		return "form-cliente";
	}
	
	@RequestMapping(value = "form-cliente", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene los campos correctamente");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, escribalos de manera correcta los campos");
			
			return "form-cliente";
		}
		else {
			model.addAttribute("result", false);
		}
		model.addAttribute("titulo", "Formulario del cliente");
		model.addAttribute("mensaje", "Se envio la infromacion correctamente");
		try {
			clienteDao.save(cliente);
			//flash.addFlashAttribute("mensaje", "Cliente guardado correctamente");
		} catch(Exception e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();
		return "redirect:form-cliente";
	}
	
	@RequestMapping(value = "/eliminar-cliente/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/clientes-lista";
	}
	
}
