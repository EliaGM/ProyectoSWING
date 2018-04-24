/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "idioma")
@NamedQueries({
    @NamedQuery(name = "getIdiomas", query = "SELECT i FROM Idioma i")})
public class Idioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ididioma")
    private Integer ididioma;
    @Basic(optional = false)
    @Column(name = "idioma")
    private String idioma;
    @OneToMany(mappedBy = "lengua", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Idioma() {
    }

    public Idioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public Idioma(Integer ididioma, String idioma) {
        this.ididioma = ididioma;
        this.idioma = idioma;
    }

    public Integer getIdidioma() {
        return ididioma;
    }

    public void setIdidioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
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
        hash += (ididioma != null ? ididioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.ididioma == null && other.ididioma != null) || (this.ididioma != null && !this.ididioma.equals(other.ididioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Idioma[ ididioma=" + ididioma + " ]";
    }
    
}
