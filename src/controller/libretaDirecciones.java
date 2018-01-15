package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Persona;
import view.VistaPersonaController;

/**
 *
 * @author dam
 */
public class libretaDirecciones extends Application {

    private ObservableList datosPersona = FXCollections.observableArrayList();
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaPersona;
    
    //Datos de ejemplo
    public libretaDirecciones(){    
        datosPersona.add(new Persona("Jorge", "Diaz Megias"));
        datosPersona.add(new Persona("Jorge", "Melero Muñoz"));
        datosPersona.add(new Persona("Sergio", "Cabello"));
        datosPersona.add(new Persona("Rodrigo", "Muñoz"));
        datosPersona.add(new Persona("Manuel", "Moreno"));
    }
    
    //Método para devolver los datos como lista observable de personas
    public ObservableList getDatosPersona() {
        return datosPersona;
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        //Debo hacerlo para que luego me funcione en l carga de escenas
        this.escenarioPrincipal = escenarioPrincipal;

        //Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");

        //Inicializo el layout principal
        initLayoutPrincipal();

        //Muestro la vista persona
        muestraVistaPersona();
    }

    public void initLayoutPrincipal() {

        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = libretaDirecciones.class.getResource("../view/vistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(libretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo y muestro la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    public void muestraVistaPersona() {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = libretaDirecciones.class.getResource("../view/vistaPersona.fxml");
        loader.setLocation(location);
        try {
            vistaPersona = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(libretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Añado la vista al centro del layoutPrincipal
        layoutPrincipal.setCenter(vistaPersona);
        
        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaPersonaController controller = loader.getController();
        controller.setLibretaDirecciones(this);
    }

    //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }

    //Metodo main
    public static void main(String[] args) {
        launch(args);
    }

}
