package com.bancoDDLS.springboot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoDDLS.springboot.app.models.dao.ITarjetaDao;
import com.bancoDDLS.springboot.app.models.entity.Tarjeta;

@Controller
public class TarjetaController {

	@Autowired
	private ITarjetaDao tarjetaDao;
	
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
	
}
