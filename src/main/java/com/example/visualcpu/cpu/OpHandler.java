package com.example.visualcpu.cpu;

public class OpHandler extends ComHandler{
    @Override
    void run(Command comm, CPU cpu) throws  Exception
    {
        switch(comm.getTask())
        {
            case add:
                cpu.add();
                break;
            case sub:
                cpu.sub();
                break;
            case mul:
                cpu.mul();
                break;
            case div:
                cpu.div();
                break;
            default:
                super.run(comm, cpu);
        }
    }
}
