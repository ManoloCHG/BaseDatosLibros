/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import BaseDato.Libro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author PC04
 */
public class Consultas {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatosPU");
            EntityManager em = emf.createEntityManager();
            
            Query querylibrso = em.createNamedQuery("Libro.findAll");
            List<Libro> listLibro = querylibrso.getResultList();
                    
            for(int i=0; i<listLibro.size(); i++) {
                Libro libro = listLibro.get(i);
                System.out.println(libro.getNombre());
            }
    }
    
}
