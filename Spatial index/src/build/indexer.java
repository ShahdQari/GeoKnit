/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build;

/**
 *
 * @author DELL
 */
import core.Point;
import core.Rectangle;
import indexing.GridPartitioner;
import indexing.QuadTree;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import build.build_QuadTree;
//import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ashwa
 */
public class indexer {

    static String path;
    static String path_out;
    static int typeindex;

    public void index(String path, String path_out, int typeindex) throws IOException {
        // Scanner input = new Scanner(System.in); 
        // System.out.print("Enter your index number : 1= quad tree, 2= GridFile, 3= Rtree ");
        // typeindex = input.nextInt();       
        // System.out.print("Enter your path data file (.txt)");  

        //  while (myReader.hasNextLine()) {
        //   String data = myReader.nextLine();
        //// myReader.close();
        // }
        if (typeindex == 1) {
            build_QuadTree test = new build_QuadTree();
            test.loadQuadTree(path, path_out);
        }
        if (typeindex == 2) {
            build_GridFile Grid = new build_GridFile();
            Grid.GridFile(path, path_out);
        }
        if (typeindex == 3) {
            build_Rtree r_tree = new build_Rtree();
            r_tree.RTree(path, path_out);

        }
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your index number : 1= quad tree, 2= GridFile, 3= Rtree ");
        typeindex = input.nextInt();
        System.out.println("Enter your path data file (.txt)");
        path = input.next();
        System.out.println("Please enter a path to save the file");
        path_out = input.next();
        indexer indexs = new indexer();
        indexs.index(path, path_out, typeindex);
        // index.index("C:\\Users\\leena\\Documents\\NetBeansProjects\\workers0.txt","C:\\Users\\leena\\Documents\\NetBeansProjects\\T3.WTK", 2);

    }
}
