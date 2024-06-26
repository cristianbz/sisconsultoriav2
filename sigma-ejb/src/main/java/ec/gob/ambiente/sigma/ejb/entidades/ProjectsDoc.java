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
@Table(name = "sigma.projects_docs")
@NamedQueries({
    @NamedQuery(name = "ProjectsDoc.findAll", query = "SELECT p FROM ProjectsDoc p")})
public class ProjectsDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prdo_id")
    private Integer prdoId;
    @Size(max = 20)
    @Column(name = "prdo_other_type")
    private String prdoOtherType;
    @Column(name = "prdo_status")
    private Boolean prdoStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne(optional = false)
    private Document docuId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;

    public ProjectsDoc() {
    	prdoStatus=true;
    }

    public ProjectsDoc(Integer prdoId) {
        this.prdoId = prdoId;
    }

    public Integer getPrdoId() {
        return prdoId;
    }

    public void setPrdoId(Integer prdoId) {
        this.prdoId = prdoId;
    }

    public String getPrdoOtherType() {
        return prdoOtherType;
    }

    public void setPrdoOtherType(String prdoOtherType) {
        this.prdoOtherType = prdoOtherType;
    }

    public Boolean getPrdoStatus() {
        return prdoStatus;
    }

    public void setPrdoStatus(Boolean prdoStatus) {
        this.prdoStatus = prdoStatus;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Document getDocuId() {
        return docuId;
    }

    public void setDocuId(Document docuId) {
        this.docuId = docuId;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prdoId != null ? prdoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsDoc)) {
            return false;
        }
        ProjectsDoc other = (ProjectsDoc) object;
        if ((this.prdoId == null && other.prdoId != null) || (this.prdoId != null && !this.prdoId.equals(other.prdoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsDoc[ prdoId=" + prdoId + " ]";
    }
    
}
