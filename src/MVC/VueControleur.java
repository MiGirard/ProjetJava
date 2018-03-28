/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import java.util.Observer;
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



public class VueControleur extends Application {

    
    Modele m;
    

    @Override
    public void start(Stage primaryStage) {

        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
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
        
       for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 5; row++) {
                Canvas canvas = new Canvas(200,200);
                initCanvas(canvas);
                gPane.add(canvas, column, row);    
            }
        }

        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

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

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

    }
        
    private void initCanvas(Canvas canvas){
            
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
            
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mousePressed) -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mousePressed.getX(), mousePressed.getY());
            graphicsContext.stroke();
            });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent mouseDragged) -> {
            
            graphicsContext.lineTo(mouseDragged.getX(), mouseDragged.getY());
            graphicsContext.stroke();
            });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent mouseReleased) -> {
            });
        }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    



}
