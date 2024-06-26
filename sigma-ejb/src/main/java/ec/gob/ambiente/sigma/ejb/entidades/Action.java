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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.actions")
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")})
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acti_id")
    private Integer actiId;
    @Column(name = "acti_order")
    private Integer actiOrder;
    @Size(max = 500)
    @Column(name = "acti_description")
    private String actiDescription;
    @Column(name = "acti_status")
    private Boolean actiStatus;
    @JoinColumn(name = "meas_id", referencedColumnName = "meas_id")
    @ManyToOne(optional = false)
    private Measure measId;
    @OneToMany(mappedBy = "actiId")
    private List<ProjectsActionsCompatibility> projectsActionsCompatibilityList;

    public Action() {
    	actiStatus=true;
    }

    public Action(Integer actiId) {
        this.actiId = actiId;
    }

    public Action(Integer actiId, String actiDescription) {
        this.actiId = actiId;
        this.actiDescription = actiDescription;
    }

    public Integer getActiId() {
        return actiId;
    }

    public void setActiId(Integer actiId) {
        this.actiId = actiId;
    }

    public Integer getActiOrder() {
        return actiOrder;
    }

    public void setActiOrder(Integer actiOrder) {
        this.actiOrder = actiOrder;
    }

    public String getActiDescription() {
        return actiDescription;
    }

    public void setActiDescription(String actiDescription) {
        this.actiDescription = actiDescription;
    }

    public Boolean getActiStatus() {
        return actiStatus;
    }

    public void setActiStatus(Boolean actiStatus) {
        this.actiStatus = actiStatus;
    }

    public Measure getMeasId() {
        return measId;
    }

    public void setMeasId(Measure measId) {
        this.measId = measId;
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
        hash += (actiId != null ? actiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.actiId == null && other.actiId != null) || (this.actiId != null && !this.actiId.equals(other.actiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Action[ actiId=" + actiId + " ]";
    }
    
}
