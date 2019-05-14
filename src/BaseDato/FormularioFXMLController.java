/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author PC04
 */
public class FormularioFXMLController implements Initializable {
    
    private TableView tableViewPrevio;
    private Libro libro;
    private EntityManager entityManager;
    private boolean nuevoLibro;
    private Character encuadernacion;

    public static final String TAPA_DURA = "G";
    public static final String TAPA_BLANDA = "B";
    public static final String DIGITAL = "D";
    public static final String CARPETA_FOTOS = "Fotos";


    @FXML
    private TextArea textAreaDescripcion;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldPaginas;
    @FXML
    private TextField textFieldEditorial;
    @FXML
    private TextField textFieldIsbn;
    @FXML
    private TextField textFieldPrecio;
    @FXML
    private CheckBox checkBoxStock;
    @FXML
    private ComboBox<Autor> comboBoxAutor;
    @FXML
    private ComboBox<?> comboBoxPais;
    @FXML
    private ComboBox<?> comboBoxIdioma;
    @FXML
    private DatePicker datePickerFechaEdicion;
    @FXML
    private RadioButton radioButtonTapadura;
    @FXML
    private RadioButton radioButtonTapablanda;
    @FXML
    private RadioButton radioButtonDigital;
    @FXML
    private ImageView imageViewFoto;
    private Pane rootLibrosView;
    @FXML
    private AnchorPane rootFormularioFXMLView;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setRootContactosView(Pane rootContactosView) {
    this.rootLibrosView = rootContactosView;
    }

    public void setTableViewPrevio(TableView tableViewPrevio) {
    this.tableViewPrevio = tableViewPrevio;
    }
    
    public void setLibro(EntityManager entityManager, Libro libro, boolean nevoLibro) {
        this.entityManager = entityManager;
        entityManager.getTransaction().begin();
        if (!nevoLibro) {
            this.libro = entityManager.find(Libro.class, libro.getId());
        } else {
            this.libro = libro;
        }
        this.nuevoLibro = nevoLibro;
    }
    
//    public void mostrarDatos() {
//        textAreaDescripcion.setText(libro.getDescripcion());
//        textFieldNombre.setText(libro.getNombre());
//        textFieldPaginas.setText(String.valueOf(libro.getNºpaginas()));
//        textFieldEditorial.setText(libro.getEditorial());
//        textFieldIsbn.setText(libro.getIsbn());
//        textFieldPrecio.setText(String.valueOf(libro.getPrecio()));
//        if (libro.getEnEstock()!= null) {
//        checkBoxStock.setSelected(libro.getEnEstock());
//        }
//        if (libro.getEncuadernacion()!= null) {
//        switch (libro.getEncuadernacion()) {
//            case TAPA_DURA:
//                radioButtonTapadura.setSelected(true);
//                break;
//            case TAPA_BLANDA:
//                radioButtonTapablanda.setSelected(true);
//                break;
//            case DIGITAL:
//                radioButtonDigital.setSelected(true);
//                break;
//        }
//        }
//        if (libro.getAñoEdicion()!= null) {
//            Date date = libro.getAñoEdicion();
//            Instant instant = date.toInstant();
//            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//            LocalDate localDate = zdt.toLocalDate();
//            datePickerFechaEdicion.setValue(localDate);
//        }
////        if (libro.getFoto() != null) {
////            String imageFileName = libro.getFoto();
////            File file = new File(CARPETA_FOTOS + "/" + imageFileName);
////            if (file.exists()) {
////                Image image = new Image(file.toURI().toString());
////                imageViewFoto.setImage(image);
////            } else {
////                Alert alert = new Alert(AlertType.INFORMATION, "No se encuentra la imagen");
////                alert.showAndWait();
////            }
////        }
//        Query queryProvinciaFindAll = entityManager.createNamedQuery("Provincia.findAll");
//        List listAutor = queryProvinciaFindAll.getResultList();
//        comboBoxAutor.setItems(FXCollections.observableList(listAutor));
//    
//        if (libro.getAutor() != null) {
//        comboBoxAutor.setValue(libro.getAutor());}
//        comboBoxAutor.setCellFactory((ListView<Autor> l) -> new ListCell<Autor>() {
//            @Override
//            protected void updateItem(Autor autor, boolean empty) {
//                super.updateItem(autor, empty);
//                if (autor == null || empty) {
//                    setText("");
//                } else {
//                    setText(autor.getNombre()+""+autor.getApellidos());
//                }
//            }
//        });
//}
    
    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        StackPane rootMain = (StackPane) rootFormularioFXMLView.getScene().getRoot();
        rootMain.getChildren().remove(rootFormularioFXMLView);

        rootLibrosView.setVisible(true);
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain = (StackPane) rootFormularioFXMLView.getScene().getRoot();
        rootMain.getChildren().remove(rootFormularioFXMLView);

        rootLibrosView.setVisible(true);
    }

    @FXML
    private void onActionButtonExaminar(ActionEvent event) {
    }
}
