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
@Table(name = "sigma.tracings_problems")
@NamedQueries({
    @NamedQuery(name = "TracingsProblem.findAll", query = "SELECT t FROM TracingsProblem t")})
public class TracingsProblem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trpr_id")
    private Integer trprId;
    @Size(max = 500)
    @Column(name = "trpr_description")
    private String trprDescription;
    @Size(max = 1000)
    @Column(name = "trpr_solution")
    private String trprSolution;
    @Column(name = "trpr_status")
    private Boolean trprStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "trac_id", referencedColumnName = "trac_id")
    @ManyToOne(optional = false)
    private Tracing tracId;

    public TracingsProblem() {
    	trprStatus=true;
    }

    public TracingsProblem(Integer trprId) {
        this.trprId = trprId;
    }

    public TracingsProblem(Integer trprId, String trprDescription, String trprSolution) {
        this.trprId = trprId;
        this.trprDescription = trprDescription;
        this.trprSolution = trprSolution;
    }

    public Integer getTrprId() {
        return trprId;
    }

    public void setTrprId(Integer trprId) {
        this.trprId = trprId;
    }

    public String getTrprDescription() {
        return trprDescription;
    }

    public void setTrprDescription(String trprDescription) {
        this.trprDescription = trprDescription;
    }

    public String getTrprSolution() {
        return trprSolution;
    }

    public void setTrprSolution(String trprSolution) {
        this.trprSolution = trprSolution;
    }

    public Boolean getTrprStatus() {
        return trprStatus;
    }

    public void setTrprStatus(Boolean trprStatus) {
        this.trprStatus = trprStatus;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Tracing getTracId() {
        return tracId;
    }

    public void setTracId(Tracing tracId) {
        this.tracId = tracId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trprId != null ? trprId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TracingsProblem)) {
            return false;
        }
        TracingsProblem other = (TracingsProblem) object;
        if ((this.trprId == null && other.trprId != null) || (this.trprId != null && !this.trprId.equals(other.trprId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.TracingsProblem[ trprId=" + trprId + " ]";
    }
    
}
