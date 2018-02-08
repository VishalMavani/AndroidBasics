package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bansi..!! on 04-07-2016.
 */
public class Email extends Activity implements View.OnClickListener{
    EditText emailAddress, subject, message;
    Button sendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initialize();
    }

    private void initialize() {
        emailAddress = (EditText) findViewById(R.id.etEmailAddress);
        subject = (EditText) findViewById(R.id.etSubject);
        message = (EditText) findViewById(R.id.etMessage);
        sendEmail = (Button) findViewById(R.id.bSendEmail);
        sendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String emailAddresses[] = {emailAddress.getText().toString()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
        startActivity(emailIntent);
    }
}
