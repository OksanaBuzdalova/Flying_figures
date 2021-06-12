/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaballs;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Aleksandr
 */
public class Square extends Rectangle{
    public Square(double sz, double sw, double sh, ArrayList<Square> list, Color c){
        super();
        a = c;
        sideSize = 2*sz;
        sceneWidth = sw;
        sceneHeight = sh;
        
        super.setWidth(sideSize);
        super.setHeight(sideSize);
        super.setX(Math.random()*(sceneWidth - sideSize)+1);
        super.setY(Math.random()*(sceneHeight - sideSize)+1);
        
        LinearGradient lg = new LinearGradient(
            0, 0,
            0.35, 0.35,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0.0, Color.WHITE),
            new Stop(1.0, c)
        );
        super.setFill(lg);
        
        Random rnd = new Random();
        val = rnd.nextInt(2);
        directX = 1 - 2*val;
        
//        directX = 0;
        directY = 1;
        
        xSpeed = Math.random()*5 + 1;
        ySpeed = Math.random()*5 + 1;
        ballList1 = list;

    }
    

public void move(){
        setX(getX()+directX);
        setY(getY()+directY);
        
        if (getX()<= 0){
            setX(sceneWidth - sideSize);
            
            
        }
         if (getX()>= sceneWidth - sideSize){
            setX(0);
            
        }
         if (getY()<= 0){
            setY(sceneHeight - sideSize + 60);
            
        }
         if (getY()>= sceneHeight - sideSize){
            setY(60);
            
        }
         
        for (Square b: ballList1){
            if ((b != this)&&(b.intersects(getLayoutBounds()))){
                double tmp = xSpeed;
                xSpeed = b.xSpeed;
                b.xSpeed = tmp;
                
                tmp = ySpeed;
                ySpeed = b.ySpeed;
                b.ySpeed = tmp;
                
                if (sideSize >= b.sideSize){
                    sideSize = sideSize + b.sideSize;
                    
                    super.setWidth(sideSize);
                    super.setHeight(sideSize);
                    b.setWidth(0);
                    b.setHeight(0);
                }
                
                
                if (sideSize < b.sideSize){
                    b.sideSize = b.sideSize + sideSize;
                    
                    sideSize=0;
                    super.setWidth(0);
                    super.setHeight(0);
                    b.setWidth(b.sideSize);
                    b.setHeight(b.sideSize);
                }
                
                break;
            }
        }
    }
    double sideSize;
    double xSpeed;
    double ySpeed;
    
    double sceneWidth;
    double sceneHeight;
    int directX;
    int directY;
    int val;
    
    ArrayList<Square> ballList1;
    Color a;
}
