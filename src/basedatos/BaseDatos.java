/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC04
 */
public class BaseDatos {
    public static void main(String[] args) {
        // Conectar con la base de datos
        Map<String, String> emfProperties = new HashMap<String, String>();
        emfProperties.put("javax.persistence.schema-generation.database.action", "create");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatosPU", emfProperties);
        EntityManager em = emf.createEntityManager();

        // REALIZAR AQUÍ LAS OPERACIONES SOBRE LA BASE DE DATOS
        em.getTransaction().begin();
        Operaciones operaciones = new Operaciones();
        em.persist(operaciones.autor());
        em.persist(operaciones.libro());
        em.getTransaction().commit();
        // Cerrar la conexión con la base de datos
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:Database;shutdown=true"); 
        } catch (SQLException ex) { 
        }
    }
    
}

