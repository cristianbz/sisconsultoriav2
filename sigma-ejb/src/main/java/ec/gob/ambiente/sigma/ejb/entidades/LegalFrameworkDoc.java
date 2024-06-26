/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.legal_framework_docs")
@NamedQueries({
    @NamedQuery(name = "LegalFrameworkDoc.findAll", query = "SELECT l FROM LegalFrameworkDoc l")})
public class LegalFrameworkDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lfdo_id")
    private Integer lfdoId;
    @Size(max = 200)
    @Column(name = "lfdo_name")
    private String lfdoName;
    @Size(max = 50)
    @Column(name = "lfdo_official_registry_number")
    private String lfdoOfficialRegistryNumber;
    @Column(name = "lfdo_official_registry_date")
    @Temporal(TemporalType.DATE)
    private Date lfdoOfficialRegistryDate;
    @Column(name = "lfdo_status")
    private Boolean lfdoStatus;
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne
    private Document docuId;

    public LegalFrameworkDoc() {
    	lfdoStatus=true;
    }

    public LegalFrameworkDoc(Integer lfdoId) {
        this.lfdoId = lfdoId;
    }

    public LegalFrameworkDoc(Integer lfdoId, String lfdoName) {
        this.lfdoId = lfdoId;
        this.lfdoName = lfdoName;
    }

    public Integer getLfdoId() {
        return lfdoId;
    }

    public void setLfdoId(Integer lfdoId) {
        this.lfdoId = lfdoId;
    }

    public String getLfdoName() {
        return lfdoName;
    }

    public void setLfdoName(String lfdoName) {
        this.lfdoName = lfdoName;
    }

    public String getLfdoOfficialRegistryNumber() {
        return lfdoOfficialRegistryNumber;
    }

    public void setLfdoOfficialRegistryNumber(String lfdoOfficialRegistryNumber) {
        this.lfdoOfficialRegistryNumber = lfdoOfficialRegistryNumber;
    }

    public Date getLfdoOfficialRegistryDate() {
        return lfdoOfficialRegistryDate;
    }

    public void setLfdoOfficialRegistryDate(Date lfdoOfficialRegistryDate) {
        this.lfdoOfficialRegistryDate = lfdoOfficialRegistryDate;
    }

    public Boolean getLfdoStatus() {
        return lfdoStatus;
    }

    public void setLfdoStatus(Boolean lfdoStatus) {
        this.lfdoStatus = lfdoStatus;
    }

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lfdoId != null ? lfdoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LegalFrameworkDoc)) {
            return false;
        }
        LegalFrameworkDoc other = (LegalFrameworkDoc) object;
        if ((this.lfdoId == null && other.lfdoId != null) || (this.lfdoId != null && !this.lfdoId.equals(other.lfdoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.LegalFrameworkDoc[ lfdoId=" + lfdoId + " ]";
    }
    
}
