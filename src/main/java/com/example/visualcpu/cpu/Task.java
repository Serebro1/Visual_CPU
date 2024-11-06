package com.example.visualcpu.cpu;

public enum Task {
    init,   // init <address> <val>
    ld,     // ld <reg> <address>
    st,     // st <reg> <address>
    mv,     //move <reg1> <reg2>
    print,  // a b c d
    add,    // d = a + b
    sub,    // d = a - b
    mul,    // d = a * b
    div;    // d = a / b
}
