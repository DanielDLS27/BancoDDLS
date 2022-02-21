package com.bancoDDLS.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDDLS.springboot.app.models.entity.Cuenta;

@Repository
public class CuentaDaoImpl implements ICuentaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cuenta> findAll() {
		return em.createQuery("from Cuenta").getResultList();
	}

	@Transactional
	@Override
	public void save(Cuenta cuenta) {
		if(cuenta.getIdCuenta() != null && cuenta.getIdCuenta() > 0) {
			em.merge(cuenta);
		}
		else {
			em.persist(cuenta);
		}
	}

	@Transactional
	@Override
	public Cuenta findOne(Long id) {
		return em.find(Cuenta.class, id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

	@Transactional(readOnly = true)
	@Override
	public Cuenta findById(Long id) {
		List<Cuenta> lista = this.findAll();
		Cuenta result = null;
		for(Cuenta cuenta: lista) {
			if(cuenta.getIdCuenta() == id) {
				result = cuenta;
			}
		}
		return result;
	}
	
	

}
