package com.react.mobilep2p;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Sven on 03.03.2015.
 */
public enum Console {

    INSTANCE;

    private ScrollView mScroller;
    private TextView mConsole;
    private EditText mInput;
    private Button mSend;

    private ConsoleListener listener;

    public void init(ScrollView scroller, TextView console, EditText input, Button send) {
        this.mScroller = scroller;
        this.mConsole = console;
        this.mInput = input;
        this.mSend = send;
    }

    public void writeLine(String line) {
        mConsole.append("\n" + line);
        scrollToBottom();
    }

    public void readLine(ConsoleListener listener) {
        this.listener = listener;

        mSend.setEnabled(true);
        mInput.setEnabled(true);
    }

    public void notifySend(String text) {
        mSend.setEnabled(false);
        mInput.setEnabled(false);

        if (listener != null) {
            listener.onResponse(text);
        }
    }

    private void scrollToBottom() {
        mScroller.post(new Runnable() {
            public void run() {
                mScroller.smoothScrollTo(0, mConsole.getBottom());
            }
        });
    }

}
