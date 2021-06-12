/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaballs;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;


/**
 *
 * @author Aleksandr
 */
public class Ball extends Circle{
    public Ball(double r, double cw, double sh, ArrayList<Ball> list, Color c){
        super();
        radius = r;
        centerWidth = cw;
        sceneHeight = sh;
        
        super.setRadius(radius);
        super.setCenterX(Math.random()*(centerWidth - radius)+ 1);
        super.setCenterY(Math.random()*(sceneHeight - radius + 60)+ 1);
        
        xSpeed = Math.random() + 1;
        ySpeed = Math.random() + 1;
        
        
        RadialGradient rg;
        rg = new RadialGradient (
                0,0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, c)
        );
        super.setFill(rg);
        ballList = list;
    }
   
    public void move(){
        super.setCenterX(super.getCenterX() + xSpeed);
        setCenterY(getCenterY()+ ySpeed);
        
        if (getCenterX()<= radius){
            setCenterX(centerWidth - radius);
            
        }
         if (getCenterX()>= centerWidth - radius){
            setCenterX(radius);
            
        }
         if (getCenterY()<= radius + 60){
            setCenterY(sceneHeight - radius + 60);
            
        }
         if (getCenterY()>= sceneHeight - radius + 60){
            setCenterY(radius + 60);
            
        }
         
        for (Ball b: ballList){
            if ((b != this)&&(b.intersects(getLayoutBounds()))&&(radius!= 0)&&(b.radius!=0)){
                double tmp = xSpeed;
                xSpeed = b.xSpeed;
                b.xSpeed = tmp;
                
                tmp = ySpeed;
                ySpeed = b.ySpeed;
                b.ySpeed = tmp;
                
                if (radius >= b.radius){
                    radius = radius + b.radius;
                    
                    super.setRadius(radius);
                    b.setRadius(0);
                }
                
                
                if (radius < b.radius){
                    b.radius = b.radius + radius;
                    radius = 0;
                    super.setRadius(0);
                    b.setRadius(b.radius);
                }
                
                break;
            }
        }
    }
    
    
    double radius;
    double xSpeed;
    double ySpeed;
    
    double centerWidth;
    double sceneHeight;
    
    ArrayList<Ball> ballList;
    Color a;
}