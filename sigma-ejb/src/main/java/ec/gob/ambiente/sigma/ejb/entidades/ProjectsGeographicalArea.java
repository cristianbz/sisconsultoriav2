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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.projects_geographical_areas")
@NamedQueries({
    @NamedQuery(name = "ProjectsGeographicalArea.findAll", query = "SELECT p FROM ProjectsGeographicalArea p")})
public class ProjectsGeographicalArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pgar_id")
    private Integer pgarId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "pgar_level")
    private String pgarLevel;
    @Size(max = 100)
    @Column(name = "pgar_geo_table")
    private String pgarGeoTable;
    @Size(max = 2)
    @Column(name = "pgar_geo_type")
    private String pgarGeoType;
    @Size(max = 100)
    @Column(name = "pgar_geo_table_cols")
    private String pgarGeoTableCols;
    @Column(name = "pgar_status")
    private Boolean pgarStatus;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @JoinColumn(name = "gelo_id", referencedColumnName = "gelo_id")
    @ManyToOne
    private GeographicalLocations geloId;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;
    @Transient
    private boolean seleccionado;

    public ProjectsGeographicalArea() {
    	pgarStatus=true;
    }

    public ProjectsGeographicalArea(Integer pgarId) {
        this.pgarId = pgarId;
    }

    public ProjectsGeographicalArea(Integer pgarId, GeographicalLocations geloId, String pgarLevel) {
        this.pgarId = pgarId;
        this.geloId = geloId;
        this.pgarLevel = pgarLevel;
    }

    public Integer getPgarId() {
        return pgarId;
    }

    public void setPgarId(Integer pgarId) {
        this.pgarId = pgarId;
    }

    

    public GeographicalLocations getGeloId() {
		return geloId;
	}

	public void setGeloId(GeographicalLocations geloId) {
		this.geloId = geloId;
	}

	public String getPgarLevel() {
        return pgarLevel;
    }

    public void setPgarLevel(String pgarLevel) {
        this.pgarLevel = pgarLevel;
    }

    public Boolean getPgarStatus() {
        return pgarStatus;
    }

    public void setPgarStatus(Boolean pgarStatus) {
        this.pgarStatus = pgarStatus;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }
    
    
    

    public String getPgarGeoTable() {
		return pgarGeoTable;
	}
    public String getPgarGeoTableExtra() {
    	String r="";
    	if(pgarGeoTable!=null){
    		r=pgarGeoTable.replace("sigma_geo.", "");
    		if(pgarGeoTable.length()>30){
    			r=r.substring(0, 20)+"...";
    		}
    	}
		return r;
	}

	public void setPgarGeoTable(String pgarGeoTable) {
		this.pgarGeoTable = pgarGeoTable;
	}

	public String getPgarGeoType() {
		return pgarGeoType;
	}

	public void setPgarGeoType(String pgarGeoType) {
		this.pgarGeoType = pgarGeoType;
	}

	public String getPgarGeoTableCols() {
		return pgarGeoTableCols;
	}

	public void setPgarGeoTableCols(String pgarGeoTableCols) {
		this.pgarGeoTableCols = pgarGeoTableCols;
	}
	
	

	public Catalog getCataId() {
		return cataId;
	}

	public void setCataId(Catalog cataId) {
		this.cataId = cataId;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pgarId != null ? pgarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectsGeographicalArea)) {
            return false;
        }
        ProjectsGeographicalArea other = (ProjectsGeographicalArea) object;
        if ((this.pgarId == null && other.pgarId != null) || (this.pgarId != null && !this.pgarId.equals(other.pgarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.ProjectsGeographicalArea[ pgarId=" + pgarId + " ]";
    }
    
}
