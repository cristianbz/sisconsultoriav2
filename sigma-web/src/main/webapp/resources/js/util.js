function CambioURL(url) {

	document.getElementById('frame').src = url;
	document.getElementById('frame').height = "auto;";
	document.getElementById('frame').width = "800px";
}

function transformToAssocArray( prmstr ) {
	  var params = {};
	  var prmarr = prmstr.split("&");
	  for ( var i = 0; i < prmarr.length; i++) {
	      var tmparr = prmarr[i].split("=");
	      params[tmparr[0]] = tmparr[1];
	  }
	  return params;
	}

/** Inicia Utilería para leer lista de variables por get **/
function getSearchParameters() {
    var prmstr = window.location.search.substr(1);
    return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
}

/**
 * Muestra mensaje de guardado correctamente, en caso de que la variable completeSucess viene por get en 1.
 */
function showMessage(){
	var parameters = getSearchParameters();	
		
	if(parameters.complete == "1")
	{
		//$('#messages').puigrowl('show', [{severity: 'info', summary: 'Ok', detail: 'La tarea ha sido completada satisfactoriamente.'}]);
		$('#messages').html('<div class="ui-messages-info ui-corner-all"><span class="ui-messages-info-icon"/> <span class="ui-messages-info-summary">La tarea ha sido completada satisfactoriamente.</span> </div>');
	}
}

function getVarsUrl(){
    var url= location.search.replace("?", "");
    var arrUrl = url.split("&");
    var urlObj={};   
    for(var i=0; i<arrUrl.length; i++){
        var x= arrUrl[i].split("=");
        urlObj[x[0]]=x[1];
    }
    return urlObj;
}

function resizeIframe(obj) {
    obj.style.height = obj.contentWindow.document.body.scrollHeight + 300 + 'px';
}

function viewHideComponent(radio, component)
{					
	if(radio.value == 'true')
	{						
		PF(component).jq.css('display', '');
	}
	else if(radio.value == 'false')
	{	
		PF(component).jq.css('display', 'none');	
	}			
}

function numbersonly(myfield, e, dec) {
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
	keychar = String.fromCharCode(key);
	// control keys
	if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13) || (key == 27))
		return true;
	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
		return true;
	// decimal point jump
	else if (dec && (keychar == ".")) {
		myfield.form.elements[dec].focus();
		return false;
	} else
		return false;
}

function nobackbutton() {
	window.location.hash = "no-back-button";
	window.location.hash = "Again-No-back-button"; // chrome
	window.onhashchange = function() {
		window.location.hash = "no-back-button";
	}
}

PrimeFaces.locales["es"] = {
	    closeText: "Cerrar",
	    prevText: "Anterior",
	    nextText: "Siguiente",
	    monthNames: ["Enero","Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
	    monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
	    dayNames: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
	    dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
	    dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
	    weekHeader: "Semana",
	    firstDay: 0,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix: "",
	    timeOnlyTitle: "Solo hora",
	    timeText: "Tiempo",
	    hourText: "Hora",
	    minuteText: "Minuto",
	    secondText: "Segundo",
	    currentText: "Fecha actual",
	    ampm: false,
	    month: "Mes",
	    week: "Semana",
	    day: "Día",
	    allDayText : "Todo el día"
	};