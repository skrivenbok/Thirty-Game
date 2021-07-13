package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Controller.GameController;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
/*
Ni får utforma gränssnittet i er app som ni vill.
Tänk dock på att göra det så användarvänligt som möjligt.
 Testa gärna på en riktig mobil om ni har möjlighet så att ni ser att det fungerar i praktiken också, samt på olika skärmstorlekar i emulatorn.
 En annan sak som bör testas är att tillstånd sparas korrekt så att appen inte startas om
(eller något annat oväntat inträffar) vid rotation av telefonen eller då aktiviteten behöver startas om då den varit i bakgrunden.
 Appen ska bestå av minst två olika skärmvyer (Activities/Fragments): en som hanterar själva spelvyn och en för att visa resultatet vid spelets slut.
  Delresultat per val (obs vilket val som gjorts för respektive poäng ska framgå tydligt) och en totalsumma ska visas i resultatvyn.
 */
/*
//VYN
 TODO:
     : Throw dice connection
 */


public class Spel_screen_view extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Variables

    //Throw Button
    Button mThrowBtn;

    //DiceImage (White 1 from start)
    ImageView img;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    List <ImageView> imgList = new ArrayList();

    //Spinner for choosing our Val
    Spinner spinn;
    String Spinnerval;
    ArrayAdapter adapter;
    ArrayList<String> files = new ArrayList<String>();

    //TextViews
    TextView turn;
    TextView points;

    //controller
    GameController controller = new GameController(this);


    //On Create Method when launching
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spel_screen);

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

        //Spinner
        spinn = findViewById(R.id.val_spinner);
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, files);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinn.setAdapter(adapter);
        spinn.setOnItemSelectedListener(this);

        //TextViews
        turn = findViewById(R.id.turn_text);
        turn.setText ("0");
        turn.setTextSize(40);
        points = findViewById(R.id.points_text);
        points.setText("0");
        points.setTextSize(40);


        //Dice images
        img = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);

        imgList.add(img);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);
        imgList.add(img5);
        imgList.add(img6);

        //Dice onClick calling onClickEvent in controller
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            controller.imageOnClickEvent(imgList.indexOf(img));
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {
                controller.imageOnClickEvent(imgList.indexOf(img2));
            }


        });
        img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller.imageOnClickEvent(imgList.indexOf(img3));
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller.imageOnClickEvent(imgList.indexOf(img4));
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller.imageOnClickEvent(imgList.indexOf(img5));
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller.imageOnClickEvent(imgList.indexOf(img6));
            }
        });

        //Button
        mThrowBtn = (Button) findViewById(R.id.throw_button);
        mThrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.buttonOnClickEvent();
            }
        });
    }

    //Toast reply
    public void replyToScreen(String j){

    }

    //Change to end activity
    public void changeToEndActivity(String [] valPoints, int total){
        Intent i = new Intent(Spel_screen_view.this, End_screen_view.class);
        i.putExtra("valpoints",valPoints);
        i.putExtra("totalpoints",total);
        startActivity(i);
        finish();
    }

    //Getters
    public String getSpinn(){
    return Spinnerval;
    }

    //Setters
    public void setTotalPoint(int point){
        points.setText(String.valueOf(point));
    }
    public void setTurn(int currTurn){
        turn.setText(String.valueOf(currTurn));
    }
    public void setImagee(int key, String color, int num){
        ImageView v = imgList.get(key);
        if(color == "g") {
            switch (num) {
                case 1:
                    v.setImageResource(R.drawable.grey1);
                    break;
                case 2:
                    v.setImageResource(R.drawable.grey2);
                    break;
                case 3:
                    v.setImageResource(R.drawable.grey3);
                    break;
                case 4:
                    v.setImageResource(R.drawable.grey4);
                    break;
                case 5:
                    v.setImageResource(R.drawable.grey5);
                    break;
                case 6:
                    v.setImageResource(R.drawable.grey6);
                    break;
            }
        }
        else if (color == "w"){
            switch (num) {
                case 1:
                    v.setImageResource(R.drawable.white1);
                    break;
                case 2:
                    v.setImageResource(R.drawable.white2);
                    break;
                case 3:
                    v.setImageResource(R.drawable.white3);
                    break;
                case 4:
                    v.setImageResource(R.drawable.white4);
                    break;
                case 5:
                    v.setImageResource(R.drawable.white5);
                    break;
                case 6:
                    v.setImageResource(R.drawable.white6);
                    break;
            }
        }
        else{
            switch (num) {
                case 1:
                    v.setImageResource(R.drawable.red1);
                    break;
                case 2:
                    v.setImageResource(R.drawable.red2);
                    break;
                case 3:
                    v.setImageResource(R.drawable.red3);
                    break;
                case 4:
                    v.setImageResource(R.drawable.red4);
                    break;
                case 5:
                    v.setImageResource(R.drawable.red5);
                    break;
                case 6:
                    v.setImageResource(R.drawable.red6);
                    break;
            }

        }
    }
    public void setThrowButton(String name){
        mThrowBtn.setText(name);
    }
    public void setFiles(String file){
        files.remove(file);
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, files);
        spinn.setAdapter(adapter);
    }


    //Saving state when rotating
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        CharSequence cS = turn.getText();
        savedInstanceState.putString("Turn", cS.toString());
        CharSequence pS = points.getText();
        savedInstanceState.putString("Points", pS.toString());

        savedInstanceState.putString("Button", (String) mThrowBtn.getText());

        savedInstanceState.putStringArrayList("FileList", files);

        savedInstanceState.putStringArrayList("ModelFiles", controller.model.getFiles());
        savedInstanceState.putInt("ModelTotalPoint", controller.model.getTotalPoint());
        savedInstanceState.putInt("ModelTurn",controller.model.getTurn());
        savedInstanceState.putInt("ModelNumOfTurns",controller.model.getNumOfturns());
        savedInstanceState.putStringArray("ModelValPoints",controller.model.getValPoints());
        savedInstanceState.putIntegerArrayList("ModelDice",controller.model.getDice());
        ArrayList<Integer> num = new ArrayList<>();
        ArrayList<String> col = new ArrayList<>();
        for(Integer index : controller.model.getDice()){
            num.add(controller.model.getDiceNum().get(index));
            col.add(controller.model.getDiceCol().get(index));
        }
        savedInstanceState.putIntegerArrayList("ModelDiceNum",num);
        savedInstanceState.putStringArrayList("ModelDiceCol",col);


    }

    //Restoring state after rotation
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //TextViews (Turn, Points)
        String cS = savedInstanceState.getString("Turn");
        turn.setText(cS);
        String pS = savedInstanceState.getString("Points");
        points.setText(pS);

        //Button
        String button = savedInstanceState.getString("Button");
        mThrowBtn.setText(button);

        //ArrayList of val(Files) and Spinner Adapter
        files = savedInstanceState.getStringArrayList("FileList");
        spinn = findViewById(R.id.val_spinner);
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, files);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spinn.setAdapter(adapter);
        spinn.setOnItemSelectedListener(this);


        int numOfturns = savedInstanceState.getInt("ModelNumOfTurns");
        int totalPoint = savedInstanceState.getInt("ModelTotalPoint");
        int modelTurn = savedInstanceState.getInt("ModelTurn");
        String [] valPoints = savedInstanceState.getStringArray("ModelValPoints");
        ArrayList<String> modelFiles = savedInstanceState.getStringArrayList("ModelFiles");
        ArrayList<Integer> modelDice = savedInstanceState.getIntegerArrayList("ModelDice");
        ArrayList<String> modelDiceCol = savedInstanceState.getStringArrayList("ModelDiceCol");
        ArrayList<Integer> modelDiceNum = savedInstanceState.getIntegerArrayList("ModelDiceNum");
        controller.model.setValPoints(valPoints);
        controller.model.setNumOfturns(numOfturns);
        controller.model.setTotalPoint(totalPoint);
        controller.model.setTurn(modelTurn);
        controller.model.setFiles(modelFiles);
        controller.model.setDice(modelDice);
        for(int i = 0; i < modelDice.size(); i++){
            controller.model.setDiceCol(i,modelDiceCol.get(i));
            controller.model.setDiceNum(i,modelDiceNum.get(i));
            setImagee(i,modelDiceCol.get(i),modelDiceNum.get(i));
        }

        }

    //Spinner methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Spinnerval = text;
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}