/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import BaseDato.Autor;
import BaseDato.Libro;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author PC04
 */
public class Operaciones {
    Autor autor01 = new Autor (1 ,"","");
    Libro libro01 = new Libro (1,"","");
    public Autor autor(){
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("11/9/1977");
            autor01.setFechaNacimiento(date1);
        } catch (ParseException ex) {}
        autor01.setNombre("Laura");
        autor01.setApellidos("Gallego");
        autor01.setPais("España");
        autor01.setProvincia("valencia");
        autor01.setTelefono("987456123");
        autor01.setCodigoPost("15476");
        autor01.setApartadoCorreos("APARTADO DE CORREOS 76\n" +"46120 ALBORAYA\n" +"VALENCIA – ESPAÑA");
        autor01.setFoto("https://st-listas.20minutos.es/images/2008-09/49560/list_640px.jpg?1222124675");
        return autor01;
    }
    public Libro libro(){
        try {
            Date datelibro1 = new SimpleDateFormat("dd/MM/yyyy").parse("15/4/2018");
            libro01.setAñoEdicion(datelibro1);
        } catch (ParseException ex) {}
        libro01.setDescripcion("El mundo de Axlin está plagado de monstruos. Algunos atacan a los viajeros en los caminos, otros asedian las aldeas hasta que logran arrasarlas por completo y otros entran en las casas por las noches para llevarse a los niños mientras duermen.");
        libro01.setAutor(autor01);
        libro01.setEditorial("Montena");
        libro01.setEnEstock(true);
        libro01.setEncuadernacion("tapa dura");
        libro01.setFoto("https://www.lauragallego.com/wp-content/uploads/2018/05/guardianes-de-la-ciudadela-i-el-bestiario-de-axlin.jpg");
        libro01.setIdioma("Español-Catalán");
        libro01.setIsbn("978-8490439319");
        libro01.setNombre("Guardianes de la Ciudadela I. El bestiario de Axlin");
        libro01.setNºpaginas(512);
        libro01.setPaisDeEdicion("España");
        libro01.setPrecio(new BigDecimal("16.95"));
        return libro01;
    }
}
