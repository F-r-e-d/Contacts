package lpcdtl.bdmob.MesContacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Main_Screen extends AppCompatActivity {

    private Contact_Adapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        contactAdapter = new Contact_Adapter(this, new DataBaseHandler(this).get_Contacts());

        ListView listView = findViewById(R.id.listViewContact);
        listView.setAdapter(contactAdapter);

        Button add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_view = new Intent(Main_Screen.this, Add_Update_Contact.class);
                startActivity(add_view);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.add){
            Intent add_view = new Intent(Main_Screen.this, Add_Update_Contact.class);
            startActivity(add_view);
            return true;
        }
        if (item.getItemId()== R.id.exit) {
            /*doExit();*/
            return true;
        }
        return true;
    }



    public void set_refresh_data(){
        contactAdapter.clear();
        contactAdapter.addAll(new DataBaseHandler(Main_Screen.this).get_Contacts());
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        set_refresh_data();
    }
}
