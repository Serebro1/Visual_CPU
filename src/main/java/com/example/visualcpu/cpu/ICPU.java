package com.example.visualcpu.cpu;

import com.example.visualcpu.visualAndDAO.Command;

public interface ICPU {
    void run(Command c) throws Exception;
    int[] getReg();
}
