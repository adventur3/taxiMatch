package xmu.mocom.taxiMatch;

public class SimClock {
	private long starttime;
	private long now;
	private int ut;
	
	public SimClock(long starttime, int ut) {
		this.starttime = starttime;
		this.now = starttime;
		this.ut = ut;
	}
	
	public SimClock(long starttime) {
		this.starttime = starttime;
		this.now = starttime;
		this.ut = 0;
	}
	
	public void add() {
		now += this.ut;
	}
	
	public void add(int ut) {
		now += ut;
	}
	
	public long getNow() {
		return now;
	}
	
	public long getStarttime() {
		return starttime;
	}
	
	public int getMinuteId() {
		long time = now - starttime;
		int minute_id = (int) (((time/1000)%86400)/600);
		return minute_id;
	}
	
	public int getUt() {
		return ut;
	}
}