package com.example.visualcpu.cpu;

public class BCPU {
    public static ICPU build(){
        ComHandler h = new ComHandler();
        h.add(new OpHandler())
          .add(new LSHandler());
        return new CPU(h);
    }
}
