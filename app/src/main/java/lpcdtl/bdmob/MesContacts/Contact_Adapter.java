package lpcdtl.bdmob.MesContacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import lpcdtl.bdmob.MesContacts.R;

import java.util.List;

public class Contact_Adapter extends ArrayAdapter<Contact> {

    public Contact_Adapter(Context context, List<Contact> allContact) {
        super(context, R.layout.listview_row, allContact);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_row, parent, false);

        TextView nameTxt = rowView.findViewById(R.id.userName_txt);
        TextView phoneNbTxt = rowView.findViewById(R.id.phone_number_txt);
        TextView mailTxt = rowView.findViewById(R.id.mail_txt);

        final Contact contact = getItem(position);
        nameTxt.setText(contact.getName());
        phoneNbTxt.setText(contact.getPhoneNumber());
        mailTxt.setText(contact.getMail());

        ImageButton deleteBut = rowView.findViewById(R.id.deleteButtton);

        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataBaseHandler(getContext()).deleteSingleContact(contact.getId());
                clear();
                addAll(new DataBaseHandler(getContext()).get_Contacts());
                notifyDataSetChanged();
            }
        });

        return rowView;
    }

    class ContactHolder{
        TextView name;
        TextView email;
        TextView number;
        Button edit;
        Button delete;
    }
}
