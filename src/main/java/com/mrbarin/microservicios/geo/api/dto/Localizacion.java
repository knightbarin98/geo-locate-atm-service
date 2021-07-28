
package com.mrbarin.microservicios.geo.api.dto;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Generated("jsonschema2pojo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Localizacion {

    private Float lat;
    private Float lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
