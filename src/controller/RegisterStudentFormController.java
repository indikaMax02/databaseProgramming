package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterStudentFormController {
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtdateOfBirth;
    public TextField txtParent;
    public TextField txtContact;
    public TextArea txtAddress;
    public TextField txtOlResult;
    public TextField txtALResult;
    public TextField txtEmail;
    public JFXButton btnRegister;
    public JFXComboBox cmdCourse;
    public JFXComboBox cmbGender;
    public TextArea txtMetadata;
    public TextField txtBatchNumber;
    Pattern idPattern = Pattern.compile("^[0-9]{1,4}$");
    Pattern namePattern = Pattern.compile("^[A-z]*[ ]?[A-z]*[ ]?[A-z]$");
    Pattern nicPattern = Pattern.compile("^[0-9]{9}[V,v]|[0-9]{12}$");
    Pattern dateOfBirthPattern = Pattern.compile("^[0-9]{4}[-|/][0-9]{2}[-|/][0-9]{2}$");
    Pattern parentNamePattern = Pattern.compile("^[A-z.]*[A-z]{3,}[ ]?[A-z]*[ ]?[A-z]*$");
    Pattern phoneNumberPattern = Pattern.compile("^[0]{1}[7][0|1|2|4|5|6|7|8|0][0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z, .0-9/]*$");

    LinkedHashMap<TextField,Pattern> hashMap=new LinkedHashMap<>();
    RegisterStudentBO registerStudentBO =(RegisterStudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER_STUDENT);

    List<CourseDTO> allCourses = registerStudentBO.getAllCourses();
    public void initialize() {


        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("male");
        gender.add("female");
        gender.add("other");
        cmbGender.setItems(gender);

        List<String> courseName=new ArrayList<>();

        for (CourseDTO c : allCourses) {
            if (!courseName.contains(c.getProgram())){
                courseName.add(c.getProgram());
            }
        }
        cmdCourse.setItems(FXCollections.observableArrayList(courseName));



        cmdCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (CourseDTO c : allCourses) {
                if (newValue.equals(c.getProgram())){
                    txtBatchNumber.setText(c.getBatch_number());
                }
            }
        });



        hashmapDataSet();


    }


    private void hashmapDataSet() {
        hashMap.put(txtStudentId,idPattern);
        hashMap.put(txtName,namePattern);
        hashMap.put(txtNIC,nicPattern);
        hashMap.put(txtdateOfBirth,dateOfBirthPattern);
        hashMap.put(txtParent,parentNamePattern);
        hashMap.put(txtContact,phoneNumberPattern);
        // hashMap.put(txtAddress,addressPattern);

    }


    public void registerStudentOnAction(ActionEvent actionEvent) {


        for (CourseDTO course : allCourses) {
            if (cmdCourse.getValue().equals(course.getProgram())){
                studentDTO.setCourseDTO(course);
                break;
            }
        }





        if (txtStudentId.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fail").showAndWait();
        }else {
            registerStudentBO.registerStudent(studentDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Student Regiter Complete").showAndWait();
        }
    }




    public void textField_KeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(hashMap,btnRegister);
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if(response instanceof TextField){
                TextField textField= (TextField) response;
                textField.requestFocus();
            }
        }

    }
}
