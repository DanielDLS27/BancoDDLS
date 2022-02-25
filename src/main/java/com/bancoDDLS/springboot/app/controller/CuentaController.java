package com.bancoDDLS.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoDDLS.springboot.app.editors.ClientePropertyEditor;
import com.bancoDDLS.springboot.app.models.dao.IClienteDao;
import com.bancoDDLS.springboot.app.models.dao.ICuentaDao;
import com.bancoDDLS.springboot.app.models.entity.Cliente;
import com.bancoDDLS.springboot.app.models.entity.Cuenta;
import com.bancoDDLS.springboot.app.validator.CuentaValidator;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {

	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private CuentaValidator cuentaValidator;
	
	@Autowired 
	private IClienteDao clienteDao;
	
	@Autowired
	private ClientePropertyEditor clienteEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, "idCliente", clienteEditor);;
	}
	
	@RequestMapping(value = "/cuentas-lista", method = RequestMethod.GET)
	public String cuentaLista(Model model , Map<String, Object> modelCuenta) {
		Cuenta cuenta = new Cuenta();
		modelCuenta.put("cuenta", cuenta);
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		//model.addAttribute("cuentas", cuentaDao.findAll());
		return "cuentas-lista";
	}
	
	@RequestMapping(value = "/form-cuenta")
	public String crear(Map<String, Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		List<Cliente> listaClientes = clienteDao.findAll();
		modelList.addAttribute("listaClientes", listaClientes);
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva cuenta, llene los datos");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "form-cuenta/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cuenta cuenta = null;
		if(id > 0) {
			cuenta = cuentaDao.findOne(id);
		}
		else {
			return "redirect:/cuentas-lista";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Edite la cuenta");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "form-cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		
		//cuentaValidator.validate(cuenta, result);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene los campos correctamente");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, escribalos de manera correcta los campos");
			
			return "form-cuenta";
		}
		else {
			model.addAttribute("result", false);
		}
		model.addAttribute("titulo", "Formulario de la cuenta");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		try {
			cuentaDao.save(cuenta);
			//flash.addFlashAttribute("mensaje", "Cliente guardado correctamente");
		} catch(Exception e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();
		return "redirect:form-cuenta";
	}
	
	@RequestMapping(value = "/eliminar-cuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			cuentaDao.delete(id);
		}
		return "redirect:/cuentas-lista";
	}
	
	@RequestMapping(value = "/buscar-cuenta-por-id", method = RequestMethod.POST)
	public String buscarCuentaPorId(Cuenta cuenta, RedirectAttributes flash) {
		if(cuentaDao.findById(cuenta.getIdCuenta()) != null) {
			flash.addFlashAttribute("cuentasEncontradas", cuentaDao.findById(cuenta.getIdCuenta()));
			flash.addFlashAttribute("mensajeSucces", "Se encontro la cuenta");
		}
		else {
			flash.addFlashAttribute("mensajeError", "No se encontro ningun registro");
		}
		return "redirect:/cuentas-lista";
	}
	
}
