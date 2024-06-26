/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sigma.projects_financing")
@NamedQueries({
    @NamedQuery(name = "ProjectsFinancing.findAll", query = "SELECT p FROM ProjectsFinancing p")})
public class ProjectsFinancing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prfi_id")
    private Integer prfiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "prfi_type")
    private String prfiType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prfi_amount")
    private BigDecimal prfiAmount;
    @Column(name = "prfi_status")
    private Boolean prfiStatus;
    @JoinColumn(name = "fuso_id", referencedColumnName = "fuso_id")
    @ManyToOne
    private FundingSource fusoId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @JoinColumn(name = "psob_id", referencedColumnName = "psob_id")
    @ManyToOne
    private ProjectsSpecificObjective psobId;

    public ProjectsFinancing() {
    	prfiStatus=true;
    }

    public ProjectsFinancing(Integer prfiId) {
        this.prfiId = prfiId;
    }

    public ProjectsFinancing(Integer prfiId, String prfiType) {
        this.prfiId = prfiId;
        this.prfiType = prfiType;
    }

    public Integer getPrfiId() {
        return prfiId;
    }

    public void setPrfiId(Integer prfiId) {
        this.prfiId = prfiId;
    }

    public String getPrfiType() {
        return prfiType;
    }

    public void setPrfiType(String prfiType) {
        this.prfiType = prfiType;
    }

    public BigDecimal getPrfiAmount() {
        return prfiAmount;
    }

    public void setPrfiAmount(BigDecimal prfiAmount) {
        this.prfiAmount = prfiAmount;
    }

    public Boolean getPrfiStatus() {
        return prfiStatus;
    }

    public void setPrfiStatus(Boolean prfiStatus) {
        this.prfiStatus = prfiStatus;
    }

    public FundingSource getFusoId() {
        return fusoId;
    }

    public void setFusoId(FundingSource fusoId) {
        this.fusoId = fusoId;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }

    public ProjectsSpecificObjective getPsobId() {
        return psobId;
    }

    public void setPsobId(ProjectsSpecificObjective psobId) {
        this.psobId = psobId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prfiId != null ? prfiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsFinancing)) {
            return false;
        }
        ProjectsFinancing other = (ProjectsFinancing) object;
        if ((this.prfiId == null && other.prfiId != null) || (this.prfiId != null && !this.prfiId.equals(other.prfiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsFinancing[ prfiId=" + prfiId + " ]";
    }
    
}
