package com.example.visualcpu.visual;

import com.example.visualcpu.cpu.Command;
import com.example.visualcpu.cpu.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComController{
    Program prog = BProgram.build();
    private Command command;
    private int comInd;

    @FXML
    Label Com;
    @FXML
    Label Arg1;
    @FXML
    Label Arg2;

    @FXML
    public void remove(){
        prog.removeCom(command);
    }
    @FXML
    public void swapUp(){
        if (comInd == 0) return;
        prog.swapComs(prog.comms.get(comInd - 1), command);
    }
    @FXML
    public void swapDown(){
        if (comInd + 1 == prog.comms.size()) return;
        prog.swapComs(command, prog.comms.get(comInd + 1));
    }
    public void setCommand(Command c){
        this.command = c;
        this.comInd = prog.comms.indexOf(c);
        if (c == prog.activeCom) {
            Com.setStyle("-fx-text-fill: Red");
            Arg1.setStyle("-fx-text-fill: Red");
            Arg2.setStyle("-fx-text-fill: Red");
        }
        if (c.getTask() == Task.mv){
            Arg1.setText(String.valueOf((char)(c.getNum1()+'a')));
            Arg2.setText(String.valueOf((char)(c.getNum2()+'a')));
        }
        else if (c.getTask() == Task.ld || c.getTask() == Task.st){
            Arg1.setText(String.valueOf((char)(c.getNum1()+'a')));
            Arg2.setText(Integer.toString(c.getNum2()));
        }
        else if (c.getTask() == Task.init){
            Arg1.setText(Integer.toString(c.getNum1()));
            Arg2.setText(Integer.toString(c.getNum2()));
        }
        Com.setText(c.getTask().toString());

    }
}
