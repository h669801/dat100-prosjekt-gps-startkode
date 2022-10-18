package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
//		System.out.println(distance);
		return distance;
			
		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			
			if (gpspoints[i].getElevation() < gpspoints[i+1].getElevation()) {
				elevation += (gpspoints[i+1].getElevation() - gpspoints[i].getElevation());
			}
			
		}
		return elevation;

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int time = 0;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			time += (gpspoints[i+1].getTime() - gpspoints[i].getTime());
		}
		
		return time;

		//throw new UnsupportedOperationException(TODO.method());

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double f = 0;
		double[] speed = new double[gpspoints.length-1];
//		GPSUtils[] test = new GPSUtils[0];
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			f = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			speed[i] = f;
			
		}
		return speed;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		for (int i = 0; i < gpspoints.length; i++) {
			maxspeed = GPSUtils.findMax(speeds());
		}
		
//		System.out.println(" max speed " + maxspeed);
		return maxspeed;
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		average = (totalDistance()/totalTime())*3.6;
//		for (int i = 0; i < gpspoints.length-1; i++) {
			
//			speeds = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
//			average += speeds;
//			avg = average / (i+1);
//		}
		
		return average;
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		
//		met = kcal / (weight * secs);
		
		if (speedmph < 10) {
			met = 4;
		}
		if (speedmph >= 10 && speedmph < 12) {
			met = 6;
		}
		if (speedmph >= 12 && speedmph < 14) {
			met = 8;
		}
		if (speedmph >= 14 && speedmph < 16) {
			met = 10;
		}
		if (speedmph >= 16 && speedmph < 20) {
			met = 12;
		}
		if (speedmph >= 20) {
			met = 16;
		}
		
		kcal = (met * weight * secs);
//		System.out.println(kcal);
		
		return kcal/3600;

		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		double speed = averageSpeed();
		int tid = totalTime();
		
		totalkcal = kcal(weight,tid,speed);
		
//		System.out.println(totalkcal);
		
		return totalkcal;
		
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		String format = "%-15s";
		String sep = ":";
		double mToKm = totalDistance()/1000;
		
		String time = GPSUtils.formatTime(totalTime());
		System.out.println(String.format(format, "Total Time") + sep + time);
		
		String distance = GPSUtils.formatDouble(mToKm);
		System.out.println(String.format(format,"Total Distance") + sep + distance + " km");
		
		String elevation = GPSUtils.formatDouble(totalElevation());
		System.out.println(String.format(format,"Total Elevation") + sep + elevation + " m");
		
		String speed = GPSUtils.formatDouble(maxSpeed());
		System.out.println(String.format(format,"Max speed") + sep + speed + " km/t");
		
		String avgSpeed = GPSUtils.formatDouble(averageSpeed());
		System.out.println(String.format(format,"Average speed") + sep + avgSpeed + " km/t");
		
		String energy = GPSUtils.formatDouble(totalKcal(WEIGHT));
		System.out.println(String.format(format,"Energy") + sep + energy + " kcal");
		

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

}
