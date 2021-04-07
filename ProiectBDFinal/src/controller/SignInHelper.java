package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import model.Acc.Cont;
import model.Activitate.ActivitateGrup;
import model.Activitate.Activity;
import model.Activitate.Grup;
import model.Activitate.Mesaj;
import model.HelperDataBase.Repository;
import model.Note.Catalog;
import model.Note.Nota;
import model.models.*;
import model.user.Administrator;
import model.user.Profesor;
import model.user.Student;
import model.user.Utilizator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignInHelper {
    private static Utilizator user;
    private static Repository repository = new Repository();
    private static Cont cont;

    private static Catalog catalog;

    private static ObservableList<String> date; //information user
    private static ObservableList<formatNota> note;
    private static ObservableList<Anunt> anunturi;    //today curs or note
    private static ObservableList<Curs> cursurileMele;
    private static ObservableList<Curs> toateCursurile;
    private static ObservableList<Curs> restulCursurilor;
    private static ObservableList<Curs> cursurileMeleAll;
    private static ObservableList<Curs> toateCursurileAll;
    private static ObservableList<Curs> restulCursurilorAll;
    private static ObservableList<Curs> cursuriProfesor;
    private static ObservableList<Curs> descriereCurs;

    private static ArrayList<FormatMesaj> mesajs = new ArrayList<>();

    private static ObservableList<formatActivitati> allActivitatiParticip;
    private static ObservableList<formatGrupuri> myGrup;
    private static ObservableList<formatGrupuri> allGrup;

    private static ObservableList<formatActivitateGrup> activitatiGrupParticip;
    private static ArrayList<ActivitateGrup> activitateGrupArrayList = new ArrayList<>();
    private static ObservableList<formatActivitateGrup> activitatiGrupNuParticip;
    private static ArrayList<ActivitateGrup> activitateGrupNuArrayList  = new ArrayList<>();

    private static ObservableList<FormatUser> usersAdmn;
    private static ObservableList<FormatUser> studentiMaterie;

    private static boolean superAdim = false;

    private static int cursSet = 0;

    public static boolean isSuperAdim() {
        return superAdim;
    }

    public static int signIn (String name , String password){

        if(!repository.open()){
            System.out.println("Can't open ");
            return 0;
        }
        else {
            System.out.println("opened");
        }

        cont = repository.logIn(name,password);
        if (cont != null){
            System.out.println(cont);
            user = null;
            System.out.println(cont.getRestriction());

            switch (cont.getRestriction()){
                case 1 :
                    user = repository.queryStudentByID(cont.getIdUtilizator());
                    break;
                case 2:
                    user = repository.queryProfesorByID(cont.getIdUtilizator());
                    break;
                case 3:
                    user = repository.queryAdministratorByID(cont.getIdUtilizator());
                    break;
                case 4:
                    user = repository.queryAdministratorByID(cont.getIdUtilizator());
                    superAdim = true;
                    break;
                default:
                    System.out.println("Incorrect restriction");
                    return 0;
            }
            addDate();
            if(cont.getRestriction() == 4) return 3;
            return  cont.getRestriction();
        }
        else {
            System.out.println("Incorrect credentials");
        }
        return 0;
    }

    public static void addDate(){
        date =  FXCollections.observableArrayList();
        if(user != null) {
            date.add("Nume: " + user.getNume());
            date.add("Prenume: " + user.getPrenume());
            date.add("Email: " + user.getEmail());
            date.add("Adresa: " + user.getAdresa());
            date.add("CNP: " + user.getCNP());
            date.add("IBAN: " + user.getIBAN());
            date.add("Telefon: " + user.getNr_telefon());
        }
    }
    public static ObservableList<String> informationStudent(){
        return date;
    }

    public static ObservableList<formatNota> catalogStudent(){
        catalog = repository.queryNoteStudenti(cont.get_id());
        note = FXCollections.observableArrayList();
        ArrayList<formatNota>  listaNote = new ArrayList<>();

        List<Activity> activities1 = repository.vizualizareCursuriInscris(cont.getIdUtilizator());

        for(Nota nota : catalog.getNote()){
            String tip = "";
            String pondere = "";
            System.out.println(nota);
            for(Activity activity : activities1){
                if(activity.getId() == nota.getIdActivity()){
                    pondere = activity.getPondere() + "%";
                    switch (activity.getTip()){
                        case 1:
                            tip = "Curs";
                            break;
                        case 2:
                            tip = "Seminar";
                            break;
                        case 3:
                            tip = "Laborator";
                            break;
                        default:
                            tip = "Curs!";
                            break;
                    }
                }
            }

            formatNota formatNota = new formatNota(String.valueOf(nota.getGrade()) , nota.getProfesor() , nota.getMaterie() + " - " + tip, nota.getDate().toString() , pondere);
            listaNote.add(formatNota);
        }

        note.addAll(listaNote);
        return note;
    }

    public static void addAnunturi(){
        ArrayList<Activity> cursuri = (ArrayList<Activity>) repository.vizualizareCursuriInscris(cont.getIdUtilizator());
        anunturi = FXCollections.observableArrayList();

        for(Activity c : cursuri){
            String data = repository.queryFirstDateForThisActivity(c.getId()).toString();
            if(data.contains(LocalDate.now().toString())){
                try {
                    anunturi.add(new Anunt("Curs:" , c.getNume() , data, c.getProfesor()));
                }catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        Catalog catalog = repository.queryNoteStudenti(cont.getIdUtilizator());
        for (Nota n : catalog.getNote()){
            Date d = n.getDate();
            String data = d.toString();
            LocalDate localDate = LocalDate.parse(data);

            if(localDate.equals(LocalDate.now())){
                try {
                    anunturi.add(new Anunt("Nota:" , n.getMaterie() , n.getDate().toString() , n.getProfesor() , String.valueOf(n.getGrade())));
                }catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static ObservableList<Anunt> completionAnunturi(){
        return anunturi;
    }

    public static void addCursuri(){
        ArrayList<Activity> cursuri1 = (ArrayList<Activity>) repository.vizualizareCursuriInscris(cont.getIdUtilizator());
        cursurileMele = FXCollections.observableArrayList();
        cursurileMeleAll = FXCollections.observableArrayList();
        ArrayList<Curs> cursuri = new ArrayList<>();
        ArrayList<Curs> cursuriAll = new ArrayList<>();
        for (Activity a :cursuri1){
            String data = repository.queryFirstDateForThisActivity(a.getId()).toString();
            boolean ok = true;
            for(Curs curs :cursuri){
                if(curs.getNume().equals( a.getNume()) && curs.getProfesor().equals(a.getProfesor())){
                    ok = false;
                    break;
                }
            }
            if(ok) {
                cursuri.add(new Curs(a.getNume(), getTip(a.getTip()), data ,a.getProfesor(), String.valueOf(a.getPondere())));
            }
            cursuriAll.add(new Curs(a.getNume(), getTip(a.getTip()),data, a.getProfesor(), String.valueOf(a.getPondere())));
        }
        cursurileMele.addAll(cursuri);
        cursurileMeleAll.addAll(cursuriAll);

        ArrayList<Activity> cursuri2 = (ArrayList<Activity>) repository.vizualizareCursuri();
        toateCursurile = FXCollections.observableArrayList();
        toateCursurileAll = FXCollections.observableArrayList();
        ArrayList<Curs> cursuri3 = new ArrayList<>();
        ArrayList<Curs> cursuriAll2 = new ArrayList<>();
        for (Activity a :cursuri2){
            String data = repository.queryFirstDateForThisActivity(a.getId()).toString();
            boolean ok = true;
            for(Curs curs :cursuri3){
                if(curs.getNume().equals( a.getNume()) && curs.getProfesor().equals(a.getProfesor())){
                    ok = false;
                    break;
                }
            }
            if(ok) {
                cursuri3.add(new Curs(a.getNume(), getTip(a.getTip()),data, a.getProfesor(), String.valueOf(a.getPondere())));
            }
            cursuriAll2.add(new Curs(a.getNume(), getTip(a.getTip()),data, a.getProfesor(), String.valueOf(a.getPondere())));
        }
        toateCursurile.addAll(cursuri3);
        toateCursurileAll.addAll(cursuriAll2);

        ArrayList<Activity> cursuri4 = (ArrayList<Activity>) repository.vizualizareCursuriNeinscris(cont.getIdUtilizator());
        restulCursurilor = FXCollections.observableArrayList();
        restulCursurilorAll = FXCollections.observableArrayList();
        ArrayList<Curs> cursuri5 = new ArrayList<>();
        ArrayList<Curs> cursuriAll3 = new ArrayList<>();

        for (Activity a :cursuri4){
            String data = repository.queryFirstDateForThisActivity(a.getId()).toString();
            boolean ok = true;
            for(Curs curs :cursuri5){
                if(curs.getNume().equals( a.getNume()) && curs.getProfesor().equals(a.getProfesor())){
                    ok = false;
                    break;
                }
            }
            if(ok) {
                cursuri5.add(new Curs(a.getNume() ,  getTip(a.getTip()) ,data , a.getProfesor() , String.valueOf(a.getPondere())));
            }
            cursuriAll3.add(new Curs(a.getNume() ,  getTip(a.getTip()) ,data , a.getProfesor() , String.valueOf(a.getPondere())));
        }

        restulCursurilor.addAll(cursuri5);
        restulCursurilorAll.addAll(cursuriAll3);

    }
    public static void informationCursuri(int curs){
        descriereCurs = FXCollections.observableArrayList();

        ArrayList<Curs> cursuri5 = new ArrayList<>();
        if(cursSet == 0){
            for(Curs curs1 : cursurileMeleAll){
                if(curs1.getNume().equals(cursurileMele.get(curs).getNume()) && curs1.getProfesor().equals(cursurileMele.get(curs).getProfesor())){
                    cursuri5.add(curs1);
                }
            }
        }else if(cursSet == 1){
            for(Curs curs1 : toateCursurileAll){
                if(curs1.getNume().equals(toateCursurile.get(curs).getNume()) && curs1.getProfesor().equals(toateCursurile.get(curs).getProfesor())){
                    cursuri5.add(curs1);
                }
            }
        }else{
            for(Curs curs1 : restulCursurilorAll){
                if(curs1.getNume().equals(restulCursurilor.get(curs).getNume()) && curs1.getProfesor().equals(restulCursurilor.get(curs).getProfesor())){
                    cursuri5.add(curs1);
                }
            }
        }
        descriereCurs.setAll(cursuri5);
    }

    public static boolean enrollCurs(int curs){
        ArrayList<Activity> cursuri1 = (ArrayList<Activity>) repository.vizualizareCursuriNeinscris(cont.getIdUtilizator());
        boolean ok = true;
        for(Activity curs1 : cursuri1){
            if(curs1.getNume().equals(restulCursurilor.get(curs).getNume()) && curs1.getProfesor().equals(restulCursurilor.get(curs).getProfesor())){
                if(!repository.registrationForTheActivity(cont.getIdUtilizator(), curs1.getNume() , curs1.getTip())){
                    ok = false;
                }
            }
        }

        if(!ok){
            repository.giveUpActivity(restulCursurilor.get(curs).getNume() , cont.getIdUtilizator());
        }

        return ok;
    }
    public static void renuntareCurs(int curs){
        repository.giveUpActivity(cursurileMele.get(curs).getNume() , cont.getIdUtilizator());
    }

    public static void setCursuri (int curs){
        cursSet = curs;
    }

    public static ObservableList<formatActivitati> setActivitatiProfesor(String data){
        cursuriProfesor();
        allActivitatiParticip = FXCollections.observableArrayList();
        for(Curs curs : cursuriProfesor){
            if(curs.getData().contains(data)){
                allActivitatiParticip.add(new formatActivitati(curs.getTip() , curs.getNume() , curs.getProfesor() , curs.getData()));
            }
        }

        return allActivitatiParticip;
    }
    public static ObservableList<formatActivitati> setAllActivitatiParticip(String data){
       addCursuri();
        allActivitatiParticip = FXCollections.observableArrayList();
        for(Curs curs : cursurileMeleAll){
            if(curs.getData().contains(data)){
                allActivitatiParticip.add(new formatActivitati(curs.getTip() , curs.getNume() , curs.getProfesor() , curs.getData()));
            }
        }
        for(ActivitateGrup activitateGrup : activitateGrupArrayList){
            if(activitateGrup.getData().toString().contains(data)){
                allActivitatiParticip.add(new formatActivitati(activitateGrup.getName() , activitateGrup.get_id() + "" , activitateGrup.getProfesor() , activitateGrup.getData().toString()));
            }
        }
        return allActivitatiParticip;
    }
    public static ObservableList<formatActivitateGrup> setAllActivitatiGrupParticip(int index){
        List<ActivitateGrup> activitateGrups = repository.queryGroupActivitiesByStudentId(cont.getIdUtilizator());
        ArrayList<formatActivitateGrup> formatActivitateGrups = new ArrayList<>();
        activitatiGrupParticip = FXCollections.observableArrayList();

        for(ActivitateGrup activitateGrup : activitateGrups){
            activitateGrupArrayList.add(activitateGrup);
            System.out.println(index + " --- " + activitateGrup.getIdGrup() + "  " + myGrup.get(index).getId());
            if(myGrup.get(index).getId().equals(activitateGrup.getIdGrup() + "")){
                formatActivitateGrups.add(new formatActivitateGrup(activitateGrup.getName() , activitateGrup.getMinPart() + "" , activitateGrup.getTermenLimita().toString() , activitateGrup.getData().toString() , activitateGrup.getProfesor()));
         }
        }
        activitatiGrupParticip.setAll(formatActivitateGrups);

        return activitatiGrupParticip;
    }

    public static ObservableList<formatActivitateGrup> setAllActivitatiGrupNuParticip(int index){
        List<ActivitateGrup> activitateGrups = repository.queryNonGroupActivitiesByStudentId(cont.getIdUtilizator());
        ArrayList<formatActivitateGrup> formatActivitateGrups = new ArrayList<>();
        activitatiGrupNuParticip = FXCollections.observableArrayList();

        for(ActivitateGrup activitateGrup : activitateGrups) {
            if (!activitateGrupArrayList.contains(activitateGrup)) {
                activitateGrupNuArrayList.add(activitateGrup);
                System.out.println(index + " --- " + activitateGrup.getIdGrup());
                if(myGrup.get(index).getId().equals(activitateGrup.getIdGrup() + ""))
                    formatActivitateGrups.add(new formatActivitateGrup(activitateGrup.getName(), activitateGrup.getMinPart() + "", activitateGrup.getTermenLimita().toString(), activitateGrup.getData().toString(), activitateGrup.getProfesor()));
            }
        }
        activitatiGrupNuParticip.setAll(formatActivitateGrups);

        return activitatiGrupNuParticip;
    }


    public static ObservableList<formatGrupuri> setMyGrup(){
        addCursuri();

        myGrup = FXCollections.observableArrayList();
        List<Grup> grupuri = repository.getGrupuriForStudent(cont.getIdUtilizator());
        ArrayList<formatGrupuri> formatGrupuris = new ArrayList<>();

        for (Grup grup: grupuri){
            if(grup != null){

              formatGrupuris.add(new formatGrupuri(grup.getId()+"" , toateCursurileAll.get(grup.getIdMaterie()).getNume()));
            }
        }
        myGrup.setAll(formatGrupuris);
        return myGrup;
    }
    public static ObservableList<formatGrupuri> setAllGrupuri(){
        addCursuri();
        setMyGrup();

        ArrayList<Activity> cursuri1 = (ArrayList<Activity>) repository.vizualizareCursuriInscris(cont.getIdUtilizator());
        ArrayList<formatGrupuri> formatGrupuris = new ArrayList<>();

        allGrup = FXCollections.observableArrayList();
        int idGrup;
        for(Activity curs : cursuri1) {
            Grup grup = repository.queryGrupByIdMaterie(curs.getId());
            if (grup == null){
                idGrup = repository.insertGrup(curs.getId());
                grup = repository.queryGrupByIdMaterie(curs.getId());
            }else{
                idGrup = grup.getId();
                grup = repository.queryGrupByIdMaterie(curs.getId());
            }

            boolean ok  = true;
            for(formatGrupuri formatGrupuri : myGrup){
                if(formatGrupuri.getId().equals(idGrup + "")){
                    ok = false;
                    break;
                }
            }
            formatGrupuri formatGrupuri = new formatGrupuri(idGrup + "",toateCursurileAll.get(grup.getIdMaterie()).getNume());
            if (ok && !allGrup.contains(formatGrupuri)) {
                formatGrupuris.add(formatGrupuri);
            }
        }
        allGrup.setAll(formatGrupuris);

        return allGrup;
    }

    public static String getTip(int tip1){
        String tip;
        switch (tip1){
            case 1:
                tip = "Curs";
                break;
            case 2:
                tip = "Seminar";
                break;
            case 3:
                tip = "Laborator";
                break;
            default:
                tip = "Curs!";
                break;
        }
        return tip;
    }
    public static ObservableList<Curs> completionCursuri(){
        return cursurileMele;
    }
    public static ObservableList<Curs> completionCursuriToate(){
        return toateCursurile;
    }
    public static ObservableList<Curs> completionCursuriRest(){
        return restulCursurilor;
    }
    public static ObservableList<Curs> getDescriereCurs(){
        return descriereCurs;
    }
    //// professor
    public static ObservableList<Curs> cursuriProfesor(){
        cursuriProfesor = FXCollections.observableArrayList();
        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        for(Activity activity : activities){
            String data = repository.queryFirstDateForThisActivity(activity.getId()).toString();
            String tip;
            switch (activity.getTip()){
                case 1:
                    tip = "Curs";
                    break;
                case 2:
                    tip = "Seminar";
                    break;
                case 3:
                    tip = "Laborator";
                    break;
                default:
                    tip = "Curs!";
                    break;
            }
            cursuriProfesor.add(new Curs(activity.getNume() , tip ,data , activity.getProfesor() , activity.getPondere() + "" ));
        }

        return cursuriProfesor;
    }

    public static ObservableList<String> activitatiprof(){
        ObservableList<String> cursuri = FXCollections.observableArrayList();
        cursuriProfesor();

        for(Curs curs : cursuriProfesor){
            String newCurs = curs.getNume() + " - " + curs.getTip();
            cursuri.add(newCurs);
        }
        return cursuri;
    }

    public static ObservableList<String> studentiPeActivitate(int activitate){
        ObservableList<String> studentii = FXCollections.observableArrayList();

        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(cont.getIdUtilizator());
        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        int idActivity = activities.get(activitate).getId();

        for(Student student : students){
            int idStudent = repository.queryIDStudentByCNP(student.getCNP());

            List<Activity> activities1 = repository.vizualizareCursuriInscris(idStudent);

            for(Activity activity : activities1){
                if(activity.getId() == idActivity){
                    studentii.add(student.getNume() + " " + student.getPrenume());
                    break;
                }
            }
        }
        return studentii;
    }

    public static ObservableList<String> noteStudenti(int student , int activitate){
        ObservableList<String> note = FXCollections.observableArrayList();
        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(cont.getIdUtilizator());

        int idStudent = repository.queryIDStudentByCNP(students.get(student).getCNP());
        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        int idActivity = activities.get(activitate).getId();

        catalog = repository.queryNoteStudenti(idStudent);

        for(Nota nota : catalog.getNote()){
            if(idActivity == nota.getIdActivity()) {
                note.add(nota.getGrade() + " " + nota.getDate());
            }
        }

        return note;
    }

    public static boolean checkHaveGrade (int student , int activitate){
        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(cont.getIdUtilizator());

        int idStudent = repository.queryIDStudentByCNP(students.get(student).getCNP());
        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        int idActivity = activities.get(activitate).getId();
        catalog = repository.queryNoteStudenti(idStudent);

        for(Nota nota : catalog.getNote()){
            if(idActivity == nota.getIdActivity()) {
               return true;
            }
        }
        return false;
    }

    public static void addNota(int student , int activitate , int nota){
        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(cont.getIdUtilizator());
        int idStudent = repository.queryIDStudentByCNP(students.get(student).getCNP());

        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        int idActivity = activities.get(activitate).getId();

        repository.adaugaNota(cont.getIdUtilizator() , idStudent , idActivity , nota);

    }

    public static void upNota(int student , int activitate , int nota){
        List<Student> students = repository.vizualizareListeStudentiPentruUnProfesorDat(cont.getIdUtilizator());
        int idStudent = repository.queryIDStudentByCNP(students.get(student).getCNP());

        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        int idActivity = activities.get(activitate).getId();

        System.out.println(idActivity);
        repository.schimbareNota(cont.getIdUtilizator() , idStudent , idActivity , nota);
    }


    public static boolean insertGrup(int grup){
        boolean ok = repository.insertListaMembriGrup(cont.getIdUtilizator() , Integer.parseInt(allGrup.get(grup).getId()));
        setMyGrup();
        return ok;
    }

    public static void inscriereActivitate(int index){
        setMyGrup();
        repository.inscriereActivitate(cont.getIdUtilizator() ,Integer.parseInt(myGrup.get(index).getId()));
    }
    public static void adaugareActivitate(int index , String name , int nrPart , java.sql.Date dateLim , java.sql.Date data){
        System.out.println(name);
        System.out.println(nrPart);
        System.out.println(dateLim);
        System.out.println(data);
        System.out.println(Integer.parseInt(myGrup.get(index).getId()));
        repository.insertActivitate( name , nrPart , dateLim ,Integer.parseInt(myGrup.get(index).getId()) , cont.getIdUtilizator() , data);

    }

    public static List<String> membriGrup (int index){
        return repository.queryListaGrup(Integer.parseInt(myGrup.get(index).getId()));
    }

    public static List<FormatMesaj> actualizareMesaje(String idGrup){
        int id = 1;

        mesajs = new ArrayList<>();

        for(formatGrupuri f : myGrup){
            if(f.getNume().equals(idGrup))
                id = Integer.parseInt(f.getId());
        }
        List<Mesaj> mesaje = repository.queryMesaje(id);

        for(Mesaj mesaj : mesaje){
            System.out.println(mesaj.getMess() + " " + mesaj.getIdStudent() + " " + repository.getQueryActivitateForName(mesaj.getIdStudent()));
            mesajs.add(new FormatMesaj(mesaj.getMess(), repository.queryStudentByID(mesaj.getIdStudent()).getNume()));
        }

        return mesajs;
    }
    public static List<MenuItem> addGrupuriMesaje(){
        List<MenuItem> items = new ArrayList<>();
        setMyGrup();
        for(formatGrupuri f : myGrup){
            items.add(new MenuItem(f.getNume()));
        }

        return items;
    }

    public static void sendMesaj(String mes , String idGrup){
        int id = 1;
        for(formatGrupuri f : myGrup){
            if(f.getNume().contains(idGrup)) {
                System.out.println(idGrup + " " + f.getNume());
                id = Integer.parseInt(f.getId());
            }
        }
        repository.postMesaj(mes , cont.getIdUtilizator() , id);
    }

    public static void schimbaPondere(int id , int newPondere){
        List<Activity> activities = repository.queryActivityByIdProf(cont.getIdUtilizator());
        repository.schimbaPonere(activities.get(id).getId() , newPondere);
    }

    public static ObservableList<FormatUser> addUsersStudents(){
        usersAdmn = FXCollections.observableArrayList();

        List<Student> utilizators = repository.queryStudent();
        int i = 1;
        for(Student s :utilizators){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }

        return usersAdmn;
    }
    public static ObservableList<FormatUser> addUsersProfesor(){
        usersAdmn = FXCollections.observableArrayList();

        List<Profesor> utilizators = repository.queryProfesor();
        int i = 1;
        for(Profesor s :utilizators){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }

        return usersAdmn;
    }
    public static ObservableList<FormatUser> addUsersAdmin(){
        usersAdmn = FXCollections.observableArrayList();

        List<Administrator> utilizators = repository.queryAdministrator();
        int i = 1;
        for(Administrator s :utilizators){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }

        return usersAdmn;
    }

    public static ObservableList<FormatUser> addUsers(){
        usersAdmn = FXCollections.observableArrayList();

        List<Profesor> utilizators = repository.queryProfesor();
        int i = 1;
        for(Profesor s :utilizators){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }

        List<Profesor> utilizators1 = repository.queryProfesor();
        for(Profesor s :utilizators1){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }

        List<Administrator> utilizators2 = repository.queryAdministrator();
        for(Administrator s :utilizators2){
            usersAdmn.add(new FormatUser(i++ , s.getNume() + " " + s.getPrenume() , s.getCNP() , s.getNr_telefon() , s.getEmail()));
        }


        return usersAdmn;
    }

    public static ObservableList<FormatUser> findUsers(String name ,String prenume, int tip){
        usersAdmn = FXCollections.observableArrayList();

        List<Utilizator> utilizators = new ArrayList<>();
        List<Utilizator> utilizators1 = new ArrayList<>();
        List<Utilizator> utilizators2 = new ArrayList<>();

        switch (tip){
            case 1:
                utilizators = repository.queryUserByNameAndType(name , prenume ,"student");
                break;
            case 2:
                utilizators = repository.queryUserByNameAndType(name , prenume ,"profesor");
                break;
            case 3:
                utilizators = repository.queryUserByNameAndType(name , prenume ,"administrator");
                break;
            case 4:
                utilizators = repository.queryUserByNameAndType(name , prenume ,"student");
                utilizators1 = repository.queryUserByNameAndType(name , prenume ,"profesor");
                utilizators2 = repository.queryUserByNameAndType(name , prenume ,"administrator");
                break;
        }

        int id = 1;
        for(Utilizator u : utilizators){
            usersAdmn.add(new FormatUser(id++ , u.getNume() + " " + u.getPrenume() , u.getCNP() , u.getNr_telefon() , u.getEmail()));
        }
        if(tip == 4){
            for(Utilizator u : utilizators1){
                usersAdmn.add(new FormatUser(id++ , u.getNume() + " " + u.getPrenume() , u.getCNP() , u.getNr_telefon() , u.getEmail()));
            }
            for(Utilizator u : utilizators2){
                usersAdmn.add(new FormatUser(id++ , u.getNume() + " " + u.getPrenume() , u.getCNP() , u.getNr_telefon() , u.getEmail()));
            }
        }
        return usersAdmn;
    }

    public static void deleteUser(int id , int tip){
        switch (tip){
            case 1:
                repository.deleteStudent(id + 1);
                break;

            case 2:
                repository.deleteProfesor(id + 1);
                break;

            case 3:
                repository.deleteAdmin(id + 1);
                break;
        }
    }

    public static ObservableList<Curs> addMateri (){
        List<Activity> activities = repository.vizualizareCursuri();

        toateCursurileAll = FXCollections.observableArrayList();

        ArrayList<Curs> cursuriAll = new ArrayList<>();
        for (Activity a :activities){
            cursuriAll.add(new Curs(a.getNume(), getTip(a.getTip()), a.getProfesor(), String.valueOf(a.getPondere())));
        }
        toateCursurileAll.addAll(cursuriAll);

        return toateCursurileAll;
    }

    public static ObservableList<FormatUser> addStudentiPerMaterie(int id){
        List<Integer> idStudenti = repository.getStudentiByMaterie(id);

        studentiMaterie = FXCollections.observableArrayList();

        SignInHelper.addUsersStudents();
        int id1 = 1;
        for(int i : idStudenti){
            i--;
            studentiMaterie.add(new FormatUser(id1++ , usersAdmn.get(i).getNume() , usersAdmn.get(i).getCnp() , usersAdmn.get(i).getPhone() , usersAdmn.get(i).getMail()));
        }

        return studentiMaterie;
    }

    public static void addCurs(String name , String idProfesor , String tip , String pondere , String descriere , String nrMax , String dateStart ,String dateEnd){
        int tipP;
        switch (tip){
            case "Curs":
                tipP = 1;
                break;
            case "Laborator":
                tipP = 2;
                break;
            case "Seminar":
                tipP = 3;
                break;
            default:
                tipP = 0;
        }

        repository.adaugaActivitate(name , Integer.parseInt(idProfesor) , tipP , Integer.parseInt(pondere) , descriere , Integer.parseInt(nrMax) , dateStart , dateEnd);
    }

    public static int getIdUtil(){
        return cont.getIdUtilizator();
    }

    public static String noteProf(){
        Catalog catalog = repository.noteProf(cont.getIdUtilizator());
        StringBuilder s = new StringBuilder();

        for(Nota n : catalog.getNote()){
            s.append(n.getGrade()).append("\t").append(n.getStudent()).append("\t").append(n.getMaterie()).append("\t").append(n.getDate()).append("\n");
        }
        return s.toString();
    }

}
