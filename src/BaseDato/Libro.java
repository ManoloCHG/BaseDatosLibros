/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC04
 */
@Entity
@Table(name = "LIBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findById", query = "SELECT l FROM Libro l WHERE l.id = :id")
    , @NamedQuery(name = "Libro.findByNombre", query = "SELECT l FROM Libro l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Libro.findByN\u00bapaginas", query = "SELECT l FROM Libro l WHERE l.n\u00bapaginas = :n\u00bapaginas")
    , @NamedQuery(name = "Libro.findByEditorial", query = "SELECT l FROM Libro l WHERE l.editorial = :editorial")
    , @NamedQuery(name = "Libro.findByIdioma", query = "SELECT l FROM Libro l WHERE l.idioma = :idioma")
    , @NamedQuery(name = "Libro.findByEncuadernacion", query = "SELECT l FROM Libro l WHERE l.encuadernacion = :encuadernacion")
    , @NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn")
    , @NamedQuery(name = "Libro.findByA\u00f1oEdicion", query = "SELECT l FROM Libro l WHERE l.a\u00f1oEdicion = :a\u00f1oEdicion")
    , @NamedQuery(name = "Libro.findByPaisDeEdicion", query = "SELECT l FROM Libro l WHERE l.paisDeEdicion = :paisDeEdicion")
    , @NamedQuery(name = "Libro.findByPrecio", query = "SELECT l FROM Libro l WHERE l.precio = :precio")
    , @NamedQuery(name = "Libro.findByEnEstock", query = "SELECT l FROM Libro l WHERE l.enEstock = :enEstock")
    , @NamedQuery(name = "Libro.findByFoto", query = "SELECT l FROM Libro l WHERE l.foto = :foto")
    , @NamedQuery(name = "Libro.findByDescripcion", query = "SELECT l FROM Libro l WHERE l.descripcion = :descripcion")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "N\u00baPAGINAS")
    private Integer nºpaginas;
    @Column(name = "EDITORIAL")
    private String editorial;
    @Column(name = "IDIOMA")
    private String idioma;
    @Column(name = "ENCUADERNACION")
    private String encuadernacion;
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "A\u00d1O_EDICION")
    @Temporal(TemporalType.DATE)
    private Date añoEdicion;
    @Column(name = "PAIS_DE_EDICION")
    private String paisDeEdicion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "EN_ESTOCK")
    private Boolean enEstock;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "AUTOR", referencedColumnName = "ID")
    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public Libro(Integer id) {
        this.id = id;
    }

    public Libro(Integer id, String nombre, String isbn) {
        this.id = id;
        this.nombre = nombre;
        this.isbn = isbn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNºpaginas() {
        return nºpaginas;
    }

    public void setNºpaginas(Integer nºpaginas) {
        this.nºpaginas = nºpaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEncuadernacion() {
        return encuadernacion;
    }

    public void setEncuadernacion(String encuadernacion) {
        this.encuadernacion = encuadernacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getAñoEdicion() {
        return añoEdicion;
    }

    public void setAñoEdicion(Date añoEdicion) {
        this.añoEdicion = añoEdicion;
    }

    public String getPaisDeEdicion() {
        return paisDeEdicion;
    }

    public void setPaisDeEdicion(String paisDeEdicion) {
        this.paisDeEdicion = paisDeEdicion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getEnEstock() {
        return enEstock;
    }

    public void setEnEstock(Boolean enEstock) {
        this.enEstock = enEstock;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaseDato.Libro[ id=" + id + " ]";
    }
    
}
