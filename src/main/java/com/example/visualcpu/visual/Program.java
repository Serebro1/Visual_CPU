package com.example.visualcpu.visual;

import com.example.visualcpu.cpu.*;

import java.util.*;

public class Program implements Iterable<Command> {
    ArrayList<Command> comms = new ArrayList<>();
    ArrayList<IObserver> allObserver = new ArrayList<>();
    Iterator<Command> iter;
    ICPU cpu = BCPU.build();
    Command activeCom;
    Statistics stats = new Statistics();
    Executer exec = new Executer(cpu);

    void eventCall(){
        allObserver.forEach(action->action.event(this));
    }
    public void addObserver(IObserver e) {
        allObserver.add(e);
        eventCall();
    }

    public Program(){
//        addCom(new Command("init 10 20"));
//        addCom(new Command("init" ,"11", "25"));
//        addCom(new Command("ld", "a" ,"10"));
//        addCom(new Command("ld", "b" ,"11"));
//        addCom(new Command("ld", "c" ,"11"));
//        addCom(new Command("add"));
//        addCom(new Command("mv", "a" ,"d"));
//        addCom(new Command("add"));
//        addCom(new Command("st", "d", "30"));
//        addCom(new Command("print"));
    }
    public void addCom(Command c){
        comms.add(c);
        resetProg();
    }

    public void removeCom(Command c){
        comms.remove(c);
        resetProg();
    }

    public void swapComs(Command c1, Command c2){
        int tmp = comms.indexOf(c1);
        comms.set(comms.indexOf(c2), c1);
        comms.set(tmp, c2);
        resetProg();
    }

    public void runCom() throws Exception {
        activeCom = iter.next();
        stats.activeCom = activeCom;
        exec.run(activeCom);
        eventCall();
    }
    public void resetProg() { // решить проблему с обманом завершения программы и отсутствием программы
        activeCom = null;
        stats.activeCom = null;
        iter = comms.iterator();
        eventCall();
    }
    @Override
    public Iterator<Command> iterator() {
        return comms.iterator();
    }
}
