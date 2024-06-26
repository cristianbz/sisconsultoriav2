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
@Table(name = "sigma.tracings_knowledge_management")
@NamedQueries({
    @NamedQuery(name = "TracingsKnowledgeManagement.findAll", query = "SELECT t FROM TracingsKnowledgeManagement t")})
public class TracingsKnowledgeManagement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tkma_id")
    private Integer tkmaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tkma_value")
    private int tkmaValue;
    @Size(max = 1000)
    @Column(name = "tkma_description")
    private String tkmaDescription;
    @Column(name = "tkma_status")
    private Boolean tkmaStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "trac_id", referencedColumnName = "trac_id")
    @ManyToOne(optional = false)
    private Tracing tracId;

    public TracingsKnowledgeManagement() {
    	tkmaStatus=true;
    }

    public TracingsKnowledgeManagement(Integer tkmaId) {
        this.tkmaId = tkmaId;
    }

    public TracingsKnowledgeManagement(Integer tkmaId, int tkmaValue) {
        this.tkmaId = tkmaId;
        this.tkmaValue = tkmaValue;
    }

    public Integer getTkmaId() {
        return tkmaId;
    }

    public void setTkmaId(Integer tkmaId) {
        this.tkmaId = tkmaId;
    }

    public int getTkmaValue() {
        return tkmaValue;
    }

    public void setTkmaValue(int tkmaValue) {
        this.tkmaValue = tkmaValue;
    }

    public String getTkmaDescription() {
        return tkmaDescription;
    }

    public void setTkmaDescription(String tkmaDescription) {
        this.tkmaDescription = tkmaDescription;
    }

    public Boolean getTkmaStatus() {
        return tkmaStatus;
    }

    public void setTkmaStatus(Boolean tkmaStatus) {
        this.tkmaStatus = tkmaStatus;
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
        hash += (tkmaId != null ? tkmaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TracingsKnowledgeManagement)) {
            return false;
        }
        TracingsKnowledgeManagement other = (TracingsKnowledgeManagement) object;
        if ((this.tkmaId == null && other.tkmaId != null) || (this.tkmaId != null && !this.tkmaId.equals(other.tkmaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.TracingsKnowledgeManagement[ tkmaId=" + tkmaId + " ]";
    }
    
}
