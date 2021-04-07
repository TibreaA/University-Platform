package model.Acc;

public class Cont {
    private int _id;
    private String ID; //username for acc
    private String password;
    private int restriction; //integer 1-Student ,2-Profesor, 3-Administrator , 4 - SuperAdministrator
    private int idUtilizator; // _id of the student /profesor / administrator from sql


    private static int STUDENT = 1;
    private static int PROFESOR = 2;
    private static int ADMINISTRATOR = 3;
    private static int SUPER_ADMINISTRATOR = 4;

    public Cont() {

    }

    public Cont(int _id, String ID, String password, int restriction, int idUtilizator) {
        this._id = _id;
        this.ID = ID;
        this.password = password;
        this.restriction = restriction;
        this.idUtilizator = idUtilizator;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean changePassword(String password) {
        if(password != null){
            this.password = password;
            return true;
        }
        return false;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }


    public int getRestriction() {
        return restriction;
    }

    public void setRestriction(int restriction) {
        this.restriction = restriction;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }


    @Override
    public String toString() {
        return "Cont{" +
                "_id=" + _id +
                ", ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", restriction=" + restriction +
                ", idUtilizator=" + idUtilizator +
                '}';
    }
}
