package com.example.myapplication.Model;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
Todo:ArrayList of integers for the dice
    :ArrayList of Val
 */

public class Thirty {
    //Variables

    //Dices
    private final static int Dice_1 = 0;
    private final static int Dice_2 = 1;
    private final static int Dice_3 = 2;
    private final static int Dice_4 = 3;
    private final static int Dice_5 = 4;
    private final static int Dice_6 = 5;
    private ArrayList<Integer> dice = new ArrayList<>();

    //Val (choice of combination)
    private ArrayList<String> files = new ArrayList<String>();

    //GameScore && Turns
    private int totalPoint = 0;
    private int turn = 0;
    private int numOfturns = 1;
    private String[] valPoints = new String[10];

    //Colors on Dice
    private String grey_Col = "g";
    private String white_Col = "w";
    private String red_Col = "r";

    //Map with Dice to Color, Dice to Number
    private Map<Integer,String>    diceCol =   new HashMap<>();
    private Map<Integer, Integer>  diceNum =   new HashMap<>();

    //Constructor, Instantiate maps and list to set default values
    public Thirty(){
        //Instansiate lists and maps to default values

        files.add("Low");
        files.add("4");
        files.add("5");
        files.add("6");
        files.add("7");
        files.add("8");
        files.add("9");
        files.add("10");
        files.add("11");
        files.add("12");

        dice.add(Dice_1);
        dice.add(Dice_2);
        dice.add(Dice_3);
        dice.add(Dice_4);
        dice.add(Dice_5);
        dice.add(Dice_6);

        for(int i = 0; i < dice.size(); i++){
            //diceImage.put(dice.get(i),img);
            diceCol.put(i,white_Col);
            diceNum.put(i,1);
        }

    }

    //Getters
    public String getWhiteCol(){return white_Col;}
    public String getGreyCol(){return grey_Col;}
    public String getRedCol(){return red_Col;}
    public Map<Integer, String> getDiceCol(){return diceCol;}
    public Map<Integer, Integer> getDiceNum(){return diceNum;}
    public ArrayList<Integer> getDice(){
        return dice;
    }
    public ArrayList<String> getFiles(){
    return files;
    }
    public int getTotalPoint(){
        return totalPoint;
    }
    public int getTurn(){
    return turn;
    }
    public int getNumOfturns(){
        return numOfturns;
    }
    public String[] getValPoints(){
        return valPoints;
    }

    //Setters
    public void setFiles(ArrayList<String> files){this.files = files;}
    public void setTotalPoint(int totalPoint){this.totalPoint = totalPoint;}
    public void setTurn(int turn){this.turn = turn;}
    public void setNumOfturns(int numOfturns){this.numOfturns = numOfturns;}
    public void setValPoints(String [] valPoints){this.valPoints = valPoints;}
    public void setDiceNum(int key, int value){diceNum.put(key,value);}
    public void setDiceCol(int key, String color){diceCol.put(key,color);}
    public void setDice(ArrayList<Integer> dice){this.dice = dice;}

}
