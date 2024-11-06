package com.example.visualcpu.visual;

import com.example.visualcpu.cpu.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class FrequencyController implements IObserver {
    Program prog = BProgram.build();
    @FXML
    VBox FreqView;

    @FXML
    void initialize(){
        prog.allObserver.add(this);

    }

    @Override
    public void event(Program prog) {
        HashMap<Task, Long> stats = prog.SortedInstruction();
        FreqView.getChildren().clear();
        for(Map.Entry<Task, Long> entry : stats.entrySet()){
            HBox freq = new HBox();
            freq.setSpacing(70);
            freq.setAlignment(Pos.TOP_CENTER);
            freq.getChildren().add(new Label(entry.getKey().toString()));
            freq.getChildren().add(new Label(Long.toString(entry.getValue())));

            FreqView.getChildren().add(freq);
        }
    }
}
