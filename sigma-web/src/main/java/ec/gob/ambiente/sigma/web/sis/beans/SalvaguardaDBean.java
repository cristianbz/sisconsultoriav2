/**
@autor proamazonia [Christian Báez]  12 mar. 2021

**/
package ec.gob.ambiente.sigma.web.sis.beans;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.sis.entidades.Questions;
import ec.gob.ambiente.sigma.ejb.sis.entidades.TableResponses;
import ec.gob.ambiente.sigma.ejb.sis.entidades.ValueAnswers;
import lombok.Getter;
import lombok.Setter;
@Named
@ViewScoped
public class SalvaguardaDBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private List<TableResponses> tablaSalvaguardaD321;
	
	@Getter
	@Setter
	private List<TableResponses> tablaSalvaguardaD331;
	
	@Getter
	@Setter
	private List<ValueAnswers> listaValoresRespuestasD;
	
	@Getter
	@Setter
	private List<Questions> listaPreguntasD;
}

