package com.example.visualcpu.cpu;

public interface ICPU {
    void run(Command c) throws Exception;
    int[] getReg();
}
