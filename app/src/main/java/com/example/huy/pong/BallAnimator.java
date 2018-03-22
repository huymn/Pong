package com.example.huy.pong;

import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.*;

/**
 * This class animates the behavior of the ball
 * @author Huy Nguyen
 * @version March 2018
 */

public class BallAnimator implements Animator {
    private final int BALL_RADIUS = 30;
    private final String CONTINUE = "Tap the screen to continue";

    private Random ranNum = new Random();
    private float xPos, yPos, changeInY, changeInX,speedX, speedY;
    private Paint paint = new Paint();
    private boolean isBallOut = false;
    private boolean isBigPaddle = false;

    public BallAnimator(){
        xPos = ranNum.nextInt(1000) + 500;
        yPos = ranNum.nextInt(500) + 500;
        randomize();
    }

    /**
     * Randomize direction and speed of the ball
     */
    public void randomize(){

        speedX = 1 + ranNum.nextInt(2) + (float)Math.random();
        speedY = 1 + ranNum.nextInt(2) + (float)Math.random();
        //Pick random Y direction
        if(ranNum.nextInt(2) == 1){
            changeInY = 10 * speedY;
        }
        else {
            changeInY = -10 * speedY;
        }

        //Pick random X direction
        if(ranNum.nextInt(2) == 1){
            changeInX = 10 * speedX;
        }
        else {
            changeInX = -10 * speedX;
        }
    }

    public void setPaddleBig() {
        isBigPaddle = true;
    }

    public void setPaddleSmall() {
        isBigPaddle = false;
    }
    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.BLACK;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {
        //set paint color to white
        paint.setColor(Color.WHITE);
        //Draw top wall
        canvas.drawRect(0.0f, 0.0f, (float)canvas.getWidth(), 60.0f, paint);
        //Draw left side wall
        canvas.drawRect(0.0f, 0.0f, 60.0f, (float)canvas.getHeight(), paint);
        //Draw bottom wall
        canvas.drawRect(0.0f, (float)(canvas.getHeight() - 60), canvas.getWidth(), canvas.getHeight(), paint);
        //draw the paddle
        if(isBigPaddle) {
            canvas.drawRect((float)(canvas.getWidth() - 150), (float)((canvas.getHeight() / 2) - 300),
                    (float)(canvas.getWidth()), (float)((canvas.getHeight() / 2) + 300), paint);
        }
        else if(isBigPaddle == false) {
            canvas.drawRect((float)(canvas.getWidth() - 60), (float)((canvas.getHeight() / 2) - 120),
                    (float)(canvas.getWidth()), (float)((canvas.getHeight() / 2) + 120), paint);
        }

        //Animation of the ball
        if((yPos - BALL_RADIUS) < 60) {
            changeInY = 10 * 0.99f;
        }

        if((yPos + BALL_RADIUS) > canvas.getHeight()- 60) {
            changeInY = -10 * 0.99f;
        }

        if((xPos - BALL_RADIUS) < 60) {
            changeInX = 10 * 0.99f;
        }

        if(isBigPaddle) {
            if((xPos + BALL_RADIUS) > canvas.getWidth() - 150){
                if((yPos >= (canvas.getHeight()/2) - 300) && (yPos <= (canvas.getHeight()/2) + 300)) {
                    changeInX = -10 * 0.99f;
                }
            }
        }
        else if(isBigPaddle == false) {
            if((xPos + BALL_RADIUS) > canvas.getWidth() - 60 ){
                if((yPos >= (canvas.getHeight()/2) - 120) && (yPos <= (canvas.getHeight()/2) + 120)) {
                    changeInX = -10 * 0.99f;
                }
            }
        }

        if(xPos > canvas.getWidth()){
            isBallOut = true;
            xPos = ranNum.nextInt(1000) + 500;
            yPos = ranNum.nextInt(500) + 500;
            randomize();
        }


        yPos += changeInY;
        xPos += changeInX;
        if(isBallOut) {
            paint.setTextSize(50.0f);
            canvas.drawText(CONTINUE, (canvas.getWidth()/2) - 200, canvas.getHeight()/2, paint);
        }
        else if (isBallOut == false) {
            canvas.drawCircle(xPos, yPos, BALL_RADIUS, paint);
        }

    }

    @Override
    public void onTouch(MotionEvent event) {
        isBallOut = false;
    }


}
