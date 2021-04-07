package model;

import model.Acc.Cont;
import model.Activitate.*;
import model.HelperDataBase.Repository;
import model.Note.Catalog;
import model.user.Student;
import model.user.Utilizator;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class MainTester {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Repository repository = new Repository();
        if (!repository.open()) {
            System.out.println("Can t open ");
            return;
        } else {
            System.out.println("opened");
        }

        //query Student
        System.out.println("\nStudenti: \n" + repository.queryStudent().toString());
        //end query Student

        //query Profesor
        System.out.println("\nProfesori: \n" + repository.queryProfesor().toString());

        //end query profesor

        //query admin
        System.out.println("\nAdmininstratori: \n" + repository.queryAdministrator().toString());

        //end query admin

        //NOTE STUDENT--
        Catalog catalog = repository.queryNoteStudenti(1);
        System.out.println(catalog);

        //END NOTE STUDENT --

        //post mesaj--

        repository.postMesaj("nu acum", 2, 1);

        //end post ---

        //QUERY MESAJE --
        System.out.println("Mesaje din grup 1");
        List<Mesaj> mesaje = repository.queryMesaje(1);
        for (Mesaj x : mesaje)
            System.out.println(x + " " + repository.getQueryStudentForName(x.getIdStudent()));
        System.out.println("End mesaje grup 1");

        //QUERY MESAJE END---
        int respons;
        //inscriere in grup --

        int idStudent =11;
        int idMaterie= 6;

        Grup grup1 = repository.queryGrupByIdMaterie(idMaterie);

        if(grup1 == null){
            respons = repository.insertGrup(idMaterie);
        }else {
            respons = grup1.getId();
        }
        if(repository.insertListaMembriGrup(idStudent,respons)){
            System.out.println(repository.queryStudentByID(1).getNume() + " sa inscris");
        }
        else {
            System.out.println("failed...");
        }
        //end inscriere
        int idGrup = respons;
        //adaugare activitate--

        String nume = "mate";
        int nrMin = 3;
        Date date = new Date(Calendar.getInstance().getTime().getTime());

         idGrup = 3;
        int idStudet = 1;
       // repository.insertActivitate(nume, nrMin, date, idGrup, idStudet);// adauga la participanti persoana care creaza activitatea


        //end adaugare activitate --

        //inscriere la activitate
        idStudet = 3;
        int idActivitate = 8;
        repository.inscriereActivitate(3, 6);


        //end inscriere la activitate

        //query activitati
        idGrup = 2;
        System.out.println("Activitati pr grup :" + idGrup);

        List<ActivitateGrup> activitati = repository.getActivitiesForGrup(idGrup);

        for (ActivitateGrup activitate : activitati) {
            System.out.println(activitate);
        }
        System.out.println("End");
        //end query activitati


        //inscriere in grup --



        //end inscriere

        //membri grup --
        System.out.println(" Membri grup " + 2);
        List<String> MembriGrup = repository.queryListaGrup(2);
        for (String x : MembriGrup)
            System.out.println(x);

        System.out.println("end membri");
        //end membri--

        //materi student--
        System.out.println("\nMateri pentru Student : " + repository.queryStudentByID(1).getNume());
        List<String> MaterileUnuiStudent = repository.queryStudentForMateri(1);
        for (String x : MaterileUnuiStudent)
            System.out.println(x);

        System.out.println("End Materi Student \n");
        // end materi student   --

        //search id activitate by activity name, type and id professor
        int res = repository.queryIdActivityByNameAndTypeAndIdProf("BD", 1, 1);
        if (res == 0)
            System.out.println("nu s-a gasit");
        else
            System.out.println(res);
        //end search id activitate


        //adauga nota
        repository.adaugaNota(1, 1, 1, 6);
        //end adauga nota

        //schimba nota
        repository.schimbareNota(1, 1, 1, 8);
        //end schimba nota

        //adauga activitate
        repository.adaugaActivitate("Geografie", 4, 2, 30, "Muntii Carpati", 30, "2021-01-01 12:00:00", "2022-01-01 12:00:00");
        //end adauga activitate

        //schimba pondere
        repository.schimbaPonere(14, 70);
        //end pondere

        //lista activitati profesor
        System.out.println("Lista activitati profesor: " + 1);
        List<Activity> activities = repository.queryActivityByIdProf(1);
        for (Activity activity : activities)
            System.out.println(activity.toString());

        System.out.println("end lista activitati profesor");
        //end lista activitati profesor

        //give up the activity
        repository.giveUpActivity("BD", 2);
        //end give up the activity

        //search user by name and type
        System.out.println("\nStudenti cu numele Avram Vasile sunt");
        List<Utilizator> utilizatori = repository.queryUserByNameAndType("Avram", "Vasile", "student");
        for (Utilizator utilizator : utilizatori) {
            System.out.println(utilizator.toString());
        }
        System.out.println("\nEnd studenti cu numele Avram Vasile");

        System.out.println("\nProfesori cu numele Pojar Euduard sunt");
        List<Utilizator> utilizatori1 = repository.queryUserByNameAndType("Pojar", "Eduard", "profesor");
        for (Utilizator utilizator : utilizatori1) {
            System.out.println(utilizator.toString());
        }
        System.out.println("\nEnd profesori cu numele Pojar Euduard ");

        System.out.println("\nAdministratori cu numele Matioc Bogdan sunt");
        List<Utilizator> utilizatori2 = repository.queryUserByNameAndType("Matioc", "Bogdan", "administrator");
        for (Utilizator utilizator : utilizatori2) {
            System.out.println(utilizator.toString());
        }
        System.out.println("\nEnd administratori cu numele Matioc Bogdan");
        //end search user by name and type

        //register for the activity
        int idStud = 6;
        String numeActiv = "MS";
        int tip = 2;
        if (repository.registrationForTheActivity(idStud, numeActiv, tip))
            System.out.println("Studentul cu id_ul " + idStud + " a fost inscris la activitatea " + numeActiv + ", tip " + tip);
        else
            System.out.println("Studentul cu id_ul " + idStud + " NU a fost inscris la activitatea " + numeActiv + ", tip " + tip);
        //end register for the activivity,

        //vizualizare liste studenti pentru un profesor dat
        int idProf = 1;
        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(1);
        System.out.println("\nStudenti inscrisi la profesorul cu id-ul " + idProf + " sunt:");
        for (Student student : students)
            System.out.println(student.toString());
        //end vizualizare liste studenti pentru un profesor dat

        //sign in
        //student
        if (repository.signInStudentProcedure("1234567890123", "Popovici", "Tiberiu", "Crasna str. Pacii", "0712345678", "Popovici.Tiberiu@utcluj.ro", "", "student"))
            System.out.println("student: Sign in with succes");
        else
            System.out.println("student: Sign in failed");

        //profesor
        if (repository.signInProfesorProcedure("7865567890123", "Pop", "Tania", "Craiova str. Sabiuta", "0710987234", "Pop.Tania@utcluj.ro", "", 4, 17, "Calculatoare", "cutit"))
            System.out.println("profesor: Sign in with succes");
        else
            System.out.println("profesor: Sign in  failed");

        //administrator
        if (repository.signInAdministratorProcedure("1234123890123", "Olimpiu", "Gabriela", "Chinteni str. Principala", "0712345678", "Olimpiu.Gabriela@utcluj.ro", "", "amin"))
            System.out.println("admin: Sign in with succes");
        else
            System.out.println("admin: Sign in failed");
        //end sign in


        //Operati Administrator

        //query Materi  by nume
        System.out.println("Materi by nume");
        String nume1 = "BD";

        List<Materie> materii = repository.getMateriByName(nume1);
        for (Materie materie : materii) {
            System.out.println(materie);
        }
        System.out.println("End");
        // end

        //query Materi  by Profesor
        System.out.println("Materi by profesor");
        nume1 = "Bar";
        System.out.println("Profesor " + nume1);
        materii = repository.getMateriByProfesor(nume1);
        for (Materie materie : materii) {
            System.out.println(materie);
        }

        System.out.println("End");
        // end

        //query Materi  by Student
        System.out.println("Materi by Student");
        nume1 = "avram";
        System.out.println("Student " + nume1);
        //materii = repository.getMateriByStudent(nume1);
        for (Materie materie : materii) {
            System.out.println(materie);
        }

        System.out.println("End");
        // end

        //End Administrator

        //queryIDStudentByCNP
        System.out.println("Studentul cu id " + repository.queryIDStudentByCNP("2127894799285"));
        //end queryIDStudentByCNP

        //vizualizare activitati inscris - student
        int idStud1 = 1;
        System.out.println("\nActivitatile la care este inscris studentul cu id- ul " + idStud1 + " sunt:");
        List<Activity> activities1 = repository.vizualizareCursuriInscris(idStud1);
        for (Activity activity : activities1)
            System.out.println(activity.toString());
        //end vizualizare activitati inscris - student

        //vizualizare activitati neinscris - student
        int idStud2 = 1;
        System.out.println("\nActivitatile la care este NU inscris studentul cu id- ul " + idStud1 + " sunt:");
        List<Activity> activities2 = repository.vizualizareCursuriNeinscris(idStud2);
        for (Activity activity : activities2)
            System.out.println(activity.toString());
        //end vizualizare activitati neinscris - student


        //vizualizare toate activitatile
        System.out.println("\nToate activitatile:");
        List<Activity> activities3 = repository.vizualizareCursuri();
        for (Activity activity : activities3)
            System.out.println(activity.toString());
        //end vizualizare toate activitatile

        //queryGroupActivitiesByStudentId
        int idStud4 = 1;
        System.out.println("\nActivitatile de grup la care este inscris studentul cu id- ul " + idStud4 + " sunt:");
        List<ActivitateGrup> activitateGrups = repository.queryGroupActivitiesByStudentId(idStud1);
        for (ActivitateGrup activitateGrup : activitateGrups)
            System.out.println(activitateGrup.toString2());
        //end queryGroupActivitiesByStudentId

        //queryNonGroupActivitiesByStudentId
        int idStud5 = 1;
        System.out.println("\nActivitatile de grup la care NU este inscris studentul cu id- ul " + idStud5 + " sunt:");
        List<ActivitateGrup> activitateGrups1 = repository.queryNonGroupActivitiesByStudentId(idStud1);
        for (ActivitateGrup activitateGrup : activitateGrups1)
            System.out.println(activitateGrup.toString2());
        //end

        //delete group activity
        int idGrupActivity = 10;
        if (repository.deleteGroupActivity(idGrupActivity))
            System.out.println("Activitatea de grup cu id_ul " + idGrupActivity + " a fost stearsa cu succes");
        else
            System.out.println("Activitatea de grup cu id_ul " + idGrupActivity + " nu se poate sterge");
        //end delete group activity

        int option = 0;

        while (option != 2) {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter username : ");
                    String username = scanner.nextLine();
                    System.out.println("Enter password : ");
                    String password = scanner.nextLine();
                    Cont cont = repository.logIn(username, password);
                    if (cont != null) {
                        System.out.println(cont);
                        Utilizator user;
                        System.out.println(cont.getRestriction());
                        if (cont.getRestriction() == 1) {
                            user = repository.queryStudentByID(cont.getIdUtilizator());
                            System.out.println(user);
                        }

                        if (cont.getRestriction() == 2) {
                            user = repository.queryProfesorByID(cont.getIdUtilizator());
                            System.out.println(user);
                        }

                        if (cont.getRestriction() == 3) {
                            user = repository.queryAdministratorByID(cont.getIdUtilizator());
                            System.out.println(user);
                        }

                        if (cont.getRestriction() == 4) {
                            //superAdministrator
                        }

                    } else {
                        System.out.println("Incorrect credentials");
                    }
                    break;

                case 2:
                    repository.close();
                    break;

            }

        }

    }

    private static void menu() {
        System.out.println("Menu :");
        System.out.println("1. Login ");
        System.out.println("2. Exit");
    }
}