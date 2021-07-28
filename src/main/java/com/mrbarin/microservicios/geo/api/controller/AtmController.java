package com.mrbarin.microservicios.geo.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrbarin.microservicios.geo.api.dto.CajeroLocateParams;
import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;
import com.mrbarin.microservicios.geo.api.service.AtmService;

@RestController
public class AtmController {

	@Autowired
	private AtmService atmService;

	@Autowired
	private ObjectMapper mapper;

	@GetMapping("/cajeros-cerca")
	public List<CajeroResponse> cajerosCerca(@RequestBody String json)
			throws IOException {
		return atmService.buscarCajeros(mapper.readValue(json, CajeroLocateParams.class));
	}
}
