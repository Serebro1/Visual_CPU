package com.example.visualcpu.visual;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.HashMap;

public class MemController implements IObserver{
    Program prog = BProgram.build();
    private HashMap<Integer, Integer> mem;
    @FXML
    VBox MemoryView;

    @FXML
    void initialize(){
        prog.allObserver.add(this);
        MemoryView.setSpacing(2.0);
        mem = prog.MemoryRange();
        int max = 1024; // как получить максимальное кол-во памяти?
        for (int row = 0; row < (max / 5 + 1); row++){
            HBox element = new HBox();

            element.setSpacing(10.0);
            for (int col = 0; col < 5; col++){
                Label tmp = new Label();
                int address = row * 5 + col;
                tmp.setText(address +" :0");
                HBox.setHgrow(tmp, Priority.ALWAYS);
                element.getChildren().add(tmp);
            }
            MemoryView.getChildren().add(element);
        }
    }

    @Override
    public void event(Program prog) {
        mem = prog.MemoryRange();
        int max = 1024;
        for (int row = 0; row < (max / 5 + 1); row++){
            for (int col = 0; col < 5; col++){
                int address = row * 5 + col;
                if (mem.containsKey(address)){
                    HBox cell = (HBox) MemoryView.getChildren().get(row);
                    Label tmp = (Label) cell.getChildren().get(col);
                    tmp.setText(address+" :"+ mem.get(address));
                    tmp.setStyle("-fx-text-fill: Red");
                }
                else {
                    HBox cell = (HBox) MemoryView.getChildren().get(row);
                    Label tmp = (Label) cell.getChildren().get(col);
                    tmp.setText((address) +" :0");
                    tmp.setStyle("-fx-text-fill: Black");
                }
            }
        }
    }
}
