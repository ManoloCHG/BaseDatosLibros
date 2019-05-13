/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDato;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author PC04
 */
public class FormularioFXMLController implements Initializable {

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
    private ComboBox<?> comboBoxAutor;
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
