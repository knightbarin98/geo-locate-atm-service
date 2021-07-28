package com.mrbarin.microservicios.geo.api.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.mrbarin.microservicios.geo.api.dto.CajeroLocateParams;
import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;
import com.mrbarin.microservicios.geo.api.utils.Utils;

@Component
public class AtmLocation {

	private List<CajeroResponse> cajerosResponses;
	private static final String HTTPS_URL = "https://www.banamex.com/localizador/jsonP/json5.json";
	
	public List<CajeroResponse> getTop10CajerosCerca(CajeroLocateParams cajero){
		return cajerosResponses.stream().filter((cajeroRes)->{
			return distance(
					(double)cajeroRes.getLocalizacion().getLat(),
					(double)cajeroRes.getLocalizacion().getLng(),
					(double)cajero.getLat(),
					(double)cajero.getLng(),"K") < 2.0;
		}).collect(Collectors.toList());
	}
	
	
	private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}

	@PostConstruct
	void init() {
		URL url = null;
		HttpsURLConnection connection = null;
		String json = "";
		try {
			url = new URL(HTTPS_URL);
			connection = (HttpsURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("URL mal formado, cambiar parametros e intentar nuevamente");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (InputStream inputStream = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr)) {
				
			json = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!json.isEmpty()) {
			try {
				cajerosResponses = Utils.obtenerListaCajeros(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			cajerosResponses = null;
		}
		
		
	}
}
