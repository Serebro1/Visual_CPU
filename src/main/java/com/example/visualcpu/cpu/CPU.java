package com.example.visualcpu.cpu;

public class CPU implements ICPU{

    private int[] mems = new int[1024];
    private int[] regs = new int[4];

    ComHandler h;
    public CPU(ComHandler h){this.h = h;}
    @Override
    public void run(Command comm) throws Exception {
        h.run(comm, this);
    }

    @Override
    public int[] getReg() {
        return regs;
    }

    //Operations
    void add()
    {
        regs[3] = regs[0] + regs[1];
        System.out.println("Add " + regs[0] + " with " + regs[1] + " was success.");
    }
    void sub()
    {
        regs[3] = regs[0] - regs[1];
    }
    void mul()
    {
        regs[3] = regs[0] * regs[1];
    }
    void div() throws div_zero
    {
        if (regs[1] == 0) throw new div_zero();
        regs[3] = regs[0] / regs[1];
    }

    void print()
    {
        System.out.print("Registers:");
        for (int reg : regs) {
            System.out.print(" " + reg);

        }
        System.out.println("\n");
    }
    void init(int add, int val)throws Exception
    {
        if (add < 0 || add > 1023)
            throw new Exception("Task 'init' get wrong address.");
        mems[add] = val;
    }
    void mv(int reg1, int reg2) throws Exception
    {
        if (reg1 < 0 || reg1 > 3 || reg2 < 0 || reg2 > 3)
            throw new Exception("Task 'mv' get wrong registers.");
        regs[reg1] = regs[reg2];
    }
    void st(int reg, int add) throws Exception
    {
        if (reg < 0 || reg > 3 || add < 0 || add > 1023)
            throw new Exception("Task 'st' get wrong register or address.");
        mems[add] = regs[reg];
    }
    void ld(int reg, int add)throws Exception
    {
        if (reg < 0 || reg > 3 || add < 0 || add > 1023)
            throw new Exception("Task 'ld' get wrong register or address.");
        regs[reg] = mems[add];
    }
}
