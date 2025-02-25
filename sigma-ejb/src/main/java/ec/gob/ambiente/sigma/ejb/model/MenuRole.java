package ec.gob.ambiente.sigma.ejb.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the menu_roles database table.
 * 
 */
@Entity
@Table(name="suia_iii.menu_roles")
@NamedQuery(name="MenuRole.findAll", query="SELECT m FROM MenuRole m")
public class MenuRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="mero_id")
	private Integer meroId;

	@Column(name="mero_status")
	private Boolean meroStatus;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public MenuRole() {
	}

	public Integer getMeroId() {
		return this.meroId;
	}

	public void setMeroId(Integer meroId) {
		this.meroId = meroId;
	}

	public Boolean getMeroStatus() {
		return this.meroStatus;
	}

	public void setMeroStatus(Boolean meroStatus) {
		this.meroStatus = meroStatus;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}