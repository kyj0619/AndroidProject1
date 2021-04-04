package com.example.androidproject1;

public class MonthItem {

    private int dayValue;

    MonthItem(int dayValue)
    {
        this.dayValue = dayValue;
    }

    public int getDay()
    {
        return dayValue;    //날짜 값을 받아서 반환하는 클래스
    }

}