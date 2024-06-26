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

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.participants")
@NamedQueries({
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p")})
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "part_id")
    private Integer partId;
    @Column(name = "part_join_day")
    private Integer partJoinDay;
    @Column(name = "part_status")
    private Boolean partStatus;
    @JoinColumn(name = "meet_id", referencedColumnName = "meet_id")
    @ManyToOne(optional = false)
    private Meeting meetId;
    @JoinColumn(name = "pers_id", referencedColumnName = "pers_id")
    @ManyToOne(optional = false)
    private Person persId;

    public Participant() {
    }

    public Participant(Integer partId) {
        this.partId = partId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getPartJoinDay() {
        return partJoinDay;
    }

    public void setPartJoinDay(Integer partJoinDay) {
        this.partJoinDay = partJoinDay;
    }

    public Boolean getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(Boolean partStatus) {
        this.partStatus = partStatus;
    }

    public Meeting getMeetId() {
        return meetId;
    }

    public void setMeetId(Meeting meetId) {
        this.meetId = meetId;
    }

    public Person getPersId() {
        return persId;
    }

    public void setPersId(Person persId) {
        this.persId = persId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partId != null ? partId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Participant[ partId=" + partId + " ]";
    }
    
}
