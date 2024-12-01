package com.example.visualcpu.visualAndDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class RegController implements IObserver{
    Program prog = BProgram.build();
    @FXML
    GridPane RegView;
    @FXML
    void initialize()
    {
        prog.allObserver.add(this);
    }
    @Override
    public void event(Program prog) {
        HashMap<Integer, Integer> regs = prog.stats.RegisterRange(prog.dao.comms, prog.cpu);

        for (int i = 0; i < 4; i++)
        {
            Label reg = (Label) RegView.getChildren().get(4 + i);
            if (regs.containsKey(i))
                if (regs.get(i) != null)
                    reg.setText(String.valueOf(regs.get(i)));
            else {reg.setText("*");}
        }
    }
}
