/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.specific_objectives")
@NamedQueries({
    @NamedQuery(name = "SpecificObjective.findAll", query = "SELECT s FROM SpecificObjective s")})
public class SpecificObjective implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "spob_id")
    private Integer spobId;
    @Column(name = "spob_order")
    private Integer spobOrder;
    @Size(max = 500)
    @Column(name = "spob_description")
    private String spobDescription;
    @Column(name = "spob_status")
    private Boolean spobStatus;
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;

    public SpecificObjective() {
    	spobStatus=true;
    }

    public SpecificObjective(Integer spobId) {
        this.spobId = spobId;
    }

    public SpecificObjective(Integer spobId, String spobDescription) {
        this.spobId = spobId;
        this.spobDescription = spobDescription;
    }

    public Integer getSpobId() {
        return spobId;
    }

    public void setSpobId(Integer spobId) {
        this.spobId = spobId;
    }

    public Integer getSpobOrder() {
        return spobOrder;
    }

    public void setSpobOrder(Integer spobOrder) {
        this.spobOrder = spobOrder;
    }

    public String getSpobDescription() {
        return spobDescription;
    }

    public void setSpobDescription(String spobDescription) {
        this.spobDescription = spobDescription;
    }

    public Boolean getSpobStatus() {
        return spobStatus;
    }

    public void setSpobStatus(Boolean spobStatus) {
        this.spobStatus = spobStatus;
    }

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spobId != null ? spobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificObjective)) {
            return false;
        }
        SpecificObjective other = (SpecificObjective) object;
        if ((this.spobId == null && other.spobId != null) || (this.spobId != null && !this.spobId.equals(other.spobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.SpecificObjective[ spobId=" + spobId + " ]";
    }
    
}
