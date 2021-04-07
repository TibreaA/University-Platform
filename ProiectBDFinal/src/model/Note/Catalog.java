package model.Note;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    List<Nota> note;

    public Catalog() {
        this.note = new ArrayList<>();
    }

    public List<Nota> getNote() {
        return new ArrayList<>(note);
    }

    public boolean addNota(Nota nota){
        if(nota != null){
            this.note.add(nota);
            return true;
        }else {
            return false;
        }

    }


    @Override
    public String toString() {
        return "Catalog{" +
                "note=" + note +
                '}';
    }
}
