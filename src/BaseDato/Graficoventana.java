/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC04
 */
public class Graficoventana extends Application {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public void start(Stage primaryStage) throws IOException {
        StackPane rootMain = new StackPane();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LibrosViewFXML.fxml"));
        Parent rootLibrosViewFXML = fxmlLoader.load();
        rootMain.getChildren().add(rootLibrosViewFXML);
        
        emf = Persistence.createEntityManagerFactory("BaseDatosPU");
        em = emf.createEntityManager();

        LibrosViewFXMLController librosViewFXMLController = (LibrosViewFXMLController) fxmlLoader.getController();                
        librosViewFXMLController.setEntityManager(em);
        librosViewFXMLController.cargarTodosLibros();

        Scene scene = new Scene(rootMain, 600, 600);

        primaryStage.setTitle("Agenda Contactos");
        primaryStage.setScene(scene);
        primaryStage.show();                
    }
    
    @Override
    public void stop() throws Exception {
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:BDAgendaContactos;shutdown=true"); 
        } catch (SQLException ex) { 
        }        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
