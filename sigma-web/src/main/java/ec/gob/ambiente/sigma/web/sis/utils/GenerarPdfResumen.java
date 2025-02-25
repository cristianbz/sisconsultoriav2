/**
@autor proamazonia [Christian Báez]  18 may. 2022

**/
package ec.gob.ambiente.sigma.web.sis.utils;

public class GenerarPdfResumen {	
	public static final String REPORTE_RESUMEN_SALVAGUARDAA="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA A</p>\r\n" +
	     "<p class='preguntas'>{P:pregunta1}</p>\r\n" +
	     "<p class='preguntas'>Marco Jurídico Internacional</p>\r\n" +
	     "{P:tablaJuridicoInternacional}\r\n" + 
	     "<p class='preguntas'>Marco Jurídico Nacional</p>\r\n" +
	     "{P:tablaJuridicoNacional}\r\n" + 
	     "<p class='preguntas'>Normativa Secundaria Nacional</p>\r\n" +
	     "{P:tablaNormativaNacional}\r\n" + 
	     "<p class='preguntas'>{P:pregunta2}</p>\r\n" +
	     "{P:tablaPoliticaPrograma}\r\n" +
	     "<p class='preguntas'>{P:pregunta3A}</p>\r\n" +
	     "{P:tabla3A}\r\n" +
	     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +
	     "{P:infoAdicionalA}\r\n" +
	     "<br/>\r\n" +
	     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA A.</p>\r\n" +
	     "<p class='contenido'>{P:resumenA}</p>\r\n" +
	     "<br/>\r\n";
	
	public static final String REPORTE_RESUMEN_SALVAGUARDAB="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA B</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo4B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta41B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla41B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta5B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo5B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta51B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla51B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta6B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo6B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta61B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla61B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta7B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo7B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta71B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla71B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta8B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo8B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta81B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla81B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta9B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo9B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta91B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla9B}</p>\r\n" +
//		     "<p class='preguntas'>{P:pregunta10B}</p>\r\n" +
//		     "<p class='preguntas'>{P:siNo10B}</p>\r\n" +
//		     "<p class='preguntas'>{P:pregunta101B}</p>\r\n" +
//		     "<p class='preguntas'>{P:tabla101B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta11B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo11B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta111B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla111B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta12B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo12B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta121B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla121B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta13B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo13B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta131B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla131B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta14B}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo14B}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta141B}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla141B}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +
		     "{P:infoAdicionalB}\r\n" +
		     "<br/>\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA B.</p>\r\n" +
		     "<p class='contenido'>{P:resumenB}</p>\r\n" +
		     "<br/>\r\n";
	
