package com.example.visualcpu.cpu;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    public Command activeCom;
    public HashMap<Task, Long> SortedInstruction(ArrayList<Command> comms){
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

    public HashMap<Integer, Integer> MemoryRange(ArrayList<Command> comms, ICPU cpu){
        HashMap<Integer, Integer> mapMem = new HashMap<>();
        for (Command c : comms){
            if (activeCom == null) return mapMem;
            switch (c.getTask()){
                case Task.init:
                    mapMem.put(c.getNum1(), c.getNum2());
                    break;
                case Task.st:
                    mapMem.put(c.getNum2(), cpu.getReg()[c.getNum1()]);
                    break;
            }
            if (c == activeCom) return mapMem;
        }
        return mapMem;
    }
    public HashMap<Integer, Integer> RegisterRange(ArrayList<Command> comms, ICPU cpu){
        HashMap<Integer, Integer> mapReg = new HashMap<>();
        HashMap<Integer, Integer> mapMem = MemoryRange(comms, cpu);
        int[] regs = cpu.getReg();
        for (Command c : comms){
            if (activeCom == null) return mapReg;
            switch (c.getTask()){
                case Task.mv:
                    mapReg.put(c.getNum1(), regs[c.getNum1()]);
                    break;
                case Task.ld:
                    mapReg.put(c.getNum1(), mapMem.get(c.getNum2()));
                    break;
                case Task.add, Task.sub, Task.div, Task.mul:
                    mapReg.put(3, regs[3]);
            }
            if (c == activeCom) return mapReg;
        }
        return mapReg;
    }
}
