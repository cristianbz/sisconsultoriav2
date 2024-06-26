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
@Table(name = "sigma.funding_sources_notes")
@NamedQueries({
    @NamedQuery(name = "FundingSourcesNote.findAll", query = "SELECT f FROM FundingSourcesNote f")})
public class FundingSourcesNote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fsno_id")
    private Integer fsnoId;
    @Size(max = 500)
    @Column(name = "fsno_note")
    private String fsnoNote;
    @Column(name = "fsno_status")
    private Boolean fsnoStatus;
    @JoinColumn(name = "fuso_id", referencedColumnName = "fuso_id")
    @ManyToOne(optional = false)
    private FundingSource fusoId;

    public FundingSourcesNote() {
    	fsnoStatus=true;
    }

    public FundingSourcesNote(Integer fsnoId) {
        this.fsnoId = fsnoId;
    }

    public FundingSourcesNote(Integer fsnoId, String fsnoNote) {
        this.fsnoId = fsnoId;
        this.fsnoNote = fsnoNote;
    }

    public Integer getFsnoId() {
        return fsnoId;
    }

    public void setFsnoId(Integer fsnoId) {
        this.fsnoId = fsnoId;
    }

    public String getFsnoNote() {
        return fsnoNote;
    }

    public void setFsnoNote(String fsnoNote) {
        this.fsnoNote = fsnoNote;
    }

    public Boolean getFsnoStatus() {
        return fsnoStatus;
    }

    public void setFsnoStatus(Boolean fsnoStatus) {
        this.fsnoStatus = fsnoStatus;
    }

    public FundingSource getFusoId() {
        return fusoId;
    }

    public void setFusoId(FundingSource fusoId) {
        this.fusoId = fusoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fsnoId != null ? fsnoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FundingSourcesNote)) {
            return false;
        }
        FundingSourcesNote other = (FundingSourcesNote) object;
        if ((this.fsnoId == null && other.fsnoId != null) || (this.fsnoId != null && !this.fsnoId.equals(other.fsnoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.FundingSourcesNote[ fsnoId=" + fsnoId + " ]";
    }
    
}
