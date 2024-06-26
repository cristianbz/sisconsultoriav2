/**
 * 
 */

function repoEjecPresCalcTotalCat(id) {
	rowObj = id.split(":")[2];
	rowCat = id.split(":")[4];
	valYear1 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year1_input').value;
	valYear2 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year2_input').value;
	valYear3 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year3_input').value;
	valYear4 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year4_input').value;
	valYear5 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year5_input').value;
	valYear6 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year6_input').value;
	valYear7 = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
			+ rowCat + ':year7_input').value;
	
	
	valYear1 = valYear1 === '' ? '0' : valYear1;
	valYear2 = valYear2 === '' ? '0' : valYear2;
	valYear3 = valYear3 === '' ? '0' : valYear3;
	valYear4 = valYear4 === '' ? '0' : valYear4;
	valYear5 = valYear5 === '' ? '0' : valYear5;
	valYear6 = valYear6 === '' ? '0' : valYear6;
	valYear7 = valYear7 === '' ? '0' : valYear7;
	year1 = parseFloat(valYear1.replaceAll('.', '').replace(',', '.'));
	year2 = parseFloat(valYear2.replaceAll('.', '').replace(',', '.'));
	year3 = parseFloat(valYear3.replaceAll('.', '').replace(',', '.'));
	year4 = parseFloat(valYear4.replaceAll('.', '').replace(',', '.'));
	year5 = parseFloat(valYear5.replaceAll('.', '').replace(',', '.'));
	year6 = parseFloat(valYear6.replaceAll('.', '').replace(',', '.'));
	year7 = parseFloat(valYear7.replaceAll('.', '').replace(',', '.'));
	total = year1 + year2 + year3 + year4 + year5 + year6 + year7;
	idTotal = 'frm:rep:' + rowObj + ':lstObjCat:' + rowCat + ':total';
	idLblTotal = 'frm:rep:' + rowObj + ':lstObjCat:' + rowCat + ':lblTotal';
	$('[id="' + idTotal + '"]').val(total);
	document.getElementById(idLblTotal).innerHTML = total
			.toLocaleString('es-EC');
	tipo = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:' + rowCat
			+ ':tipo').value;
	idTotAcumulado = '';
	idLblTotAcumulado = '';
	if (tipo === 'ADM') {
		idTotAcumulado = 'frm:lstAcumulados:' + rowObj + ':montoAdministrativo';
		idLblTotAcumulado = 'frm:lstAcumulados:' + rowObj
				+ ':lblMontoAdministrativo';
	} else {
		idTotAcumulado = 'frm:lstAcumulados:' + rowObj + ':montoOperativo';
		idLblTotAcumulado = 'frm:lstAcumulados:' + rowObj
				+ ':lblMontoOperativo';
	}
	repoEjecPresCalAcumulados(tipo, rowObj, idTotAcumulado, idLblTotAcumulado);
	repoEjecPresCalcAvance2(rowObj);
	repoEjecPresCalAvanceTotal();
}

function repoEjecPresCalAcumulados(tipo, rowObj, idTotAc, idLblTotAc) {
	total = 0;
	for (var x = 0; x < 10; x++) {
		tipoCat = document.getElementById('frm:rep:' + rowObj + ':lstObjCat:'
				+ x + ':tipo').value;
		if (tipo === tipoCat) {
			valTotal = document.getElementById('frm:rep:' + rowObj
					+ ':lstObjCat:' + x + ':total').value;
			valTotal = valTotal === '' ? '0' : valTotal;
			total = total + parseFloat(valTotal);
		}
	}

	document.getElementById(idTotAc).value = total;
	document.getElementById(idLblTotAc).innerHTML = total.toLocaleString('es-EC');
}

function repoEjecPresCalcAvance2(rowObj) {
	valorAdm = document.getElementById('frm:lstAcumulados:' + rowObj
			+ ':montoAdministrativo').value;
	valorOpe = document.getElementById('frm:lstAcumulados:' + rowObj
			+ ':montoOperativo').value;
	;
	valorAdm = valorAdm === '' ? 0 : valorAdm;
	valorOpe = valorOpe === '' ? 0 : valorOpe;

	montoEjecutado = parseFloat(valorAdm) + parseFloat(valorOpe);

	idEj = 'frm:lstAcumulados:' + rowObj + ':montoEjecutado';
	$('[id="' + idEj + '"]').val(montoEjecutado);
	document.getElementById('frm:lstAcumulados:' + rowObj
			+ ':lblMontoEjecutado').innerHTML = montoEjecutado
			.toLocaleString('es-EC');

	num = (montoEjecutado / parseFloat((document
			.getElementById('frm:lstAcumulados:' + rowObj + ':montoPlanificado').value))) * 100;

	idAv = 'frm:lstAcumulados:' + rowObj + ':avance';
	$('[id="' + idAv + '"]').val(Math.round(num));
	document.getElementById(idAv).readOnly = true;

}

function repoEjecPresCalAvanceTotal() {
	// var table = $('[id="frm:lstAcumulados"]').DataTable();
	numObj = PF('lstAcumulados').tbody[0].children.length;

	// numObj=Number(document.getElementById('frm:numObj').value);
	total = 0;
	totalPl = 0;
	totalEj = 0;
	for (var i = 0; i < numObj; i++) {
		idPl = 'frm:lstAcumulados:' + i + ':montoPlanificado';
		idEj = 'frm:lstAcumulados:' + i + ':montoEjecutado';
		valPl = document.getElementById(idPl).value;
		valEj = document.getElementById(idEj).value;
		valPl = valPl === '' ? '0' : valPl;
		valEj = valEj === '' ? '0' : valEj;
		totalPl = totalPl
				+ parseFloat(valPl);
		totalEj = totalEj + parseFloat(valEj);
	}
	total = Math.round(totalEj * 100 / totalPl);
	document.getElementById('frm:avancePresTotal').value = total;
	document.getElementById('frm:avancePresTotal').readOnly = true;
}

