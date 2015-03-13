package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabio.cardboardpb.DB.DBConnect;
import com.example.fabio.cardboardpb.DB.PostCall;
import com.example.fabio.cardboardpb.Manager.Enum.TypeCall;
import com.example.fabio.cardboardpb.Manager.PasswdManager;
import com.example.fabio.cardboardpb.R;


public class LogInActivity extends Activity {

    private boolean logInOrRegistration = true; //false: registration; true: log in;
    private EditText email;
    private EditText password;
    private Button logIn;
    private TextView signUp;
    private Button play;
    private Button forgot;
    private String passwordToSend;
    private CheckBox keepLog;
    private boolean isChecked;
    private String memMail;
    private PostCall post;
    private Activity logInActivity;
    private String result;

    private DBConnect DBConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInActivity = this;
        DBConnect = new DBConnect();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        logIn = (Button) findViewById(R.id.logInButton);
        signUp = (TextView) findViewById(R.id.textViewSignUp);
        keepLog = (CheckBox) findViewById(R.id.checkBox);
        isChecked = false;
        passwordToSend = PasswdManager.calculateHash(password.toString());
        play = (Button) findViewById(R.id.playWithoutReg);
        forgot = (Button) findViewById(R.id.forgotPassword);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // passwordToSend = PasswdManager.calculateHash(password.getText().toString());
                post = new PostCall(email.getText().toString(), password.getText().toString());
                post.myPostCall(TypeCall.LOG_IN, logInActivity);
                //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();

              /* if(result.equals("connection")){
                    Intent i = new Intent(LogInActivity.this, SplashActivity.class);
                    startActivity(i);
                }*/
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSignUp("", "", "");
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningNoRegistration();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordAlert();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void alertSignUp(String firstname, String lastname, String email) {


        final EditText firstName = new EditText(this);
        final EditText lastName = new EditText(this);
        final EditText eMail = new EditText(this);
        final EditText answare = new EditText(this);
        final EditText password = new EditText(this);
        final EditText confirmPassword = new EditText(this);
        final Spinner spinner = new Spinner(this);
        LinearLayout layout = new LinearLayout(this);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        ArrayAdapter<CharSequence> adapter_gg = ArrayAdapter.createFromResource(this, R.array.security_question, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter_gg);
        alert.setMessage("SIGN UP");

        if (firstname.equals("") && lastname.equals("") && email.equals("")) {
            firstName.setHint("first name");
            lastName.setHint("last name");
            eMail.setHint("email");
        }
        if (firstname.equals("") && !lastname.equals("") && !email.equals("")) {
            firstName.setHint("first name");
            lastName.setText(lastname);
            eMail.setText(email);
        }
        if (firstname.equals("") && lastname.equals("") && !email.equals("")) {
            firstName.setHint("first name");
            lastName.setHint("last name");
            eMail.setText(email);
        }
        if (!firstname.equals("") && lastname.equals("") && !email.equals("")) {
            firstName.setText(firstname);
            lastName.setHint("last name");
            eMail.setText(email);
        }
        if (!firstname.equals("") && !lastname.equals("") && email.equals("")) {
            firstName.setText(firstname);
            lastName.setText(lastname);
            eMail.setHint("email");
        }
        if (!firstname.equals("") && lastname.equals("") && email.equals("")) {
            firstName.setText(firstname);
            lastName.setHint("last name");
            eMail.setHint("email");
        }

        if (firstname.equals("") && !lastname.equals("") && email.equals("")) {
            firstName.setHint("firstname");
            lastName.setText(lastname);
            eMail.setHint("email");
        } else {
            firstName.setText(firstname);
            lastName.setText(lastname);
            eMail.setText(email);
        }
        answare.setHint("your answare");
        password.setHint("password");
        confirmPassword.setHint("confirm password");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(firstName);
        layout.addView(lastName);
        layout.addView(eMail);
        layout.addView(password);
        layout.addView(confirmPassword);
      //  layout.addView(answare);
       // layout.addView(spinner);
        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String backUpFirstName = firstName.getText().toString();
                String backUpLastName = lastName.getText().toString();
                String backUpEmail = eMail.getText().toString();


                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    //ALERT MESSAGE
                    Toast.makeText(getBaseContext(), "Please insert the same password", Toast.LENGTH_LONG).show();
                    alertSignUp(backUpFirstName, backUpLastName, backUpEmail);


                } else {

                    Toast.makeText(getBaseContext(), "Please wait", Toast.LENGTH_LONG).show();
                    //Cript the password
                    passwordToSend = PasswdManager.calculateHash(password.getText().toString());
                    //send data
                    post=new PostCall(firstName.getText().toString(),lastName.getText().toString(),eMail.getText().toString(),passwordToSend);
                    post.myPostCall(TypeCall.SIGN_UP,logInActivity);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        alert.show();

    }


    private void warningNoRegistration() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning");
        alert.setMessage("you can play without registration, but we can't trace your improvement");
        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(LogInActivity.this, SplashActivity.class);
                startActivity(i);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }

    private void forgotPasswordAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("resert your password: ");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText mailTo = new EditText(this);
        mailTo.setHint("insert email");
        layout.addView(mailTo);
        alert.setView(layout);
        alert.setTitle("Warning");
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }


}
