package lpcdtl.bdmob.MesContacts;


public class Contact {
    private int _id;
    private String _name;
    private String _phone_Number;
    private String _mail;

    public Contact(){}

    public Contact(int id, String _name, String _phone_Number, String _mail) {
        this._id = id;
        this._name = _name;
        this._phone_Number = _phone_Number;
        this._mail = _mail;
    }

    public Contact(String _name, String _phone_Number, String _mail) {
        this._name = _name;
        this._phone_Number = _phone_Number;
        this._mail = _mail;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getPhoneNumber() {
        return _phone_Number;
    }

    public void setPhoneNumber(String _phone_Number) {
        this._phone_Number = _phone_Number;
    }


    public String getMail() {
        return _mail;
    }

    public void setMail(String _mail) {
        this._mail = _mail;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }
}
