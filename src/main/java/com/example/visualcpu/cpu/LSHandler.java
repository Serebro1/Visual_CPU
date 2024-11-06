package com.example.visualcpu.cpu;

public class LSHandler extends ComHandler {
    @Override
    void run(Command comm, CPU cpu) throws  Exception
    {
        switch(comm.getTask())
        {
            case init:
                cpu.init(comm.getNum1(), comm.getNum2());
                break;
            case st:
                cpu.st(comm.getNum1(), comm.getNum2());
                break;
            case ld:
                cpu.ld(comm.getNum1(), comm.getNum2());
                break;
            case mv:
                cpu.mv(comm.getNum1(), comm.getNum2());
                break;
            case print:
                cpu.print();
                break;
            default:
                super.run(comm, cpu);
        }
    }
}
