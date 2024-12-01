package com.example.visualcpu.visualAndDAO;

import java.util.ArrayList;
import java.util.Iterator;

public class DAO_Commands implements Iterable<Command> {
    protected
    ArrayList<Command> comms = new ArrayList<>();

    public void addCom(Command c){
        comms.add(c);
    }

    public void removeCom(Command c) {
        comms.remove(c);
    }

    public void swapComs(Command c1, Command c2){
        int tmp = comms.indexOf(c1);
        comms.set(comms.indexOf(c2), c1);
        comms.set(tmp, c2);
    }
    @Override
    public Iterator<Command> iterator() {
        return comms.iterator();
    }

}