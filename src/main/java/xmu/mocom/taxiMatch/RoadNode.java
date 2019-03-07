package xmu.mocom.taxiMatch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.jgrapht.Graph;

import xmu.mocom.path.DijkstraNode;

public class RoadNode {
	
	public String osm_id;	//osm id
	public double lon;		//经度
	public double lat;		//纬度
	public boolean isCore;
	public DijkstraNode dijkstraNode;
	
	public RoadNode(Element e) {
		List<Attribute> list = e.attributes();
        for(Attribute attribute : list){
            if(attribute.getName().equals("id")) 
            	this.osm_id = attribute.getValue();
            if(attribute.getName().equals("lon"))
                this.lon = Double.parseDouble(attribute.getValue());
            if(attribute.getName().equals("lat"))
                this.lat = Double.parseDouble(attribute.getValue());
        }
        isCore = false;
        dijkstraNode = null;
	}
	
	public String getOsmId() {
		return osm_id;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public double getLon() {
		return this.lon;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public void setCore() {
		this.isCore = true;
	}
	
	public boolean isCore() {
		return this.isCore;
	}
	
	public void setDijkstraNode(DijkstraNode node) {
		this.dijkstraNode = node;
	}
	
	public DijkstraNode getDijkstraNode() {
		return this.dijkstraNode;
	}
	
	public Set<RoadSegmentEdge> getAllNextEdge(Graph<RoadNode, RoadSegmentEdge> g) {
		Set<RoadSegmentEdge> edgeSet = g.edgesOf(this);
		Iterator it = edgeSet.iterator();
		Set<RoadSegmentEdge> nextEdgeSet = new HashSet<RoadSegmentEdge>();
		while(it.hasNext()) {
			RoadSegmentEdge nextEdge = (RoadSegmentEdge) it.next();
			if(g.getEdgeSource(nextEdge) == this) {
				nextEdgeSet.add(nextEdge);
			}
		}
		return nextEdgeSet;
	}
	
}
