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
@Table(name = "sigma.meetings_options")
@NamedQueries({
    @NamedQuery(name = "MeetingsOption.findAll", query = "SELECT m FROM MeetingsOption m")})
public class MeetingsOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meop_id")
    private Integer meopId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "meop_type")
    private String meopType;
    @Column(name = "meop_status")
    private Boolean meopStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @JoinColumn(name = "meet_id", referencedColumnName = "meet_id")
    @ManyToOne(optional = false)
    private Meeting meetId;

    public MeetingsOption() {
    }

    public MeetingsOption(Integer meopId) {
        this.meopId = meopId;
    }

    public MeetingsOption(Integer meopId, String meopType) {
        this.meopId = meopId;
        this.meopType = meopType;
    }

    public Integer getMeopId() {
        return meopId;
    }

    public void setMeopId(Integer meopId) {
        this.meopId = meopId;
    }

    public String getMeopType() {
        return meopType;
    }

    public void setMeopType(String meopType) {
        this.meopType = meopType;
    }

    public Boolean getMeopStatus() {
        return meopStatus;
    }

    public void setMeopStatus(Boolean meopStatus) {
        this.meopStatus = meopStatus;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Meeting getMeetId() {
        return meetId;
    }

    public void setMeetId(Meeting meetId) {
        this.meetId = meetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (meopId != null ? meopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeetingsOption)) {
            return false;
        }
        MeetingsOption other = (MeetingsOption) object;
        if ((this.meopId == null && other.meopId != null) || (this.meopId != null && !this.meopId.equals(other.meopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.MeetingsOption[ meopId=" + meopId + " ]";
    }
    
}
