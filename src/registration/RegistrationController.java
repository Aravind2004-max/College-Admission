
package registration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RegistrationController implements Initializable {
    @FXML
    private AnchorPane glassPane;
    @FXML
    private TextField name;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton other;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> com1;
    @FXML
    private TextField fo;
    @FXML
    private TextField fName;
    @FXML
    private ComboBox<String> com2;
    @FXML
    private TextField mName;
    @FXML
    private TextField mark2;
    @FXML
    private TextField sName1;
    @FXML
    private TextField mark1;
    @FXML
    private TextField sName2;
    @FXML
    private TextField mo;
    @FXML
    private TextArea addr;
    @FXML
    private Button sub;
    @FXML
    private Label status;
    @FXML
    private Label status2;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label marks;
    @FXML
    private Label markks;
    @FXML
    private Label ageLabel;
    @FXML
    private Label board1;
    @FXML
    private Label board2;
    @FXML
    private Label nameLable;
    @FXML
    private Label genLable;
    @FXML
    private ImageView img;
    @FXML
    private Label fnLable;
    @FXML
    private Label mnLable;
    @FXML
    private Label foLable;
    @FXML
    private Label moLable;
    @FXML
    private Label addrLable;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        glassPane.setEffect(new GaussianBlur(25));
        
        String []board = {"StateBoard","CBSE"};
        com1.getSelectionModel().select("Board");
        com2.getSelectionModel().select("Board");
        for(String b : board){
            com1.getItems().add(b);
            com2.getItems().add(b);
        }
    }    

    @FXML
    private void submit(ActionEvent event) {
        Registeration reg = new Registeration();
        Dao dao = new Dao();
        int age = 0;
        int mark12 = 0;
        int mark10 = 0;

        //converting part.
        String name = this.name.getText().trim();
        boolean male = this.male.isSelected();
        boolean female = this.female.isSelected();
        boolean other = this.other.isSelected();
        String stdAge = this.age.getText().trim();
        String board = com1.getValue().trim();
        String hsc = sName1.getText().trim();
        String board10 = com2.getValue().trim();
        String sslc = sName2.getText().trim();
        String fname = this.fName.getText().trim();
        String mname = this.mName.getText().trim();
        String foc = this.fo.getText().trim();
        String moc = this.mo.getText().trim();
        String addre = this.addr.getText().trim();

        //logic part
        boolean form = !name.isEmpty()&& !board.isEmpty()
                &&!stdAge.isEmpty()&& !hsc.isEmpty() && !board10.isEmpty() && !sslc.isEmpty()
                && !fname.isEmpty() && !mname.isEmpty() && !foc.isEmpty()
                && !moc.isEmpty() && !addre.isEmpty();

        if (form) {
            status.setText("");
            status2.setText("");
            //Name validation
            if(name.length() > 2){
                nameLable.setText("");
                reg.setName(name);}
            else{
                nameLable.setText("Atleast greater than 3 characters");
                return;
            }
            //age validation
            try {
                age = Integer.parseInt(this.age.getText());
                ageLabel.setText("");
            } catch (NumberFormatException e) {
                ageLabel.setText("Age must be in numeric only !");
                return;
            }
            if (age > 17) {
                ageLabel.setText("");
                reg.setAge(Integer.toString(age));
            } else if (age < 18) {
                ageLabel.setText("Age must be greater than 17 !");
                return;
            }
            if(male == false && female == false && other == false){
               genLable.setText("Please select something !");
               return;
            }else{
                genLable.setText("");
                if (male){
                    reg.setGender(this.male.getText());
                } else if (female) {
                    reg.setGender(this.female.getText());
                } else if (other) {
                    reg.setGender(this.other.getText());
                }
            }
            //hsc validation
            try {
                mark12 = Integer.parseInt(mark1.getText());
                marks.setText("");
            } catch (NumberFormatException e) {
                marks.setText("Marks must be in numeric only !");
                return;
            }
            if (mark12 >= 300 && mark12 <= 600) {
                marks.setText("");
                if (board.equals("Board")) {
                    board1.setText("Please select the BOARD !");
                    return;
                }
                board1.setText("");
                reg.setBoard(board);
                reg.setsName(hsc);
                reg.setMark(Integer.toString(mark12));
            } else if (mark12 < 300) {
                marks.setText("Invalid mark should be greater than 299 !");
                return;
            } else if (mark12 > 600) {
                marks.setText("Invalid mark should be less than 600 !");
                return;
            }
            //sslc validation
            try {
                mark10 = Integer.parseInt(mark2.getText());
                markks.setText("");
            } catch (NumberFormatException e) {
                markks.setText("Marks must be in numeric only !");
                return;
            }
            if (mark10 > 249 && mark10 < 501) {
                markks.setText("");
                if (board10.equals("Board")) {
                    board2.setText("Please select the BOARD !");
                    return;
                }
                board2.setText("");
                reg.setBoard1(board10);
                reg.setsName1(sslc);
                reg.setMark1(Integer.toString(mark10));
            } else if (mark10 < 249) {
                markks.setText("Invalid mark should be greater than 249 !");
                return;
            } else if (mark10 > 500) {
                markks.setText("Invalid mark should be less than 501 !");
                return;
            }
            if(fname.length() > 2){
                fnLable.setText("");
                reg.setfName(fname);
            }else{
                fnLable.setText("Atleast greater than 3 characters");
                return;
            }
            if(mname.length() > 2){
                mnLable.setText("");
                reg.setmName(mname);
            }else{
                mnLable.setText("Atleast greater than 3 characters");
                return;
            }
            if(foc.length() > 2){
                foLable.setText("");
                reg.setFo(foc);
            }else{
                foLable.setText("Atleast greater than 3 characters");
                return;
            }
            if(moc.length() > 2){
                moLable.setText("");
                reg.setMo(moc);
            }else{
                moLable.setText("Atleast greater than 3 characters");
                return;
            }
            if(addre.length() > 10){
                addrLable.setText("");
                reg.setAddr(addre);
            }else{
                addrLable.setText("Atleast greater than 10 characters");
                return;
            }

            //inserting part 
            boolean success = dao.registry(reg);
            if (success) {
                //cleaning process after inserting
                status.setText("");
                ageLabel.setText("");
                markks.setAccessibleHelp("");
                marks.setText("");
                this.name.setText("");
                this.age.setText("");
                this.male.setSelected(false);
                this.female.setSelected(false);
                this.other.setSelected(false);
                com1.setValue("Board");
                com2.setValue("Board");
                sName1.setText("");
                mark1.setText("");
                com2.setValue("Board");
                sName2.setText("");
                mark2.setText("");
                fName.setText("");
                mName.setText("");
                fo.setText("");
                mo.setText("");
                addr.setText("");
                status2.setText("Registered Successfully");
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(r -> {
                    status2.setText("");
                });
                pause.play();

                //next screen for final registration
                pause = new PauseTransition(Duration.seconds(6));
                pause.setOnFinished(r -> {
                    try {
                        Parent route = FXMLLoader.load(getClass().getResource("/registration/Degree.fxml"));
                        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(route);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
                pause.play();
            } else {
                status2.setText("Something went wrong");
            }
        } else {
            status.setText("Please fill all Required fields!");
        }
    }

    @FXML
    private void male(ActionEvent event) {
        female.setSelected(false);
        other.setSelected(false);
    }

    @FXML
    private void female(ActionEvent event) {
        male.setSelected(false);
        other.setSelected(false);
    }

    @FXML
    private void others(ActionEvent event) {
        female.setSelected(false);
        male.setSelected(false);
    }

    @FXML
    private void backToHome(ActionEvent event) {
        try {
            Parent route = FXMLLoader.load(getClass().getResource("/collegeregistry/FXMLDocument.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(route);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
