package model.user;

public abstract class Utilizator {
    private String CNP;
    private String nume;
    private String prenume;
    private String adresa;
    private String nr_telefon;
    private String email;
    private String IBAN;
    private int nrContract;

    public Utilizator() {
    }

    public Utilizator(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrContract) {
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.nr_telefon = nr_telefon;
        this.email = email;
        this.IBAN = IBAN;
        this.nrContract = nrContract;
    }


    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getNrContract() {
        return nrContract;
    }

    public void setNrContract(int nrContract) {
        this.nrContract = nrContract;
    }

    @Override
    public String toString() {
        return
                "CNP='" + CNP + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nr_telefon='" + nr_telefon + '\'' +
                ", email='" + email + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", nrContract=" + nrContract;
    }
}
