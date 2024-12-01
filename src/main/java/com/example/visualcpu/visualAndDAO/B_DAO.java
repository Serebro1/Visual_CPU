package com.example.visualcpu.visualAndDAO;

public class B_DAO {
    static DAO_Commands dao = new DAO_H(); //new DAO_H();
    static DAO_Commands getDAO(){
        return dao;
    }
}
