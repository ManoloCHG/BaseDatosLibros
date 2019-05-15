/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.math.BigDecimal;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;

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
    
    public void mostrarDatos() {
        textAreaDescripcion.setText(libro.getDescripcion());
        textFieldNombre.setText(libro.getNombre());
        textFieldPaginas.setText(String.valueOf(libro.getNºpaginas()));
        textFieldEditorial.setText(libro.getEditorial());
        textFieldIsbn.setText(libro.getIsbn());
        textFieldPrecio.setText(String.valueOf(libro.getPrecio()));
        if (libro.getEnEstock()!= null) {
        checkBoxStock.setSelected(libro.getEnEstock());
        }
        if (libro.getEncuadernacion()!= null) {
        switch (libro.getEncuadernacion()) {
            case TAPA_DURA:
                radioButtonTapadura.setSelected(true);
                break;
            case TAPA_BLANDA:
                radioButtonTapablanda.setSelected(true);
                break;
            case DIGITAL:
                radioButtonDigital.setSelected(true);
                break;
            }
        }
        if (libro.getAñoEdicion()!= null) {
            Date date = libro.getAñoEdicion();
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            datePickerFechaEdicion.setValue(localDate);
        }
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
        Query queryProvinciaFindAll = entityManager.createNamedQuery("Libro.findAll");
        List listAutor = queryProvinciaFindAll.getResultList();
        comboBoxAutor.setItems(FXCollections.observableList(listAutor));
    
        if (libro.getAutor() != null) {
        comboBoxAutor.setValue(libro.getAutor());}
        comboBoxAutor.setCellFactory((ListView<Autor> l) -> new ListCell<Autor>() {
            @Override
            protected void updateItem(Autor autor, boolean empty) {
                super.updateItem(autor, empty);
                if (autor == null || empty) {
                    setText("");
                } else {
                    setText(autor.getNombre()+" "+autor.getApellidos());
                }
            }
        });
        comboBoxAutor.setConverter(new StringConverter<Autor>() {
            @Override
            public String toString(Autor autor) {
                if (libro == null) {
                    return null;
                } else {
                    return autor.getNombre()+" "+autor.getApellidos();
                }
            }

            @Override
            public Autor fromString(String ID) {
                return null;
            }
        });
}
    
    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        int numFilaSeleccionada;
        boolean errorFormato = false;

        libro.setNombre(textFieldNombre.getText());
        libro.setDescripcion(textAreaDescripcion.getText());
        libro.setNºpaginas(Integer.valueOf(textFieldPaginas.getText()));
        libro.setEditorial(textFieldEditorial.getText());
        libro.setIsbn(textFieldIsbn.getText());
        textFieldPrecio.setText(String.valueOf(libro.getPrecio()));
        libro.setPrecio(BigDecimal.valueOf(Double.valueOf(textFieldPrecio.getText())));
        if(radioButtonTapadura.isSelected()) {
                libro.setEncuadernacion(TAPA_DURA);
            } else if(radioButtonTapablanda.isSelected()) {
                libro.setEncuadernacion(TAPA_BLANDA);
            } else if(radioButtonDigital.isSelected()) {
                libro.setEncuadernacion(DIGITAL);
            }
            if(datePickerFechaEdicion.getValue() != null) {
            LocalDate localDate = datePickerFechaEdicion.getValue();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            libro.setAñoEdicion(date);
            } else {
                libro.setAñoEdicion(null);
            }
        if (!errorFormato) {
            try {
                if (nuevoLibro) {
                    entityManager.persist(libro);
                } else {
                    entityManager.merge(libro);
                }
                entityManager.getTransaction().commit();

                StackPane rootMain = (StackPane) rootFormularioFXMLView.getScene().getRoot();
                rootMain.getChildren().remove(rootFormularioFXMLView);
                rootLibrosView.setVisible(true);

                if (nuevoLibro) {
                    tableViewPrevio.getItems().add(libro);
                    numFilaSeleccionada = tableViewPrevio.getItems().size() - 1;
                    tableViewPrevio.getSelectionModel().select(numFilaSeleccionada);
                    tableViewPrevio.scrollTo(numFilaSeleccionada);
                } else {
                    numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
                    tableViewPrevio.getItems().set(numFilaSeleccionada, libro);
                }
                TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
                tableViewPrevio.getFocusModel().focus(pos);
                tableViewPrevio.requestFocus();
            } catch (RollbackException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios. "
                        + "Compruebe que los datos cumplen los requisitos");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            }
        }
        
        rootLibrosView.setVisible(true);
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        entityManager.getTransaction().rollback();

        
        StackPane rootMain = (StackPane) rootFormularioFXMLView.getScene().getRoot();
        rootMain.getChildren().remove(rootFormularioFXMLView);

        rootLibrosView.setVisible(true);
        int numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
    }

    @FXML
    private void onActionButtonExaminar(ActionEvent event) {
    }
}
