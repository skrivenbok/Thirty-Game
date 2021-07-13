package com.example.myapplication.Controller;

import com.example.myapplication.Model.Thirty;
import com.example.myapplication.View.Spel_screen_view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GameController {

    //Variabler
    private Spel_screen_view view;
    public Thirty model;

    //Randomizer
    Random ran = new Random();

    public GameController(Spel_screen_view view) {
        this.view = view;
        this.model = new Thirty();

    }

    //For throwing the dice
    public void throwDice() {
        for (int i = 0; i < model.getDice().size(); i++) {
            int key = i;
            if (model.getDiceCol().get(key) != model.getGreyCol()) {
                int curr = ran.nextInt(6) + 1;
                model.setDiceNum(key, curr);
                view.setImagee(key, model.getWhiteCol(), curr);
            }
        }
    }


    //For calculating the 4-12 val.
    public int getPoints(List<Integer> choosen, int target) {
        int[] arr = new int[choosen.size()];
        int i = 0;
        for (Integer j : choosen) {
            arr[i] = j;
            i++;
        }
        List<List<Integer>> l = combinationSum(arr, target);
        return chooseComb(l, choosen);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    public void backtrack(int[] cand, int start, int target, List<Integer> list, List<List<Integer>> result) {
        if (target < 0)
            return;
        if (target == 0)
            result.add(new ArrayList<>(list));
        for (int i = start; i < cand.length; i++) {
            list.add(cand[i]);
            backtrack(cand, i, target - cand[i], list, result);
            list.remove(list.size() - 1);
        }

    }

    public List<String> toStringList(List<List<Integer>> l) {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < l.get(i).size(); j++) {
                sb.append(String.valueOf(l.get(i).get(j)));
            }
            stringList.add(i, sb.toString());
        }
        return stringList;
    }

    public int chooseComb(List<List<Integer>> l, List<Integer> choosen) {
        List<String> stringList = toStringList(l);
        Comparator<String> stringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        Collections.sort(stringList, stringLengthComparator);
        int counter = 0;
        int total = 0;
        int points = 0;
        List<Integer> comb = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            char[] chars = stringList.get(i).toCharArray();
            for (char ch : chars) {
                int c = Character.getNumericValue(ch);
                total += c;
                if (choosen.contains(c)) {
                    counter += c;
                    choosen.remove(new Integer(c));
                    comb.add(c);
                }
            }
            if (counter == total) {
                points += counter;
                counter = 0;
                total = 0;
                for (Integer in : comb) {
                    res.add(in);
                }
            } else {
                counter = 0;
                total = 0;
                comb.clear();
            }
        }
        return points;
    }


    //For calculating low
    public int calculateLow(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            if (i <= 3) {
                sum += i;
            }
        }
        return sum;
    }

    //When clicking ion Image - contacts setImagee(in view)
    public void imageOnClickEvent(int key) {
        if (model.getTurn() == 3 && model.getDiceCol().get(key) != model.getRedCol()) {
            view.setImagee(key, model.getRedCol(), model.getDiceNum().get(key));
            model.setDiceCol(key, model.getRedCol());
        } else if (model.getTurn() == 3 && model.getDiceCol().get(key) == model.getRedCol()) {
            view.setImagee(key, model.getGreyCol(), model.getDiceNum().get(key));
            model.setDiceCol(key, model.getGreyCol());
        } else if (model.getTurn() != 3 && model.getDiceCol().get(key) == model.getGreyCol()) {
            view.setImagee(key, model.getWhiteCol(), model.getDiceNum().get(key));
            model.setDiceCol(key, model.getWhiteCol());
        } else if (model.getTurn() != 3 && model.getDiceCol().get(key) == model.getWhiteCol()) {
            view.setImagee(key, model.getGreyCol(), model.getDiceNum().get(key));
            model.setDiceCol(key,model.getGreyCol());
        }
    }

    //When clicking on button
    public void buttonOnClickEvent() {
        int turn = model.getTurn();
        int numOfturns = model.getNumOfturns();
        if (turn == 0 ) {
            turn0(turn);
        } else if (turn == 1) {
            turn1(turn);
        } else if (turn == 2) {
            turn2(turn);
        } else if (turn == 3 && numOfturns != 10) {
            turn3();
        } else if (turn == 3) {
            turn3();
            end();
        }
    }

    public void turn0(int key) {
        model.setTurn(key + 1);
        view.setTurn(key + 1);
        throwDice();
    }

    public void turn1(int key) {
        model.setTurn(key + 1);
        view.setTurn(key + 1);
        throwDice();
    }

    public void turn2(int key) {
        model.setTurn(key + 1);
        view.setTurn(key + 1);
        view.setThrowButton("Beräkna Omgång");
        throwDice();
        for (int i = 0; i < model.getDice().size(); i++) {
            if(model.getDiceCol().get(i).equals(model.getGreyCol())){
                model.setDiceCol(i, model.getRedCol());
                view.setImagee(i, model.getRedCol(), model.getDiceNum().get(i));
            }else {
                model.setDiceCol(i, model.getGreyCol());
                view.setImagee(i, model.getGreyCol(), model.getDiceNum().get(i));
            }
        }
    }

    public void turn3() {
        int turn = model.getTurn();
        int numOfturns = model.getNumOfturns();
        int totalPoint = model.getTotalPoint();
        int currPoint;
        ArrayList<Integer> choosen = new ArrayList<>();
        String[] valPoint = model.getValPoints();
        for (int i = 0; i < model.getDice().size(); i++) {
            if (model.getDiceCol().get(i).equals(model.getRedCol())) {
                choosen.add(model.getDiceNum().get(i));
            }
        }
        if (choosen.isEmpty()) {
            view.replyToScreen("Choose Dice Combinations");
        } else if (view.getSpinn() == "Low") {
            currPoint = calculateLow(choosen);
            valPoint[numOfturns-1] = view.getSpinn() + " " + currPoint;
            model.setValPoints(valPoint);
            totalPoint += currPoint;
            model.setTotalPoint(totalPoint);
            view.setTotalPoint(totalPoint);

            ArrayList<String> tillf = model.getFiles();
            tillf.remove(view.getSpinn());
            model.setFiles(tillf);
            view.setFiles(view.getSpinn());

            model.setTurn(0);
            view.setTurn(0);
            model.setNumOfturns(numOfturns + 1);
            view.setThrowButton("Throw");

            for (int i = 0; i < model.getDice().size(); i++) {
                model.setDiceCol(model.getDice().get(i), model.getWhiteCol());
                view.setImagee(model.getDice().get(i), model.getWhiteCol(), model.getDiceNum().get(i));
            }

        } else {
            currPoint = getPoints(choosen, Integer.parseInt(view.getSpinn()));
            valPoint[numOfturns-1] = view.getSpinn() + " " + currPoint;
            model.setValPoints(valPoint);
            totalPoint = model.getTotalPoint();
            totalPoint += currPoint;
            model.setTotalPoint(totalPoint);
            view.setTotalPoint(totalPoint);

            ArrayList<String> tillf = model.getFiles();
            tillf.remove(view.getSpinn());
            model.setFiles(tillf);
            view.setFiles(view.getSpinn());

            model.setTurn(0);
            view.setTurn(0);
            model.setNumOfturns(numOfturns + 1);
            view.setThrowButton("Throw");

            for (int i = 0; i < model.getDice().size(); i++) {
                model.setDiceCol(model.getDice().get(i), model.getWhiteCol());
                view.setImagee(model.getDice().get(i), model.getWhiteCol(), model.getDiceNum().get(i));
            }
        }
    }


    //Changing to end activity
    public void end() {
        view.changeToEndActivity(model.getValPoints(), model.getTotalPoint());
    }
}

