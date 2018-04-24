/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Akari
 */
@Entity
@Table(name = "seccion")
@NamedQueries({
    @NamedQuery(name = "getSecciones", query = "SELECT s FROM Seccion s")})
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseccion")
    private Integer idseccion;
    @Basic(optional = false)
    @Column(name = "seccion")
    private String seccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccion", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Seccion() {
    }

    public Seccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public Seccion(Integer idseccion, String seccion) {
        this.idseccion = idseccion;
        this.seccion = seccion;
    }

    public Integer getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseccion != null ? idseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idseccion == null && other.idseccion != null) || (this.idseccion != null && !this.idseccion.equals(other.idseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Seccion[ idseccion=" + idseccion + " ]";
    }
    
}