	public static final String REPORTE_RESUMEN_SALVAGUARDAC="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA C</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta20C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo20C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta201C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla201C}</p>\r\n" +	     
		     "<p class='preguntas'>{P:pregunta21C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo21C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta211C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla211C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta24C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo24C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta241C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla241C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta242C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla242C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta25C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo25C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta26C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla26C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta27C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo27C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta271C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla271C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta28C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla28C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta29C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo29C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta291C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla291C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta30C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo30C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta301C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla301C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta31C}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo31C}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta311C}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla311C}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +
		     "{P:infoAdicionalC}\r\n" +	     
		     "<br/>\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA C.</p>\r\n" +
		     "<p class='contenido'>{P:resumenC}</p>\r\n" +
		     "<br/>\r\n" ;
	public static final String REPORTE_RESUMEN_SALVAGUARDAD="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA D</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta32D}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo32D}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta321D}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla321D}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta33D}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo33D}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta331D}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla331D}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +
		     "{P:infoAdicionalD}\r\n" +	     
		     "<br/>\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA D.</p>\r\n" +
		     "<p class='contenido'>{P:resumenD}</p>\r\n" +
		     "<br/>\r\n" ;
	public static final String REPORTE_RESUMEN_SALVAGUARDAE="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA E</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta34E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo34E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta341E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla341E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta35E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo35E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta351E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla351E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta36E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo36E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta361E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla361E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta37E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo37E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta371E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla371E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta372E}</p>\r\n" +
		     "<p class='preguntas'>{P:respuesta372E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta38E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo38E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta381E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla381E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta39E}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo39E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta391E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla391E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta40E}</p>\r\n" +
		     "<p class='preguntas'>COBENEFICIOS POLÍTICAS Y GESTIÓN INSTITUCIONAL</p>\r\n" +
		     "<p class='preguntas'>SOCIALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta401E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla401E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta402E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla402E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta403E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla403E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta404E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla404E}</p>\r\n" +
		     "<p class='preguntas'>AMBIENTALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta405E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla405E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta406E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla406E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta407E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla407E}</p>\r\n" +
		     "<p class='preguntas'>TRANSICIÓN A SISTEMAS PRODUCTIVOS SOSTENIBLES</p>\r\n" +
		     "<p class='preguntas'>SOCIALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta408E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla408E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta409E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla409E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4010E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4010E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4011E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4011E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4012E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4012E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4013E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4013E}</p>\r\n" +
		     "<p class='preguntas'>AMBIENTALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4014E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4014E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4015E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4015E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4016E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4016E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4017E}</p>\r\n" +
		     "<p class='preguntas'>MANEJO FORESTAL SOSTENIBLE</p>\r\n" +
		     "<p class='preguntas'>SOCIALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4018E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4018E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4019E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4019E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4020E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4020E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4021E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4021E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4022E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4022E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4023E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4023E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4024E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4024E}</p>\r\n" +
		     "<p class='preguntas'>AMBIENTALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4025E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4025E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4026E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4026E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4027E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4027E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4028E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4028E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4029E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4029E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4030E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4030E}</p>\r\n" +
		     "<p class='preguntas'>CONSERVACION Y RESTAURACION</p>\r\n" +
		     "<p class='preguntas'>SOCIALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4031E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4031E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4032E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4032E}</p>\r\n" +
		     "<p class='preguntas'>AMBIENTALES</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4033E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4033E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4034E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4034E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4035E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4035E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4036E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4036E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4037E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4037E}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta4038E}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla4038E}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +	     
		     "{P:infoAdicionalE}\r\n" +
		     "<br/>\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA E.</p>\r\n" +
		     "<p class='contenido'>{P:resumenE}</p>\r\n" +
		     "<br/>\r\n" ;
	public static final String REPORTE_RESUMEN_SALVAGUARDAF=	     "<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA F</p>\r\n" +	     
		     "<p class='preguntas'>{P:pregunta41F}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo41F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta411F}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla411F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta42F}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo42F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta421F}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla421F}</p>\r\n" +
//		     "<p class='preguntas'>{P:pregunta43F}</p>\r\n" +
//		     "<p class='preguntas'>{P:siNo43F}</p>\r\n" +
//		     "<p class='preguntas'>{P:pregunta431F}</p>\r\n" +
//		     "<p class='preguntas'>{P:tabla431F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta44F}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo44F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta441F}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla441F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta45F}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo45F}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta451F}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla451F}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +	     
		     "{P:infoAdicionalF}\r\n" +
		     "<br/>\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA F.</p>\r\n" +
		     "<p class='contenido'>{P:resumenF}</p>\r\n" +
		     "<br/>\r\n";
	public static final String REPORTE_RESUMEN_SALVAGUARDAG="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>SALVAGUARDA G</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta46G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo46G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta461G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla461G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta47G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo47G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta471G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla471G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta48G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo48G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta481G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla481G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta49G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo49G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta491G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla491G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta50G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo50G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta501G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla501G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta51G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo51G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta511G}</p>\r\n" +
		     "<p class='preguntas'>{P:tabla511G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta512G}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo512G}</p>\r\n" +
		     "<p class='preguntas'>{P:pregunta513G}</p>\r\n" +
		     "<p class='preguntas'>{P:respuesta513G}</p>\r\n" +
		     "<p class='preguntas'>INFORMACION ADICIONAL OPCIONAL</p>\r\n" +	     
		     "{P:infoAdicionalG}\r\n" +
		     "<p class='preguntas'>RESUMEN DE LA SALVAGUARDA G.</p>\r\n" +
		     "<p class='contenido'>{P:resumenG}</p>\r\n" +
		     "<br/>\r\n" ;
	public static final String REPORTE_RESUMEN_PIE="<table  width='100%' style='margin: 0 auto;border-style: none;border-collapse: collapse;font-size:12px;font-family: sans-serif' border='0'>\r\n" +
			"<tr>\r\n" +
		    "    <td><img src='{P:logoPieIzquierda}'></td>\r\n" +
		    "   <td style='background-color: #ffffff; width: 50%'> </td>\r\n" +
		    "    <td><img src='{P:logoPieDerecha}'></td>\r\n" +
		     "</tr>\r\n" +
		"</table>\r\n" +
		"</body>\r\n" +
			"</html>";
	public static final String REPORTE_RESUMEN_ENCABEZADO="<!DOCTYPE html >\r\n" + 
			"<html xmlns='http://www.w3.org/1999/xhtml'>\r\n" + 
			"<head>\r\n" + 
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\r\n" + 
			"<title>Documento sin título</title>\r\n" + 
			"<style>\r\n" +
			"table td {\r\n" +
			"	  white-space: normal !important;\r\n" +
			"   word-wrap: break;\r\n" +
			"	}\r\n" +
			".preguntas {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 11px;\r\n" +
			"font-weight: bold;\r\n" +
			"}\r\n" +
			".titulotabla {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 10px;\r\n" +
			"font-weight: bold;\r\n" +
			"}\r\n" +
			".tablaborder {\r\n" +
			"border: 1px solid black;\r\n" +
			"border-collapse:collapse;\r\n" +
			"height: auto;\r\n" +						
			"}\r\n" +
			".contenido {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 11px;\r\n" +			
			"}\r\n" +
			"</style>\r\n" +
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" +
			"<table  width='80%' style='margin: 0 auto;border-style: none;border-collapse: collapse;font-size:12px;font-family: sans-serif' border='0'>\r\n" +
			"<tr>\r\n" +
		    "    <td><img src='{P:logoEscudo}' width='150' height='65'></td>\r\n" +
		    "   <td style='background-color: #ffffff; width: 30%'> </td>\r\n" +
		     "   <td><img src='{P:logoMae}' width='300' height='55'> </td>\r\n" +    
		     "</tr>\r\n" +
			"<tr style='margin: 0 auto;border-style: none;border-collapse: collapse;font-size:12px;background-color: #0da5c4;border-color: #ffffff;font-family: sans-serif'>\r\n" +
		    "    <td>Sistema de Información de Salvaguardas SIS</td>\r\n" +
		     "   <td style='background-color: #ffffff; width: 30%'> </td>\r\n" +    
		     "   <td>Resumen de las salvaguardas reportadas</td>\r\n" +        
		    "</tr>\r\n" +
		    "<tr >\r\n" +
		     "   <td style='background-color: #ffffff'></td>\r\n" +
		      "  <td style='background-color: #ffffff'> </td>    \r\n" +
		      "  <td style='background-color: #ffffff'>{P:fecha}</td>\r\n" +        
		    "</tr>\r\n" +
		"</table>\r\n" +		
		"<br/>\r\n" +		
		"<table  width='80%' style='margin: 0 auto;border-style: solid;border-collapse: collapse;font-size:10px;font-family: sans-serif;background-color: #0da5c4;' border='0'>\r\n" +
		 "   <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		  "      <td style='font-weight: bold;vertical-align: baseline;text-align:right;' width: 50%'>Título del Plan de implementación, Programa o Proyecto:</td>\r\n" +
		   "     <td width: 50%'>{P:proyecto} </td>\r\n" +
		   " </tr>\r\n" +
		   " <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		   "     <td style='font-weight: bold;vertical-align: baseline;text-align:right;'>Socio implementador:</td>\r\n" +
		   "     <td>{P:socioImplementador} </td>\r\n" +
		   " </tr>\r\n" +
		   " <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		   "     <td style='font-weight: bold;vertical-align: baseline;text-align:right;'>Socio Estratégico:</td>\r\n" +
		   "     <td>{P:socioEstrategico} </td>\r\n" +
		   " </tr>\r\n" +
		   " <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		   "     <td style='font-weight: bold;vertical-align: baseline;text-align:right;'>Con qué sector se identifica:</td>\r\n" +
		   "     <td>{P:sectores} </td>\r\n" +
		   " </tr>\r\n" +
		"</table>\r\n" +
		"<br/>\r\n" +
		"<br/>\r\n"+
		"<p style='background-color: #0da5c4;font-family: sans-serif;font-size:11px;width:70px;display:inline-block'>PERIODO: </P><p style='font-family: sans-serif;font-size:11px;width:120px;display:inline-block'>{P:periodo}</p>\r\n";
	
