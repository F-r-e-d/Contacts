package lpcdtl.bdmob.MesContacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Add_Update_Contact extends AppCompatActivity {

    private EditText nameAddTxt;
    private EditText phoneAddTxt;
    private EditText mailAddTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_contact);

        nameAddTxt = findViewById(R.id.addNameTxt);
        phoneAddTxt = findViewById(R.id.addPhoneTxt);
        mailAddTxt = findViewById(R.id.addMailTxt);
        Button addBut = findViewById(R.id.addBut);

        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setName(nameAddTxt.getText().toString());
                contact.setPhoneNumber(phoneAddTxt.getText().toString());
                contact.setMail(mailAddTxt.getText().toString());

                try {
                    new DataBaseHandler(getApplicationContext()).Add_Contact(contact);
                    Toast.makeText(getApplicationContext(), "Contact ajouté",  Toast.LENGTH_LONG).show();
                    nameAddTxt.setText("");
                    phoneAddTxt.setText("");
                    mailAddTxt.setText("");
                }catch (Exception e){

                }
            }
        });


    }

    public void addContact(){
        Contact contact = new Contact();
        contact.setName(nameAddTxt.getText().toString());
        contact.setPhoneNumber(phoneAddTxt.getText().toString());
        contact.setMail(mailAddTxt.getText().toString());

        new DataBaseHandler(getApplicationContext()).Add_Contact(contact);
    }
}
