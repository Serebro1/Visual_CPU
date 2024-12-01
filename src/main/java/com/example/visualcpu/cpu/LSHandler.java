package com.example.visualcpu.cpu;

import com.example.visualcpu.visualAndDAO.Command;

public class LSHandler extends ComHandler {
    @Override
    void run(Command comm, CPU cpu) throws  Exception
    {
        switch(comm.getTask())
        {
            case init:
                cpu.init(comm.getArg1(), comm.getArg2());
                break;
            case st:
                cpu.st(comm.getArg1(), comm.getArg2());
                break;
            case ld:
                cpu.ld(comm.getArg1(), comm.getArg2());
                break;
            case mv:
                cpu.mv(comm.getArg1(), comm.getArg2());
                break;
            case print:
                cpu.print();
                break;
            default:
                super.run(comm, cpu);
        }
    }
}