	public static final String REPORTE_RESUMEN_ENCABEZADO_GENERO="<!DOCTYPE html >\r\n" + 
			"<html xmlns='http://www.w3.org/1999/xhtml'>\r\n" + 
			"<head>\r\n" + 
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\r\n" + 
			"<title>Documento sin título</title>\r\n" + 
			"<style>\r\n" +
			"table td {\r\n" +
			"	  white-space: normal !important;\r\n" +
			"   word-wrap: break;\r\n" +
			"	}\r\n" +
			".preguntas {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 11px;\r\n" +
			"font-weight: bold;\r\n" +
			"}\r\n" +
			".titulotabla {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 9px;\r\n" +
			"font-weight: bold;\r\n" +
			"}\r\n" +
			".tablaborder {\r\n" +
			"border: 1px solid black;\r\n" +
			"border-collapse:collapse;\r\n" +
			"height: auto;\r\n" +						
			"}\r\n" +
			".contenido {\r\n" +
			"font-family: sans-serif;\r\n" +
			"font-size: 11px;\r\n" +			
			"}\r\n" +
			"</style>\r\n" +
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" +
			"<table  width='80%' style='margin: 0 auto;border-style: none;border-collapse: collapse;font-size:12px;font-family: sans-serif' border='0'>\r\n" +
			"<tr>\r\n" +
		    "    <td><img src='{P:logoEscudo}' width='150' height='55'></td>\r\n" +
		    "   <td style='background-color: #ffffff; width: 30%'> </td>\r\n" +
		     "   <td><img src='{P:logoMae}' width='300' height='55'> </td>\r\n" +    
		     "</tr>\r\n" +
			"<tr style='margin: 0 auto;border-style: none;border-collapse: collapse;font-size:12px;background-color: #0da5c4;border-color: #ffffff;font-family: sans-serif'>\r\n" +
		    "    <td>Sistema de Información de Salvaguardas SIS</td>\r\n" +
		     "   <td style='background-color: #ffffff; width: 30%'> </td>\r\n" +    
		     "   <td>Resumen del abordaje de género</td>\r\n" +        
		    "</tr>\r\n" +
		    "<tr >\r\n" +
		     "   <td style='background-color: #ffffff'></td>\r\n" +
		      "  <td style='background-color: #ffffff'> </td>    \r\n" +
		      "  <td style='background-color: #ffffff'>{P:fecha}</td>\r\n" +        
		    "</tr>\r\n" +
		"</table>\r\n" +		
		"<br/>\r\n" +		
		"<table  width='80%' style='margin: 0 auto;border-style: solid;border-collapse: collapse;font-size:10px;font-family: sans-serif;background-color: #0da5c4;' border='0'>\r\n" +
		 "   <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		  "      <td style='font-weight: bold;vertical-align: baseline;text-align:right;' width: 50%'>Título del Plan de implementación, Programa o Proyecto:</td>\r\n" +
		   "     <td width: 50%'>{P:proyecto} </td>\r\n" +
		   " </tr>\r\n" +
		   " <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		   "     <td style='font-weight: bold;vertical-align: baseline;text-align:right;'>Socio implementador:</td>\r\n" +
		   "     <td>{P:socioImplementador} </td>\r\n" +
		   " </tr>\r\n" +
		   " <tr style='height:3px;border-color: #ffffff;'>\r\n" +
		   "     <td style='font-weight: bold;vertical-align: baseline;text-align:right;'>Socio Estratégico:</td>\r\n" +
		   "     <td>{P:socioEstrategico} </td>\r\n" +
		   " </tr>\r\n" +		   
		"</table>\r\n" +
		"<br/>\r\n"+
		"<p style='background-color: #0da5c4;font-family: sans-serif;font-size:11px;width:70px;display:inline-block'>PERIODO: </P><p style='font-family: sans-serif;font-size:11px;width:120px;display:inline-block'>{P:periodo}</p>\r\n"+
		"<p style='margin: 0 auto;font-size:10px;font-family: sans-serif;font-weight: bold;'>Seguimiento de acciones relacionadas a género</p>\n\r" +
		"<br/>\r\n";

