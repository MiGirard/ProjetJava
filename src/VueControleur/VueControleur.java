/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modele.Grille;
import Modele.Lien;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;



public class VueControleur extends Application {

    private int largeur = 3, longueur = 3;
    Grille m;
    private int windowX, windowY;

    @Override
    public void start(Stage primaryStage) {

        // initialisation du modèle que l'on souhaite utiliser
        m = new Grille();
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Text affichage = new Text("Grille Drag&Drop");
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        


        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // TODO
            }
        });
        
       Canvas canvas = new Canvas((200*m.getTab().length), (200*m.getTab()[0].length));
       initCanvas(canvas);
       gPane.add(canvas,0,0);

        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTGRAY);

        primaryStage.setTitle("Drag & Drop");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
   
        private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        
        
        Image img = null;
        try {
            img = new Image(new FileInputStream(Lien.CASE_VIDE.getChemin()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int column = 0; column < m.getTab().length; column++) {
            for (int row = 0; row < m.getTab()[0].length; row++) {
                if(m.getTab()[column][row].getLien() == null){
                     try {
                        img = new Image(new FileInputStream(m.getTab()[column][row].getSymbole().getChemin()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(m.getTab()[column][row].getSymbole() == null){
                    try {
                        img = new Image(new FileInputStream(m.getTab()[column][row].getLien().getChemin()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                gc.drawImage(img, column*200, row*200);
            }
        }

        gc.fill();
        //Création de la grille (graphique)
        for (int column = 0; column < m.getTab().length; column++) {
            for (int row = 0; row < m.getTab()[0].length; row++) {
                gc.strokeRect(
                column*200,              //x of the upper left corner
                row*200,              //y of the upper left corner
                200,    //width of the rectangle
                200);  //height of the rectangle
            }
        }
        

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

    }
        
    private void initCanvas(Canvas canvas){
     
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
            
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mousePressed) -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mousePressed.getX(), mousePressed.getY());
            graphicsContext.stroke();
            int col, row;
            col = (int)(mousePressed.getX())%200;
            row = (int)(mousePressed.getY())%200;
            m.startDD(col,row);
            
            });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent mouseDragged) -> {
            
            graphicsContext.lineTo(mouseDragged.getX(), mouseDragged.getY());
            graphicsContext.stroke();
            int col, row;
            col = (int)(mouseDragged.getX())%200;
            row = (int)(mouseDragged.getY())%200;
            m.parcoursDD(col,row);
            });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent mouseReleased) -> {
            Scene scene = canvas.getScene();
            Point2D nodeCoord = new Point2D(scene.getX(), scene.getY());
            int col, row;
            col = (int)(mouseReleased.getX()-nodeCoord.getX())%200;
            row = (int)(mouseReleased.getY()-nodeCoord.getY())%200;
            m.stopDD(col,row);
            //System.out.println(nodeCoord.getY());
            
            
            });
        }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    



}
