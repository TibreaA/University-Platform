package model.user;

public class SuperAdministrator extends Administrator {
    public SuperAdministrator(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrContract) {
        super(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nrContract);
    }
}
