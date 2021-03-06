/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 *
 * @author mjoen
 */
public class MrPac {
    
    /*
        Dette er klassen til selve Pac-Man figuren. Vet ikke helt om det skal 
        være et bibliotek eller objekt. Vanskelig å plasere et objekt i javafx
        da det kun finnes en størelse på pacman. Kan prøve å lage en getmetode 
        i konstruktøren
    */
    
    
    protected Arc pacman; 
    protected ParallelTransition animation; 
    protected RotateTransition counterclockwise;
    protected RotateTransition clockwise;
    protected Double speed;
    
    
    public MrPac() {
        setMrPac();
        //setMovement();
        //setGaping();
        setAnimation();
    }
    
    
    private void setAnimation() {     
        /* Gaping */
        Timeline gaping = new Timeline(); 
        gaping.setCycleCount(Timeline.INDEFINITE);
        gaping.setAutoReverse(true);
        
        KeyValue angle = new KeyValue(pacman.startAngleProperty(), 10); 
        KeyValue bow = new KeyValue(pacman.lengthProperty(), 360); 
        
        KeyFrame kf = new KeyFrame(Duration.millis(200), angle, bow);
        gaping.getKeyFrames().add(kf);
        
        /* Animasjon stuff */
        animation = new ParallelTransition(); 
        animation.getChildren().add(gaping); 
    }
    
    protected void startAnimation() {
        animation.play();
    }
    
    
    protected double getPosX() {
        return pacman.getCenterX(); 
    }
    protected double getPosY() {
        return pacman.getCenterY();
    }

    protected double getSpeed() {
        return speed;
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
    }
    
    
    /* Flytte med piltasting */
    protected void setMovement() {
        ArrayList<String> input = new ArrayList<String>();

        pacman.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            input.clear();
            input.add(code);
        });

        pacman.setOnKeyReleased(e -> {
            String code = e.getCode().toString();

            if (input.contains(code))
                input.remove(code);
        });

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (input.contains("LEFT")) {
                    pacman.setRotate(-180);
                    pacman.setCenterX(pacman.getCenterX() - getSpeed());
                }

                if (input.contains("RIGHT")) {
                    pacman.setRotate(0);
                    pacman.setCenterX(pacman.getCenterX() + getSpeed());
                }

                if (input.contains("DOWN")) {
                    pacman.setRotate(90);
                    pacman.setCenterY(pacman.getCenterY() + getSpeed());
                }

                if (input.contains("UP")) {
                    pacman.setRotate(-90);
                    pacman.setCenterY(pacman.getCenterY() - getSpeed());
                }
            }
        }.start();
        
        pacman.requestFocus();
    }
    
    
    private void setMrPac() {
        pacman = new Arc(50, 100, 25, 25, 15, 300); 
        pacman.setStroke(Color.BLACK);
        pacman.setFill(Color.YELLOW);
        pacman.setType(ArcType.ROUND);
    }
    
    protected Arc getMrPac() {
        return pacman; 
    }
    
}
