/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_risks")
@NamedQueries({
    @NamedQuery(name = "ProjectsRisk.findAll", query = "SELECT p FROM ProjectsRisk p")})
public class ProjectsRisk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prri_id")
    private Integer prriId;
    @Size(max = 1000)
    @Column(name = "prri_description")
    private String prriDescription;
    @Size(max = 1000)
    @Column(name = "prri_mitigation_measure")
    private String prriMitigationMeasure;
    @Size(max = 500)
    @Column(name = "prri_compliance_indicator")
    private String prriComplianceIndicator;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prri_status")
    private boolean prriStatus;
   
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @Transient
    private List<String> listaSalvaguardas;

    public ProjectsRisk() {
    	prriStatus=true;
    }

    public ProjectsRisk(Integer prriId) {
        this.prriId = prriId;
    }

    public ProjectsRisk(Integer prriId, String prriDescription, String prriMitigationMeasure, String prriComplianceIndicator, boolean prriStatus) {
        this.prriId = prriId;
        this.prriDescription = prriDescription;
        this.prriMitigationMeasure = prriMitigationMeasure;
        this.prriComplianceIndicator = prriComplianceIndicator;
        this.prriStatus = prriStatus;
    }

    public Integer getPrriId() {
        return prriId;
    }

    public void setPrriId(Integer prriId) {
        this.prriId = prriId;
    }

    public String getPrriDescription() {
        return prriDescription;
    }

    public void setPrriDescription(String prriDescription) {
        this.prriDescription = prriDescription;
    }

    public String getPrriMitigationMeasure() {
        return prriMitigationMeasure;
    }

    public void setPrriMitigationMeasure(String prriMitigationMeasure) {
        this.prriMitigationMeasure = prriMitigationMeasure;
    }

    public String getPrriComplianceIndicator() {
        return prriComplianceIndicator;
    }

    public void setPrriComplianceIndicator(String prriComplianceIndicator) {
        this.prriComplianceIndicator = prriComplianceIndicator;
    }

    public boolean getPrriStatus() {
        return prriStatus;
    }

    public void setPrriStatus(boolean prriStatus) {
        this.prriStatus = prriStatus;
    }


    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }
    
    

    public List<String> getListaSalvaguardas() {
		return listaSalvaguardas;
	}

	public void setListaSalvaguardas(List<String> listaSalvaguardas) {
		this.listaSalvaguardas = listaSalvaguardas;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (prriId != null ? prriId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsRisk)) {
            return false;
        }
        ProjectsRisk other = (ProjectsRisk) object;
        if ((this.prriId == null && other.prriId != null) || (this.prriId != null && !this.prriId.equals(other.prriId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsRisk[ prriId=" + prriId + " ]";
    }
    
}
