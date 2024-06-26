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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_financing_agreements")
public class ProjectsFinancingAgreement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pfag_id")
    private Integer pfagId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pfag_status")
    private boolean pfagStatus;
    @JoinColumn(name = "fiag_id", referencedColumnName = "fiag_id")
    @ManyToOne(optional = false)
    private FinancingAgreement fiagId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @Transient
    private boolean seleccionado;

    public ProjectsFinancingAgreement() {
    	pfagStatus=true;
    }

    public ProjectsFinancingAgreement(Integer pfagId) {
        this.pfagId = pfagId;
    }

    public ProjectsFinancingAgreement(Integer pfagId, boolean pragStatus) {
        this.pfagId = pfagId;
        this.pfagStatus = pragStatus;
    }

    

    public Integer getPfagId() {
		return pfagId;
	}

	public void setPfagId(Integer pfagId) {
		this.pfagId = pfagId;
	}

	public boolean isPfagStatus() {
		return pfagStatus;
	}

	public void setPfagStatus(boolean pfagStatus) {
		this.pfagStatus = pfagStatus;
	}

	public FinancingAgreement getFiagId() {
		return fiagId;
	}

	public void setFiagId(FinancingAgreement fiagId) {
		this.fiagId = fiagId;
	}

	public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }
    
    

    public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pfagId != null ? pfagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsFinancingAgreement)) {
            return false;
        }
        ProjectsFinancingAgreement other = (ProjectsFinancingAgreement) object;
        if ((this.pfagId == null && other.pfagId != null) || (this.pfagId != null && !this.pfagId.equals(other.pfagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement[ pragId=" + pfagId + " ]";
    }
    
}
