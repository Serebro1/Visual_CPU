package com.example.visualcpu.cpu;

public class Executer {
    ICPU cpu;
    public void run(Command com) throws Exception {
        try {
                cpu.run(com);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public Executer(ICPU cpu){
        this.cpu = cpu;
    }
}
