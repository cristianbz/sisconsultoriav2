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
@Table(name = "sigma.projects_specific_objectives")
@NamedQueries({
    @NamedQuery(name = "ProjectsSpecificObjective.findAll", query = "SELECT p FROM ProjectsSpecificObjective p")})
public class ProjectsSpecificObjective implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "psob_id")
    private Integer psobId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "psob_sequence")
    private int psobSequence;
    @Size(max = 20)
    @Column(name = "psob_code")
    private String psobCode;
    @Size(max = 500)
    @Column(name = "psob_description")
    private String psobDescription;
    @Size(max = 2)
    @Column(name = "psob_type")
    private String psobType;
    @Column(name = "psob_status")
    private Boolean psobStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @JoinColumn(name = "gelo_id", referencedColumnName = "gelo_id")
    @ManyToOne
    private GeographicalLocations geloId;
    @JoinColumn(name = "psob_parent_id", referencedColumnName = "psob_id")
    @ManyToOne
    private ProjectsSpecificObjective psobParentId;

    public ProjectsSpecificObjective() {
    	psobStatus=true;
    }

    public ProjectsSpecificObjective(Integer psobId) {
        this.psobId = psobId;
    }

    public ProjectsSpecificObjective(Integer psobId, int psobSequence, String psobDescription) {
        this.psobId = psobId;
        this.psobSequence = psobSequence;
        this.psobDescription = psobDescription;
    }

    public Integer getPsobId() {
        return psobId;
    }

    public void setPsobId(Integer psobId) {
        this.psobId = psobId;
    }

    public int getPsobSequence() {
        return psobSequence;
    }

    public void setPsobSequence(int psobSequence) {
        this.psobSequence = psobSequence;
    }

    public String getPsobDescription() {
        return psobDescription;
    }

    public void setPsobDescription(String psobDescription) {
        this.psobDescription = psobDescription;
    }

    public Boolean getPsobStatus() {
        return psobStatus;
    }

    public void setPsobStatus(Boolean psobStatus) {
        this.psobStatus = psobStatus;
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

    

    public GeographicalLocations getGeloId() {
		return geloId;
	}

	public void setGeloId(GeographicalLocations geloId) {
		this.geloId = geloId;
	}
	
	

	public ProjectsSpecificObjective getPsobParentId() {
		return psobParentId;
	}

	public void setPsobParentId(ProjectsSpecificObjective psobParentId) {
		this.psobParentId = psobParentId;
	}
	
	

	public String getPsobCode() {
		return psobCode;
	}

	public void setPsobCode(String psobCode) {
		this.psobCode = psobCode;
	}

	public String getPsobType() {
		return psobType;
	}

	public void setPsobType(String psobType) {
		this.psobType = psobType;
	}
	
	public String obtenerTextoObj(){
		String res="";
		if(geloId!=null){
			res=geloId.getGeloName()+" "+psobCode+" "+psobDescription;
		}else{
			res="Nacional - "+psobCode+" "+psobDescription;
		}
		return res;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (psobId != null ? psobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsSpecificObjective)) {
            return false;
        }
        ProjectsSpecificObjective other = (ProjectsSpecificObjective) object;
        if ((this.psobId == null && other.psobId != null) || (this.psobId != null && !this.psobId.equals(other.psobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective[ psobId=" + psobId + " ]";
    }
    
}
