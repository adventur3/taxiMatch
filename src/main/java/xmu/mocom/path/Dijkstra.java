package xmu.mocom.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graph;

import xmu.mocom.taxiMatch.RoadNode;
import xmu.mocom.taxiMatch.RoadSegmentEdge;
import xmu.mocom.taxiMatch.SimClock;

/*
 * Dijkstra Algorithm 
 */
public class Dijkstra {
	
	public static long CURRENT_ID = 0;
	
	/*
	 * Bidirectional Dijkstra Algorithm,return the BidirectionalDijkstraData when the set S and T have intersection or priority queues are empty
	 * 
	 * @param g the roadnet
	 * @param start the start node on the roadnet
	 * @param target the target node on the roadnet
	 * @return BidirectionalDijkstraData
	 */
	public static BidirectionalDijkstraData bidirectionalDijkstra(Graph<RoadNode, RoadSegmentEdge> g, RoadNode start, RoadNode target, SimClock clock) {
		//init
		BidirectionalDijkstraData dijkstraData = new BidirectionalDijkstraData();
		long current_id = CURRENT_ID ++;
		start.setDijkstraNode(new DijkstraNode(current_id));
		start.getDijkstraNode().setDistance(0);
		start.getDijkstraNode().setDistanceFromParent(0);
		start.getDijkstraNode().setArrivalTime(clock.getNow());
		
		while(!dijkstraData.isPriorityQueueEmpty()) {
			RoadNode rnode = dijkstraData.pollPriorityQueue();
			
		}
		
		
		
		
		return dijkstraData;
	}
	
	public static void init(Graph<RoadNode, RoadSegmentEdge> g, BidirectionalDijkstraData dijkstraData, RoadNode start, RoadNode target, SimClock clock) {
		
		
	}
	
	public static RoadNode updatePriorityQueue(Graph<RoadNode, RoadSegmentEdge> g, BidirectionalDijkstraData dijkstraData, RoadNode roadNode, RoadNode source, RoadNode target, long current_id, SimClock clock) {
		//get the all connected edge of roadNode
		Set<RoadSegmentEdge> edgeSet = g.edgesOf(roadNode);
		Iterator it = edgeSet.iterator();
		Set<RoadNode> nextEdgeSet = new HashSet<RoadNode>();
		while(it.hasNext()) {
			RoadSegmentEdge nextEdge = (RoadSegmentEdge) it.next();
			//get the edge start from roadNode
			if(g.getEdgeSource(nextEdge) == roadNode) {
				long edge_distance = 0;
				if(roadNode.getDijkstraNode().getParentNode().getDijkstraNode().isOnS()) {
					if(roadNode.getDijkstraNode().isOnS()) {
						continue;
					}
					edge_distance = nextEdge.getDistanceList().get(clock.getMinuteId());
				}else {
					if(roadNode.getDijkstraNode().isOnT()) {
						continue;
					}
					edge_distance = nextEdge.getMinDistance();
				}
				//get the target node
				RoadNode targetNode = g.getEdgeTarget(nextEdge);
				if(targetNode == source || targetNode ==target) {
					return targetNode;
				}
				if(targetNode.getDijkstraNode() == null ){
					targetNode.setDijkstraNode(new DijkstraNode(current_id));
				}
				if(targetNode.getDijkstraNode().getCurrentId() != current_id){
					targetNode.setDijkstraNode(new DijkstraNode(current_id));
					targetNode.getDijkstraNode().setParentNode(roadNode);
					targetNode.getDijkstraNode().setDistance(roadNode.getDijkstraNode().getDistance() + edge_distance);
					targetNode.getDijkstraNode().setDistanceFromParent(edge_distance);
					targetNode.getDijkstraNode().setArrivalTime(roadNode.getDijkstraNode().getArrivalTime() + edge_distance);
					dijkstraData.addPriorityQueue(targetNode);
				}else{
					//比较distance，判断是否更新
					long temp_distance = roadNode.getDijkstraNode().getDistance() + edge_distance;
					if( temp_distance < targetNode.getDijkstraNode().getDistance() ) {
						targetNode.getDijkstraNode().setParentNode(roadNode);
						roadNode.getDijkstraNode().setArrivalTime(roadNode.getDijkstraNode().getArrivalTime()+edge_distance);
						roadNode.getDijkstraNode().setDistance(temp_distance);
						roadNode.getDijkstraNode().setDistanceFromParent(edge_distance);
					}//if( temp_distance < targetNode.getDijkstraNode().getDistance())
				}//else
				//DijkstraNode nextDijkNode = new DijkstraNode();
			}//if(g.getEdgeSource(nextEdge) == roadNode)
		}//while(it.hasNext()) 
		return null;
	}
	
}//class

/*
 * the data structure that bidirectionalDijkstra search need to use,
 * including foward search set S, backward search set T, priority queues...
 */
class BidirectionalDijkstraData{
	public Set<RoadNode> S;
	public Set<RoadNode> T;
	public List<RoadNode> forward_priority_queue;
	public List<RoadNode> backward_priority_queue;
	public LinkedList<RoadNode> priority_queue;
	
	public BidirectionalDijkstraData(){
		this.S = new HashSet<RoadNode>();
		this.T = new HashSet<RoadNode>();
		this.forward_priority_queue = new ArrayList<RoadNode>();
		this.backward_priority_queue = new ArrayList<RoadNode>();
		this.priority_queue = new LinkedList<RoadNode>();
	}
	
	public boolean isOnSandT(RoadNode node) {
		return node.getDijkstraNode().isOnS() && node.getDijkstraNode().isOnT();
	}
	
	public void putIntoS(RoadNode node) {
		S.add(node);
		node.getDijkstraNode().setOnS();
	}
	
	public void putIntoT(RoadNode node) {
		T.add(node);
		node.getDijkstraNode().setOnT();
	}
	
	public void addPriorityQueue(RoadNode roadNode) {
		if(!this.priority_queue.isEmpty()) {
			for(int i = 0;i < this.priority_queue.size(); i++) {
				RoadNode rnode = this.priority_queue.get(i);
				if(roadNode.getDijkstraNode().getDistanceFromParent() < rnode.getDijkstraNode().getDistanceFromParent()) {
					this.priority_queue.add(i,roadNode);
					break;
				}else {
					if(i == this.priority_queue.size()-1) {
						this.priority_queue.add(roadNode);
						break;
					}//if(i == this.priority_queue.size()-1)
				}//else
			}//for(int i = 0;i < this.priority_queue.size(); i++) 
		}else {
			this.priority_queue.add(roadNode);
		}//else
	}
	
	public RoadNode pollPriorityQueue() {
		if(this.priority_queue.isEmpty()) {
			return null;
		}else {
			return this.priority_queue.poll();
		}
	}
	
	public RoadNode firstOfPriorityQueue() {
		if(this.priority_queue.isEmpty()) {
			return null;
		}else {
			return this.priority_queue.getFirst();
		}
	}
	
	public Queue<RoadNode> getPriorityQueue(){
		return this.priority_queue;
	}
	
	public boolean isPriorityQueueEmpty() {
		return this.priority_queue.isEmpty();
	}
}
