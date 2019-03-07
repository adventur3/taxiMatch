package xmu.mocom.dataProcess;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.DocumentException;
import org.jgrapht.Graph;

import xmu.mocom.taxiMatch.RoadNetCreator;
import xmu.mocom.taxiMatch.RoadNode;
import xmu.mocom.taxiMatch.RoadSegmentEdge;

public class CoreNodeLocator {
	
	private static String OSM_PATH = "./map_highway.osm";
	
	public static void main(String[] args) throws IOException,DocumentException {
	
		Graph<RoadNode, RoadSegmentEdge> g = RoadNetCreator.CreateRoadNetByOsm(OSM_PATH);
		Set<RoadNode> setOfNode = g.vertexSet();
		Iterator it = setOfNode.iterator();
		int core_count = 0;
		int no_core_count = 0;
		while(it.hasNext()) {
			RoadNode rnode = (RoadNode) it.next();
			if(g.edgesOf(rnode).size() >2) {
				rnode.setCore();
				core_count ++;
			}else {
				no_core_count ++;
			}
		}
		
		System.out.println("core = "+core_count+" , no_core = "+no_core_count);
	}
}
