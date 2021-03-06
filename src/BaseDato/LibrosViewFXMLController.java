/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
        try{
            if (libroSeleccionado != null) {
            libroSeleccionado.setNombre(textFieldNombre.getText());
            libroSeleccionado.setEditorial(textFieldEditorial.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(libroSeleccionado);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tableViewLibros.getSelectionModel().getSelectedIndex();
            tableViewLibros.getItems().set(numFilaSeleccionada, libroSeleccionado);
            TablePosition pos = new TablePosition(tableViewLibros, numFilaSeleccionada, null);
            tableViewLibros.getFocusModel().focus(pos);
            tableViewLibros.requestFocus();
        }
        }catch(Exception e){}
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
                try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootLibrosView.setVisible(false);
            
            FormularioFXMLController foromularioFXMLControler = (FormularioFXMLController) fxmlLoader.getController();  
            foromularioFXMLControler.setRootContactosView(rootLibrosView);
            foromularioFXMLControler.setTableViewPrevio(tableViewLibros);
            
            libroSeleccionado = new Libro();
            foromularioFXMLControler.setLibro(entityManager, libroSeleccionado, true);
            foromularioFXMLControler.mostrarDatos();

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootLibrosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) {
            Logger.getLogger(LibrosViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        if(libroSeleccionado != null && libroSeleccionado.getId() != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
                Parent rootDetalleView = fxmlLoader.load();
                rootLibrosView.setVisible(false);
                
                StackPane rootMain = (StackPane)rootLibrosView.getScene().getRoot();
                rootMain.getChildren().add(rootDetalleView);
                
                FormularioFXMLController foromularioFXMLControler = (FormularioFXMLController) fxmlLoader.getController();  
                foromularioFXMLControler.setRootContactosView(rootLibrosView);
                foromularioFXMLControler.setTableViewPrevio(tableViewLibros);
                foromularioFXMLControler.setLibro(entityManager, libroSeleccionado, false);
                foromularioFXMLControler.mostrarDatos();
            } catch (IOException ex) {
                Logger.getLogger(LibrosViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
        if(libroSeleccionado != null && libroSeleccionado.getId() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Desea suprimir el siguiente registro?");
            alert.setContentText(libroSeleccionado.getNombre() + " "
                    + libroSeleccionado.getNombre());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                entityManager.getTransaction().begin();
                entityManager.merge(libroSeleccionado);
                entityManager.remove(libroSeleccionado);
                entityManager.getTransaction().commit();
                tableViewLibros.getItems().remove(libroSeleccionado);
                tableViewLibros.getFocusModel().focus(null);
                tableViewLibros.requestFocus();
            } else {
                int numFilaSeleccionada = tableViewLibros.getSelectionModel().getSelectedIndex();
                tableViewLibros.getItems().set(numFilaSeleccionada, libroSeleccionado);
                TablePosition pos = new TablePosition(tableViewLibros, numFilaSeleccionada, null);
                tableViewLibros.getFocusModel().focus(pos);
                tableViewLibros.requestFocus();            
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }
}

