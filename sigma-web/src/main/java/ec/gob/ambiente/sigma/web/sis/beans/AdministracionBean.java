/**
@autor proamazonia [Christian Báez]  16 jun. 2021

**/
package ec.gob.ambiente.sigma.web.sis.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.entidades.Component;
import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsStrategicPartner;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.entidades.User;
import ec.gob.ambiente.sigma.ejb.sis.entidades.CatalogTypes;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Catalogs;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Indicators;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.model.Role;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class AdministracionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Integer codigoSalvaguarda;
	
	@Getter
	@Setter
	private Integer codigoPartner;
	
	@Getter
	@Setter
	private Integer codigoProyecto;
	
	@Getter
	@Setter
	private Integer codigoTipoRespuesta;
	
	@Getter
	@Setter
	private Integer codigoTipoCatalogo;
	
	@Getter
	@Setter
	private Integer codigoProyectoUsuario;
	
	@Getter
	@Setter
	private boolean nuevaPregunta; 
	
	@Getter
	@Setter
	private boolean nuevaPreguntaGenero; 
	
	@Getter
	@Setter
	private boolean nuevoCatalogo; 
	
	@Getter
	@Setter
	private boolean nuevoUsuario; 
	
	@Getter
	@Setter
	private boolean noEsSocioEstrategico;
	
	@Getter
	@Setter
	private String nombreUsuario;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntas;
	
	@Getter
	@Setter
	private List<Catalogs> listaTipoRespuestaPregunta;
	
	@Getter
	@Setter
	private List<Catalogs> listaCatalogos;
	
	@Getter
	@Setter
	private List<Component> listaComponentes;
	
	@Getter
	@Setter
	private List<Catalogs> listaCatalogosFiltrados;
	
	@Getter
	@Setter
	private List<CatalogTypes> listaTipoCatalogo;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntasGenero;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntasFiltradas;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntasGeneroFiltradas;
	
	@Getter
	@Setter
	private List<ProjectsStrategicPartner> listaPartners;
	
	@Getter
	@Setter
	private Questions preguntaSeleccionada;
	
	@Getter
	@Setter
	private Catalogs catalogoSeleccionado;
	
	@Getter
	@Setter
	private List<Safeguard> listaSalvaguardas;
	

	@Getter
	@Setter
	private User usuarioSeleccionado;
	
	@Getter
	@Setter
	private List<User> listaUsuarios;
	
	@Getter
	@Setter
	private List<Project> listaProyectos;
	
		
	@Getter
	@Setter
	private List<Role> listaRolesUsuario;
	
	@Getter
	@Setter
	private boolean deshabilitaOrdenPregunta;
	
	@Getter
	@Setter
	private boolean deshabilitaOrdenCatalogo;
	
	@Getter
	@Setter
	private List<Indicators> listaIndicadores;
	
	@Getter
	@Setter
	private Indicators indicadorSeleccionado;
	
	@Getter
	@Setter
	private boolean nuevoIndicador;
}

