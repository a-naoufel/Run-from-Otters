package com.example.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import java.util.ArrayList;
import java.util.Random;

public class Terrain extends ConstraintLayout {
    private ArrayList<Point> positions;
    private ArrayList<ImageView> mangoustes;
    private Paint paint;
    private Point debut, fin;
    private Random random;
    private int dim;
    private boolean lose = false;

    public Terrain(Context context) {
        super(context);
        setWillNotDraw(false);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, dm);
        positions = new ArrayList<>();
        mangoustes = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        random = new Random();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                positions = new ArrayList<>();
                debut = new Point(event.getX(), event.getY(), 2f);
                ImageView imageView;
                for (int i = 0; i < 5; i++) {
                    imageView = new ImageView(getContext());
                    mangoustes.add(imageView);
                    imageView.setLayoutParams(new Constraints.LayoutParams(dim, dim));
                    imageView.setBackgroundResource(R.drawable.mangouste);
                    imageView.setId(View.generateViewId());
                    this.addView(imageView);
                    imageView.setX(random.nextFloat() * getWidth());
                    imageView.setY(random.nextFloat() * getHeight());
                }


                break;
            case MotionEvent.ACTION_MOVE:
                Point paint = new Point(event.getX(), event.getY(), 2f);
                positions.add(paint);
                for (ImageView image : mangoustes) {
                    if (inView(paint, image)) {
                        lose = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                fin = new Point(event.getX(), event.getY(), 2f);
                if (lose) {
                    Toast.makeText(getContext().getApplicationContext(), "Défaite", Toast.LENGTH_SHORT).show();
                } else {
                    if (debut.getX() < 0.1 * getWidth() && debut.getY() < 0.1 * getHeight() && fin.getX() > 0.9 * getWidth() && fin.getY() > 0.9 * getHeight()) {

                        Toast.makeText(getContext().getApplicationContext(), "Victoire", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext().getApplicationContext(), "Défaite", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        Point paint = new Point(event.getX(), event.getY(), 2f);
        positions.add(paint);
        invalidate();
        return true;
    }

    boolean inView(Point p, View v) {
        float x = p.getX();
        float y = p.getY();
        if (x >= v.getX() && x <= v.getX() + v.getWidth()) {
            if (y >= v.getY() && y <= v.getY() + v.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Point x, y;
        for (int i = 0; i < positions.size() - 1; i++) {
            x = positions.get(i);
            y = positions.get(i + 1);
            canvas.drawLine(x.getX(), x.getY(), y.getX(), y.getY(), paint);
        }
    }


    public ArrayList<Point> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Point> positions) {
        this.positions = positions;
    }

    public ArrayList<ImageView> getMangoustes() {
        return mangoustes;
    }

    public void setMangoustes(ArrayList<ImageView> mangoustes) {
        this.mangoustes = mangoustes;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }
}
