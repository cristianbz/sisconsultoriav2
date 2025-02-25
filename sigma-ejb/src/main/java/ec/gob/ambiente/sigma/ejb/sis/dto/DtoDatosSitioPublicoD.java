/**
@autor proamazonia [Christian Báez]  1 dic. 2021

**/
package ec.gob.ambiente.sigma.ejb.sis.dto;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import lombok.Getter;
import lombok.Setter;

public class DtoDatosSitioPublicoD extends DtoSitioPublico implements Jsonable {

	public DtoDatosSitioPublicoD(String salvaguarda) {
		super(salvaguarda);
		// TODO Auto-generated constructor stub
	}
//	@Getter
//	@Setter
//	private Integer totalEventosHombres;
//	@Getter
//	@Setter
//	private Integer totalEventosMujeres;
	@Getter
	@Setter
	private Integer totalEspaciosD;
	@Getter
	@Setter
	private Integer totalPersonasParticipacionD;
	@Getter
	@Setter
	private Integer numeroOrganizacionesLocalesD;
	
	@Override
	public String toJson() {
		final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        	e.printStackTrace();
        }
        return writable.toString();

	}

	@Override
	public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("salvaguarda", super.salvaguarda);
//        json.put("totalEventosHombresD", getTotalEventosHombres());
//        json.put("totalEventosMujeresD", getTotalEventosMujeres());
        json.put("totalEspaciosD", getTotalEspaciosD());
        json.put("totalPersonasParticipacionD", getTotalPersonasParticipacionD());
        json.put("numeroOrganizacionesLocalesD", getNumeroOrganizacionesLocalesD());  
        json.toJson(writer);
    }

}

