package com.bancoDDLS.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDDLS.springboot.app.models.entity.Tarjeta;

@Repository
public class TarjetaDaoImpl implements ITarjetaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findAll() {
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Transactional
	@Override
	public Tarjeta findOne(Long id) {
		return em.find(Tarjeta.class, id);
	}

	@Transactional(readOnly = true)
	@Override
	public Tarjeta findByNumeroTarjeta(String numeroTarjeta) {
		List<Tarjeta> listaTarjeta = this.findAll();
		Tarjeta result = null;
		for(Tarjeta tarjeta: listaTarjeta) {
			if(tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) {
				result = tarjeta;
			}
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findByIdCuenta(Long idCuenta) {
		List<Tarjeta> listaTarjeta = this.findAll();
		List<Tarjeta> result = new ArrayList<Tarjeta>();
		for(Tarjeta tarjeta: listaTarjeta) {
			if(tarjeta.getIdCuenta().getIdCuenta() == idCuenta) {
				result.add(tarjeta);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public void save(Tarjeta tarjeta) {
		if(tarjeta.getIdTarjeta() != null && tarjeta.getIdTarjeta() > 0) {
			em.merge(tarjeta);
		}
		else {
			em.persist(tarjeta);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
