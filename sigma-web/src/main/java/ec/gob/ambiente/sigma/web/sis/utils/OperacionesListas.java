/**
@autor proamazonia [Christian Báez]  6 sep. 2021

**/
package ec.gob.ambiente.sigma.web.sis.utils;

import java.util.ArrayList;
import java.util.List;

import ec.gob.ambiente.sigma.ejb.sis.entidades.TableResponses;



public class OperacionesListas {
	public static List<TableResponses> filtrar(List<TableResponses> lista,int codigoPregunta){
		List<TableResponses> listaTemp=new ArrayList<>();
		for (TableResponses tr : lista) {			
			if(tr.getQuestions().getQuesId()== codigoPregunta)
				listaTemp.add(tr);
		}
		return listaTemp;
	}
}

