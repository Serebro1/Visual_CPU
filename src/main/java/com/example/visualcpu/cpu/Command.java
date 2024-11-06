package com.example.visualcpu.cpu;

import java.util.Objects;

public class Command {
    Task t;
    int num1, num2;
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

    public Task getTask(){return t;}
    public int getNum1(){return num1;}
    public int getNum2(){return num2;}
    public Command(String action)
    {
        String[] str = action.split(" ");
        if (str.length > 1)
        {
            this.num1 = ArgParsing(str[1]);
            this.num2 = ArgParsing(str[2]);
        }
        this.t = ComParsing(str[0]);
    }

    public Command(String action, String arg1, String arg2){
        this.t = ComParsing(action);
        this.num1 = ArgParsing(arg1);
        this.num2 = ArgParsing(arg2);
    }

    @Override
    public String toString() {
        return "Command{" +
                "t=" + t +
                ", addr1=" + num1 +
                ", addr2=" + num2 +
                '}';
    }
}