	public static final String RESUMEN_SALVAGUARDA_PROYECTO="<p style='color: #0da5c4; font-family: sans-serif;font-size: 13;font-weight: bold;'>PROYECTO</p>\r\n" +
		     "<p class='preguntas'>{P:proyecto}</p>\r\n" +
		     "<p class='preguntas'>SOCIO IMPLEMENTADOR</p>\r\n" +
		     "{P:resumenSocioImplementador}\r\n" + 
		     "<p class='preguntas'>SOCIOS ESTRATEGICOS</p>\r\n" +
		     "{P:tablaResumenSociosEstrategicos}\r\n" + 		     
		     "<br/>\r\n";

	public static final String REPORTE_RESUMEN_GENERO_CONTENIDO=		     
		     "<p class='preguntas'>{P:tablaTemas}</p>\r\n" +
		     "<p class='preguntas'>{P:preguntaUno}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo1}</p>\r\n" + 		 
		     "<p class='preguntas'>{P:tablaUno}</p>\r\n" +
		     "<p class='preguntas'>{P:preguntaDos}</p>\r\n" +		     
		     "<p class='preguntas'>{P:siNo2}</p>\r\n" + 
		     "<p class='preguntas'>{P:tablaDos}</p>\r\n" +
		     "<p class='preguntas'>{P:preguntaTres}</p>\r\n" +
		     "<p class='preguntas'>{P:siNo3}</p>\r\n" + 
		     "<p class='preguntas'>{P:tablaTres}</p>\r\n" +		     
		     "<br/>\r\n" ;
}