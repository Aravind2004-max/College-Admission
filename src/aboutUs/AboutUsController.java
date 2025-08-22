
package aboutUs;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AboutUsController implements Initializable {
    @FXML
    private AnchorPane glassPane;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void backToHome(ActionEvent event) {
        try{
            Parent route = FXMLLoader.load(getClass().getResource("/collegeregistry/FXMLDocument.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(route);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
