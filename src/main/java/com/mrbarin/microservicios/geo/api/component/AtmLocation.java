package com.mrbarin.microservicios.geo.api.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;

@Component
public class AtmLocation {

	private List<CajeroResponse> cajerosResponses;
	private static final String HTTPS_URL = "https://www.banamex.com/localizador/jsonP/json5.json";

	@PostConstruct
	void init() {
		URL url = null;
		HttpsURLConnection connection = null;

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

			System.out.println(br.readLine());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
