package com.mrbarin.microservicios.geo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbarin.microservicios.geo.api.component.AtmLocation;
import com.mrbarin.microservicios.geo.api.dto.CajeroLocateParams;
import com.mrbarin.microservicios.geo.api.dto.CajeroResponse;

@Service
public class AtmService {
	
	@Autowired
	private AtmLocation location;
	
	public List<CajeroResponse> buscarCajeros(CajeroLocateParams params){
		return location.getTop10CajerosCerca(params);
	}
}
