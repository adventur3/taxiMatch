package xmu.mocom.path;

import org.jgrapht.Graph;

import xmu.mocom.taxiMatch.RoadNode;
import xmu.mocom.taxiMatch.RoadSegmentEdge;

public class CoreRouting {

	public static Path coreRoutingShortestPath(Graph<RoadNode, RoadSegmentEdge> g, RoadNode start, RoadNode target) {
		BidirectionalDijkstraData dijkstraData = Initialize(g, start, target);
	}
	
	public static BidirectionalDijkstraData Initialize(Graph<RoadNode, RoadSegmentEdge> g, RoadNode start, RoadNode target) {
		BidirectionalDijkstraData dijkstraData = Dijkstra.bidirectionalDijkstra(g, start, target);
		return dijkstraData;
	}
}

