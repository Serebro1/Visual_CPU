package com.example.visualcpu.visual;

import com.example.visualcpu.cpu.BCPU;
import com.example.visualcpu.cpu.Command;
import com.example.visualcpu.cpu.ICPU;
import com.example.visualcpu.cpu.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    ArrayList<Command> comms = new ArrayList<>();
    Command activeCom;
    ArrayList<IObserver> allObserver = new ArrayList<>();
    Iterator<Command> iter;
    ICPU cpu = BCPU.build();

    void eventCall(){
        allObserver.forEach(action->action.event(this));
    }

    public HashMap<Long, List<Task>> mostPopularInstruction(){
        Map<Task, Long> mapTasks =
                comms
                        .stream()
                        .collect(Collectors.groupingBy(Command::getTask, Collectors.counting()));

        System.out.println("Самая популярная инструкция");

        Long max = Collections.max(mapTasks.values());
        HashMap<Long, List<Task>> res = new HashMap<>();
        res.put(Collections.max(mapTasks.values()), mapTasks.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));

        return res;
    }

    public HashMap<Task, Long> SortedInstruction(){
        HashMap<Task, Long> mapTasks =
                (HashMap<Task, Long>) comms
                        .stream()
                        .collect(Collectors.groupingBy(Command::getTask, Collectors.counting()));
        List<Map.Entry<Task, Long> > list =
                new LinkedList<>(mapTasks.entrySet());

        // Sort the list
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        HashMap<Task, Long> res = new LinkedHashMap<>();
        for (Map.Entry<Task, Long> aa : list) {
            res.put(aa.getKey(), aa.getValue());
        }
        return res;
    }

    public HashMap<Integer, Integer> MemoryRange(){
        HashMap<Integer, Integer> mapMem = new HashMap<>();
        for (Command c : comms){
            if (activeCom == null) return mapMem;
            if (c.getTask() == Task.init){
                mapMem.put(c.getNum1(), c.getNum2());
            }
            else if (c.getTask() == Task.st){
                mapMem.put(c.getNum2(), cpu.getReg()[c.getNum1()]);
            }
            if (c == activeCom) return mapMem;
        }
        return mapMem;
    }
    public HashMap<Integer, Integer> RegisterRange(){
        HashMap<Integer, Integer> mapReg = new HashMap<>();
        HashMap<Integer, Integer> mapMem = MemoryRange();
        int[] regs = cpu.getReg();
        for (Command c : comms){
            if (activeCom == null) return mapReg;
            if (c.getTask() == Task.mv){
                mapReg.put(c.getNum1(), regs[c.getNum2()]);
            }
            else if (c.getTask() == Task.ld){
                mapReg.put(c.getNum1(), mapMem.get(c.getNum2()));
            }
            if (c == activeCom) return mapReg;
        }
        return mapReg;
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
        try {
            activeCom = iter.next();
            cpu.run(activeCom);
            eventCall();
        }
        catch (NoSuchElementException e){
            System.out.println("Программа завершена");
            resetProg();
        }
        catch (NullPointerException e) {
            System.out.println("Добавьте команду");
            resetProg();
        }
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            resetProg();
        }
    }
    public void resetProg() {
        activeCom = null;
        iter = comms.iterator();
        eventCall();
    }
    @Override
    public Iterator<Command> iterator() {
        return comms.iterator();
    }

    public void addObserver(IObserver e) {
        allObserver.add(e);
        eventCall();
    }
}
