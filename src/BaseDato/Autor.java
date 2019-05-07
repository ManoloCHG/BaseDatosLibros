/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC04
 */
@Entity
@Table(name = "AUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
    , @NamedQuery(name = "Autor.findById", query = "SELECT a FROM Autor a WHERE a.id = :id")
    , @NamedQuery(name = "Autor.findByNombre", query = "SELECT a FROM Autor a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Autor.findByApellidos", query = "SELECT a FROM Autor a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Autor.findByTelefono", query = "SELECT a FROM Autor a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Autor.findByPais", query = "SELECT a FROM Autor a WHERE a.pais = :pais")
    , @NamedQuery(name = "Autor.findByProvincia", query = "SELECT a FROM Autor a WHERE a.provincia = :provincia")
    , @NamedQuery(name = "Autor.findByLocalidad", query = "SELECT a FROM Autor a WHERE a.localidad = :localidad")
    , @NamedQuery(name = "Autor.findByApartadoCorreos", query = "SELECT a FROM Autor a WHERE a.apartadoCorreos = :apartadoCorreos")
    , @NamedQuery(name = "Autor.findByCodigoPost", query = "SELECT a FROM Autor a WHERE a.codigoPost = :codigoPost")
    , @NamedQuery(name = "Autor.findByFechaNacimiento", query = "SELECT a FROM Autor a WHERE a.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Autor.findByFoto", query = "SELECT a FROM Autor a WHERE a.foto = :foto")})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Column(name = "APARTADO_CORREOS")
    private String apartadoCorreos;
    @Column(name = "CODIGO_POST")
    private String codigoPost;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "FOTO")
    private String foto;
    @OneToMany(mappedBy = "autor")
    private Collection<Libro> libroCollection;

    public Autor() {
    }

    public Autor(Integer id) {
        this.id = id;
    }

    public Autor(Integer id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getApartadoCorreos() {
        return apartadoCorreos;
    }

    public void setApartadoCorreos(String apartadoCorreos) {
        this.apartadoCorreos = apartadoCorreos;
    }

    public String getCodigoPost() {
        return codigoPost;
    }

    public void setCodigoPost(String codigoPost) {
        this.codigoPost = codigoPost;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Libro> getLibroCollection() {
        return libroCollection;
    }

    public void setLibroCollection(Collection<Libro> libroCollection) {
        this.libroCollection = libroCollection;
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
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaseDato.Autor[ id=" + id + " ]";
    }
    
}
