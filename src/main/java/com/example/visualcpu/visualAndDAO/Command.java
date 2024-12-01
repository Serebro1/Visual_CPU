package com.example.visualcpu.visualAndDAO;

import com.example.visualcpu.cpu.Task;

import javax.persistence.*;

@Entity
@Table(name = "Commands")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID = -1;

    @Column
    Task task = null;
    @Column
    int arg1 = -1;
    @Column
    int arg2 = -1;

    public Command(){
    }
    public Command(Task task, int arg1, int arg2) {
        this.task = task;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    public Command(Task task, int arg1, int arg2, int id) {
        this.task = task;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.ID = id;
    }

//    public Command(int i, int num1, int num2) {
//        this.t = Task.values()[i];
//        this.num1 = num1;
//        this.num2 = num2;
//    }

    private int ArgParsing(String arg) {
        try {
            return Integer.parseInt(arg); // It's address or value
        } catch (NumberFormatException e) {

            char firstChar = arg.charAt(0);
            return firstChar - 'a';

        }
    }
    private Task ComParsing(String action)
    {
        try {
            return Task.valueOf(action);
        }
        catch (IllegalArgumentException e){
            System.out.println("There is no command '" + action + "' in CPU.");
            return null; // ?
        }
    }

    public Task getTask(){return task;}
    public int getArg1(){return arg1;}
    public int getArg2(){return arg2;}


    public Command(String action)
    {
        String[] str = action.split(" ");
        if (str.length > 1)
        {
            this.arg1 = ArgParsing(str[1]);
            this.arg2 = ArgParsing(str[2]);
        }
        this.task = ComParsing(str[0]);
    }

    public Command(String action, String arg1, String arg2){
        this.task = ComParsing(action);
        this.arg1 = ArgParsing(arg1);
        this.arg2 = ArgParsing(arg2);
    }

    @Override
    public String toString() {
        return "Command{" +
                "t=" + task +
                ", addr1=" + arg1 +
                ", addr2=" + arg2 +
                '}';
    }
}
