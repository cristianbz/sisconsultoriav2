/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dguano@proamazonia.org
 */
@Entity
@Table(name = "public.geographical_locations")
public class GeographicalLocations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gelo_id")
    private Integer geloId;
    @Size(max = 255)
    @Column(name = "gelo_codification_inec")
    private String geloCodificationInec;
    @Size(max = 255)
    @Column(name = "gelo_name")
    private String geloName;
    @Column(name = "gelo_status")
    private Boolean geloStatus;
    @Size(max = 255)
    @Column(name = "gelo_region")
    private String geloRegion;
    /*@Size(max = 1024)
    @Column(name = "gelo_observations")
    private String geloObservations;
    @OneToMany(mappedBy = "geloParentId")
    private List<GeographicalLocations> geographicalLocationsList;
    */
    @JoinColumn(name = "gelo_parent_id", referencedColumnName = "gelo_id")
    @ManyToOne
    private GeographicalLocations geloParentId;
    @Transient
    private boolean seleccionado;
    /*
    @OneToMany(mappedBy = "geloId")
    private List<People> peopleList;*/

    public GeographicalLocations() {
    }

    public GeographicalLocations(Integer geloId) {
        this.geloId = geloId;
    }
    
    public GeographicalLocations(Integer geloId, String name) {
        this.geloId = geloId;
        this.geloName=name;
    }
    
    public GeographicalLocations(Integer geloId, String name,Integer parentId) {
        this.geloId = geloId;
        this.geloName=name;
        this.geloParentId=new GeographicalLocations(parentId);
    }

    public Integer getGeloId() {
        return geloId;
    }

    public void setGeloId(Integer geloId) {
        this.geloId = geloId;
    }

    public String getGeloCodificationInec() {
        return geloCodificationInec;
    }

    public void setGeloCodificationInec(String geloCodificationInec) {
        this.geloCodificationInec = geloCodificationInec;
    }

    public String getGeloName() {
        return geloName;
    }

    public void setGeloName(String geloName) {
        this.geloName = geloName;
    }

    public Boolean getGeloStatus() {
        return geloStatus;
    }

    public void setGeloStatus(Boolean geloStatus) {
        this.geloStatus = geloStatus;
    }

    public String getGeloRegion() {
        return geloRegion;
    }

    public void setGeloRegion(String geloRegion) {
        this.geloRegion = geloRegion;
    }

    /*public String getGeloObservations() {
        return geloObservations;
    }

    public void setGeloObservations(String geloObservations) {
        this.geloObservations = geloObservations;
    }

    public List<GeographicalLocations> getGeographicalLocationsList() {
        return geographicalLocationsList;
    }

    public void setGeographicalLocationsList(List<GeographicalLocations> geographicalLocationsList) {
        this.geographicalLocationsList = geographicalLocationsList;
    }*/

    public GeographicalLocations getGeloParentId() {
        return geloParentId;
    }

    public void setGeloParentId(GeographicalLocations geloParentId) {
        this.geloParentId = geloParentId;
    }
    
    
/*
    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }*/

    public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (geloId != null ? geloId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeographicalLocations)) {
            return false;
        }
        GeographicalLocations other = (GeographicalLocations) object;
        if ((this.geloId == null && other.geloId != null) || (this.geloId != null && !this.geloId.equals(other.geloId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations[ geloId=" + geloId + " ]";
    }
    
}
