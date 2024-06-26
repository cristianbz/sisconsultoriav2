/**
 * 
 */
var map = "";
var layerControl="";
function inicializarMapa() {
	map = L.map('mapa1').setView([ -1.457945, -77.91373 ], 7);
}

function cargarMapa() {
	map.remove();
	inicializarMapa();
	var osm = L
			.tileLayer(
					'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
					{
						attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
						maxZoom : 18
					});

	var mapBox = L
			.tileLayer(
					'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}',
					{
						attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
						maxZoom : 18,
						id : 'mapbox/satellite-v9',
						tileSize : 512,
						zoomOffset : -1,
						accessToken : 'pk.eyJ1IjoiZGd1YW5vIiwiYSI6ImNrbzA0cWc0bTAyZXkyb3BhcWVmaHZyczgifQ.FwMog-FS61yx9NGynjvT1g'
					});

	osm.addTo(map);
	mapBox.addTo(map);

	L.control.scale().addTo(map);
	var layers = [];
	var listProjectsMetadata1 = JSON.parse(document
			.getElementById("frm:metadataNombreCapas").value);
	var listProjectsMetadata2 = JSON.parse(document
			.getElementById("frm:metadataDescripcionFeatures").value);
	var listProjectsDataGeoJson = JSON.parse(document
			.getElementById("frm:data").value);

	for (var x = 0; x < listProjectsDataGeoJson.length; x++) {
		var dataGeoJson = listProjectsDataGeoJson[x];

		var arrayFeatures = [];

		if (dataGeoJson[0].type === 'Point') {
			for (var i = 0; i < dataGeoJson.length; i++) {
				var geojsonFeature1 = {
					"type" : "Feature",
					"properties" : {
						"name" : " ",
						"amenity" : " ",
						"idx" : x,
						"icon_name" : listProjectsMetadata1[x].icono,
						"popupContent" : listProjectsMetadata2[x][i]
					},
					"geometry" : dataGeoJson[i]

				};
				arrayFeatures[i] = L
						.geoJSON(
								geojsonFeature1,
								{
									pointToLayer : function(feature, latlng) {
										return L
												.marker(
														latlng,
														{
															icon : getSigmaIcon(geojsonFeature1.properties.icon_name)
														});
									}// The basic style
									,
									style : style,
									onEachFeature : onEachFeature
								});
			}
		} else {
			for (var i = 0; i < dataGeoJson.length; i++) {
				var geojsonFeature1 = {
					"type" : "Feature",
					"properties" : {
						"name" : " ",
						"amenity" : " ",
						"idx" : x,
						"code" : "A",
						"color" : listProjectsMetadata1[x].color,
						"popupContent" : listProjectsMetadata2[x][i]
					},
					"geometry" : dataGeoJson[i]

				};
				arrayFeatures[i] = L.geoJSON(geojsonFeature1, {
					style : style,
					onEachFeature : onEachFeature
				});

			}
		}

		layers[x] = L.layerGroup(arrayFeatures);
		layers[x].addTo(map);
	}
	
	var baseMaps = {
		"Sin Mapa Base":L.tileLayer(''),
		"MapBox Satellite" : mapBox,
		"OpenStreetMap" : osm
	};
	var overlayMaps = {};
	for (var y = 0; y < listProjectsMetadata1.length; y++) {
		overlayMaps[listProjectsMetadata1[y].nombre] = layers[y];
	}

	layerControl=L.control.layers(baseMaps, overlayMaps).addTo(map);

}

function onEachFeature(feature, layer) {
	// does this feature have a property named popupContent?
	if (feature.properties && feature.properties.popupContent) {
		layer.bindPopup(feature.properties.popupContent);
	}
}

function style(feature) {
	return {
		fillColor : feature.properties.color,
		weight : 2,
		opacity : 1,
		color : '#000000',
		dashArray : '3',
		fillOpacity : 0.4
	};
}

var SigmaIcon = L.Icon.extend({
	options : {
		shadowUrl : '../../images/icons/shadow1.png',
		iconSize : [ 32, 32 ],
		shadowSize : [ 33, 18 ],
		iconAnchor : [ 12, 0 ],
		shadowAnchor : [ 0, -24 ],
		popupAnchor : [ -3, -76 ]
	}
});

function getSigmaIcon(icon_name) {
	var newIcon = new SigmaIcon({
		iconUrl : '../../images/icons/' + icon_name + '.png'
	});
	return newIcon;
}

function getColorFeature(i) {
	return i < 1 ? '#fc8d59' : i < 2 ? '#DBDB42' : i < 3 ? '#91cf60'
			: i < 4 ? '#FC4E2A' : i < 5 ? '#FD8D3C' : i < 6 ? '#FEB24C'
					: i < 7 ? '#FED976' : '#FFEDA0';
}

function consumoGeoServiciosWfs(owsrootUrl, pversion, prequest,
		ptypeName, poutputFormat, pnameColumnDesc,pnombreCapa) {

	var parameters0 = {
		service : 'wfs',
		version : pversion,
		request : prequest,
		typeName : ptypeName,
		outputFormat : poutputFormat,
		srsName : 'EPSG:4326'

	};

	var parameters = L.Util.extend(parameters0);

	var URL = owsrootUrl + L.Util.getParamString(parameters);

	$.ajax({
				url : URL,
				success : function(data) {
					try {
						var geojson = new L.geoJson(data, {
							style : {"color" : "red","weight" : 2},
							onEachFeature : function(feature, layer) {
								layer.bindPopup("Has hecho click en: "+ feature.properties[pnameColumnDesc]);
							}
						}).addTo(map);
						var newLayer = L.layerGroup([geojson]);
						layerControl.addOverlay(newLayer, pnombreCapa);
					} catch (err) {
						alert("Hay un error en los parámetros o el GeoServicio no está disponible."+err);
					}

				}
			});
}

function consumoGeoServiciosWms(owsrootUrl, players, pformat,
		ptransparent, pversion, pattribution,pnombreCapa) {
	var layersWms = L.tileLayer.wms(owsrootUrl+'?SERVICE=WMS&', {
		   layers: players,
		   format: pformat,
		   transparent: ptransparent,
		   version: pversion,
		   attribution: pattribution,
		   srsName : 'EPSG:4326'
		}).addTo(map);
	
	layerControl.addBaseLayer(layersWms, pnombreCapa);
	
}
