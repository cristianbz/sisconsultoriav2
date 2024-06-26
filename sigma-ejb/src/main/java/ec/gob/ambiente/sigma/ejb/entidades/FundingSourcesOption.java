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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.funding_sources_options")
@NamedQueries({
    @NamedQuery(name = "FundingSourcesOption.findAll", query = "SELECT f FROM FundingSourcesOption f")})
public class FundingSourcesOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fsop_id")
    private Integer fsopId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "fsop_type")
    private String fsopType;
    @Column(name = "fsop_status")
    private Boolean fsopStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "fuso_id", referencedColumnName = "fuso_id")
    @ManyToOne(optional = false)
    private FundingSource fusoId;
    @Transient
    private Boolean selected;

    public FundingSourcesOption() {
    	fsopStatus=true;
    }

    public FundingSourcesOption(Integer fsopId) {
        this.fsopId = fsopId;
    }

    public FundingSourcesOption(Integer fsopId, String fsopType) {
        this.fsopId = fsopId;
        this.fsopType = fsopType;
    }

    public Integer getFsopId() {
        return fsopId;
    }

    public void setFsopId(Integer fsopId) {
        this.fsopId = fsopId;
    }

    public String getFsopType() {
        return fsopType;
    }

    public void setFsopType(String fsopType) {
        this.fsopType = fsopType;
    }

    public Boolean getFsopStatus() {
        return fsopStatus;
    }

    public void setFsopStatus(Boolean fsopStatus) {
        this.fsopStatus = fsopStatus;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public FundingSource getFusoId() {
        return fusoId;
    }

    public void setFusoId(FundingSource fusoId) {
        this.fusoId = fusoId;
    }
    
    

    public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (fsopId != null ? fsopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FundingSourcesOption)) {
            return false;
        }
        FundingSourcesOption other = (FundingSourcesOption) object;
        if ((this.fsopId == null && other.fsopId != null) || (this.fsopId != null && !this.fsopId.equals(other.fsopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesOption[ fsopId=" + fsopId + " ]";
    }
    
}
