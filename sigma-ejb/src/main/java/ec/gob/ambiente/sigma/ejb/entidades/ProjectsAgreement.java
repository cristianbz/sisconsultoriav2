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

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_agreements")
@NamedQueries({
    @NamedQuery(name = "ProjectsAgreement.findAll", query = "SELECT p FROM ProjectsAgreement p")})
public class ProjectsAgreement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prag_id")
    private Integer pragId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prag_status")
    private boolean pragStatus;
    @JoinColumn(name = "agre_id", referencedColumnName = "agre_id")
    @ManyToOne(optional = false)
    private Agreement agreId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @Transient
    private boolean seleccionado;

    public ProjectsAgreement() {
    	pragStatus=true;
    }

    public ProjectsAgreement(Integer pragId) {
        this.pragId = pragId;
    }

    public ProjectsAgreement(Integer pragId, boolean pragStatus) {
        this.pragId = pragId;
        this.pragStatus = pragStatus;
    }

    public Integer getPragId() {
        return pragId;
    }

    public void setPragId(Integer pragId) {
        this.pragId = pragId;
    }

    public boolean getPragStatus() {
        return pragStatus;
    }

    public void setPragStatus(boolean pragStatus) {
        this.pragStatus = pragStatus;
    }

    public Agreement getAgreId() {
        return agreId;
    }

    public void setAgreId(Agreement agreId) {
        this.agreId = agreId;
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
        hash += (pragId != null ? pragId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsAgreement)) {
            return false;
        }
        ProjectsAgreement other = (ProjectsAgreement) object;
        if ((this.pragId == null && other.pragId != null) || (this.pragId != null && !this.pragId.equals(other.pragId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsAgreement[ pragId=" + pragId + " ]";
    }
    
}
