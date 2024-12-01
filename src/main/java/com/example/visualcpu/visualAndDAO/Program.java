package com.example.visualcpu.visualAndDAO;

import com.example.visualcpu.cpu.*;

import java.util.*;

public class Program implements Iterable<Command> {
    DAO_Commands dao = B_DAO.getDAO();
    ArrayList<IObserver> allObserver = new ArrayList<>();
    Iterator<Command> iter = iterator();
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

    }
    public void addCom(Command c){
        dao.addCom(c);
        //comms.add(c);

        resetProg();
    }

    public void removeCom(Command c){
        dao.removeCom(c);
        //comms.remove(c);
        resetProg();
    }

    public void swapComs(Command c1, Command c2){
        dao.swapComs(c1, c2);
//        int tmp = comms.indexOf(c1);
//        comms.set(comms.indexOf(c2), c1);
//        comms.set(tmp, c2);
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
        iter = iterator();
        eventCall();
    }
    @Override
    public Iterator<Command> iterator() {
        return dao.iterator();
        //return comms.iterator();
    }
}
