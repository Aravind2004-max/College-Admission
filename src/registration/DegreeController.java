
package registration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DegreeController implements Initializable {
    @FXML
    private AnchorPane basePane;
    @FXML
    private AnchorPane glassPane;
    @FXML
    private ComboBox<String> graduationCom;
    @FXML
    private ComboBox<CourseCom> coursesCom;
    @FXML
    private TextField stdName;
    @FXML
    private TextField stdAge;
    @FXML
    private TextField stdPh;
    @FXML
    private TextField stdMail;
    @FXML
    private TextField ugClg;
    @FXML
    private TextField ugCgpa;
    @FXML
    private ComboBox<String> feeCom;
    @FXML
    private TextField amt;
    @FXML
    private Label totalFee;
    @FXML
    private Label semFee;
    @FXML
    private Label gLable;
    @FXML
    private Label cLable;
    @FXML
    private Label clgLable;
    @FXML
    private Label mailLable;
    @FXML
    private Label ageLable;
    @FXML
    private Label cgpaLable;
    @FXML
    private Label phLable;
    @FXML
    private Label feeLable;
    @FXML
    private Label amtLable;
    @FXML
    private Label nameLable;
    
    //Payment varialbles
    private int totalSem;
    private int perSem;
    @FXML
    private Label success;
    @FXML
    private Label fill;
    @FXML
    private Label rollNo;
    @FXML
    private Label wrongID;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String graduationType[] = {"UG","PG"};
        for(String g : graduationType){
            graduationCom.getItems().add(g);
        }
        String fee[] = {"Total Fee","Per Sem"};
        for(String str : fee){
            feeCom.getItems().add(str);
        }
    }    
    @FXML
    private void settingCourses(ActionEvent event) {
        Dao dao = new Dao();
        List<CourseCom> courses = dao.comboCourses();
        String graduationType = graduationCom.getValue();
        
        //course setting up part
        coursesCom.getItems().clear();
        for(CourseCom iterating : courses){
            String c = iterating.getCourse();
            if(graduationType.equalsIgnoreCase("ug") && c.startsWith("B")){
                    coursesCom.getItems().add(iterating);
            }else if(graduationType.equalsIgnoreCase("pg") && c.startsWith("M")){
                    coursesCom.getItems().add(iterating);
            }
        }
    }

    @FXML
    private void amtSetting(ActionEvent event) {
    }
 
    @FXML
    private void submit(ActionEvent event) {
        //initialaizing part
        if (graduationCom.getValue() == null) {
            gLable.setText("Please select Graduation Type");
            return;
        } else {
            gLable.setText("");
        }
        if (coursesCom.getValue() == null) {
            cLable.setText("Please select Course Type");
            return;
        } else if (coursesCom.getValue().getCourse() == null) {
            cLable.setText("Invalid course !");
            return;
        } else {
            cLable.setText("");
        }
        String gType = graduationCom.getValue().trim();
        String cType = coursesCom.getValue().getCourse().trim();
        String name = stdName.getText().trim();
        String stdage = stdAge.getText().trim();
        String phStrats = stdPh.getText().trim();
        String mail = stdMail.getText().trim();
        String ugclg = ugClg.getText().trim();
        String amot = amt.getText().trim();
        
        //validating starts
        boolean form = !name.isEmpty() && !stdage.isEmpty() && !phStrats.isEmpty() && !mail.isEmpty()
                && !cType.isEmpty() && !amot.isEmpty() &&!gType.isEmpty();
        int age = 0;
        int amount = 0;
        long ph = 0;
        double ugcgpa = 0.0;
        if (form) {
            rollNo.setText("");
            fill.setText("");
            FinallRegistration datas = new FinallRegistration();
            Dao dao = new Dao();
            //Graduation setting
            datas.setGraduationType(gType);
            //Course setting
            datas.setCourseType(cType);
            //Name validating
            if (name.length() > 2) {
                nameLable.setText("");
                datas.setStdName(name);
            } else {
                nameLable.setText("Atleast greater than 3 characters");
                return;
            }
            //Age validating
            try {
                ageLable.setText("");
                age = Integer.parseInt(stdAge.getText().trim());
            } catch (NumberFormatException e) {
                ageLable.setText("Age should be in numeric !");
                return;
            }
            if (age > 17) {
                ageLable.setText("");
                datas.setStdAge(Integer.toString(age));
            } else {
                ageLable.setText("Age should be greater than 17");
                return;
            }
            //Phone validating
            try {
                phLable.setText("");
                ph = Long.parseLong(stdPh.getText());
            } catch (NumberFormatException e) {
                phLable.setText("Phone No Should be in numeric");
                return;
            }
            boolean starts = phStrats.startsWith("6") || phStrats.startsWith("7")
                    || phStrats.startsWith("8") || phStrats.startsWith("9");
            boolean phNo = ph >= 1000000000L && ph <= 9999999999L;

            if (String.valueOf(ph).length() == 10 && phNo && starts) {
                phLable.setText("");
                datas.setStdPh(Long.toString(ph));
            } else {
                phLable.setText("Invalid Phone number");
                return;
            }
            //Email validation
            if (mail.matches("^[a-zA-Z0-9]+@gmail.com$")) {
                mailLable.setText("");
                datas.setStdMail(mail);
            } else {
                mailLable.setText("Invalid Email Address");
                return;
            }
            //UG College validation 
            if (gType.equals("PG")) {
                if (!ugclg.isEmpty()) {
                    clgLable.setText("");
                    datas.setUgClg(ugclg);
                } else {
                    clgLable.setText("PG Students must fill this !");
                    return;
                }
                try {
                    ugcgpa = Double.parseDouble(ugCgpa.getText());
                    cgpaLable.setText("");
                } catch (NumberFormatException e) {
                    cgpaLable.setText("CGPA must be in Numeric !");
                    return;
                }
                if (ugcgpa > 0 && ugcgpa < 11) {
                    if (ugcgpa > 4.0) {
                        cgpaLable.setText("");
                        datas.setUgCgpa(Double.toString(ugcgpa));
                    } else if (ugcgpa <= 4.0) {
                        cgpaLable.setText("Not Eligible with this CGPA");
                        return;
                    }
                } else {
                    cgpaLable.setText("Invalid CGPA ! Must in 1 to 10 range");
                    return;
                }
            }
            if(gType.equals("UG")){
                if(!ugclg.isEmpty()){
                    clgLable.setText("UG students no need to fill this !");
                    return;
                }else{
                    clgLable.setText("");
                }
                if(!ugCgpa.getText().isEmpty()){
                    cgpaLable.setText("UG students no need to fill this !");
                    return;
                }else{
                    cgpaLable.setText("");
                }
            }
            //Payment Validation 
            if (feeCom.getValue() == null) {
                feeLable.setText("Please select Fees Type");
                return;
            } else {
                feeLable.setText("");
            }
            String feeType = feeCom.getValue();
            datas.setFeeType(feeType);
            try {
                amtLable.setText("");
                amount = Integer.parseInt(amot);
            } catch (NumberFormatException e) {
                amtLable.setText("Amount should be in numeric !");
                return;
            }
            if (feeType.startsWith("T")) {
                if (amount == totalSem) {
                    amtLable.setText("");
                    datas.setAmount(Integer.toString(amount));
                } else {
                    amtLable.setText("You have to pay the Total Fee mentioned above !");
                    return;
                }
            } else if (feeType.startsWith("P")) {
                if (amount == perSem) {
                    amtLable.setText("");
                    datas.setAmount(Integer.toString(amount));
                } else {
                    amtLable.setText("You have to pay the Per Sem Fee mentioned above !");
                    return;
                }
            }
            boolean result = dao.finallRegistry(datas);
            if (result) {
                ugCgpa.setText("");
                ugClg.setText("");
                clgLable.setText("");
                cgpaLable.setText("");
                graduationCom.setValue("");
                coursesCom.setValue(null);
                stdName.setText("");
                stdAge.setText("");
                stdPh.setText("");
                stdMail.setText("");
                feeCom.setValue("");
                amt.setText("");
                fill.setText("");
                success.setText("Registered Successfully");

                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                pause.setOnFinished(evennt -> {
                    success.setText("");
                    //Id generation
                    int id = Integer.parseInt(dao.getId());
                    if(id > 100){
                        rollNo.setText(Integer.toString(id));
                    }else{
                        wrongID.setText("Please Contact Respected Staff !");
                    }
                });
                pause.play();
                
                PauseTransition pause2 = new PauseTransition(Duration.seconds(8));
                pause2.setOnFinished(eve ->{
                    try{
                       Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); 
                       Parent route = FXMLLoader.load(getClass().getResource("/collegeregistry/FXMLDocument.fxml"));
                       Scene scene = new Scene(route);
                       stage.setScene(scene);
                       stage.show();
                    }catch(IOException e){
                        throw new RuntimeException(e.getMessage());
                    }
                });
                pause2.play();
            } else {
                fill.setText("Something Went Wrong !");
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                pause.setOnFinished(pausing -> {
                    fill.setText("");
                });
                pause.play();
            }
        } else {
            success.setText("");
            fill.setText("Please Fill All Requirements !");
        }
    }

    @FXML
    private void fixingFees(ActionEvent event) {
        //amount setting after course selected
        Dao dao = new Dao();
        
        //checking null
        if(coursesCom.getValue() == null){
            totalFee.setText("");
            semFee.setText("");
            return;
        }
        
        // fixing fees
        String course = coursesCom.getValue().toString();
        List<CourseAmt> amt = dao.courseAmt();
        String courseNull = coursesCom.getValue().getCourse().trim();
        if(!courseNull.isEmpty()){
            for(CourseAmt courseAmt : amt){
                if(course.equals(courseAmt.getCourseName())){
                    totalFee.setText(courseAmt.getCourseAmt());
                    totalSem = Integer.parseInt(courseAmt.getCourseAmt());
                    String ugPg = graduationCom.getValue();
                    if(ugPg.equals("UG")){
                        perSem = totalSem/6;
                        semFee.setText(Integer.toString(perSem));
                    }else if(ugPg.equals("PG")){
                        perSem = totalSem/4;
                        semFee.setText(Integer.toString(perSem));
                    }
                }
            }
        }else {
            semFee.setText("");
            totalFee.setText("");
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
