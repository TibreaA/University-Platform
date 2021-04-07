package controller;

import model.HelperDataBase.Repository;

public class SignUpHelper {
    private static final Repository repository = new Repository();

    public static void open (){
        repository.open();
    }

    public static boolean SignUpStudent (String cnp , String fName , String lName , String Address , String number , String mail , String IBAN , String pass){
        return repository.signInStudentProcedure(cnp , fName, lName , Address , number , mail , IBAN , pass);
    }
    public static boolean SignUpProfessor (String cnp , String fName , String lName , String Address , String number , String mail , String IBAN , String pass , int nrMin , int nrMax , String department){
        return repository.signInProfesorProcedure(cnp , fName, lName , Address , number , mail , IBAN ,nrMin , nrMax , department, pass);
    }
    public static boolean SignUpAdmin (String cnp , String fName , String lName , String Address , String number , String mail , String IBAN , String pass){
        return repository.signInAdministratorProcedure(cnp , fName, lName , Address , number , mail , IBAN , pass);
    }

}
