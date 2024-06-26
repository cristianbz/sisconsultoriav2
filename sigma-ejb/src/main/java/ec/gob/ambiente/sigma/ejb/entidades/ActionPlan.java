/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.action_plans")
@NamedQueries({
    @NamedQuery(name = "ActionPlan.findAll", query = "SELECT a FROM ActionPlan a")})
public class ActionPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acpl_id")
    private Integer acplId;
    @Size(max = 100)
    @Column(name = "acpl_name")
    private String acplName;
    @Column(name = "acpl_start_date")
    @Temporal(TemporalType.DATE)
    private Date acplStartDate;
    @Column(name = "acpl_finish_date")
    @Temporal(TemporalType.DATE)
    private Date acplFinishDate;
    @Column(name = "acpl_new_finish_date")
    @Temporal(TemporalType.DATE)
    private Date acplNewFinishDate;
    @Size(max = 500)
    @Column(name = "acpl_general_objective")
    private String acplGeneralObjective;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acpl_iscurrent")
    private boolean acplIscurrent;
    @Column(name = "acpl_status")
    private Boolean acplStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "acpl_creator_user")
    private String acplCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acpl_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acplCreationDate;
    @Size(max = 255)
    @Column(name = "acpl_user_update")
    private String acplUserUpdate;
    @Column(name = "acpl_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acplDateUpdate;
    @Size(max = 1024)
    @Column(name = "acpl_dba_observation")
    private String acplDbaObservation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<Project> projectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<SpecificObjective> specificObjectiveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<Goal> goalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<Component> componentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<Safeguard> safeguardList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acplId")
    private List<LegalFrameworkDoc> legalFrameworkDocList;

    public ActionPlan() {
    	acplStatus=true;
    }

    public ActionPlan(Integer acplId) {
        this.acplId = acplId;
    }

    public ActionPlan(Integer acplId, String acplName, Date acplStartDate, Date acplFinishDate, String acplGeneralObjective, boolean acplIscurrent, String acplCreatorUser, Date acplCreationDate) {
        this.acplId = acplId;
        this.acplName = acplName;
        this.acplStartDate = acplStartDate;
        this.acplFinishDate = acplFinishDate;
        this.acplGeneralObjective = acplGeneralObjective;
        this.acplIscurrent = acplIscurrent;
        this.acplCreatorUser = acplCreatorUser;
        this.acplCreationDate = acplCreationDate;
    }

    public Integer getAcplId() {
        return acplId;
    }

    public void setAcplId(Integer acplId) {
        this.acplId = acplId;
    }

    public String getAcplName() {
        return acplName;
    }

    public void setAcplName(String acplName) {
        this.acplName = acplName;
    }

    public Date getAcplStartDate() {
        return acplStartDate;
    }

    public void setAcplStartDate(Date acplStartDate) {
        this.acplStartDate = acplStartDate;
    }

    public Date getAcplFinishDate() {
        return acplFinishDate;
    }

    public void setAcplFinishDate(Date acplFinishDate) {
        this.acplFinishDate = acplFinishDate;
    }

    public Date getAcplNewFinishDate() {
        return acplNewFinishDate;
    }

    public void setAcplNewFinishDate(Date acplNewFinishDate) {
        this.acplNewFinishDate = acplNewFinishDate;
    }

    public String getAcplGeneralObjective() {
        return acplGeneralObjective;
    }

    public void setAcplGeneralObjective(String acplGeneralObjective) {
        this.acplGeneralObjective = acplGeneralObjective;
    }

    public boolean getAcplIscurrent() {
        return acplIscurrent;
    }

    public void setAcplIscurrent(boolean acplIscurrent) {
        this.acplIscurrent = acplIscurrent;
    }

    public Boolean getAcplStatus() {
        return acplStatus;
    }

    public void setAcplStatus(Boolean acplStatus) {
        this.acplStatus = acplStatus;
    }

    public String getAcplCreatorUser() {
        return acplCreatorUser;
    }

    public void setAcplCreatorUser(String acplCreatorUser) {
        this.acplCreatorUser = acplCreatorUser;
    }

    public Date getAcplCreationDate() {
        return acplCreationDate;
    }

    public void setAcplCreationDate(Date acplCreationDate) {
        this.acplCreationDate = acplCreationDate;
    }

    public String getAcplUserUpdate() {
        return acplUserUpdate;
    }

    public void setAcplUserUpdate(String acplUserUpdate) {
        this.acplUserUpdate = acplUserUpdate;
    }

    public Date getAcplDateUpdate() {
        return acplDateUpdate;
    }

    public void setAcplDateUpdate(Date acplDateUpdate) {
        this.acplDateUpdate = acplDateUpdate;
    }

    public String getAcplDbaObservation() {
        return acplDbaObservation;
    }

    public void setAcplDbaObservation(String acplDbaObservation) {
        this.acplDbaObservation = acplDbaObservation;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<SpecificObjective> getSpecificObjectiveList() {
        return specificObjectiveList;
    }

    public void setSpecificObjectiveList(List<SpecificObjective> specificObjectiveList) {
        this.specificObjectiveList = specificObjectiveList;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    public List<Safeguard> getSafeguardList() {
        return safeguardList;
    }

    public void setSafeguardList(List<Safeguard> safeguardList) {
        this.safeguardList = safeguardList;
    }

    public List<LegalFrameworkDoc> getLegalFrameworkDocList() {
        return legalFrameworkDocList;
    }

    public void setLegalFrameworkDocList(List<LegalFrameworkDoc> legalFrameworkDocList) {
        this.legalFrameworkDocList = legalFrameworkDocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acplId != null ? acplId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionPlan)) {
            return false;
        }
        ActionPlan other = (ActionPlan) object;
        if ((this.acplId == null && other.acplId != null) || (this.acplId != null && !this.acplId.equals(other.acplId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ActionPlan[ acplId=" + acplId + " ]";
    }
    
}
