package com.mrbarin.microservicios.geo.api.dto;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CajeroLocateParams {

	private Float lat;
	
    private Float lng;
	
	private String cp;
	
	private String estado;
	
	
}
