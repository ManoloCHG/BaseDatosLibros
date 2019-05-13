/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC04
 */
public class LibrosViewFXMLController implements Initializable {
    
    private EntityManager entityManager;
    private Libro libroSeleccionado;
    @FXML
    private TableView<Libro> tableViewLibros;
    @FXML
    private TableColumn<Libro, String> columnNombre;
    @FXML
    private TableColumn<Libro, Integer> columnPaginas;  
    @FXML
    private TableColumn<Libro, String> columnEditorial;
    
    @FXML
    private TableColumn<Libro, String> columnAutor;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldEditorial;
    @FXML
    private AnchorPane rootLibrosView;
   
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPaginas.setCellValueFactory(new PropertyValueFactory<>("nºpaginas"));
        columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        //columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnAutor.setCellValueFactory(
            cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue().getAutor()!= null) {
                   Autor autor = cellData.getValue().getAutor();
                    property.setValue(autor.getNombre()+' '+autor.getApellidos());
                }
                return property;
            });
        tableViewLibros.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    libroSeleccionado = newValue;
                    if (libroSeleccionado != null) {
                        textFieldNombre.setText(libroSeleccionado.getNombre());
                         textFieldEditorial.setText(libroSeleccionado.getEditorial());
                    } else {
                        textFieldNombre.setText("");
                        textFieldEditorial.setText("");
                    }
                });
    }    
    
    public void cargarTodosLibros() {
        Query queryLibrosFindAll = entityManager.createNamedQuery("Libro.findAll");
        List<Libro> listLibro = queryLibrosFindAll.getResultList();
        tableViewLibros.setItems(FXCollections.observableArrayList(listLibro));
    } 

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        if (libroSeleccionado != null) {
            libroSeleccionado.setNombre(textFieldNombre.getText());
            libroSeleccionado.setNombre(textFieldNombre.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(libroSeleccionado);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tableViewLibros.getSelectionModel().getSelectedIndex();
            tableViewLibros.getItems().set(numFilaSeleccionada, libroSeleccionado);
            TablePosition pos = new TablePosition(tableViewLibros, numFilaSeleccionada, null);
            tableViewLibros.getFocusModel().focus(pos);
            tableViewLibros.requestFocus();
        }
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
                try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootLibrosView.setVisible(false);
            
            FormularioFXMLController ForomularioFXMLControler = (FormularioFXMLController) fxmlLoader.getController();  
            ForomularioFXMLControler.setRootContactosView(rootLibrosView);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootLibrosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) {
            Logger.getLogger(LibrosViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootLibrosView.setVisible(false);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootLibrosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) {
            Logger.getLogger(LibrosViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
    }
}
