package model.user;

public class Administrator extends Utilizator {



    public Administrator() {
    }

    public Administrator(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrContract) {
        super(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nrContract);

    }

    @Override
    public String toString() {
        return "Administrator{ " + super.toString() + " }";
    }
}
