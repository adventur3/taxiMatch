package xmu.mocom.taxiMatch;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;

public class RoadSegmentEdge extends DefaultWeightedEdge{
	private List<Long> distanceList;
	private long minDistance;
	
	public RoadSegmentEdge(List<Long> list, long minDistance){
		this.distanceList = list;
		this.minDistance = minDistance;
	}
	
	public List<Long> getDistanceList() {
		return this.distanceList;
	}
	
	public void setDistanceList(List<Long> list) {
		this.distanceList = list;
	}
	
	public long getMinDistance() {
		return minDistance;
	}
	
	public void setMinDistance(long distance) {
		this.minDistance = distance;
	}
}