package com.bancoDDLS.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDDLS.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Transactional
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findById(Long id) {
		List<Cliente> lista = this.findAll();
		Cliente result = null;
		for(Cliente cliente: lista) {
			if(cliente.getIdCliente() == id) {
				result = cliente;
			}
		}
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findByTelefono(String telefono) {
		List<Cliente> listaClientes = this.findAll();
		List<Cliente> result = new ArrayList<Cliente>();
		for(Cliente cliente: listaClientes) {
			if(cliente.getNumeroTelefonico().equals(telefono)) {
				result.add(cliente);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		if(cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {
			em.merge(cliente);
		}
		else {
			em.persist(cliente);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