function repoEjecPresCargarValoresEnLabels() {
	numObj = PF('lstAcumulados').tbody[0].children.length;
	for (var i = 0; i < numObj; i++) {
		for (var j = 0; j < 10; j++) {
			valTotal = document.getElementById('frm:rep:' + i + ':lstObjCat:'
					+ j + ':total').value;
			if (valTotal.length > 0) {
				document.getElementById('frm:rep:' + i + ':lstObjCat:' + j
						+ ':lblTotal').innerHTML = parseFloat(valTotal)
						.toLocaleString('es-EC');
			}
		}
		valPl = document.getElementById('frm:lstAcumulados:' + i
				+ ':montoPlanificado').value;
		if (valPl.length > 0) {
			document.getElementById('frm:lstAcumulados:' + i
					+ ':lblMontoPlanificado').innerHTML = parseFloat(valPl)
					.toLocaleString('es-EC');
		}
		valAdm = document.getElementById('frm:lstAcumulados:' + i
				+ ':montoAdministrativo').value;

		if (valAdm.length > 0) {
			document.getElementById('frm:lstAcumulados:' + i
					+ ':lblMontoAdministrativo').innerHTML = parseFloat(valAdm)
					.toLocaleString('es-EC');
		}
		valOpe = document.getElementById('frm:lstAcumulados:' + i
				+ ':montoOperativo').value;
		if (valOpe.length > 0) {
			document.getElementById('frm:lstAcumulados:' + i
					+ ':lblMontoOperativo').innerHTML = parseFloat(valOpe)
					.toLocaleString('es-EC');
		}
		valEj = document.getElementById('frm:lstAcumulados:' + i
				+ ':montoEjecutado').value;
		if (valEj.length > 0) {
			document.getElementById('frm:lstAcumulados:' + i
					+ ':lblMontoEjecutado').innerHTML = parseFloat(valEj)
					.toLocaleString('es-EC');
		}
	}
}

function repoAccCalAvanceDesdeValorAlcanzado(id, valor, numAcc) {
	rowAcc = id.split(":")[2];
	valorNum="0";
	if(valor!==null&&valor!==''){
		valorNum=valor;
	}
	valorMeta = parseFloat(document.getElementById('frm:lstAcc:' + rowAcc + ':valMeta').value);
	valorAlcanzado = parseFloat(valorNum.replace('.', '').replace(',', '.'));
	idAvanceAcc = 'frm:lstAcc:' + rowAcc + ':avance';
	porcAvance=Math.round((valorAlcanzado / valorMeta) * 100);
	$('[id="' + idAvanceAcc + '"]').val(porcAvance);
	document.getElementById(idAvanceAcc).value = porcAvance;
	document.getElementById(idAvanceAcc).readOnly = true;
	repoAccCalAvanceTotal(numAcc);

}

function repoAccCalAvanceTotal(numAcc) {
	total = 0;
	for (var i = 0; i < Number(numAcc); i++) {
		avanceAcc = document.getElementById('frm:lstAcc:' + i + ':avance').value;
		if (avanceAcc == null || avanceAcc === '') {
			avanceAcc = 0;
		}
		pondAcc = document.getElementById('frm:lstAcc:' + i + ':pond').value;
		if (pondAcc == null || pondAcc === '') {
			pondAcc = 0;
		}
		num = parseFloat(avanceAcc) * parseFloat(pondAcc) / 100;
		total = total + Math.round(num);

	}
	document.getElementById('frm:avanceTotal').value = total;
	document.getElementById('frm:avanceTotal').readOnly = true;

}

function saveAsImg(component) {
	var out = document.createElement('div');
	out.appendChild(PF(component).exportAsImage());
	var innerHTML = out.innerHTML;
	document.write(innerHTML);

	/*
	 * var openWindow = window.open('', 'Report', '');
	 * openWindow.document.write(innerHTML); openWindow.document.close();
	 * openWindow.focus(); openWindow.print(); openWindow.close();
	 */
}

function prepararImagenesCharts() {
	// var canvas = document.getElementById("frm:chart1_canvas");
	// var img = canvas.toDataURL("image/png");
	// var newImg=document.createElement('img');
	// console.log(img);
	// newImg.src=img;
	// $('#output1').empty().append(img);
	// $('#output1').empty().append(newImg);
	// document.body.appendChild(newImg);
	// document.write('<img src="'+img+'"/>');

	$('#frm\\:output1').empty().append(PF('chart1').exportAsImage());
	$('#frm\\:output2').empty().append(PF('chart2').exportAsImage());
	$('#frm\\:output3').empty().append(PF('chart3').exportAsImage());
	// PF('dlg').show();
}

function chartExtender() {
	var options = {
		plugins : [ ChartDataLabels ],
		options : {
			plugins : {
				// Change options for ALL labels of THIS CHART
				datalabels : {
					color : '#36A2EB'
				}
			}
		},
		data : {
			datasets : [ {
				// Change options only for labels of THIS DATASET
				datalabels : {
					color : '#FFCE56'
				}
			} ]
		}
	};

	// merge all options into the main chart options
	$.extend(true, this.cfg.config, options);
};
// La siguiente programación es para cerrar un dialog luego de 3 segundos
// útil para acciones con ajax=false
var myVarDlgEnProc2;

function dlgEnProc2Function()
{
	myVarDlgEnProc2=setTimeout(function(){ PF('dlgEnProc2').hide()},3000);
}

function dlgEnProc2StopFunction()
{
clearTimeout(myVarDlgEnProc2);
}
