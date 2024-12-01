package com.example.visualcpu.visualAndDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCommandController {
    Program prog = BProgram.build();
    @FXML
    private ChoiceBox<String> CBcomm;

    @FXML
    private TextField Targ1;

    @FXML
    private TextField Targ2;

    @FXML
    void initialize(){

        CBcomm.getItems().addAll("init", "ld", "mv", "st", "add", "sub", "div", "mul", "print");
        CBcomm.setOnAction(_ -> {
            switch (CBcomm.getValue()){
                case "add", "sub", "div", "mul", "print":
                    Targ1.setDisable(true);
                    Targ2.setDisable(true);
                    Targ1.setText("");
                    Targ2.setText("");
                    break;
                default:
                    Targ1.setDisable(false);
                    Targ2.setDisable(false);
                    break;
            }
        });
    }

    @FXML
    void btnAddCommandClicked(ActionEvent event) {
        System.out.println("btnAddPersonClicked");
        Command com = new Command(CBcomm.getValue() + " " + (Targ1.getText() == null ? " " : Targ1.getText()) + " " + (Targ2.getText() == null ? " " : Targ2.getText()));
        prog.addCom(com);
        closeStage(event);


    }
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
