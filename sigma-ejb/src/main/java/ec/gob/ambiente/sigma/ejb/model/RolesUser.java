package ec.gob.ambiente.sigma.ejb.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import ec.gob.ambiente.sigma.ejb.entidades.User;


/**
 * The persistent class for the roles_users database table.
 * 
 */
@Entity
@Table(name="suia_iii.roles_users")
@NamedQuery(name="RolesUser.findAll", query="SELECT r FROM RolesUser r")
public class RolesUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ROUS_ID_GENERATOR", initialValue = 1, sequenceName = "seq_rous_id", schema = "suia_iii", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROUS_ID_GENERATOR")
	@Column(name="rous_id")
	private Integer rousId;

	@Column(name="rous_status")
	private Boolean rousStatus;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="rous_description")
	@Size(max = 100)
	private String rousDescription;

	public RolesUser() {
	}

	public Integer getRousId() {
		return this.rousId;
	}

	public void setRousId(Integer rousId) {
		this.rousId = rousId;
	}

	public Boolean getRousStatus() {
		return this.rousStatus;
	}

	public void setRousStatus(Boolean rousStatus) {
		this.rousStatus = rousStatus;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRousDescription() {
		return rousDescription;
	}

	public void setRousDescription(String rousDescription) {
		this.rousDescription = rousDescription;
	}

	
	
	
}