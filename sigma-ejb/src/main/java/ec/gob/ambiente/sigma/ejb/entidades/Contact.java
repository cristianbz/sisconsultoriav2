package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Contact
 *
 */
@Entity
@Table(name = "public.contacts")
public class Contact implements Serializable {

	 private static final long serialVersionUID = 1L;
	    @Id
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "cont_id")
	    private Integer contId;
	    @Size(max = 255)
	    @Column(name = "cont_value")
	    private String contValue;
	    @Column(name = "cofo_id")
	    private Integer cofoId;
	    @JoinColumn(name = "pers_id", referencedColumnName = "peop_id")
	    @ManyToOne
	    private People peopId;
	    @Column(name = "cont_status")
	    private Boolean contStatus;
	    @Size(max = 255)
	    @Column(name = "cont_user_create")
	    private String contUserCreate;
	    @Column(name = "cont_date_create")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date contDateCreate;
	    @Size(max = 255)
	    @Column(name = "cont_user_update")
	    private String contUserUpdate;
	    @Column(name = "cont_date_update")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date contDateUpdate;
	    @Size(max = 255)
	    @Column(name = "cont_ip_create")
	    private String contIpCreate;
	    @Size(max = 255)
	    @Column(name = "cont_ip_update")
	    private String contIpUpdate;
	    @JoinColumn(name = "orga_id", referencedColumnName = "orga_id")
	    @ManyToOne
	    private Organization orgaId;
	    
	    
	    public Contact(){
	    	
	    }
	    
	    

	    public Integer getContId() {
			return contId;
		}



		public void setContId(Integer contId) {
			this.contId = contId;
		}



		public String getContValue() {
			return contValue;
		}



		public void setContValue(String contValue) {
			this.contValue = contValue;
		}



		public Integer getCofoId() {
			return cofoId;
		}



		public void setCofoId(Integer cofoId) {
			this.cofoId = cofoId;
		}



		public People getPeopId() {
			return peopId;
		}



		public void setPeopId(People peopId) {
			this.peopId = peopId;
		}



		public Boolean getContStatus() {
			return contStatus;
		}



		public void setContStatus(Boolean contStatus) {
			this.contStatus = contStatus;
		}



		public String getContUserCreate() {
			return contUserCreate;
		}



		public void setContUserCreate(String contUserCreate) {
			this.contUserCreate = contUserCreate;
		}



		public Date getContDateCreate() {
			return contDateCreate;
		}



		public void setContDateCreate(Date contDateCreate) {
			this.contDateCreate = contDateCreate;
		}



		public String getContUserUpdate() {
			return contUserUpdate;
		}



		public void setContUserUpdate(String contUserUpdate) {
			this.contUserUpdate = contUserUpdate;
		}



		public Date getContDateUpdate() {
			return contDateUpdate;
		}



		public void setContDateUpdate(Date contDateUpdate) {
			this.contDateUpdate = contDateUpdate;
		}



		public String getContIpCreate() {
			return contIpCreate;
		}



		public void setContIpCreate(String contIpCreate) {
			this.contIpCreate = contIpCreate;
		}



		public String getContIpUpdate() {
			return contIpUpdate;
		}



		public void setContIpUpdate(String contIpUpdate) {
			this.contIpUpdate = contIpUpdate;
		}



		public Organization getOrgaId() {
			return orgaId;
		}



		public void setOrgaId(Organization orgaId) {
			this.orgaId = orgaId;
		}



		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (peopId != null ? peopId.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof People)) {
	            return false;
	        }
	        Contact other = (Contact) object;
	        if ((this.contId == null && other.contId != null) || (this.contId != null && !this.contId.equals(other.contId))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "ec.gob.ambiente.sigma.ejb.entidades.Contact[ contId=" + contId + " ]";
	    }
   
}
