package com.example.testing.fun;

import java.util.Arrays;

public class Current {
    public static Current.facing facing;
    private int[][] floor;
    private int cX;
    private int cY;
    private boolean Pen;
    private facing currentFace;
    private int rot;

    public Current(int n) {
//        System.out.println(n);
        floor = new int[n][n];
        currentFace  = facing.NORTH;
        cX  = 0;
        cY = 0;
        Pen = false;
        rot =0;
    }

    public int[][] getFloor() {
        return floor;
    }
    
    
    public char[][] printFloor() {
        int size = floor.length;
        char[][] print = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if(floor[j][size-i-1]==1)
                
                print[i][j]= '*';
            	else {
            		print[i][j]= '0';
            	}
            }
            

        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	System.out.print(print[i][j]+" ");
            }
        System.out.println();
        }

        return print;
    }
    public void setFloor(int[][] floor) {
        this.floor = floor;
    }

    public int getcX() {
        return cX;
    }

    public void setcX(int cX) {
        this.cX = cX;
    }

    public int getcY() {
        return cY;
    }

    public void setcY(int cY) {
        this.cY = cY;
    }

    public boolean isPen() {
        return Pen;
    }

    public void setPen(boolean pen) {
        Pen = pen;
    }

//    public facing getCurrentFace() {
//        return currentFace;
//    }
//
//    public void setCurrentFace(facing currentFace) {
//        this.currentFace = currentFace;
//    }
    public String[] array = {"North", "East", "South", "West"};

    @Override
    public String toString() {
        return "Current{" +
                "cX=" + cX +
                ", cY=" + cY +
                ", Pen=" + Pen +
                ", currentFace=" + array[rot] +
                '}';
    }

    public int getRot() {
        return rot;
    }

    public void setRot(int rot) {
        this.rot = rot;
    }

    public enum facing {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

}
