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
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_actions_compatibility")
@NamedQueries({
    @NamedQuery(name = "ProjectsActionsCompatibility.findAll", query = "SELECT p FROM ProjectsActionsCompatibility p")})
public class ProjectsActionsCompatibility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paco_id")
    private Integer pacoId;
    @Size(max = 100)
    @Column(name = "paco_other")
    private String pacoOther;
    @Column(name = "paco_status")
    private Boolean pacoStatus;
    @JoinColumn(name = "acti_id", referencedColumnName = "acti_id")
    @ManyToOne
    private Action actiId;
    @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    @ManyToOne
    private Component compId;
    @JoinColumn(name = "meas_id", referencedColumnName = "meas_id")
    @ManyToOne
    private Measure measId;
    @JoinColumn(name = "prac_id", referencedColumnName = "prac_id")
    @ManyToOne(optional = false)
    private ProjectsAction pracId;

    public ProjectsActionsCompatibility() {
    	pacoStatus=true;
    }

    public ProjectsActionsCompatibility(Integer pacoId) {
        this.pacoId = pacoId;
    }

    public Integer getPacoId() {
        return pacoId;
    }

    public void setPacoId(Integer pacoId) {
        this.pacoId = pacoId;
    }

    public String getPacoOther() {
        return pacoOther;
    }

    public void setPacoOther(String pacoOther) {
        this.pacoOther = pacoOther;
    }

    public Boolean getPacoStatus() {
        return pacoStatus;
    }

    public void setPacoStatus(Boolean pacoStatus) {
        this.pacoStatus = pacoStatus;
    }

    public Action getActiId() {
        return actiId;
    }

    public void setActiId(Action actiId) {
        this.actiId = actiId;
    }

    public Component getCompId() {
        return compId;
    }

    public void setCompId(Component compId) {
        this.compId = compId;
    }

    public Measure getMeasId() {
        return measId;
    }

    public void setMeasId(Measure measId) {
        this.measId = measId;
    }

    public ProjectsAction getPracId() {
        return pracId;
    }

    public void setPracId(ProjectsAction pracId) {
        this.pracId = pracId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacoId != null ? pacoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsActionsCompatibility)) {
            return false;
        }
        ProjectsActionsCompatibility other = (ProjectsActionsCompatibility) object;
        if ((this.pacoId == null && other.pacoId != null) || (this.pacoId != null && !this.pacoId.equals(other.pacoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsActionsCompatibility[ pacoId=" + pacoId + " ]";
    }
    
}
