package xmu.mocom.path;

import xmu.mocom.taxiMatch.RoadNode;

/*
 * the data structure dijkstraNode have some more attributes can be used in dijkstra search, which roadNode do not have 
 */
public class DijkstraNode{
	private long current_id;
	//RoadNode roadNode;
	private RoadNode parentNode;
	private long distance;
	private long distanceFromParent;
	private long arrivalTime;
	private boolean is_setted;
	private boolean isOnS;
	private boolean isOnT;
	
	public DijkstraNode(long current_id) {
		this.current_id = current_id;
		//this.roadNode = roadNode;
		parentNode = null;
		distance = 999999999;
		distanceFromParent = 999999999;
		is_setted = false;
		isOnS = false;
		isOnT = false;
	}
	
//	public void setRoadNode(RoadNode roadNode) {
//		this.roadNode = roadNode;
//	}
//	
//	public RoadNode getRoadNode() {
//		return this.roadNode;
//	}
	
	public long getCurrentId() {
		return this.current_id;
	}
	
	public void setParentNode(RoadNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public RoadNode getParentNode() {
		return this.parentNode;
	}
	
	public void setDistance(long cost_time) {
		this.distance = cost_time;
	}
	
	public long getDistance() {
		return this.distance;
	}
	
	public void setDistanceFromParent(long cost_time) {
		this.distanceFromParent = cost_time;
	}
	
	public long getDistanceFromParent() {
		return this.distanceFromParent;
	}
	
	public void setArrivalTime(long time) {
		this.arrivalTime = time;
	}
	
	public long getArrivalTime() {
		return this.arrivalTime;
	}
	
	public void setSetted() {
		this.is_setted = true;
	}
	
	public void setUnsetted() {
		this.is_setted = false;
	}
	
	public boolean isSetted() {
		return this.is_setted;
	}
	
	public void setOnS() {
		this.isOnS = true;
	}
	
	public void setOutOfS() {
		this.isOnS = false;
	}
	
	public boolean isOnS() {
		return this.isOnS;
	}
	
	public void setOnT() {
		this.isOnT = true;
	}
	
	public void setOutOfT() {
		this.isOnT = false;
	}
	
	public boolean isOnT() {
		return this.isOnT;
	}
	
	
}