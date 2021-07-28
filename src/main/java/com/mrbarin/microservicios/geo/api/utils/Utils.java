package com.mrbarin.microservicios.geo.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;
import com.mrbarin.microservicios.geo.api.dto.Localizacion;

public class Utils {
	
	private static final String REMOVE_STRING = "jsonCallback";
	private static final int ID = 0;
	private static final int UBICACION = 2;
	private static final int NOMBRE_REFERENCIA = 3;
	private static final int DOMICILIO = 4;
	private static final int LNG = 15;
	private static final int LAT = 16;
	

	public static List<CajeroResponse> obtenerListaCajeros(String json) 
			throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<CajeroResponse> cajeros = new ArrayList<>();
		
		JsonNode jsonNode = objectMapper.readTree(cleanJson(json));
		jsonNode = jsonNode.get("Servicios");
		
		jsonNode.forEach(
				(servicioNodes)-> servicioNodes.forEach(
						(children)-> children.forEach(
								(nextLevel)->  cajeros.add(new CajeroResponse(
										Integer.parseInt(nextLevel.get(ID).asText()), 
										nextLevel.get(UBICACION).asText(), 
										nextLevel.get(NOMBRE_REFERENCIA).asText(), 
										nextLevel.get(DOMICILIO).asText(), new Localizacion(
												Float.valueOf(nextLevel.get(LAT).asText()),
												Float.valueOf(nextLevel.get(LNG).asText()),null), null))
										)));
		
		return cajeros;
	}
	
	private static String cleanJson(String json) {
		StringBuilder builder = new StringBuilder(json);
		
		//remove jsonCallback
		int index = builder.indexOf(REMOVE_STRING);
		if (index != -1) {
	        builder.delete(index, index + REMOVE_STRING.length());
	    }
		
		index = builder.indexOf("(");
		if (index != -1) {
	        builder.delete(index, index + "(".length());
	    }
		
		index = builder.indexOf(");");
		if (index != -1) {
	        builder.delete(index, index + REMOVE_STRING.length());
	    }
		

		return builder.toString();
	}

}
