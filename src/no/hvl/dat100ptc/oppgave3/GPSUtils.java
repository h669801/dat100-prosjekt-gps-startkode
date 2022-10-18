package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] latitudes = new double [gpspoints.length];
		
		for (int i = 0; i<gpspoints.length; i++) {
			latitudes [i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] longitudes = new double [gpspoints.length];
		
		for (int i = 0; i<gpspoints.length; i++) {
			longitudes [i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
//		double dLat = (latitude2 - latitude1) * Math.PI / 180.0;
//		double dLon = (longitude2 - longitude1) * Math.PI / 180.0;

		double a = pow(sin(toRadians(latitude2-latitude1) / 2), 2) + pow(sin(toRadians(longitude2-longitude1) / 2), 2) * cos(toRadians(latitude1)) * cos(toRadians(latitude2));
		double c = 2 * (atan2(sqrt(a), sqrt(1-a)));
		
		d = R * c;
		
		return d;

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		
		
		int tid1 = gpspoint1.getTime();
		int tid2 = gpspoint2.getTime();
		
		double p = (distance(gpspoint1, gpspoint2))*3.6;
		
		secs = tid2 - tid1;
		speed = p / secs;
		
		return speed;
		
//		double lat1 = gpspoint1.getLatitude();
//		double long1 = gpspoint1.getLongitude();
//		double lat2 = gpspoint2.getLatitude();
//		double long2 = gpspoint2.getLongitude();
//		
//		double dist = lat2 - lat1 + long2 - long1;
		
		//double g1 = distance(lat1, long1);

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int hh = secs / 3600;
		int mm = (secs % 3600) / 60;
		int ss = secs % 60;
		

		String format = String.format("%02d" + TIMESEP + "%02d" + TIMESEP + "%02d", hh, mm ,ss);
		timestr = String.format("%10s",format);

		
		
		return timestr;
		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		String format = String.format("%.2f", d).replace(",",".");
		str = String.format("%10s", format);
		
		return str;

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
