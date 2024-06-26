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
@Table(name = "sigma.components")
@NamedQueries({
    @NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")})
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comp_id")
    private Integer compId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "comp_code")
    private String compCode;
    @Size(max = 100)
    @Column(name = "comp_name")
    private String compName;
    @Size(max = 500)
    @Column(name = "comp_objective")
    private String compObjective;
    @Column(name = "comp_status")
    private Boolean compStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compId")
    private List<Measure> measureList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compId")
    private List<Cobenefit> cobenefitList;
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @OneToMany(mappedBy = "compId")
    private List<ProjectsActionsCompatibility> projectsActionsCompatibilityList;
    

    public Component() {
    	compStatus=true;
    }

    public Component(Integer compId) {
        this.compId = compId;
    }

    public Component(Integer compId, String compCode, String compName, String compObjective) {
        this.compId = compId;
        this.compCode = compCode;
        this.compName = compName;
        this.compObjective = compObjective;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompObjective() {
        return compObjective;
    }

    public void setCompObjective(String compObjective) {
        this.compObjective = compObjective;
    }

    public Boolean getCompStatus() {
        return compStatus;
    }

    public void setCompStatus(Boolean compStatus) {
        this.compStatus = compStatus;
    }

    public List<Measure> getMeasureList() {
        return measureList;
    }

    public void setMeasureList(List<Measure> measureList) {
        this.measureList = measureList;
    }

    public List<Cobenefit> getCobenefitList() {
        return cobenefitList;
    }

    public void setCobenefitList(List<Cobenefit> cobenefitList) {
        this.cobenefitList = cobenefitList;
    }

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
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
        hash += (compId != null ? compId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Component)) {
            return false;
        }
        Component other = (Component) object;
        if ((this.compId == null && other.compId != null) || (this.compId != null && !this.compId.equals(other.compId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Component[ compId=" + compId + " ]";
    }
    
}
