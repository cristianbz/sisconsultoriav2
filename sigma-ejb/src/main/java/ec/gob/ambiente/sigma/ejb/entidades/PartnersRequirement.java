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

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.partners_requirements")
@NamedQueries({
    @NamedQuery(name = "PartnersRequirements.findAll", query = "SELECT p FROM PartnersRequirement p")})
public class PartnersRequirement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pare_id")
    private Integer pareId;
    @Column(name = "pare_status")
    private Boolean pareStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne
    private Document docuId;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    @ManyToOne(optional = false)
    private Partner partId;

    public PartnersRequirement() {
    	pareStatus=true;
    }

    public PartnersRequirement(Integer pareId) {
        this.pareId = pareId;
    }

    public Integer getPareId() {
        return pareId;
    }

    public void setPareId(Integer pareId) {
        this.pareId = pareId;
    }

    public Boolean getPareStatus() {
        return pareStatus;
    }

    public void setPareStatus(Boolean pareStatus) {
        this.pareStatus = pareStatus;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Document getDocuId() {
        return docuId;
    }

    public void setDocuId(Document docuId) {
        this.docuId = docuId;
    }

    public Partner getPartId() {
        return partId;
    }

    public void setPartId(Partner partId) {
        this.partId = partId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pareId != null ? pareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartnersRequirement)) {
            return false;
        }
        PartnersRequirement other = (PartnersRequirement) object;
        if ((this.pareId == null && other.pareId != null) || (this.pareId != null && !this.pareId.equals(other.pareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.PartnersRequirements[ pareId=" + pareId + " ]";
    }
    
}
