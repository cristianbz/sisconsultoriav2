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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_actions")
@NamedQueries({
    @NamedQuery(name = "ProjectsAction.findAll", query = "SELECT p FROM ProjectsAction p")})
public class ProjectsAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prac_id")
    private Integer pracId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "prac_type")
    private String pracType;
    @Size( max =20)
    @Column(name = "prac_code")
    private String pracCode;
    @Size(max = 500)
    @Column(name = "prac_description")
    private String pracDescription;
    @Size(max = 500)
    @Column(name = "prac_goal")
    private String pracGoal;
    @Column(name = "prac_status")
    private Boolean pracStatus;
    @Size(max = 500)
    @Column(name = "prac_indicator")
    private String pracIndicator;
    @Size(max = 1000)
    @Column(name = "prac_assumptions")
    private String pracAssumptions;
    @Size(max = 1000)
    @Column(name = "prac_checkers")
    private String pracCheckers;
    @Column(name = "prac_goal_value")
    private Double pracGoalValue;
    @Column(name = "prac_weighing")
    private Double pracWeighing;
    @JoinColumn(name = "prac_parent_id", referencedColumnName = "prac_id")
    @ManyToOne
    private ProjectsAction pracParentId;
    @JoinColumn(name = "psob_id", referencedColumnName = "psob_id")
    @ManyToOne
    private ProjectsSpecificObjective psobId;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    @ManyToOne
    private Partner partId;
    @Transient
    private List<String[]> listaCompatibilidadPA;
    @Transient
    private List<String[]> listaCompatibilidadIndicadoresPA;
    

    public ProjectsAction() {
    	pracStatus=true;
    }

    public ProjectsAction(Integer pracId) {
        this.pracId = pracId;
    }

    public ProjectsAction(Integer pracId, String pracType, String pracDescription) {
        this.pracId = pracId;
        this.pracType = pracType;
        this.pracDescription = pracDescription;
    }

    public Integer getPracId() {
        return pracId;
    }

    public void setPracId(Integer pracId) {
        this.pracId = pracId;
    }

   

    public String getPracType() {
        return pracType;
    }

    public void setPracType(String pracType) {
        this.pracType = pracType;
    }

    public String getPracDescription() {
        return pracDescription;
    }

    public void setPracDescription(String pracDescription) {
        this.pracDescription = pracDescription;
    }

    public String getPracGoal() {
        return pracGoal;
    }

    public void setPracGoal(String pracGoal) {
        this.pracGoal = pracGoal;
    }

    public Boolean getPracStatus() {
        return pracStatus;
    }

    public void setPracStatus(Boolean pracStatus) {
        this.pracStatus = pracStatus;
    }


    public ProjectsSpecificObjective getPsobId() {
        return psobId;
    }

    public void setPsobId(ProjectsSpecificObjective psobId) {
        this.psobId = psobId;
    }
    
    
    
    

    public String getPracIndicator() {
		return pracIndicator;
	}

	

	public void setPracIndicator(String pracIndicator) {
		this.pracIndicator = pracIndicator;
	}

	public ProjectsAction getPracParentId() {
		return pracParentId;
	}

	public void setPracParentId(ProjectsAction pracParentId) {
		this.pracParentId = pracParentId;
	}
	
	

	public Double getPracGoalValue() {
		return pracGoalValue;
	}

	public void setPracGoalValue(Double pracGoalValue) {
		this.pracGoalValue = pracGoalValue;
	}

	public Double getPracWeighing() {
		return pracWeighing;
	}

	public void setPracWeighing(Double pracWeighing) {
		this.pracWeighing = pracWeighing;
	}
	

	public List<String[]> getListaCompatibilidadPA() {
		return listaCompatibilidadPA;
	}

	public void setListaCompatibilidadPA(List<String[]> listaCompatibilidadPA) {
		this.listaCompatibilidadPA = listaCompatibilidadPA;
	}
	
	

	public List<String[]> getListaCompatibilidadIndicadoresPA() {
		return listaCompatibilidadIndicadoresPA;
	}

	public void setListaCompatibilidadIndicadoresPA(List<String[]> listaCompatibilidadIndicadoresPA) {
		this.listaCompatibilidadIndicadoresPA = listaCompatibilidadIndicadoresPA;
	}
	
	

	public String getPracCode() {
		return pracCode;
	}

	public void setPracCode(String pracCode) {
		this.pracCode = pracCode;
	}

	public Partner getPartId() {
		return partId;
	}

	public void setPartId(Partner partId) {
		this.partId = partId;
	}
	
	

	public String getPracAssumptions() {
		return pracAssumptions;
	}

	public void setPracAssumptions(String pracAssumptions) {
		this.pracAssumptions = pracAssumptions;
	}

	public String getPracCheckers() {
		return pracCheckers;
	}

	public void setPracCheckers(String pracCheckers) {
		this.pracCheckers = pracCheckers;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pracId != null ? pracId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsAction)) {
            return false;
        }
        ProjectsAction other = (ProjectsAction) object;
        if ((this.pracId == null && other.pracId != null) || (this.pracId != null && !this.pracId.equals(other.pracId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsAction[ pracId=" + pracId + " ]";
    }
    
}
