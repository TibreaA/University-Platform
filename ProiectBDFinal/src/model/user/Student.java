package model.user;

public class Student extends Utilizator{
    private String anStudiu;
    private int nrOre;

    public Student() {
    }

    public Student(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrContract, String anStudiu, int nrOre) {
        super(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nrContract);
        this.anStudiu = anStudiu;
        this.nrOre = nrOre;
    }

    public String getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(String anStudiu) {
        this.anStudiu = anStudiu;
    }

    public int getNrOre() {
        return nrOre;
    }

    public void setNrOre(int nrOre) {
        this.nrOre = nrOre;
    }


    @Override
    public String toString() {
        return "Student{" +
                "anStudiu='" + anStudiu + '\'' +
                ", nrOre=" + nrOre +
                 super.toString()+ "} " ;
    }
}
