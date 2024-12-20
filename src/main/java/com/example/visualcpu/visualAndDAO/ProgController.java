package com.example.visualcpu.visualAndDAO;

import com.example.visualcpu.ProgApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ProgController{
    Program prog = BProgram.build();
    @FXML
    Button addBut;

    @FXML
    void onOpenDialog(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ProgApplication.class.getResource("addCommand-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 200, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    @FXML
    void resetProgram(){
        addBut.setDisable(false);
        prog.resetProg();
    }

    @FXML
    void runCom() throws Exception {
        addBut.setDisable(true);
        try {
            prog.runCom();
        }
        catch (NoSuchElementException e){

            System.out.println("Программа завершена");
            resetProgram();
        }
        catch (NullPointerException e) {
            System.out.println("Добавьте команду");
        }
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            resetProgram();
        }
    }
}
