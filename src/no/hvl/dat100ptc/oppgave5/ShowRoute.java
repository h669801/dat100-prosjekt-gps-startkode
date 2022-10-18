package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {
		
		double xstep = xstep();
		double ystep = ystep();
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minLong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		for (int i = 0; i < gpspoints.length; i++) {
			setColor(0,255,0);
			int y = (int)((gpspoints[i].getLatitude()-minLat)*ystep);
			int x = (int)((gpspoints[i].getLongitude()-minLong)*xstep);
			drawCircle(x , (ybase-y) , 1);
		}
		
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int WEIGHT = 80;

		setColor(0,0,0);
		setFont("Courier",12);
		
		String format = "%-15s";
		String sep = ":";
		double mToKm = gpscomputer.totalDistance()/1000;
		
		String time = GPSUtils.formatTime(gpscomputer.totalTime());
		String time1 = (String.format(format, "Total Time") + sep + time);
		
		String distance = GPSUtils.formatDouble(mToKm);
		String distance1 = (String.format(format,"Total Distance") + sep + distance + " km");
		
		String elevation = GPSUtils.formatDouble(gpscomputer.totalElevation());
		String elevation1 = (String.format(format,"Total Elevation") + sep + elevation + " m");
		
		String speed = GPSUtils.formatDouble(gpscomputer.maxSpeed());
		String speed1 = (String.format(format,"Max speed") + sep + speed + " km/t");
		
		String avgSpeed = GPSUtils.formatDouble(gpscomputer.averageSpeed());
		String avgSpeed1 = (String.format(format,"Average speed") + sep + avgSpeed + " km/t");
		
		String energy = GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT));
		String energy1 = (String.format(format,"Energy") + sep + energy + " kcal");
		
		
		int i = 1;
		
		drawString(time1, MARGIN, TEXTDISTANCE);
		i++;
		
		drawString(distance1, MARGIN, TEXTDISTANCE*i);
		i++;
		
		drawString(elevation1, MARGIN,TEXTDISTANCE*i);
		i++;
		
		drawString(speed1, MARGIN,  TEXTDISTANCE*i);
		i++;
		
		drawString(avgSpeed1, MARGIN,  TEXTDISTANCE*i);
		i++;
		
		drawString(energy1, MARGIN,  TEXTDISTANCE*i);
//		i++;
		
//		drawString(gpscomputer.displayStatistics(), MARGIN, MAPYSIZE - MARGIN*i);
//		i++;
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

}
