package com.crud_jsf_jpa.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	//Defino el nombre de la persistencia que está creado en META-INF/persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	private static EntityManagerFactory factory;

	//Esto sirve para la conexion a la base de datos
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
	
	//Esto debe servir para cerrar la base de datos
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}		
	}

}