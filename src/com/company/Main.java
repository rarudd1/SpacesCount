package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int spaceCount(int n, int k, int r_q, int c_q, int[][] obstacles) {

        //flags if an obtacle in each direction is found.
        boolean upDone = false;
        boolean downDone = false;
        boolean upLeftDone = false;
        boolean upRightDone = false;
        boolean leftDone = false;
        boolean rightDone = false;
        boolean downLeftDone = false;
        boolean downRightDone = false;
        //holds the closest obstacle count
        int up =0, down =0, upLeft =0, upRight=0, left =0, right =0, downLeft =0, downRight =0;

        int count =0;
        int countholder =0;
        for(int [] obstacle: obstacles)
        {
            if(obstacle[0]==r_q)
            {

                if(obstacle[1]<c_q)
                {   //if an obstacle is found to the left of the center object the amount is added to count
                    if(!leftDone)
                    {
                        count = (count + (c_q - obstacle[1])-1);
                        leftDone = true;
                        left = c_q -obstacle[1];
                    }
                    else
                    {
                        //if a new obstacle is found left of the center object the original amount is subtracted and the new amount added
                        if(c_q-obstacle[1]< left)
                        {
                            count = count -left;
                            count = c_q-obstacle[1];
                            left = c_q-obstacle[1];
                        }
                    }
                }
                else
                {
                    //If an obstacle is found right of the center object, the amount is added to count
                    if(!rightDone)
                    {
                        count = (count + (obstacle[1]-c_q)-1);
                        rightDone = true;
                        right = obstacle[1] -c_q;
                    }
                    else
                    {//If a closer obstacle is found right of the center object, the original amount is subtracted and the new amount added.
                        if(obstacle[1]-c_q<right)
                        {
                            count = count -right;
                            count = count + obstacle[1]-c_q;
                            right = obstacle[1]-c_q;
                        }
                    }
                }

            }
            if(obstacle[1]==c_q)
            {

                if(obstacle[0]<r_q)
                {//If an obstacle is found below the object the amount is added to count
                    if(!downDone)
                    {
                        count = (count + (r_q - obstacle[0])-1);
                        downDone = true;
                        down = r_q-obstacle[0];

                    }
                    else
                    {//If an obstacle is found closer to the center object the old amount is subtracted and the new amount added
                        if(r_q-obstacle[0]<down)
                        {
                            count = count -down;
                            count = count +r_q-obstacle[0];
                            down = r_q-obstacle[0];
                        }
                    }
                }
                else
                {//if an obsticle is found above the center object the amount is added to count
                    if(!upDone)
                    {
                        count = (count + (obstacle[0]-r_q)-1);
                        upDone = true;
                        up = obstacle[0]-r_q;
                    }
                    else
                    {//if a new object is found closer to the center object the old amount is subtracted and the new amount added
                        count = count - up;
                        count = count + obstacle[0] -r_q;
                        up = obstacle[0] -r_q;
                    }
                }

            }
            else
            {
                if(Math.abs(obstacle[0]-r_q)==Math.abs(obstacle[1]-c_q))
                {
                    if(obstacle[0]-r_q>0 && obstacle[1]-c_q>0)
                    {//if an obstacle is found to the upper right of the center object the amount is added

                        if(!upRightDone)
                        {
                            count = count + (obstacle[0]-r_q-1);
                            upRightDone = true;
                            upRight = obstacle[0]-r_q;
                        }
                        else
                        {//if an obstacle is found closer to the center the old amount is subtracted and the new amount added
                            if(obstacle[0]-r_q <upRight)
                            {
                                count = count -upRight;
                                count = count +obstacle[0]-r_q;
                                upRight = obstacle[0]-r_q;
                            }
                        }

                    }
                    else if (obstacle[0]-r_q<0 && obstacle[1]-c_q>0)
                    {//if an object is found below to the right the amount is added to the count
                        if(!downRightDone)
                        {
                            count = count + (obstacle[1] - c_q -1);
                            downRightDone = true;
                            downRight = obstacle[1] - c_q;
                        }
                        else
                        {//if a closer object is found below to the right the old amount is subtracted and the new amount added
                            if(obstacle[1]-c_q<downRight)
                            {
                                count = count - downRight;
                                count = count + obstacle[1] -c_q;
                                downRight= obstacle[1]-c_q;
                            }
                        }
                    }
                    else if (obstacle[0]-r_q >0 && obstacle[1] -c_q<0)
                    {//if an obstacle is found above to the left of the center the amount away is added
                        if(!upLeftDone)
                        {
                            count = count + (obstacle[0]-r_q-1);
                            upLeftDone = true;
                            upLeft = obstacle[0]-r_q;
                        }
                        else
                        {//if a closer obstacle is found subtract the old amount and add the new
                            if(obstacle[0]-r_q<upLeft)
                            {
                                count = count - upLeft;
                                count = count + obstacle[0] -r_q;
                                upLeft = obstacle[0]-r_q;
                            }
                        }
                    }
                    else
                    {//if an obstacle is found below to the left, the amount to the obstacle if added to count
                        if(!downLeftDone)
                        {
                            count = count + (r_q - obstacle[0]-1);
                            downLeftDone = true;
                            downLeft = r_q-obstacle[0];
                        }
                        else
                        {//if a closer obstacle is found the old amount is subtracted and the new amount added
                            if(r_q-obstacle[0]<downLeft)
                            {
                                count = count - downLeft;
                                count = count + r_q-obstacle[0];
                                downLeft = r_q-obstacle[0];
                            }

                        }
                    }
                }
            }

        }
        //Count up the remaining amounts for any paths that did not contain obstacles.
        if(!upDone)
            count = count +(n-r_q);

        if(!upLeftDone)
        {
            if(n-r_q<c_q-1)
                count = count + (n-r_q);
            else
                count = count +(c_q-1);
        }
        if(!leftDone)
            count = count + (c_q-1);

        if(!downLeftDone)
        {
            if(r_q < c_q)
                count = count + (r_q-1);
            else
                count = count + (c_q-1);
        }

        if(!downDone)
            count = count + (r_q-1);

        if(!downRightDone)
        {
            if(n-c_q<r_q-1)
                count = count + (n-c_q);
            else
                count = count + (r_q-1);
        }
        if(!rightDone)
            count = count + (n-c_q);

        if(!upRightDone)
        {
            if(n-r_q<n-c_q)
                count = count + (n - r_q);
            else
                count = count + (n-c_q);
        }

        return(count);


    }

    public static void main(String[] args) throws IOException {


        try (Scanner scanner = new Scanner(new FileReader("tests.txt"))) {


            int size = scanner.nextInt();
            int numberOfObstacles = scanner.nextInt();
            int oRow = scanner.nextInt();
            int oColumn = scanner.nextInt();
            int[][] obstacles = new int[numberOfObstacles][2];
            for (int i = 0; i < numberOfObstacles; i++) {
                obstacles[i][0]  = scanner.nextInt();
                obstacles[i][1] = scanner.nextInt();

            }
            System.out.println(spaceCount(size, numberOfObstacles, oRow, oColumn, obstacles));
        }
    }
}
