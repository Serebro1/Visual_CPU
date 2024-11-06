package com.example.visualcpu.visual;

import com.example.visualcpu.ProgApplication;
import com.example.visualcpu.cpu.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController implements IObserver {
    Program prog = BProgram.build();
    @FXML
    VBox StaticView;
    @FXML
    VBox CommandView;
    @FXML
    VBox ProgView;
    @FXML
    void initialize()
    {
        prog.addObserver(this);
        FXMLLoader regLoad = new FXMLLoader(
                ProgApplication.class.getResource("reg-view.fxml"));
        FXMLLoader memLoad = new FXMLLoader(
                ProgApplication.class.getResource("mem-view.fxml"));
        FXMLLoader freqLoad = new FXMLLoader(
                ProgApplication.class.getResource("frequency-view.fxml"));

        FXMLLoader progLoad = new FXMLLoader(
                ProgApplication.class.getResource("prog-view.fxml"));

        try {
            Pane paneReg = regLoad.load();
            paneReg.setStyle("-fx-border-color: black");
            StaticView.getChildren().add(paneReg);
            Pane paneMem = memLoad.load();
            paneMem.setStyle("-fx-border-color: black");
            StaticView.getChildren().add(paneMem);
            Pane paneFreq = freqLoad.load();
            paneFreq.setStyle("-fx-border-color: black");
            StaticView.getChildren().add(paneFreq);

            Pane paneProg = progLoad.load();
            paneProg.setStyle("-fx-border-color: black");
            ProgView.getChildren().add(paneProg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void event(Program prog) {
        CommandView.getChildren().clear();
        for (Command com:prog){
            FXMLLoader comLoad = new FXMLLoader(
                    ProgApplication.class.getResource("com-view.fxml"));


            ComController cc = new ComController();
            comLoad.setController(cc);
            try{
                Pane paneCom = comLoad.load();
                paneCom.setStyle("-fx-border-color: black");
                cc.setCommand(com);

                CommandView.getChildren().add(paneCom);
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }



    }
}
