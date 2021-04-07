package model.models;

public class Anunt {
    private String titlu;
    private String date;
    private String profesor;
    private String nota;
    private String tip;
    private String pondere;

    public Anunt(String titlu, String date, String profesor, String nota, String tip, String pondere) {
        this.titlu = titlu;
        this.date = date;
        this.profesor = profesor;
        this.nota = nota;
        this.tip = tip;
        this.pondere = pondere;
    }

    public Anunt(String titlu, String date, String profesor, String tip, String nota) {
        this.titlu = titlu;
        this.date = date;
        this.profesor = profesor;
        this.tip = tip;
        this.nota = nota;
    }

    public Anunt(String titlu, String date, String profesor) {
        this(titlu , date , profesor , null);
    }

    public Anunt(String titlu, String date, String profesor, String nota) {
        this.titlu = titlu;
        this.date = date;
        this.profesor = profesor;
        this.nota = nota;
    }

    public Anunt() {
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }


    @Override
    public String toString() {
        return titlu + '\n' + date + '\n' +
                profesor + '\n' +
                nota + '\n' + tip + '\n' +
                pondere ;
    }
}
