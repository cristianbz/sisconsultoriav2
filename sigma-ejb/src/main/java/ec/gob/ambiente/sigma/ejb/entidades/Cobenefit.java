/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.cobenefits")
@NamedQueries({
    @NamedQuery(name = "Cobenefit.findAll", query = "SELECT c FROM Cobenefit c")})
public class Cobenefit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cobe_id")
    private Integer cobeId;
    @Column(name = "cobe_order")
    private Integer cobeOrder;
    @Size(max = 250)
    @Column(name = "cobe_description")
    private String cobeDescription;
    @Column(name = "cobe_status")
    private Boolean cobeStatus;
    @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    @ManyToOne(optional = false)
    private Component compId;
   

    public Cobenefit() {
    	cobeStatus=true;
    }

    public Cobenefit(Integer cobeId) {
        this.cobeId = cobeId;
    }

    public Cobenefit(Integer cobeId, String cobeDescription) {
        this.cobeId = cobeId;
        this.cobeDescription = cobeDescription;
    }

    public Integer getCobeId() {
        return cobeId;
    }

    public void setCobeId(Integer cobeId) {
        this.cobeId = cobeId;
    }

    public Integer getCobeOrder() {
        return cobeOrder;
    }

    public void setCobeOrder(Integer cobeOrder) {
        this.cobeOrder = cobeOrder;
    }

    public String getCobeDescription() {
        return cobeDescription;
    }

    public void setCobeDescription(String cobeDescription) {
        this.cobeDescription = cobeDescription;
    }

    public Boolean getCobeStatus() {
        return cobeStatus;
    }

    public void setCobeStatus(Boolean cobeStatus) {
        this.cobeStatus = cobeStatus;
    }

    public Component getCompId() {
        return compId;
    }

    public void setCompId(Component compId) {
        this.compId = compId;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cobeId != null ? cobeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cobenefit)) {
            return false;
        }
        Cobenefit other = (Cobenefit) object;
        if ((this.cobeId == null && other.cobeId != null) || (this.cobeId != null && !this.cobeId.equals(other.cobeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Cobenefit[ cobeId=" + cobeId + " ]";
    }
    
}
