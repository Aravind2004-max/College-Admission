
package collegeregistry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane glassPane;
    @FXML
    private ImageView image;
    @FXML
    private Button courses;
    @FXML
    private Button registry;
    @FXML
    private Button enrollment;
    @FXML
    private Button aboutUs;
    @FXML
    private AnchorPane banner;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        glassPane.setEffect(new GaussianBlur(15));
    }    

    @FXML
    private void courses(ActionEvent event) {
        try{
            Parent route = FXMLLoader.load(getClass().getResource("/courses/Courses.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(route);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void registration(ActionEvent event) {
        try{
          //  glassPane.getScene().getWindow().hide();
            Parent route = FXMLLoader.load(getClass().getResource("/registration/registration.fxml"));
            Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(route);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void enrolledStudents(ActionEvent event) {
        try{
            Parent route = FXMLLoader.load(getClass().getResource("/enrolledStudents/EnrolledStudents.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(route);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void aboutUs(ActionEvent event) {
        try{
            Parent route = FXMLLoader.load(getClass().getResource("/aboutUs/AboutUs.fxml"));
            Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            Scene scene =  new Scene(route);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
