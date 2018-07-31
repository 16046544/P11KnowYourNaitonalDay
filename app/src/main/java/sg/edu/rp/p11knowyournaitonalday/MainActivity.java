package sg.edu.rp.p11knowyournaitonalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> alFacts;
    ArrayAdapter aaFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.ListView);
        alFacts = new ArrayList<>();
        alFacts.add("Singapore National Day is on 9 Aug");
        alFacts.add("Singapore is 53 years old");
        alFacts.add("Theme is 'We Are Singapore'");
        aaFacts = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, alFacts);
        listView.setAdapter(aaFacts);

        final LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout login =
                (LinearLayout) inflater.inflate(R.layout.login, null);
        final EditText etCode = (EditText) login
                .findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login");
                builder.setView(login);

                builder.setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Contact 999 to get your code", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String input = etCode.getText().toString();
                        if(input.equalsIgnoreCase("738944")){
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Invalid Code", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.itemQuit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.itemSend) {
            String [] list = new String[] { "Email", "SMS" };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                //email
                                Snackbar sb = Snackbar.make(listView, "Sent an Email", Snackbar.LENGTH_SHORT);
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                                try {
                                    startActivity(Intent.createChooser(i, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }
                                sb.show();
                            } else if (which == 1) {
                                //sms
                                Snackbar sb = Snackbar.make(listView, "Sent a SMS", Snackbar.LENGTH_SHORT);
                                sb.show();
                            }
                        }
                        });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (item.getItemId() == R.id.itemQuiz) {

            final LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout login =
                    (LinearLayout) inflater.inflate(R.layout.quiz, null);
            final RadioGroup rg1 = login.findViewById(R.id.rg1);
            final RadioGroup rg2 = login.findViewById(R.id.rg2);
            final RadioGroup rg3 = login.findViewById(R.id.rg3);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself");
            builder.setView(login);

            builder.setNegativeButton("Don't Know Lah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    int quiz = 0;
                    if(rg1.getCheckedRadioButtonId() == R.id.radioButtonNo1){
                        quiz = quiz+1;
                    }
                    if(rg2.getCheckedRadioButtonId() == R.id.radioButtonYes2){
                        quiz = quiz+1;
                    }
                    if(rg3.getCheckedRadioButtonId() == R.id.radioButtonYes3){
                        quiz = quiz+1;
                    }
                    Toast.makeText(getApplicationContext(), "Total score:" + quiz, Toast.LENGTH_LONG).show();

                }
            });
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

}
