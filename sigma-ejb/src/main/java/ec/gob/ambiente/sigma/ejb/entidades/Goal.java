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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.goals")
@NamedQueries({
    @NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")})
public class Goal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "goal_id")
    private Integer goalId;
    @Column(name = "goal_order")
    private Integer goalOrder;
    @Size( max = 500)
    @Column(name = "goal_description")
    private String goalDescription;
    @Size(max = 200)
    @Column(name = "goal_delete_comment")
    private String goalDeleteComment;
    @Column(name = "goal_status")
    private Boolean goalStatus;
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;

    public Goal() {
    	goalStatus=true;
    }

    public Goal(Integer goalId) {
        this.goalId = goalId;
    }

    public Goal(Integer goalId, String goalDescription) {
        this.goalId = goalId;
        this.goalDescription = goalDescription;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public Integer getGoalOrder() {
        return goalOrder;
    }

    public void setGoalOrder(Integer goalOrder) {
        this.goalOrder = goalOrder;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public String getGoalDeleteComment() {
        return goalDeleteComment;
    }

    public void setGoalDeleteComment(String goalDeleteComment) {
        this.goalDeleteComment = goalDeleteComment;
    }

    public Boolean getGoalStatus() {
        return goalStatus;
    }

    public void setGoalStatus(Boolean goalStatus) {
        this.goalStatus = goalStatus;
    }

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (goalId != null ? goalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goal)) {
            return false;
        }
        Goal other = (Goal) object;
        if ((this.goalId == null && other.goalId != null) || (this.goalId != null && !this.goalId.equals(other.goalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Goal[ goalId=" + goalId + " ]";
    }
    
}
