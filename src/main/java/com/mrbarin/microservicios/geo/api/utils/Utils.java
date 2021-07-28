package com.mrbarin.microservicios.geo.api.utils;

import java.util.List;

import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;

public class Utils {
	
	private final String REMOVE_STRING = "jsonCallback";

	private static List<CajeroResponse> obtenerListaCajeros(String json) {

		return null;
	}
	
	private String cleanJson(String json) {
		StringBuilder builder = new StringBuilder(json);
		
		//remove jsonCallback
		int index = builder.indexOf(REMOVE_STRING, 0);
		if (index != -1) {
	        builder.delete(index, index + REMOVE_STRING.length());
	    }
		
		
		
		
		
		return "";
	}

}
