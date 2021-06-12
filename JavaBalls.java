/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaballs;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Aleksandr
 */
public class JavaBalls extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        balls = new ArrayList<>();
        squares = new ArrayList<>();
        double SIZE = 6;
        for (int i=0; i<10; i++){
            balls.add(new Ball(SIZE,WIDTH, HEIGHT, balls,Color.RED));
        }
        
        a = Color.RED;
  
        Group taskbar = new Group();
        taskbar.getChildren().addAll(balls);
        
        HBox name = new HBox(10);
        name.setPadding(new Insets(10,30,50,30));
        name.setPrefHeight(50);
        taskbar.getChildren().add(name);
        
        
        TextField size = createTextField(String.valueOf(10));
        TextField number = createTextField(String.valueOf(10));
        name.getChildren().add(createLabel("Размер объекта"));
        name.getChildren().add(size);
        name.getChildren().add(createLabel("Кол-во объектов"));
        name.getChildren().add(number);
        name.getChildren().add(createLabel("Цвет объекта"));
        name.getChildren().add(createComboBox());
        name.getChildren().add(createComboBox1());
        name.getChildren().add(createButton("Кнопка.png", new Runnable(){
            @Override
            public void run(){
                
                String radius = size.getText();
                String onumber = number.getText();
                Double RADIUS = Double.valueOf(radius);
                Double n = Double.valueOf(onumber);
                c=b;
                
                balls.clear();
                squares.clear();
                taskbar.getChildren().clear();
                if (c==0){
                for (int i=0; i<n; i++){
                 balls.add(new Ball(RADIUS,WIDTH, HEIGHT, balls,a));
        }
                for (Ball b: balls){
                       b.move();
                       
                   }
                taskbar.getChildren().addAll(balls);
                taskbar.getChildren().add(name);
                }
                else {
                for (int i=0;i<n;i++){
                    squares.add(new Square(RADIUS,WIDTH,HEIGHT,squares,a));
                }
                for (Square b: squares){
                    b.move();
                }
                taskbar.getChildren().addAll(squares);
                taskbar.getChildren().add(name);}
                
                
            }
        }));
        
        Scene scene = new Scene(taskbar, WIDTH,HEIGHT);
        stage.setTitle("Some balls");
        stage.setScene(scene);
        stage.show();
        
        
        KeyFrame kf = new KeyFrame(Duration.millis(10),
               e ->
               {
                   if (c==0){
                   for (Ball b: balls){
                       b.move();
                   }
                   }
                   else {
                    for (Square b: squares){
                    b.move();
                   }
                   }
               }
        );
        Timeline tl = new Timeline(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
    
    private ArrayList<Ball> balls;
    private ArrayList<Square> squares;
    public Color a;
    
    private Node createLabel (String lblName){
        Label lbl = new Label(lblName);
        return lbl;
    }
    private TextField createTextField(String value){
        TextField tf = new TextField(value);
        
        return tf;
    }
     
    private Node createButton(String iconName, final Runnable action){
        final ImageView node = new ImageView(new Image("file:"+iconName));
        node.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                action.run();
                
            }
        });
        return node;
    }
            
    private Node createComboBox(){
        ComboBox<String> cb = new ComboBox<>();
        cb.getItems().addAll("Красный","Синий","Желтый");
        cb.getSelectionModel().selectFirst();
        
        cb.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle (ActionEvent event){
                a = myColor[cb.getSelectionModel().getSelectedIndex()];
            }
        }); 
        return cb;
    }
    
    private Node createComboBox1(){
        ComboBox<String> cb1 = new ComboBox<>();
        cb1.getItems().addAll("Круг","Квадрат");
        cb1.getSelectionModel().selectFirst();
        
        cb1.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle (ActionEvent event){
                 b = cb1.getSelectionModel().getSelectedIndex();
            }
        }); 
        return cb1;
    }
    
    Color[] myColor = new Color[] { Color.RED, Color.BLUE, Color.YELLOW };
    
    
    final private int WIDTH = 1000;
    final private int HEIGHT = 600;
    int b;
    int c;
}
