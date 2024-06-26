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
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_strategic_partners")
@NamedQueries({
    @NamedQuery(name = "ProjectsStrategicPartner.findAll", query = "SELECT p FROM ProjectsStrategicPartner p")})
public class ProjectsStrategicPartner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pspa_id")
    private Integer pspaId;
    @Size(max = 1)
    @Column(name = "pspa_type")
    private String pspaType;
    @Column(name = "pspa_has_project")
    private Boolean pspaHasProject;
    @Column(name = "pspa_status")
    private Boolean pspaStatus;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    @ManyToOne(optional = false)
    private Partner partId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @Transient
    private boolean seleccionado;

    public ProjectsStrategicPartner() {
    	pspaStatus=true;
    }

    public ProjectsStrategicPartner(Integer pspaId) {
        this.pspaId = pspaId;
    }

    public Integer getPspaId() {
        return pspaId;
    }

    public void setPspaId(Integer pspaId) {
        this.pspaId = pspaId;
    }

    public Boolean getPspaStatus() {
        return pspaStatus;
    }

    public void setPspaStatus(Boolean pspaStatus) {
        this.pspaStatus = pspaStatus;
    }

    public Partner getPartId() {
        return partId;
    }

    public void setPartId(Partner partId) {
        this.partId = partId;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }
    
    
    

    public String getPspaType() {
		return pspaType;
	}

	public void setPspaType(String pspaType) {
		this.pspaType = pspaType;
	}

	public Boolean getPspaHasProject() {
		return pspaHasProject;
	}

	public void setPspaHasProject(Boolean pspaHasProject) {
		this.pspaHasProject = pspaHasProject;
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
        hash += (pspaId != null ? pspaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsStrategicPartner)) {
            return false;
        }
        ProjectsStrategicPartner other = (ProjectsStrategicPartner) object;
        if ((this.pspaId == null && other.pspaId != null) || (this.pspaId != null && !this.pspaId.equals(other.pspaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner[ pspaId=" + pspaId + " ]";
    }
    
}
