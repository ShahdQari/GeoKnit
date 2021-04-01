/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import core.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import utility.PointDistancePair;

/**
 *
 * @author DELL
 */
public class KNN {
    
    
    private List<Point> kNearestNeighbors(int k, long x, long y) {
      PriorityQueue<PointDistancePair> heap = new PriorityQueue<>(k, Collections.reverseOrder());
      //knn(k, x, y, heap);

      List<Point> neighbors = new ArrayList<>();
      for (PointDistancePair n : heap) 
          neighbors.add(n.getPoint());
      return neighbors;
    }
    
   
}
