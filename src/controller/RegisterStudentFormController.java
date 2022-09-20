package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.RegisterStudent;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterStudentFormController {
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtContact;
    public TextArea txtAddress;
    public TextField txtEmail;
    public JFXButton btnRegister;
    public JFXComboBox cmdCourse;
    public JFXComboBox cmbGender;
    public TextField txtBatchNumber;
    public TextField txtRegisterId;
    public TextField txtPayment;
    public JFXComboBox cmbIntake;
    Pattern idPattern = Pattern.compile("^[0-9]{1,4}$");
    Pattern namePattern = Pattern.compile("^[A-z]*[ ]?[A-z]*[ ]?[A-z]$");
    Pattern nicPattern = Pattern.compile("^[0-9]{9}[V,v]|[0-9]{12}$");
    Pattern dateOfBirthPattern = Pattern.compile("^[0-9]{4}[-|/][0-9]{2}[-|/][0-9]{2}$");
    Pattern parentNamePattern = Pattern.compile("^[A-z.]*[A-z]{3,}[ ]?[A-z]*[ ]?[A-z]*$");
    Pattern phoneNumberPattern = Pattern.compile("^[0]{1}[7][0|1|2|4|5|6|7|8|0][0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z, .0-9/]*$");

   /* LinkedHashMap<TextField,Pattern> hashMap=new LinkedHashMap<>();
    RegisterStudentBO registerStudentBO =(RegisterStudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER_STUDENT);

    List<CourseDTO> allCourses = registerStudentBO.getAllCourses();*/
    public void initialize() {


        ObservableList<String> courseIdList=FXCollections.observableArrayList();

        try {
            Connection connection = DbConnection.getInstance().getConnection();


            txtStudentId.setText(autoGenarateStudentId());
            txtRegisterId.setText(autoGenarateRegisterId());

            ObservableList<String> intake=FXCollections.observableArrayList();
            PreparedStatement stm1 = connection.prepareStatement("SELECT description FROM Intake");
            ResultSet resultSet1 = stm1.executeQuery();
            while (resultSet1.next()){
                intake.add(resultSet1.getString("description"));
            }
            cmbIntake.setItems(intake);


            PreparedStatement stm = connection.prepareStatement("SELECT course_id FROM Course");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()){
                courseIdList.add(resultSet.getString("course_id"));
            }

            for (String s : courseIdList) {
                System.out.println(s);
            }
            cmdCourse.setItems(courseIdList);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("male");
        gender.add("female");
        gender.add("other");
        cmbGender.setItems(gender);




        cmdCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            /*for (CourseDTO c : allCourses) {
                if (newValue.equals(c.getProgram())){
                    txtBatchNumber.setText(c.getBatch_number());
                }
            }*/
        });



       /* hashmapDataSet();*/


    }


   /* private void hashmapDataSet() {
        hashMap.put(txtStudentId,idPattern);
        hashMap.put(txtName,namePattern);
        hashMap.put(txtNIC,nicPattern);
        hashMap.put(txtdateOfBirth,dateOfBirthPattern);
        hashMap.put(txtParent,parentNamePattern);
        hashMap.put(txtContact,phoneNumberPattern);
        // hashMap.put(txtAddress,addressPattern);

    }*/


    public void registerStudentOnAction(ActionEvent actionEvent) {

             RegisterStudent registerStudent=new RegisterStudent();
             registerStudent.setId(txtStudentId.getText());
             registerStudent.setRegisterId(txtRegisterId.getText());
             registerStudent.setName(txtName.getText());
             registerStudent.setDate(String.valueOf(java.time.LocalDate.now()));
             registerStudent.setEmail(txtEmail.getText());
             registerStudent.setContact(txtContact.getText());
             registerStudent.setAddress(txtAddress.getText());
             registerStudent.setNic(txtNIC.getText());
             registerStudent.setCourse(String.valueOf(cmdCourse.getValue()));
             registerStudent.setIntake(txtBatchNumber.getText());
             registerStudent.setPayment(Double.parseDouble(txtPayment.getText()));

        try {
            if(registerStudent(registerStudent)){
                new Alert(Alert.AlertType.CONFIRMATION,"Sudent Register Completed..").showAndWait();
            }else {
                new Alert(Alert.AlertType.WARNING,"Registration Fail..").showAndWait();
            }
        } catch (SQLException se) {
            new Alert(Alert.AlertType.ERROR,se.getLocalizedMessage()).showAndWait();
        }
/*
        for (CourseDTO course : allCourses) {
            if (cmdCourse.getValue().equals(course.getProgram())){
                studentDTO.setCourseDTO(course);
                break;
            }*/




        }
    public String autoGenarateRegisterId() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT registration_id FROM Registration ORDER BY registration_id DESC LIMIT 1");
        ResultSet resultSet = stm.executeQuery();
        String studentId = null;
        if (resultSet.next()) {
            studentId=null;
            studentId = resultSet.getString("registration_id");
            if(studentId != null) {
                String[] split = studentId.split("-");
                int id = Integer.parseInt(split[1])+1;

                if (id < 10) {
                    return "REG-00" + id;
                } else if (id >= 10 && id < 100) {
                    return "REG-0" + id;
                } else if (id > 100) {
                    return "REG-" + id;
                }
                return "REG-001";
            } else {
                return "REG-001";
            }
        }

        return "REG-001";
    }


        public String autoGenarateStudentId() throws SQLException, ClassNotFoundException {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT student_id FROM Student ORDER BY student_id DESC LIMIT 1");
            ResultSet resultSet = stm.executeQuery();
            String studentId = null;
            if (resultSet.next()) {
                studentId=null;
                studentId = resultSet.getString("student_id");
                if(studentId != null) {
                    String[] split = studentId.split("-");
                    int id = Integer.parseInt(split[1])+1;

                    if (id < 10) {
                        return "S-00" + id;
                    } else if (id >= 10 && id < 100) {
                        return "S-0" + id;
                    } else if (id > 100) {
                        return "S-" + id;
                    }
                    return "S-001";
                } else {
                    return "S-001";
                }
            }

           return "S-001";
        }

        public boolean registerStudent(RegisterStudent student) throws SQLException {

            Connection connection = null;
            try {
                connection = DbConnection.getInstance().getConnection();

                connection.setAutoCommit(false);
                Savepoint savepoint1 = connection.setSavepoint("Savepoint1");

                PreparedStatement stm = connection.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?,?)");
                stm.setObject(1,student.getId());
                stm.setObject(2,student.getName());
                stm.setObject(3,student.getEmail());
                stm.setObject(4,student.getContact());
                stm.setObject(5,student.getAddress());
                stm.setObject(6,student.getNic());

                if(stm.executeUpdate()>0){
                    PreparedStatement stm2 = connection.prepareStatement("INSERT INTO Registration VALUES (?,?,?,?)");
                    stm2.setObject(1,student.getRegisterId());
                    stm2.setObject(2,student.getDate());
                    stm2.setObject(3,student.getId());
                    stm2.setObject(4,student.getIntake());



                    if(stm2.executeUpdate()>0){
                        PreparedStatement stm3 = connection.prepareStatement("INSERT INTO Payment(date,cost,registration_id) VALUES (?,?,?)");
                        stm3.setObject(1,student.getDate());
                        stm3.setObject(2,student.getCost());
                        stm3.setObject(3,student.getRegisterId());

                        if(stm3.executeUpdate()>0){
                           connection.commit();
                           connection.rollback();
                            return true;
                        }else {
                            return false;
                        }
                    };

                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




            return false;
        }





      /*  if (txtStudentId.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fail").showAndWait();
        }else {
            registerStudentBO.registerStudent(studentDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Student Regiter Complete").showAndWait();
        }*/





    public void textField_KeyReleased(KeyEvent keyEvent) {
       /* Object response = ValidationUtil.validate(hashMap,btnRegister);
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if(response instanceof TextField){
                TextField textField= (TextField) response;
                textField.requestFocus();
            }
        }*/

    }
}
