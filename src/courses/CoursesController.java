
package courses;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CoursesController implements Initializable {
    @FXML
    private Label courseLable;
    @FXML
    private TableView<Courses> courseTable;
    @FXML
    private TableColumn<Courses, String> id;
    @FXML
    private TableColumn<Courses, String> course;
    @FXML
    private TableColumn<Courses, Boolean> avail;
    @FXML
    private TableColumn<Courses, String> fee;
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane courseAnchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       courseLable.setText("Our Courses");
       courseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Dao dao = new Dao();
        List<Courses> datas = dao.datas();
        for(Courses data : datas){
            courseTable.getItems().add(data);
        }
    }    

    @FXML
    private void backToHome(ActionEvent event) {
        try{
        Parent route = FXMLLoader.load(getClass().getResource("/collegeregistry/FXMLDocument.fxml"));
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(route);
        stage.setScene(scene);
        stage.show();
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
