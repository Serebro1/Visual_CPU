package com.example.visualcpu.cpu;

import com.example.visualcpu.visualAndDAO.Command;

public class Executer {
    ICPU cpu;
    public void run(Command commas) {
        try {
            cpu.run(commas);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public Executer(ICPU cpu){
        this.cpu = cpu;
    }
}
