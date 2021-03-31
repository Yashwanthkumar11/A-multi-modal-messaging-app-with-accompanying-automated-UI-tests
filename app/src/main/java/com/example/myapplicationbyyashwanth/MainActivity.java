package com.example.myapplicationbyyashwanth;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "THIS_IS_THE_KEY";
    public static String MESSAGE = "";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.message);                      // Get EditText component.


        editText.addTextChangedListener(new TextWatcher()                     // TextWatcher watches the text in the editText and changes the button state accordingly.
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {                 // This method is invoked after user inputs text in EditText.
                EnablingButton();
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MESSAGE = String.valueOf(editText.getText());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        editText.setText(MESSAGE);
    }


    public void sendMessage(View view)
    {
        String message = editText.getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    /******************************************************************************
     Speech to Text
     ******************************************************************************/

    private static final int SPEECH_REQUEST_CODE = 10;

    public void displaySpeechRecognizer(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            MESSAGE = results.get(0);
        }
    }

    /******************************************************************************
     ******************************************************************************/



    private void EnablingButton()                                // This method enables SEND button if and only if user enters some message
    {
        Button button = (Button)findViewById(R.id.sendButton);
        String inputText = editText.getText().toString();
        if(inputText.length() > 0)
        {
            button.setEnabled(true);
        }else
        {
            button.setEnabled(false);
        }
    }


}
