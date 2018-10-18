package lpcdtl.bdmob.MesContacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_EMAIL = "email";
    private final ArrayList<Contact> contact_list = new ArrayList<>();

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "+ TABLE_CONTACTS + "("+ KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_NAME + " TEXT," + KEY_PHONE_NUMBER + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
        onCreate(db);
    }

    //Adding new contact

    public void  Add_Contact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(KEY_EMAIL, contact.getMail());
        //Inserting row
        db.insert(TABLE_CONTACTS,null, values);
        db.close();
    }

    /**
     * Retourne tous les utilisateurs
     * @return tous les utilisateurs
     */
    public ArrayList<Contact> get_Contacts() {
       try {
           contact_list.clear();

           String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
           SQLiteDatabase db = this.getReadableDatabase();

           Cursor cursor = db.rawQuery(selectQuery, null);

           //Looping through allrows and adding to list
           if (cursor.moveToFirst()){
               do {
                   Contact contact = new Contact();
                   contact.setId(cursor.getInt(0));
                   contact.setName(cursor.getString(1));
                   contact.setPhoneNumber(cursor.getString(2));
                   contact.setMail(cursor.getString(3));

                   //Adding contact to list
                   contact_list.add(contact);
               }while (cursor.moveToNext());
           }

           //return contact_list
           cursor.close();
           db.close();
           return contact_list;

        }catch (Exception e){
           Log.e("all_contact", ""+e);
       }

       return contact_list;
    }

    public void deleteSingleContact(int index){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, "id=" + index, null);
    }
}
