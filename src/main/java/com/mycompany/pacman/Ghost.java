/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Mats Engesund
 */
public class Ghost {
    
    /*
        Dette er superklassen til spøkelsene 
        Hvert enkelt spørelse (rød, rosa, cyan og oransje) får sin 
        egen subklasse av denne. Men hvilke felles egenskaper har disse: 
        * path, filestream, image, imageView, 
        * størelse
        
        Vi har nå rød, oransje/gul, grønn og blå. ALLE ER PÅ PLASS
    */
    
    protected String path; 
    protected double xpos, ypos; 
    protected Image image; 
    protected FileInputStream stream; 
    protected ImageView view;
    protected final int SIZE = 50; // alle blir like store 

    
    public Ghost(String path, double xpos, double ypos) throws FileNotFoundException {
        this.path   = path; 
        this.xpos   = xpos; 
        this.ypos   = ypos;
        stream      = new FileInputStream(path); 
        image       = new Image(stream); 
        
        setImageView(SIZE, SIZE);
    }
    
    
    protected ImageView getImageView() {
        return view; 
    }
    
    
    protected void setImageView(double sizeX, double sizeY) {
        view = new ImageView(getImage());
        view.setFitHeight(SIZE);
        view.setFitWidth(SIZE);
        view.setX(xpos);
        view.setY(ypos);
    }
    
    
    protected Image getImage() {
        return image; 
    }
    
    
    /*protected void chase(MrPac pacman) {
        Rectangle rect = new Rectangle(100, 100); 
        PathTransition trans = new PathTransition(); 
        trans.setNode(getImageView());
        trans.setDuration(Duration.seconds(3));
        trans.setPath(rect);
        trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        trans.setCycleCount(PathTransition.INDEFINITE);
        trans.play();
        
        
        double pacPosX = pacman.getMrPac().getCenterX(); 
        double pacPosY = pacman.getMrPac().getCenterY(); 
        
        Path pacPath = new Path();
        pacPath.getElements().add(new MoveTo(pacPosX, pacPosY)); 
        pacPath.getElements().add(new LineTo(pacPosX, pacPosY)); 
        
        PathTransition move = new PathTransition(); 
        move.setDuration(Duration.millis(2000));
        move.setPath(pacPath);
        move.setNode(view);
        
        SequentialTransition chase = new SequentialTransition(); 
        chase.getChildren().add(move); 
        
        ParallelTransition animasjon = new ParallelTransition(); 
        animasjon.getChildren().add(chase); 
        animasjon.play();
    }*/
    
}
