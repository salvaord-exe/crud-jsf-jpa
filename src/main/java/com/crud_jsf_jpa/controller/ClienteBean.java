package com.crud_jsf_jpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.crud_jsf_jpa.dao.ClienteDAO;
import com.crud_jsf_jpa.model.*;

//Define que va a ser el controlador (para JSF)
@ManagedBean(name="clienteBean")
//Indica que se va a usar para manejar peticiones http
@RequestScoped
public class ClienteBean {
	
	private boolean editarCliente;
	
	public ClienteBean(){
		this.editarCliente = false;
	}
	
	//Metodo para listar clientes desde el ClienteDAO
	public List<Cliente> obtenerClientes(){
		List<Cliente> lstClientes = new ArrayList<>();
		
		ClienteDAO cliente = new ClienteDAO();
		
		lstClientes = cliente.obtenerClientes();
		
		/*Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		
		c1.setId(1L);
		c1.setNombres("Salvador Isai");
		c1.setApellidos("Ordonez Loor");
		c1.setEmail("correo@ejemplo.com");
		c1.setTelefono("0978699517");
		
		c2.setId(1L);
		c2.setNombres("Eduardo");
		c2.setApellidos("Llerena");
		c2.setEmail("correo@ejemplo.com");
		c2.setTelefono("0978699777");
		
		lstClientes.add(c1);
		lstClientes.add(c2);*/
	
		return lstClientes;
	}
	
	public String editar(Long id){
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente c = new Cliente();
		c = clienteDAO.buscar(id);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		this.setEditarCliente(true);
		sessionMap.put("cliente", c);
		
		return "/faces/form.xhtml";
	}
	
	public String actualizar(Cliente cliente){
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Date horaActual = new Date();
		
		cliente.setFactualizacion(new java.sql.Date(horaActual.getTime()));
		
		clienteDAO.editar(cliente);
		
		return "/faces/index.xhtml";
	}
	
	public String eliminar(Long id){
		ClienteDAO clienteDAO = new ClienteDAO();
		
		clienteDAO.eliminar(id);
		
		return "/faces/index.xhtml";
	}
	
	public String crear(){
		this.setEditarCliente(false);
		
		Cliente cliente = new Cliente();
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", cliente);
		
		return "/faces/form.xhtml";
	}
	
	public String guardar(Cliente cliente){
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Date fechaActual = new Date();
		
		cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));
		
		clienteDAO.guardar(cliente);
		
		return "/faces/index.xhtml";
	}
	
	public String cancelar(){
		return "/faces/index.xhtml";
	}

	public boolean isEditarCliente() {
		return editarCliente;
	}

	public void setEditarCliente(boolean editarCliente) {
		this.editarCliente = editarCliente;
	}
	
	
	
}
