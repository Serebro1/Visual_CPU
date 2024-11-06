package com.example.visualcpu.cpu;

public class ComHandler {
    ComHandler next;
    void run(Command comm, CPU cpu) throws Exception
    {
        if (next != null){
            next.run(comm, cpu);
        }
        else {
            throw new Exception("не могу выполнить");
        }

    }

    ComHandler add(ComHandler next){
        this.next = next;
        return next;
    }
}
