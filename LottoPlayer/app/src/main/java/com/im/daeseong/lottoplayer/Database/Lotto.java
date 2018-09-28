package com.im.daeseong.lottoplayer.Database;

public class Lotto {

    private int _rIndex;
    private String Date;
    private int Part1;
    private int Part2;
    private int Part3;
    private int Part4;
    private int Part5;
    private int Part6;
    private int Bonus;

    public Lotto(){
    }

    public Lotto(int rIndex, String Date, int Part1, int Part2, int Part3, int Part4, int Part5, int Part6, int Bonus){
        this._rIndex = rIndex;
        this.Date = Date;
        this.Part1 = Part1;
        this.Part2 = Part2;
        this.Part3 = Part3;
        this.Part4 = Part4;
        this.Part5 = Part5;
        this.Part6 = Part6;
        this.Bonus = Bonus;
    }

    public void setrIndex(int rIndex){
        this._rIndex = rIndex;
    }

    public void setDate(String Date){
        this.Date = Date;
    }

    public void setPart1(int Part1){
        this.Part1 = Part1;
    }

    public void setPart2(int Part2){
        this.Part2 = Part2;
    }

    public void setPart3(int Part3){
        this.Part3 = Part3;
    }

    public void setPart4(int Part4){
        this.Part4 = Part4;
    }

    public void setPart5(int Part5){
        this.Part5 = Part5;
    }

    public void setPart6(int Part6){
        this.Part6 = Part6;
    }

    public void setBonus(int Bonus){
        this.Bonus = Bonus;
    }

    public int getrIndex(){
        return this._rIndex;
    }

    public String getDate(){
        return this.Date;
    }

    public int getPart1(){
        return this.Part1;
    }

    public int getPart2(){
        return this.Part2;
    }

    public int getPart3(){
        return this.Part3;
    }

    public int getPart4(){
        return this.Part4;
    }

    public int getPart5(){
        return this.Part5;
    }

    public int getPart6(){
        return this.Part6;
    }

    public int getBonus(){
        return this.Bonus;
    }

}


