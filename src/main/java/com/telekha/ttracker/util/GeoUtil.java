package com.telekha.ttracker.util;

import org.springframework.stereotype.Service;

@Service
public class GeoUtil {

	private final Double Rk = 6373D; // mean radius of the earth (km) at 39 degrees from the equator


	public boolean isWithinRange(Double lat1, Double long1, Double lat2, Double long2, Integer notifKM) {
		Double dlat1,dlong1,dlat2,dlong2,difflat,difflon,a,c,dk;
		dlat1 = deg2rad(lat1);
		dlong1 = deg2rad(long1);
		dlat2 = deg2rad(lat2);
		dlong2 = deg2rad(long2);

		difflat = dlat2 - dlat1;
		difflon = dlong2 - dlong1;

		a  = Math.pow(Math.sin(difflat/2),2) + Math.cos(dlat1) * Math.cos(dlat2) * Math.pow(Math.sin(difflon/2),2);
		c  = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a)); // great circle distance in radians
		dk = c * Rk; // great circle distance in km

		Long dist = round(dk);
		
		if(dist <= notifKM) {
			return true;
		}
		else {
			return false;
		}
	}

	// convert degrees to radians
	private Double deg2rad(Double deg) {
		return deg * Math.PI/180; // radians = degrees * pi/180
	}

	// round to the nearest 1/1000
	private Long round(Double x) {
		return Math.round( x * 1000) / 1000;
	}
}
