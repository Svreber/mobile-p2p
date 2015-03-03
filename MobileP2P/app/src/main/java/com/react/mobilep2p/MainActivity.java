package com.react.mobilep2p;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private ScrollView mScrollView;
    private TextView mConsole;
    private EditText mInput;
    private Button mButton;

    private Console console = Console.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScrollView = (ScrollView) findViewById(R.id.scroller);
        mConsole = (TextView) findViewById(R.id.console);
        mInput = (EditText) findViewById(R.id.input);
        mButton = (Button) findViewById(R.id.send);

        console.init(mScrollView, mConsole, mInput, mButton);
    }

    public void send(View v) {
        String newLine = mInput.getText().toString();
        mInput.getText().clear();
        console.writeLine(newLine);
        console.notifySend(newLine);
    }

    public void read(View v) {
        console.readLine(new ConsoleListener() {
            @Override
            public void onResponse(String data) {
                System.out.println(data);
            }
        });
    }

}
