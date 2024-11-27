package com.example.visualcpu;

import com.example.visualcpu.cpu.BCPU;
import com.example.visualcpu.cpu.Command;
import com.example.visualcpu.cpu.Executer;
import com.example.visualcpu.cpu.ICPU;
import com.example.visualcpu.visual.Program;

/**
 * Hello world!
 *
 */
public class App 
{
    static Program prog = new Program();

    public static void main( String[] args ) throws Exception {

        prog.addCom(new Command("init 10 20"));
        prog.addCom(new Command("init" ,"11", "25"));
        prog.addCom(new Command("ld", "a" ,"10"));
        prog.addCom(new Command("ld", "b" ,"11"));
        prog.addCom(new Command("ld", "c" ,"11"));
        prog.addCom(new Command("add"));
        prog.addCom(new Command("mv", "a" ,"d"));
        prog.addCom(new Command("add"));
        prog.addCom(new Command("st", "d", "30"));
        prog.addCom(new Command("print"));
        for(Command c: prog) System.out.println(c);


    }
}
