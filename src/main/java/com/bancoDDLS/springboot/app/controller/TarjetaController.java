package com.bancoDDLS.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.InitBinder;

import com.bancoDDLS.springboot.app.editors.CuentaPropertyEditor;
import com.bancoDDLS.springboot.app.models.dao.ICuentaDao;
import com.bancoDDLS.springboot.app.models.dao.ITarjetaDao;
import com.bancoDDLS.springboot.app.models.entity.Cuenta;
import com.bancoDDLS.springboot.app.models.entity.Tarjeta;

@Controller
@SessionAttributes("tarjeta")
public class TarjetaController {

	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private CuentaPropertyEditor cuentaEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class, "idCuenta", cuentaEditor);
	}
	
	@RequestMapping(value = "/tarjetas-lista", method = RequestMethod.GET)
	public String tarjetaLista(Model model, Map<String, Object> modelTarjeta) {
		Tarjeta tarjeta = new Tarjeta();
		modelTarjeta.put("tarjeta", tarjeta);
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetas-lista";
	}
	
	@RequestMapping(value = "/buscar-tarjeta-por-numeroYIdCuenta", method = RequestMethod.POST)
	public String buscarTarjetaPorNumeroYIdCuenta(Tarjeta tarjeta, RedirectAttributes flash) {
		if(tarjetaDao.findByNumeroTarjeta(tarjeta.getNumeroTarjeta()) != null) {
			flash.addFlashAttribute("tarjetasEncontradas", tarjetaDao.findByNumeroTarjeta(tarjeta.getNumeroTarjeta()));
			flash.addFlashAttribute("mensajeSuccess", "Se encontro la tarjeta");
		}
		else if(!tarjetaDao.findByIdCuenta(Long.parseLong(tarjeta.getNumeroTarjeta())).isEmpty()) {
			flash.addFlashAttribute("tarjetasEncontradas", tarjetaDao.findByIdCuenta(Long.parseLong(tarjeta.getNumeroTarjeta())));
			flash.addFlashAttribute("mensajeSuccess", "Se encontro la tarjeta");
		}
		else {
			flash.addFlashAttribute("mensajeError", "No se encontro ningun registro");
		}
		return "redirect:/tarjetas-lista";
	}
	
	@RequestMapping(value = "/form-tarjeta")
	public String crear(Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta = new Tarjeta();
		List<Cuenta> listaCuentas = cuentaDao.findAll();
		model.put("tarjeta", tarjeta);
		modelList.addAttribute("listaCuentas", listaCuentas);
		model.put("titulo", "Nueva tarjeta, llene los datos");
		return "form-tarjeta";
	}
	
	@RequestMapping(value = "form-tarjeta/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Tarjeta tarjeta = null;
		if(id > 0) {
			tarjeta = tarjetaDao.findOne(id);
		}
		else {
			return "redirect:/tarjetas-lista";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Edite la tarjeta");
		return "form-tarjeta";
	}
	
	@RequestMapping(value = "form-tarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene los campos correctos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, escriba correctamente los campos");
			return "form-tarjeta";
		}
		else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario de la tarjeta");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		try {
			tarjetaDao.save(tarjeta);
		} catch(Exception e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		
		status.setComplete(); 
		return "redirect:form-tarjeta";
	}
	
	@RequestMapping(value = "/eliminar-tarjeta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id != null && id > 0) {
			tarjetaDao.delete(id);
		}
		return "redirect:/tarjetas-lista";
	}
	
}
