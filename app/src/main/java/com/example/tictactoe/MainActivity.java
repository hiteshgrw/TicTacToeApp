package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean p1turn = true;
    private int p1points;
    private int p2points;
    private int rcount=0;
    private TextView tvp1;
    private TextView tvp2;
    private Button reset;
    private String pname1;
    private String pname2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b1 = getIntent().getExtras();
        pname1 = b1.getString("pname1");
        pname2 = b1.getString("pname2");
        tvp1 = findViewById(R.id.player1);
        tvp2 = findViewById(R.id.player2);
        tvp1.setText(pname1 + " : 0");
        tvp2.setText(pname2 + " : 0");
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                String bname = "button"+i+j;
                int resid = getResources().getIdentifier(bname,"id",getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1points = 0;
                p2points = 0;
                updatepointtable();
                resetboard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals(""))
            return;
        if(p1turn)
            ((Button) v).setText("X");
        else
            ((Button) v).setText("O");
        rcount++;

        if(checkwin())
        {
            if(p1turn)
                p1win();
            else
                p2win();
        }
        else if(rcount==9)
            draw();
        else
            p1turn=!p1turn;
    }
    public boolean checkwin()
    {
        String field [][] = new String[3][3];
        for (int i = 0; i <3; i++) {
            for (int j = 0; j <3 ; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i <3 ; i++) {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                return true;
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                return true;
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
            return true;
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
            return true;
        return false;
    }
    public void p1win()
    {
        p1points++;
        Toast.makeText(this, "Player 1 won!!", Toast.LENGTH_SHORT).show();
        updatepointtable();
        resetboard();
    }
    public void p2win()
    {
        p2points++;
        Toast.makeText(this, "Player 2 won!!", Toast.LENGTH_SHORT).show();
        updatepointtable();
        resetboard();
    }
    public void draw()
    {
        Toast.makeText(this, "Draw !!!", Toast.LENGTH_SHORT).show();
        resetboard();
    }
    public void updatepointtable()
    {
        tvp1.setText(pname1+" : "+p1points);
        tvp2.setText(pname2+" : "+p2points);
    }
    public void resetboard()
    {
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                buttons[i][j].setText("");
            }
        }
        rcount=0;
        p1turn = true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("rcount",rcount);
        outState.putInt("p1point",p1points);
        outState.putInt("p2point",p2points);
        outState.putBoolean("p1turn",p1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rcount = savedInstanceState.getInt("rcount");
        p1points = savedInstanceState.getInt("p1point");
        p2points = savedInstanceState.getInt("p2point");
        p1turn = savedInstanceState.getBoolean("p1turn");
    }
}
