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
@Table(name = "sigma.measures")
@NamedQueries({
    @NamedQuery(name = "Measure.findAll", query = "SELECT m FROM Measure m")})
public class Measure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meas_id")
    private Integer measId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "meas_type")
    private String measType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "meas_code")
    private String measCode;
    @Size(max = 500)
    @Column(name = "meas_description")
    private String measDescription;
    @Size(max = 500)
    @Column(name = "meas_goal")
    private String measGoal;
    @Column(name = "meas_status")
    private Boolean measStatus;
    @Column(name = "meas_goal_value")
    private Double measGoalValue;
    @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    @ManyToOne(optional = false)
    private Component compId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measId")
    private List<Action> actionList;
    @OneToMany(mappedBy = "measId")
    private List<ProjectsActionsCompatibility> projectsActionsCompatibilityList;

    public Measure() {
    	measStatus=true;
    }

    public Measure(Integer measId) {
        this.measId = measId;
    }

    public Measure(Integer measId, String measCode, String measDescription) {
        this.measId = measId;
        this.measCode = measCode;
        this.measDescription = measDescription;
    }

    public Integer getMeasId() {
        return measId;
    }

    public void setMeasId(Integer measId) {
        this.measId = measId;
    }

    public String getMeasCode() {
        return measCode;
    }

    public void setMeasCode(String measCode) {
        this.measCode = measCode;
    }

    public String getMeasDescription() {
        return measDescription;
    }

    public void setMeasDescription(String measDescription) {
        this.measDescription = measDescription;
    }

    public Boolean getMeasStatus() {
        return measStatus;
    }

    public void setMeasStatus(Boolean measStatus) {
        this.measStatus = measStatus;
    }

    public Component getCompId() {
        return compId;
    }

    public void setCompId(Component compId) {
        this.compId = compId;
    }

    public List<Action> getActionList() {
        return actionList;
    }
    
    

    public Double getMeasGoalValue() {
		return measGoalValue;
	}

	public void setMeasGoalValue(Double measGoalValue) {
		this.measGoalValue = measGoalValue;
	}

	public String getMeasType() {
		return measType;
	}

	public void setMeasType(String measType) {
		this.measType = measType;
	}

	public String getMeasGoal() {
		return measGoal;
	}

	public void setMeasGoal(String measGoal) {
		this.measGoal = measGoal;
	}

	public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public List<ProjectsActionsCompatibility> getProjectsActionsCompatibilityList() {
        return projectsActionsCompatibilityList;
    }

    public void setProjectsActionsCompatibilityList(List<ProjectsActionsCompatibility> projectsActionsCompatibilityList) {
        this.projectsActionsCompatibilityList = projectsActionsCompatibilityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measId != null ? measId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measure)) {
            return false;
        }
        Measure other = (Measure) object;
        if ((this.measId == null && other.measId != null) || (this.measId != null && !this.measId.equals(other.measId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Measure[ measId=" + measId + " ]";
    }
    
}
