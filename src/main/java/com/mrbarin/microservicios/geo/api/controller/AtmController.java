package com.mrbarin.microservicios.geo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.mrbarin.microservicios.geo.api.service.AtmService;

@RestController
public class AtmController {
	
	@Autowired
	private AtmService atmService;
	
}
