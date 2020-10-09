package com.example.cosmicsaki.userInfo.userStorage;

import com.example.cosmicsaki.userInfo.Users;

import java.util.ArrayList;

public class UserStorage {

    public static ArrayList<Users> list = new ArrayList<>();

    public static ArrayList<Users> getList() {
        return list;
    }
}
