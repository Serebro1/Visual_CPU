package com.example.visualcpu.cpu;

import com.example.visualcpu.visual.Program;

public class Executer {
    ICPU cpu;
    public void run(Command commas) throws Exception {
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
