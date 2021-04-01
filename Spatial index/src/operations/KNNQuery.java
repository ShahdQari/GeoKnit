package operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

import core.Point;
import core.Rectangle;
import operationsupport.CacheInfo;
import operationsupport.OutputUtil;
import utility.PointDistancePair;

public class KNNQuery {

 	public static void knnQuery(Point point, int k, Ignite ignite, String path,CacheInfo cacheInfo) {
 		
			int key=0;
			IgniteCache <String,Rectangle> cache = ignite.cache("Rectangles");
			ArrayList<String> result = new ArrayList<String>();
			while(key<cacheInfo.getQCount()) {
				Rectangle rect = cache.get("Cache---"+key);
				if(rect.contains(point)) {
					result.add("Cache---"+key);
				}
				key++;
			}
		PriorityQueue<PointDistancePair> nearestPoints = new PriorityQueue<PointDistancePair>();
		//double largestDistance = 0;
		for(int i=0;i<result.size();i++) {
			IgniteCache<Integer, ArrayList<Point>> pcache = cacheInfo.getCacheMap().get(result.get(i));
			ArrayList<Point> points= pcache.get(0);
			for(Point p : points) {
				double distance = p.distanceTo(point.x,point.y);
				if(nearestPoints.size()<k) {
					nearestPoints.add(new PointDistancePair(p,distance));
					//largestDistance = getlargestDistance(nearestPoints, distance, largestDistance, point);
				}
				else if(distance<nearestPoints.peek().getDistance()) {
					nearestPoints.poll();
					nearestPoints.add(new PointDistancePair(p,distance));
					//removeLargestDistance(nearestPoints, point);
					//largestDistance = getlargestDistance(nearestPoints, distance, largestDistance,point);
				}
				
			}	
		}
		
		PointDistancePair [] sortedPoints = new PointDistancePair[nearestPoints.size()];
		sortedPoints = nearestPoints.toArray(sortedPoints);
		Arrays.sort(sortedPoints, Collections.reverseOrder());
		List<PointDistancePair> sortedPointList = Arrays.asList(sortedPoints);
		
		OutputUtil.writeToFile(sortedPointList,path);
     //ignite.destroyCaches(ignite.cacheNames());
     //System.out.println(ignite.cacheNames());
     ///ignite.close();
	}
}
