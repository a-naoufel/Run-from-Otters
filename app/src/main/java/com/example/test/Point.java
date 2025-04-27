package com.example.test;

public class Point {
    private float x;
    private float y;
    private float rayan;
    public Point(float x,float y,float rayan){
        this.x = x;
        this.y = y;
        this.rayan = rayan;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getRayan() {
        return rayan;
    }

    public void setRayan(float rayan) {
        this.rayan = rayan;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
