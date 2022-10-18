package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		String time = timestr.substring(TIME_STARTINDEX, 13);
		String minutt = timestr.substring(14, 16);
		String sekund = timestr.substring(17, 19);
		
		hr = Integer.parseInt(time);
		min = Integer.parseInt(minutt);
		sec = Integer.parseInt(sekund);
		
		secs = (hr * 3600) + (min * 60) + sec;
		
		return secs;
		
		// TODO
		// OPPGAVE - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// OPPGAVE - SLUTT
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		Double latitude = Double.parseDouble(latitudeStr);
		Double longitude = Double.parseDouble(longitudeStr);
		Double elevation = Double.parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(toSeconds(timeStr), latitude, longitude, elevation);
		
		return gpspoint;
		

		// TODO - START ;
		
		//throw new UnsupportedOperationException(TODO.method());

		// OPPGAVE - SLUTT ;
	    
	}
	
}
