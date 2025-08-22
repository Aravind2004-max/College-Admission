
package enrolledStudents;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class EnrolledStudentsController implements Initializable {
    @FXML
    private AnchorPane glassPane;
    @FXML
    private TableView<StdDetails> stdDetails;
    @FXML
    private TableColumn<StdDetails,String> details;
    @FXML
    private TextField rollNo;
    @FXML
    private Label notFound;
    @FXML
    private Button backBtn;
    @FXML
    private Label invalidRno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        details.setCellFactory(column -> new TableCell<StdDetails, String>() {
            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(column.widthProperty().subtract(10)); // wrap within column
                text.getStyleClass().add("table-cell"); // apply CSS styles
                setGraphic(text);
                setPrefHeight(Control.USE_COMPUTED_SIZE);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    text.setText(null);
                } else {
                    text.setText(item);
                }
            }
        });
    }    

    @FXML
    private void searcher(ActionEvent event) {
        Dao dao = new Dao();
        stdDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        stdDetails.getItems().clear();
        int id = 0;
        try{
          invalidRno.setText("");
          notFound.setText("");
          id = Integer.parseInt(rollNo.getText());   
        }catch(NumberFormatException e){
            invalidRno.setText("Invalid Roll No !");
            notFound.setText("Roll No must be in numeric !");
            return;
        }
        List<StdDetails> student = dao.enrolledStuds(id);
        details.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().toString()));
        
        boolean found = false;
        for(StdDetails std : student){
            if(std.getId().equals(rollNo.getText())){
                notFound.setText("");
                stdDetails.getItems().add(std);
                found = true;
                break;
            }
        }
        if(!found){
            invalidRno.setText("Invalid Roll No !");
            notFound.setText("Student Not Found !");
        }
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
