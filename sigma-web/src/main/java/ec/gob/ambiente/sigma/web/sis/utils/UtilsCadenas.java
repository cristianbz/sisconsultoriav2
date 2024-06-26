/**
@autor proamazonia [Christian Báez]  8 jun. 2022

**/
package ec.gob.ambiente.sigma.web.sis.utils;

import java.util.regex.Pattern;


public class UtilsCadenas {
	
	public static String romperCadena(String cadena, int tamanio){
		String returnString="";
		StringBuffer auxCadena=new StringBuffer();
		int posicion=0;
		while(cadena!=null && cadena.length()>0 &&  posicion <= tamanio){
			if(tamanio - posicion > 25){
				auxCadena = auxCadena.append(cadena.substring(posicion,posicion+25).toString()).append("\n");
				posicion+=25;
			}else{
				auxCadena = auxCadena.append(cadena.substring(posicion,tamanio).toString());
				break;
			}
		}
		if (posicion == 0)
			returnString = cadena;
		else
			returnString = auxCadena.toString();
	    return returnString;
	}
	
	public static String eliminaSaltoLinea(String cadena){
		String resultado= cadena.replace("\n", "");
		return resultado;
	}
	public static String pasarArrayAString(String [] arreglo){
		String resultado="";
		int fin= arreglo.length;
		for(int pos=0;pos<fin;pos++)
			resultado=resultado+arreglo[pos].concat(",");
		
		return resultado;
	}
	
	public static boolean isValidEmailAddress(String email) {
		String regex = "^[\\w-\\.+]+@([a-zA-Z0-9]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matches(regex,email);
    }
	
	public static String ultimosCaracteres(String s, int n) {
        if (s == null) {
            return null;
        }
        return s.substring(Math.max(0, s.length() - n));
    }
}

