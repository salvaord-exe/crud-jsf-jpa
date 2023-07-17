package com.crud_jsf_jpa.dao;

import com.crud_jsf_jpa.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteDAO {
	
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	//Metodo para guardar (persistir) cliente
	public void guardar(Cliente cliente){
		entity.getTransaction().begin();
		entity.persist(cliente);
		entity.getTransaction().commit();
//		entity.close();
	}
	
	//Metodo para actualizar cliente
	public void editar(Cliente cliente){
		entity.getTransaction().begin();
		entity.merge(cliente);
		entity.getTransaction().commit();
		//entity.close();
	}
	
	//Metodo para buscar cliente
	public Cliente buscar(Long id){
		Cliente c = new Cliente();
		c = entity.find(Cliente.class, id);
		//entity.close();
		return c;
	}
	
	//Obtener todos los clientes
	public List<Cliente> obtenerClientes(){
		
		
		
		List<Cliente> lstClientes = new ArrayList<>();
		Query q = entity.createQuery("SELECT c FROM Cliente c");
		lstClientes = q.getResultList();
		
		return lstClientes;
	}
	
	public void eliminar(Long id){
		Cliente c = new Cliente();
		c = entity.find(Cliente.class, id);
		entity.getTransaction().begin();
		entity.remove(c);
		entity.getTransaction().commit();
	}
	
}
