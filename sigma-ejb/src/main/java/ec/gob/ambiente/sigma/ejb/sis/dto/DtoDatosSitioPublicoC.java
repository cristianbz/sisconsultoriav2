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

public class DtoDatosSitioPublicoC extends DtoSitioPublico implements Jsonable{

	public DtoDatosSitioPublicoC(String salvaguarda) {
		super(salvaguarda);
		// TODO Auto-generated constructor stub
	}
	@Getter
	@Setter
	private Integer numeroComunidadesC;
	@Getter
	@Setter
	private Integer numeroPracticas;
	@Getter
	@Setter
	private Integer numeroOrganizacionIndigenaC;
	@Getter
	@Setter
	private Integer numeroEventosCPLIC;

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
        json.put("numeroComunidadesC", getNumeroComunidadesC());
        json.put("numeroPracticasC", getNumeroPracticas());
        json.put("numeroOrganizacionIndigenaC", getNumeroOrganizacionIndigenaC());
        json.put("numeroEventosCPLIC", getNumeroEventosCPLIC());
        json.toJson(writer);

    }

}

