/**
@autor proamazonia [Christian Báez]  2 jun. 2021

**/
package ec.gob.ambiente.sigma.web.sis.utils;

import java.util.ArrayList;
import java.util.List;

import ec.gob.ambiente.sigma.ejb.entidades.GeographicalLocations;



public class UtilsGeografia {

	public static List<GeographicalLocations> filtraCantones(List<Object[]> listaCantones,int codigoProvincia){
		List<GeographicalLocations> listaAux= new ArrayList<>();
//		List<Object[]> listaTemporal = listaCantones.stream().filter(canton->Integer.valueOf(canton[3].toString()).equals(codigoProvincia)).collect(Collectors.toList());	
		List<Object[]> listaTemporal = new ArrayList<>();
		for (Object[] obj : listaCantones) {
			if(obj[3].toString().equals(codigoProvincia))
				listaTemporal.add(obj);
		}
		for (Object[] objects : listaTemporal) {
			GeographicalLocations geo=new GeographicalLocations();
			geo.setGeloName(objects[0].toString());
			geo.setGeloId(Integer.valueOf(objects[1].toString()));
			geo.setGeloCodificationInec(objects[2].toString());
			listaAux.add(geo);
		}
		return listaAux;
	}
	
	public static List<GeographicalLocations> filtraParroquias(List<Object[]> listaParroquias,int codigoCanton){
		List<GeographicalLocations> listaAux= new ArrayList<>();
//		List<Object[]> listaTemporal = listaParroquias.stream().filter(canton->Integer.valueOf(canton[3].toString()).equals(codigoCanton)).collect(Collectors.toList());		
		List<Object[]> listaTemporal = new ArrayList<>();
		for (Object[] obj : listaParroquias) {
			if(obj[3].toString().equals(codigoCanton))
				listaTemporal.add(obj);
		}
		for (Object[] objects : listaTemporal) {
			GeographicalLocations geo=new GeographicalLocations();
			geo.setGeloName(objects[0].toString());
			geo.setGeloId(Integer.valueOf(objects[1].toString()));
			geo.setGeloCodificationInec(objects[2].toString());
			listaAux.add(geo);
		}
		return listaAux;
	}
}

