package com.example.myapplicationbyyashwanth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    String msg = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);             // Getting the message from the first activity
    }

    public void sendingMessage(View view)
    {
        EditText text = findViewById(R.id.phone);

        if(checkPhone(String.valueOf(text.getText()))){                         // This method gets executed when a valid phone number is entered

            Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ text.getText()));
            it.putExtra("sms_body", msg);
            startActivity(Intent.createChooser(it, "sms to"));

            Context context = getApplicationContext();
            CharSequence TOASTMESSAGE = "Sending SMS...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, TOASTMESSAGE , duration);
            toast.show();
        }else if(checkEmail(String.valueOf(text.getText()))){                       // This method gets executed when a valid email id is entered
            Intent intentMail = new Intent(Intent.ACTION_SEND);
            intentMail.setType("message/rfc822");
            intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{String.valueOf(text.getText())}); // "To" part of the mail
            intentMail.putExtra(Intent.EXTRA_TEXT, msg);                                           // Message body
            startActivity(Intent.createChooser(intentMail, "mail to"));

            Context context = getApplicationContext();
            CharSequence TOASTMESSAGE = "Sending email...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, TOASTMESSAGE, duration);
            toast.show();
        }
        else{
            text.setText("");
            text.setHint("Enter a valid phone number or email address.");
        }
    }

    private Boolean checkPhone(String s){
        return s.matches("((0091)|(\\+91)|0?)[6789]{1}\\d{9}");                // Regular expression checks for an indian number (with and without +91)
    }                                                                                //d{9}= any of 0-9 digits 9times

    private Boolean checkEmail(String s){
        return s.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$"); //\w+ is same as [a-zA-Z0-9_]+)
    }
}