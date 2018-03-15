/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class tests {
    AVLNode root = new AVLNode("1", 1);
    @Test
    public void insertNode() {


        root = root.dodaj(root, 1, "jana");
        System.out.println("Adding first member - same value as the root");
        root.LKD(root);

        System.out.println("Adding regular values");
        root = root.dodaj(root, 2, "2");
        root = root.dodaj(root, 3, "3");
        root = root.dodaj(root, -4, "-4");
        root = root.dodaj(root, -5, "-5");
        root = root.dodaj(root, -6, "-6");
        root = root.dodaj(root, 0, "0");
        root = root.dodaj(root, 7, "7");
        root = root.dodaj(root, 10, "10");
        root = root.dodaj(root, -20, "-20");
        root = root.dodaj(root, 11, "11");
        root = root.dodaj(root, 5, "5");
        root = root.dodaj(root, 1, "1");
        root = root.dodaj(root, 17, "17");
        root = root.dodaj(root, 15, "15");
        root = root.dodaj(root, 16, "16");
        root = root.dodaj(root, 13, "13");

        System.out.println("Final Tree");
        System.out.println(root.toString());
        //you can follow the various rotations in the console
        assertEquals("testing insertion and various rotations",
                "((((-20)-6(-5))-4((0)1))2((3(5))7(((10)11(13))15(16(17)))))", root.toString() );
    }
    @Test
    public void deleteNode(){
        root = root.dodaj(root, 1, "1");
        root = root.dodaj(root, 3, "3");
        root = root.dodaj(root, 4, "4");
        root = root.dodaj(root, 5, "5");
        root = root.dodaj(root, 6, "6");
        root = root.dodaj(root, 2, "2");
        root = root.dodaj(root, 3, "3");
        root = root.dodaj(root, -4, "-4");
        root = root.dodaj(root, -5, "-5");
        root = root.dodaj(root, -6, "-6");

        System.out.println("First removal - root");
        root = root.ukloni(root,2);
        assertEquals("Testing random deletion", "(((-6)-5((-4)1))3((4)5(6)))", root.toString());
        root=root.dodaj(root,2,"2");

        System.out.println("Second removal - leaf");
        root=root.ukloni(root,6);
        assertEquals("Leaf Removal","(((-6)-5((-4)1(2)))3((4)5))",root.toString());

        System.out.println("Third removal - middle");
        root=root.ukloni(root,3);
        assertEquals("Middle removal", "(((-6)-5(-4))1((2)3(5)))", root.toString());
    }
}
