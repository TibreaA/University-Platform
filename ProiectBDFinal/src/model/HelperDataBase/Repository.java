package model.HelperDataBase;

import model.Acc.Cont;
import model.Activitate.*;
import model.Note.Catalog;
import model.Note.Nota;
import model.user.Administrator;
import model.user.Profesor;
import model.user.Student;
import model.user.Utilizator;


import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Repository {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/administratiefacultate?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "Marius13";

        //Common fields:

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CNP = "CNP";
        public static final String COLUMN_NUME = "nume";
        public static final String COLUMN_PRENUME = "prenume";
        public static final String COLUMN_ADRESA = "adresa";
        public static final String COLUMN_NR_TELEFON = "nr_telefon";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_IBAN = "IBAN";
        public static final String COLUMN_NR_CONTRACT = "nr_Contract";

        public static final int ORDER_BY_NONE = 1;
        public static final int ORDER_BY_ASC = 2;
        public static final int ORDER_BY_DESC = 3;

        //Table Student:

        public static final String TABLE_STUDENT = "student";
        public static final String COLUMN_AN_STUDIU = "anStudiu";
        public static final String COLUMN_NR_ORE = "nrOre";

        //Table Profesor:

        public static final String TABLE_PROFESOR = "profesor";
        public static final String COLUMN_NR_MIN_ORE = "nrMinOre";
        public static final String COLUMN_NR_MAX_ORE = "nrMaxOre";
        public static final String COLUMN_DEPARTAMENT = "departament";

        //Table Administrator:

        public static final String TABLE_ADMINISTRATOR = "administrator";

        //Table Conturi:

        public static final String TABLE_CONTURI = "conturi";
        //use the common _id
        public static final String COLUMN_USERNAME = "id";
        public static final String COLUMN_PASSWORD = "parola";
        public static final String COLUMN_RESTRICTIE = "restrictie";
        public static final String COLUMN_ID_UTILIZATOR = "idUtilizator";

        // Table catalog:
        public static final String TABLE_CATALOG = "catalog";

        public static final String COLUMN_ID_PROFESOR_ACT = "idProfesorAct";
        public static final String COLUMN_ID_STUDENT_ACT = "idStudentAct";
        public static final String COLUMN_ID_ACTIVITATE = "idActivitate";
        public static final String COLUMN_NOTA = "nota";

        //TABLE ACTIVITATE
        public static final String TABLE_ACTIVITATE_DIDACTICA = "activitatedidactica";
        public static final String COLUMN_TIP = "tip";
        public static final String COLUMN_ID_PROFESOR = "idProfesor";
        public static final String COLUMN_DATA = "data";

        //TABLE GRUPURI
        public static final String TABLE_GRUPURI = "grupuri";
        public static final String COLUMN_ID_MATERIE = "idMaterie";

        //TABELA STUDENT_ACT
        public static final String TABLE_STUDENT_ACTIVITATE_DIDACTICA = "student_activitatedidactica";
        public static final String COLUMN_ID_STUDENT = "idStudent";

        //TABELA LISTA MEMBRI

        public static final String TABLE_LISTA_MEMBRI = "listamembri";
        public static final String COLUMN_ID_GRUP = "idGrup";

        // TABELA MESAJE
        public static final String TABLE_MESAJE = "mesaje";
        public static final String COLUMN_ID_CHAT = "idChat";
        public static final String COLUMN_MESAJ = "mesaj";

        //TABELA CHAT
        public static final String TABLE_CHAT = "chat";

        //TABLE PROFESPR_ACTIVITATEDIDACTICA
        public static final String TABLE_PROFESOR_ACTIVITATEDIDACTICA = "profesor_activitatedidactica";
        public static final String COLUMN_NRSTUDENT = "nrStudent";

        //TABLE CALENDAR
        public  static final String TABLE_CALENDAR = "calendar";
        public static final String COLUMN_DATE = "date";
        //TABELA ACTIVITATE

        public static final String TABLE_ACTIVITATE = "activitate";
        public static final String COLUMN_NR_MIN_PARTICIPANTI = "nrMinParticipanti";
        public static final String COLUMN_TERMEN_LIMITA = "termenLimita";

        //TABELA PARTICIPANTI

        public static final String TABLE_PARTICIPANTI = "participanti";


        ///Calls start:

        public static final String SIGN_IN_PROFESOR = "CALL signInProfesorProcedure(?, ?, ?,?, ?, ?,?, ?, ?,?, ?)";

        public static final String SIGN_IN_STUDENT = "CALL signInStudentProcedure(?, ?, ?, ?, ?, ?, ?, ?)";

        public static final String SIGN_IN_ADMINISTRATOR = "CALL signInAdministratorProcedure(?, ?, ?, ?, ?, ?, ?, ?)";

        public static final String GIVE_UP_THE_ACTIVITY = "CALL giveUpCourse(?, ?)";

        public static final String ADAUGA_ACTIVITATE = "CALL adauga_activitate(?, ?, ?, ?, ?, ?, ?, ?) ";

        public static final String SCHIMBA_PONDERE = "CALL  schimba_pondere(?, ?)";

        public static final String ADAUGA_NOTA = "CALL  adaugare_nota(?, ?, ?, ?)";

        public static final String SCHIMBARE_NOTA = "CALL  schimbare_nota(?, ?, ?, ?)";

        public static final String INSCRIERE_LA_O_ACTIVITATE = "CALL inscriereLaActivitate(?, ?, ?);";

        public static final String DELETE_CONT = "CALL delete_cont(?, ?);";

        public static final String  DELETE_STUDENT_FROM_STUDENT = "CALL delete_student_from_student(?);";

        //Calls end


        ///Querys start:

        public static final String QUERY_CONTURI = "SELECT * FROM " +
                TABLE_CONTURI + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";

        public static final String QUERY_STUDENT_BY_ID = "SELECT * FROM " + TABLE_STUDENT +
                " WHERE " + COLUMN_ID + " = ?";

        public static final String QUERY_PROFESOR_BY_ID = "SELECT * FROM " + TABLE_PROFESOR +
                " WHERE " + COLUMN_ID + " = ?";

        public static final String QUERY_AMINISTRATOR_BY_ID = "SELECT * FROM " + TABLE_ADMINISTRATOR +
                " WHERE " + COLUMN_ID + " = ?";


    /*public static final String VIEW_LISTA_STUDENTI_PROFESOR = "select distinct" + TABLE_STUDENT + "." + COLUMN_NUME  + TABLE_STUDENT + "." + COLUMN_PRENUME +
            "from " + TABLE_STUDENT + " , activitatedidactica , " +
            " student_activitatedidactica sa where a.idProfesor = ? and a._id = sa.idActivitate and sa.idStudent " +
            "= "+ TABLE_STUDENT + "." + COLUMN_ID +" and sa.idActivitate is not null ;";
    */

        public static final String VIEW_LISTA_STUDENTI_PROFESOR = "select distinct s.CNP, s.nume, s.prenume, s.adresa, s.nr_telefon, s.email, s.IBAN, s.nr_Contract, s.anStudiu, s.nrOre"
                + " from student s,  activitatedidactica a, student_activitatedidactica sa"
                + " where a.idProfesor = ? and a._id = sa.idActivitate and sa.idStudent = s._id and sa.idActivitate is not null ;";

        public static final String QUERY_CATALOG_BY_STUDENT = "SELECT " + TABLE_PROFESOR + "." + COLUMN_NUME + ", " + COLUMN_ID_STUDENT_ACT + ", " + COLUMN_ID_ACTIVITATE + ", " + COLUMN_NOTA + "," + COLUMN_DATA +
                " FROM " + TABLE_CATALOG + " INNER JOIN " + TABLE_PROFESOR + " ON " + TABLE_CATALOG + "." + COLUMN_ID_PROFESOR_ACT + " = " + TABLE_PROFESOR + "." + COLUMN_ID
                + " WHERE " + COLUMN_ID_STUDENT_ACT + " = ? ";

        public static final String QUERY_ACTIVITATE_FOR_NAME = "SELECT " + COLUMN_NUME + " FROM " + TABLE_ACTIVITATE_DIDACTICA + " WHERE " + COLUMN_ID + " = ?";

        /**
         * SELECT nume FROM activitatedidactica
         * WHERE _id = ?;
         */

        public static final String QUERY_STUDENT_FOR_NAME = "SELECT " + TABLE_STUDENT + "." + COLUMN_NUME + " FROM " + TABLE_STUDENT + " WHERE " + COLUMN_ID + " = ? ";

        public static final String VIEW_CURSURI_INSCRIS = "select distinct a._id, a.nume, a.tip, a.idProfesor, a.pondere, p.nume, p.prenume, a.descriere, a.nrMaxStudenti "
                + " from activitatedidactica a , student_activitatedidactica sa, profesor p where a._id = sa.idActivitate and sa.idStudent = ? and p._id = a.idProfesor;";

        public static final String VIEW_CURSURI_NEINSCRIS = "select distinct a._id, a.nume, a.tip, a.idProfesor, a.pondere, p.nume, p.prenume, a.descriere, a.nrMaxStudenti"
                + " from activitatedidactica a, profesor p where p._id = a.idProfesor and a.nume != ALL" +
                "(select a1.nume from activitatedidactica a1, student_activitatedidactica sa1 where a1._id = sa1.idActivitate and idStudent = ?)";

        public static final String VIEW_ALL_CURSURI = "select * from activitatedidactica";

        /**
         * public static final String  VIEW_CURSURI_INSCRIS = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + " from activitatedidactica a , student_activitatedidactica sa, profesor p where a._id = sa.idActivitate and sa.idStudent = ? and p._id = a.idProfesor;";
         * <p>
         * public static final String  VIEW_CURSURI_NEINSCRIS = "select distinct a._id, a.nume, a.tip, a._data, a.pondere,  p.nume, p.prenume "
         * + " from activitatedidactica a , student_activitatedidactica sa, profesor p where a._id = sa.idActivitate and sa.idStudent <> ? and p._id = a.idProfesor;";
         * <p>
         * public static final String  VIEW_ALL_CURSURI = "select distinct a._id, a.nume, a.tip, a._data, a.pondere,  p.nume, p.prenume from activitatedidactica a, profesor p where p._id = a.idProfesor;";
         * <p>
         * <p>
         * <p>
         * public static final String  VIEW_CURRENT_HOUR_PROFESSOR_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + " from activitatedidactica a , profesor p where a.idProfesor = p._id and a.idProfesor = ?"
         * + " and year(_data) = year(current_date()) and month(_data) = month(current_date()) and day(_data) = day(current_date()) and hour(_data) = hour(current_time());";
         * <p>
         * public static final String  VIEW_CURRENT_DAY_PROFESSOR_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere,  p.nume, p.prenume e"
         * + " from activitatedidactica a , profesor p where a.idProfesor = p._id and a.idProfesor = ?"
         * + " and year(_data) = year(current_date()) and month(_data) = month(current_date()) and day(_data) = day(current_date());";
         * <p>
         * public static final  String  VIEW_FUTURE_PROFESSOR_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + "from activitatedidactica a , profesor p where a.idProfesor = p._id and a.idProfesor = ?"
         * + " and datediff(_data, current_timestamp()) > 0;";
         * <p>
         * public static final String  VIEW_CURRENT_HOUR_STUDENT_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + " from activitatedidactica a, student_activitatedidactica sa, profesor p where a.idProfesor = p._id and sa.idStudent = ?"
         * + "  and sa.idActivitate = a._id and date(_data) = current_date() and hour(_data) = hour(current_time());";
         * <p>
         * public static final String  VIEW_CURRENT_DAY_STUDENT_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + " from activitatedidactica a, student_activitatedidactica, sa profesor p where a.idProfesor = p._id and sa.idStudent = ?"
         * + "  and sa.idActivitate = a._id and date(_data) = current_date();";
         * <p>
         * public static final  String  VIEW_FUTURE_STUDENT_ACTIVITY = "select distinct a._id, a.nume, a.tip, a._data, a.pondere, p.nume, p.prenume "
         * + " from activitatedidactica a, student_activitatedidactica sa, profesor p where a.idProfesor = p._id and sa.idStudent = ?"
         * + "  and sa.idActivitate = a._id and datediff(_data, current_timestamp()) > 0;";
         */


        public static final String QUERY_STUDENT_FOR_MATERI = "SELECT " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_NUME + " FROM " + TABLE_STUDENT +
                " INNER JOIN " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + " ON " + TABLE_STUDENT + "." + COLUMN_ID + " = " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_STUDENT +
                " INNER JOIN " + TABLE_ACTIVITATE_DIDACTICA + " ON " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_ACTIVITATE + " = " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID +
                " WHERE " + TABLE_STUDENT + "." + COLUMN_ID + " = ? AND " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_TIP + " = 1";

        public static final String INSERT_STUDENTI_GRUPURI = "INSERT INTO " + TABLE_LISTA_MEMBRI + "( " + COLUMN_ID_STUDENT + ", " + COLUMN_ID_GRUP + " )" + " VALUES ( ?,? )";

        public static final String QUERY_MEMBRI_GRUP = "SELECT " + TABLE_STUDENT + "." + COLUMN_NUME + " FROM " + TABLE_LISTA_MEMBRI +
                " INNER JOIN " + TABLE_GRUPURI + " ON " + TABLE_LISTA_MEMBRI + "." + COLUMN_ID_GRUP + " = " + TABLE_GRUPURI + "." + COLUMN_ID +
                " INNER JOIN " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + " ON " + TABLE_GRUPURI + "." + COLUMN_ID_MATERIE + " = " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_ACTIVITATE +
                " INNER JOIN " + TABLE_STUDENT + " ON " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_STUDENT + " = " + TABLE_STUDENT + "." + COLUMN_ID +
                " WHERE " + COLUMN_ID_GRUP + " = ? " + " AND " + TABLE_LISTA_MEMBRI + "." + COLUMN_ID_STUDENT + " = " + TABLE_STUDENT + "." + COLUMN_ID;

        public static final String QUERY_MEMBRU_GRUP_BY_ID = "SELECT * FROM " + TABLE_LISTA_MEMBRI +
                " WHERE " + COLUMN_ID_GRUP + " = ? AND " + COLUMN_ID_STUDENT + " = ?";//implementata pentru inserare membri , nu are metoda separata

        public static final String INSERT_GRUPURI = "INSERT INTO " + TABLE_GRUPURI + " ( " + COLUMN_ID_MATERIE + " ) VALUES ( ? )";

        public static final String QUERY_GRUP_BY_MATERIE = "SELECT " + TABLE_GRUPURI + "." + COLUMN_ID + ", " + TABLE_GRUPURI + "." + COLUMN_ID_MATERIE  + " FROM " + TABLE_GRUPURI +
                " WHERE " + TABLE_GRUPURI + "." + COLUMN_ID_MATERIE + " = ? "; //// nume + profesor

        public static final String QUERY_CHAT_BY_GRUP = "SELECT " + TABLE_MESAJE + "." + COLUMN_MESAJ + ", " + TABLE_MESAJE + "." + COLUMN_ID_STUDENT + " FROM " + TABLE_MESAJE +
                " INNER JOIN " + TABLE_CHAT + " ON " + TABLE_MESAJE + "." + COLUMN_ID_CHAT + " = " + TABLE_CHAT + "." + COLUMN_ID +
                " WHERE " + COLUMN_ID_CHAT + " = ?";

        public static final String INSER_MESAJ = "INSERT INTO " + TABLE_MESAJE + "( " + COLUMN_MESAJ + ", " + COLUMN_ID_STUDENT + ", " + COLUMN_ID_CHAT + " ) VALUES ( ?,?,?)";

        public static final String INSERT_CHAT = "INSERT INTO " + TABLE_CHAT + "( " + COLUMN_ID_GRUP + " ) VALUES (?)";//IMPLEMENTAT FARA METODA SPEARATA , SE FOLOSESTE CAND SE ADAUGA GRUP



        public static final String QUERY_IDACTIVITY_BY_NAME_TYPE_AND_IDPROF = "SELECT   " + COLUMN_ID
                + " FROM " + TABLE_ACTIVITATE_DIDACTICA + " WHERE " + COLUMN_NUME + " = ? AND " + COLUMN_TIP + " = ? AND " + COLUMN_ID_PROFESOR + " = ?";

        public static final String QUERY_ACTIVITY_BY_IDPROF = "SELECT * FROM " + TABLE_ACTIVITATE_DIDACTICA + " WHERE " + COLUMN_ID_PROFESOR + "= ?";

        public static final String QUERY_STUDENT_BY_ALL_NAME = "SELECT * FROM " + TABLE_STUDENT + " WHERE " + COLUMN_NUME + " = ? AND " + COLUMN_PRENUME + " = ?";

        public static final String QUERY_PROFESOR_BY_ALL_NAME = "SELECT * FROM " + TABLE_PROFESOR + " WHERE " + COLUMN_NUME + " = ? AND " + COLUMN_PRENUME + " = ?";

        public static final String QUERY_ADMINISTRATOR_BY_ALL_NAME = "SELECT * FROM " + TABLE_ADMINISTRATOR + " WHERE " + COLUMN_NUME + " = ? AND " + COLUMN_PRENUME + " = ?";

        public static final String QUERY_ACTIVITY_BY_NAME_AND_TYPE_ASCENDING_BY_NRSTUDENT= "SELECT " + TABLE_PROFESOR_ACTIVITATEDIDACTICA + "." + COLUMN_ID_ACTIVITATE + ", " + TABLE_PROFESOR_ACTIVITATEDIDACTICA + "." + COLUMN_NRSTUDENT
                + " FROM " + TABLE_PROFESOR_ACTIVITATEDIDACTICA
                + " INNER JOIN " + TABLE_ACTIVITATE_DIDACTICA
                + " ON " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID + " = " + TABLE_PROFESOR_ACTIVITATEDIDACTICA + "." + COLUMN_ID_ACTIVITATE
                + " WHERE " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_NUME + " = ? AND " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_TIP + " = ?"
                + " ORDER BY " + COLUMN_NRSTUDENT + " ASC";

        public static final String QUERY_VALIDITY_DATE_ACTIVITY = "SELECT * FROM " + TABLE_STUDENT_ACTIVITATE_DIDACTICA
                + " INNER JOIN "+ TABLE_ACTIVITATE_DIDACTICA
                + " ON " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_ACTIVITATE + " = " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID
                + " INNER JOIN " + TABLE_CALENDAR + " ON " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID + " = " + TABLE_CALENDAR + "." + COLUMN_ID_ACTIVITATE
                + " WHERE " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_STUDENT + " = ? AND " + TABLE_CALENDAR + "." + COLUMN_ID_ACTIVITATE + " <> ? AND "
                + TABLE_CALENDAR + "." + COLUMN_DATE + " = ANY(SELECT " + TABLE_CALENDAR + "." + COLUMN_DATE + " FROM " + TABLE_CALENDAR
                + " WHERE " + TABLE_CALENDAR + "." + COLUMN_ID_ACTIVITATE + " = ?)";

        public static final String INSERT_ACTIVITATE = "INSERT INTO " + TABLE_ACTIVITATE + " ( " + COLUMN_NUME + ", " + COLUMN_NR_MIN_PARTICIPANTI + ", " + COLUMN_TERMEN_LIMITA
                + ", " + COLUMN_ID_GRUP +  ", _" + COLUMN_DATA +" ) VALUES (?,?,?,?,?)";

        public static final String QUERY_ACTIVITATI_BY_GRUP = "SELECT * FROM " + TABLE_ACTIVITATE + " INNER JOIN " + TABLE_GRUPURI + " ON " +
                TABLE_ACTIVITATE + "." + COLUMN_ID_GRUP + " = " + TABLE_GRUPURI + "." + COLUMN_ID + " WHERE " + TABLE_GRUPURI + "." + COLUMN_ID + " = ?";

        public static final String INSERT_PARTICIPANTI_ACTIVITATE = "INSERT INTO " + TABLE_PARTICIPANTI + "( " + COLUMN_ID_STUDENT + ", " + COLUMN_ID_ACTIVITATE + " ) VALUES (?,?)";

        public static final String QUERY_PARTICIPANTI = "SELECT * FROM " + TABLE_PARTICIPANTI + " WHERE " + COLUMN_ID_STUDENT + " = ? AND " + COLUMN_ID_ACTIVITATE + " = ?";

        public static final String QUERY_MATERI_BY_NUME = "SELECT * FROM " + TABLE_ACTIVITATE_DIDACTICA + " WHERE " + COLUMN_NUME + " LIKE " + " ? ";

        public static final String QUERY_MATERI_BY_PROFESOR = "SELECT * FROM " + TABLE_ACTIVITATE_DIDACTICA +
                " INNER JOIN " + TABLE_PROFESOR_ACTIVITATEDIDACTICA + " ON "+ TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID + " = " + TABLE_PROFESOR_ACTIVITATEDIDACTICA + "." + COLUMN_ID_ACTIVITATE +
                " INNER JOIN " + TABLE_PROFESOR + " ON "+ TABLE_PROFESOR_ACTIVITATEDIDACTICA + "." + COLUMN_ID_PROFESOR + " = " + TABLE_PROFESOR + "." + COLUMN_ID +
                " WHERE " + TABLE_PROFESOR + "." + COLUMN_NUME + " LIKE ? ";

        public static final String QUERY_MATERI_BY_STUDENT = "SELECT * FROM " + TABLE_ACTIVITATE_DIDACTICA +
                " INNER JOIN " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + " ON "+ TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID + " = " + TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_ACTIVITATE +
                " INNER JOIN " + TABLE_STUDENT + " ON "+ TABLE_STUDENT_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_STUDENT + " = " + TABLE_STUDENT + "." + COLUMN_ID +
                " WHERE " + TABLE_STUDENT + "." + COLUMN_NUME + " LIKE ? ";


        public static final String QUERY_MATERI_BY_DATE = "SELECT " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID + ", " +TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_NUME +
                ", " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID_PROFESOR + ", " + TABLE_CALENDAR + "." + COLUMN_DATE  + " FROM " + TABLE_CALENDAR +
                " INNER JOIN " + TABLE_ACTIVITATE_DIDACTICA + " ON " + TABLE_CALENDAR + "." + COLUMN_ID_ACTIVITATE + " = " + TABLE_ACTIVITATE_DIDACTICA + "." + COLUMN_ID +
                " WHERE " + TABLE_CALENDAR + "." + COLUMN_DATE + " LIKE ?";

        public static final String QUERY_GRUPURI_BY_STUDENT = " SELECT " + TABLE_LISTA_MEMBRI + "." + COLUMN_ID_GRUP + ", " + TABLE_GRUPURI + "." + COLUMN_ID_MATERIE +
                " FROM " + TABLE_LISTA_MEMBRI + " INNER JOIN " + TABLE_GRUPURI +
                " ON " + TABLE_LISTA_MEMBRI + "." + COLUMN_ID_GRUP + " = " + TABLE_GRUPURI + "." + COLUMN_ID +
                " WHERE " + COLUMN_ID_STUDENT + " = ?";


        public static final String QUERY_ACTIVITATI_FOR_STUDENTI = " SELECT " + TABLE_ACTIVITATE + "." + COLUMN_ID + ", " + TABLE_ACTIVITATE + "." + COLUMN_NUME + ", " +
                TABLE_ACTIVITATE + "." + COLUMN_NR_MIN_PARTICIPANTI + ", " + TABLE_ACTIVITATE + "." + COLUMN_TERMEN_LIMITA + " FROM " + TABLE_ACTIVITATE +
                " INNER JOIN " + TABLE_PARTICIPANTI + " ON " + TABLE_ACTIVITATE + "." + COLUMN_ID + " = " + TABLE_PARTICIPANTI + "." + COLUMN_ID_ACTIVITATE +
                " WHERE " + TABLE_PARTICIPANTI + "." + COLUMN_ID_STUDENT + " = ?";

        public static final String QUERY_ID_STUDENT_BY_CNP = "SELECT MIN(" + COLUMN_ID + ") FROM " + TABLE_STUDENT + " WHERE " + COLUMN_CNP + " = ?";

        public static final String QUERY_GROUP_ACTIVITIES_BY_STUDENT_ID = "SELECT * FROM " + TABLE_ACTIVITATE
                + " INNER JOIN "  +  TABLE_PARTICIPANTI
                + " ON " + TABLE_PARTICIPANTI + "." + COLUMN_ID_ACTIVITATE + " = " + TABLE_ACTIVITATE + "." + COLUMN_ID
                + " WHERE " + TABLE_PARTICIPANTI + "." + COLUMN_ID_STUDENT + " = ?";

        public static final String QUERY_NON_GROUP_ACTIVITIES_BY_STUDENT_ID = "SELECT * FROM " + TABLE_ACTIVITATE
                + " INNER JOIN "  +  TABLE_PARTICIPANTI
                + " ON " + TABLE_PARTICIPANTI + "." + COLUMN_ID_ACTIVITATE + " = " + TABLE_ACTIVITATE + "." + COLUMN_ID
                + " WHERE " + TABLE_PARTICIPANTI + "." + COLUMN_ID_STUDENT + " <> ?";

        public static final String QUERY_STUDENT = "SELECT * FROM " + TABLE_STUDENT;

        public static final String QUERY_PROFESOR = "SELECT * FROM " + TABLE_PROFESOR;

        public static final String QUERY_AMINISTRATOR = "SELECT * FROM " + TABLE_ADMINISTRATOR;

        public static final String QUERY_NOTE_BY_PROFESOR =  "SELECT * FROM " + TABLE_CATALOG + " WHERE " + TABLE_CATALOG + "." + COLUMN_ID_PROFESOR_ACT + " = ?";

        //INSERT


        public static final String QUERY_FIRST_DATE_FOR_THIS_ACTIVITY = "SELECT MIN(" + COLUMN_DATE + ") FROM " + TABLE_CALENDAR
                + " WHERE " + COLUMN_DATE + " >=  current_date() and "+ COLUMN_ID_ACTIVITATE + "  = ?"; //PRIMA DATA CARE URMEAZA PT O ACTIVITATE

        //DELETE

        public static final String DELETE_PARTICIPANTS_IN_THE_ACTIVITY_WITH_ID = "DELETE FROM " + TABLE_PARTICIPANTI + " WHERE " + COLUMN_ID_ACTIVITATE + " = ?"; //IMPLEMENTAT FARA METODA SPEARATA , SE FOLOSESTE CAND SE STERGE ACTIVITATE

        public static final String DELETE_GROUP_ACTIVITY = "DELETE FROM " + TABLE_ACTIVITATE + " WHERE " + COLUMN_ID + " = ?";

        private static final String DELETE_ADMIN_FROM_ADMINISTRATOR = "DELETE FROM " + TABLE_ADMINISTRATOR + " WHERE " + COLUMN_ID + " = ?";

        /**
         * SELECT activitate._id,activitate.nume,activitate.nrMinParticipanti,activitate.termenLimita FROM activitate
         * inner join participanti ON activitate._id = participanti.idActivitate
         * WHERE participanti.idStudent = 1;
         *
         */
        public static final String DELETE_PROFESOR = "DELETE FROM " + TABLE_PROFESOR + " WHERE " + COLUMN_ID + " = ?";

        public static final String QUERY_STUDENTI_BY_MATERIE= " SELECT " + COLUMN_ID_STUDENT + " FROM " + TABLE_STUDENT_ACTIVITATE_DIDACTICA +
                " WHERE " + COLUMN_ID_ACTIVITATE + " = ?";

        //Querys end:

        private PreparedStatement queryConturi;

        private PreparedStatement queryNoteProf;

        private PreparedStatement queryAdministratorByID;

        private PreparedStatement queryStudentByID;

        private PreparedStatement queryProfesorByID;

        private CallableStatement adaugaAactivitate;

        private CallableStatement schimbaPpondere;

        private CallableStatement adaugaNnota;

        private CallableStatement schimbareNnota;

        private CallableStatement signInStudentProcedure;

        private CallableStatement signInProfesorProcedure;

        private CallableStatement signInAdministratorProcedure;

        private PreparedStatement viewListaStudentiProfesor;

        private PreparedStatement queryCatalogByStudent;

        private PreparedStatement queryStudentForName;

        private PreparedStatement queryActivitateForName;

        private CallableStatement inscriereLaActivitate;

        private PreparedStatement insertActivitate;

        private PreparedStatement queryActiivitateByGrup;

        private PreparedStatement insertParticipantiActivitate;

        private PreparedStatement queryParticipanti;

        private PreparedStatement vizualizareCursuriInscris;

        private PreparedStatement vizualizareCursuriNeinscris;

        private PreparedStatement vizualizareCursuri;


        /**
         * private PreparedStatement vizualizareCursuriInscris;
         * <p>
         * private PreparedStatement vizualizareCursuriNeinscris;
         * <p>
         * private PreparedStatement vizualizareCursuri;
         * <p>
         * <p>
         * <p>
         * private PreparedStatement viewCurrentHourProfessorActivity;
         * <p>
         * private PreparedStatement viewCurrentDayProfessorActivity;
         * <p>
         * private PreparedStatement viewFutureProfessorActivity;
         * <p>
         * private PreparedStatement viewCurrentHourStudentActivity;
         * <p>
         * private PreparedStatement viewCurrentDayStudentActivity;
         * <p>
         * private PreparedStatement viewFutureStudentActivity;
         */

        private PreparedStatement queryStudentForMateri;

        private PreparedStatement insertListaGrup;

        private PreparedStatement queryMembriGrup;

        private PreparedStatement queryMembriGrupById;

        private PreparedStatement insertGrup;

        private PreparedStatement insertChat;

        private PreparedStatement queryGrupuriByMaterie;

        private PreparedStatement queryChatByGrup;

        private PreparedStatement insertMesaj;

        private PreparedStatement queryIdActivityByNameAndTypeAndIdProf;

        private PreparedStatement queryActivityByIdProf;

        private CallableStatement giveUpActivity;

        private PreparedStatement queryStudentByAllName;

        private PreparedStatement queryProfesorByAllName;

        private PreparedStatement queryAdministratorByAllName;

        private PreparedStatement queryActivityByNameAndTypeAscendingByNrStudent;

        private PreparedStatement queryValidityDateActivity;

        private PreparedStatement queryMateriByNume;

        private PreparedStatement queryMateriByProfesor;

        private PreparedStatement queryMateriByStudent;

        private PreparedStatement queryMateriByDate;

        private PreparedStatement queryGrupuriByStudent;

        private PreparedStatement queryActivitatiByStudent;

        private PreparedStatement queryIDStudentByCNP;

        private PreparedStatement queryGruupActivitiesByIdStudent;

        private PreparedStatement queryGruupNonActivitiesByIdStudent;

        private PreparedStatement deleteParticipansInTheActivityWithId;

        private PreparedStatement deleteGroupActivity;

        private PreparedStatement queryFirstDateForThisActivity;

        private PreparedStatement deleteProfesor;

        private PreparedStatement queryStudentByMaterie;

        private CallableStatement deleteCont;

        private CallableStatement deleteStudentFromStudent;

        private PreparedStatement deleteAdminFromAdministrator;

        private PreparedStatement queryAdministrator;

        private PreparedStatement queryStudent;

        private PreparedStatement queryProfesor;


        private Connection conn;


        public boolean open() {
            try {
                conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
                queryConturi = conn.prepareStatement(QUERY_CONTURI);
                queryAdministratorByID = conn.prepareStatement(QUERY_AMINISTRATOR_BY_ID);
                queryStudentByID = conn.prepareStatement(QUERY_STUDENT_BY_ID);
                queryProfesorByID = conn.prepareStatement(QUERY_PROFESOR_BY_ID);
                adaugaAactivitate = conn.prepareCall(ADAUGA_ACTIVITATE);
                schimbaPpondere = conn.prepareCall(SCHIMBA_PONDERE);
                adaugaNnota = conn.prepareCall(ADAUGA_NOTA);
                schimbareNnota = conn.prepareCall(SCHIMBARE_NOTA);
                viewListaStudentiProfesor = conn.prepareStatement(VIEW_LISTA_STUDENTI_PROFESOR);
                signInAdministratorProcedure = conn.prepareCall(SIGN_IN_ADMINISTRATOR);
                signInProfesorProcedure = conn.prepareCall(SIGN_IN_PROFESOR);
                signInStudentProcedure = conn.prepareCall(SIGN_IN_STUDENT);
                queryCatalogByStudent = conn.prepareStatement(QUERY_CATALOG_BY_STUDENT);
                queryStudentForName = conn.prepareStatement(QUERY_STUDENT_FOR_NAME);
                queryActivitateForName = conn.prepareStatement(QUERY_ACTIVITATE_FOR_NAME);
                inscriereLaActivitate = conn.prepareCall(INSCRIERE_LA_O_ACTIVITATE); vizualizareCursuriInscris = conn.prepareStatement(VIEW_CURSURI_INSCRIS);
                vizualizareCursuriNeinscris = conn.prepareStatement(VIEW_CURSURI_NEINSCRIS);
                vizualizareCursuri = conn.prepareStatement(VIEW_ALL_CURSURI);

                /** vizualizareCursuriInscris =conn.prepareStatement(VIEW_CURSURI_INSCRIS);
                 vizualizareCursuriNeinscris =conn.prepareStatement(VIEW_CURSURI_NEINSCRIS);
                 vizualizareCursuri = conn.prepareStatement(VIEW_ALL_CURSURI);

                 viewCurrentHourProfessorActivity = conn.prepareStatement(VIEW_CURRENT_HOUR_PROFESSOR_ACTIVITY);
                 viewCurrentDayProfessorActivity = conn.prepareStatement(VIEW_CURRENT_DAY_PROFESSOR_ACTIVITY);
                 viewFutureProfessorActivity = conn.prepareStatement(VIEW_FUTURE_PROFESSOR_ACTIVITY);
                 viewCurrentHourStudentActivity = conn.prepareStatement(VIEW_CURRENT_HOUR_STUDENT_ACTIVITY);
                 viewCurrentDayStudentActivity = conn.prepareStatement(VIEW_CURRENT_DAY_STUDENT_ACTIVITY);
                 viewFutureStudentActivity = conn.prepareStatement(VIEW_FUTURE_STUDENT_ACTIVITY);*/
                queryStudentForMateri = conn.prepareStatement(QUERY_STUDENT_FOR_MATERI);
                insertListaGrup = conn.prepareStatement(INSERT_STUDENTI_GRUPURI, Statement.RETURN_GENERATED_KEYS);
                queryMembriGrup = conn.prepareStatement(QUERY_MEMBRI_GRUP);
                insertGrup = conn.prepareStatement(INSERT_GRUPURI, Statement.RETURN_GENERATED_KEYS);
                insertChat = conn.prepareStatement(INSERT_CHAT, Statement.RETURN_GENERATED_KEYS);
                queryGrupuriByMaterie = conn.prepareStatement(QUERY_GRUP_BY_MATERIE);
                queryChatByGrup = conn.prepareStatement(QUERY_CHAT_BY_GRUP);
                insertMesaj = conn.prepareStatement(INSER_MESAJ);
                queryMembriGrupById = conn.prepareStatement(QUERY_MEMBRU_GRUP_BY_ID);
                queryIdActivityByNameAndTypeAndIdProf = conn.prepareStatement(QUERY_IDACTIVITY_BY_NAME_TYPE_AND_IDPROF);
                queryActivityByIdProf = conn.prepareStatement(QUERY_ACTIVITY_BY_IDPROF);
                giveUpActivity = conn.prepareCall(GIVE_UP_THE_ACTIVITY);
                queryStudentByAllName = conn.prepareStatement(QUERY_STUDENT_BY_ALL_NAME);
                queryProfesorByAllName = conn.prepareStatement(QUERY_PROFESOR_BY_ALL_NAME);
                queryAdministratorByAllName = conn.prepareStatement(QUERY_ADMINISTRATOR_BY_ALL_NAME);
                queryActivityByNameAndTypeAscendingByNrStudent = conn.prepareStatement(QUERY_ACTIVITY_BY_NAME_AND_TYPE_ASCENDING_BY_NRSTUDENT);
                queryValidityDateActivity = conn.prepareStatement(QUERY_VALIDITY_DATE_ACTIVITY);
                insertActivitate = conn.prepareStatement(INSERT_ACTIVITATE,Statement.RETURN_GENERATED_KEYS);
                queryActiivitateByGrup = conn.prepareStatement(QUERY_ACTIVITATI_BY_GRUP);
                insertParticipantiActivitate = conn.prepareStatement(INSERT_PARTICIPANTI_ACTIVITATE);
                queryParticipanti = conn.prepareStatement(QUERY_PARTICIPANTI);
                queryMateriByNume = conn.prepareStatement(QUERY_MATERI_BY_NUME);
                queryMateriByProfesor = conn.prepareStatement(QUERY_MATERI_BY_PROFESOR);
                queryMateriByStudent = conn.prepareStatement(QUERY_MATERI_BY_STUDENT);
                queryMateriByDate = conn.prepareStatement(QUERY_MATERI_BY_DATE);
                queryGrupuriByStudent = conn.prepareStatement(QUERY_GRUPURI_BY_STUDENT);
                queryActivitatiByStudent = conn.prepareStatement(QUERY_ACTIVITATI_FOR_STUDENTI);
                queryIDStudentByCNP = conn.prepareStatement(QUERY_ID_STUDENT_BY_CNP);
                queryGruupActivitiesByIdStudent = conn.prepareStatement(QUERY_GROUP_ACTIVITIES_BY_STUDENT_ID);
                queryGruupNonActivitiesByIdStudent = conn.prepareStatement(QUERY_NON_GROUP_ACTIVITIES_BY_STUDENT_ID);
                deleteParticipansInTheActivityWithId = conn.prepareStatement(DELETE_PARTICIPANTS_IN_THE_ACTIVITY_WITH_ID);
                deleteGroupActivity = conn.prepareStatement(DELETE_GROUP_ACTIVITY);
                queryFirstDateForThisActivity = conn.prepareStatement(QUERY_FIRST_DATE_FOR_THIS_ACTIVITY);
                deleteCont = conn.prepareCall(DELETE_CONT);
                deleteStudentFromStudent = conn.prepareCall(DELETE_STUDENT_FROM_STUDENT);
                deleteAdminFromAdministrator = conn.prepareStatement(DELETE_ADMIN_FROM_ADMINISTRATOR);
                deleteProfesor = conn.prepareStatement(DELETE_PROFESOR);
                queryStudentByMaterie = conn.prepareStatement(QUERY_STUDENTI_BY_MATERIE);deleteCont = conn.prepareCall(DELETE_CONT);
                queryAdministrator = conn.prepareStatement(QUERY_AMINISTRATOR);
                queryStudent = conn.prepareStatement(QUERY_STUDENT);
                queryProfesor = conn.prepareStatement(QUERY_PROFESOR);
                queryNoteProf = conn.prepareStatement(QUERY_NOTE_BY_PROFESOR);

                return true;
            } catch (SQLException e) {
                System.out.println("Couldn't connect to data base " + e.getMessage());
                return false;
            }
        }

        public void close() {
            try {
                if (queryNoteProf != null) {
                    queryNoteProf.close();
                }
                if (queryProfesor != null) {
                    queryProfesor.close();
                }
                if (queryStudent != null)
                    queryStudent.close();
                if (queryAdministrator != null)
                    queryAdministrator.close();
                if(deleteAdminFromAdministrator != null){
                    deleteAdminFromAdministrator.close();
                }
                if(deleteStudentFromStudent != null){
                    deleteStudentFromStudent.close();
                }
                if(deleteCont != null){
                    deleteCont.close();
                }

                if(queryStudentByMaterie != null){
                    queryStudentByMaterie.close();
                }
                if(deleteProfesor != null){
                    deleteProfesor.close();
                }
                if(queryFirstDateForThisActivity != null){
                    queryFirstDateForThisActivity.close();
                }
                if(deleteGroupActivity != null){
                    deleteGroupActivity.close();
                }
                if(deleteParticipansInTheActivityWithId != null){
                    deleteParticipansInTheActivityWithId.close();
                }
                if(queryGruupNonActivitiesByIdStudent != null){
                    queryGruupNonActivitiesByIdStudent.close();
                }
                if(queryGruupActivitiesByIdStudent != null){
                    queryGruupActivitiesByIdStudent.close();
                }
                if (queryIDStudentByCNP != null) {
                    queryIDStudentByCNP.close();
                }
                if(queryActivitatiByStudent != null){
                    queryActivitatiByStudent.close();
                }

                if(queryGrupuriByStudent != null){
                    queryGrupuriByStudent.close();
                }

                if(queryMateriByDate != null){
                    queryMateriByDate.close();
                }

                if(queryMateriByStudent != null ){
                    queryMateriByStudent.close();
                }
                if(queryMateriByProfesor != null ){
                    queryMateriByProfesor.close();
                }
                if(queryMateriByNume != null ){
                    queryMateriByNume.close();
                }
                if(queryParticipanti != null ){
                    queryParticipanti.close();
                }

                if(insertParticipantiActivitate != null ){
                    insertParticipantiActivitate.close();
                }
                if(queryActiivitateByGrup != null ){
                    queryActiivitateByGrup.close();
                }
                if(insertActivitate != null ){
                    insertActivitate.close();
                }

                if(queryValidityDateActivity != null){
                    queryValidityDateActivity.close();
                }
                if(queryActivityByNameAndTypeAscendingByNrStudent != null){
                    queryActivityByNameAndTypeAscendingByNrStudent.close();
                }
                if (queryAdministratorByAllName != null) {
                    queryAdministratorByAllName.close();
                }
                if (queryProfesorByAllName != null) {
                    queryProfesorByAllName.close();
                }
                if (queryStudentByAllName != null) {
                    queryStudentByAllName.close();
                }
                if (giveUpActivity != null) {
                    giveUpActivity.close();
                }
                if (queryActivityByIdProf != null) {
                    queryActivityByIdProf.close();
                }
                if (queryIdActivityByNameAndTypeAndIdProf != null) {
                    queryIdActivityByNameAndTypeAndIdProf.close();
                }
                if (queryMembriGrupById != null) {
                    queryMembriGrupById.close();
                }
                if (insertMesaj != null) {
                    insertMesaj.close();
                }

                if (queryChatByGrup != null) {
                    queryChatByGrup.close();
                }
                if (queryGrupuriByMaterie != null) {
                    queryGrupuriByMaterie.close();
                }

                if (insertChat != null) {
                    insertChat.close();
                }
                if (insertGrup != null) {
                    insertGrup.close();
                }
                if (queryMembriGrup != null) {
                    queryMembriGrup.close();
                }
                if (insertListaGrup != null) {
                    insertListaGrup.close();
                }

                if (queryStudentForMateri != null) {
                    queryStudentForMateri.close();
                }

                /** if(viewFutureStudentActivity != null){
                 viewFutureStudentActivity.close();
                 }
                 if(viewCurrentDayStudentActivity != null){
                 viewCurrentDayStudentActivity.close();
                 }
                 if(viewCurrentHourStudentActivity != null){
                 viewCurrentHourStudentActivity.close();
                 }
                 if(viewFutureProfessorActivity != null){
                 viewFutureProfessorActivity.close();
                 }
                 if(viewCurrentDayProfessorActivity != null){
                 viewCurrentDayProfessorActivity.close();
                 }
                 if(viewCurrentHourProfessorActivity != null){
                 viewCurrentHourProfessorActivity.close();
                 }*/
                if(vizualizareCursuri != null){
                    vizualizareCursuri.close();
                }
                if(vizualizareCursuriNeinscris != null){
                    vizualizareCursuriNeinscris.close();
                }
                if(vizualizareCursuriInscris != null) {
                    vizualizareCursuriInscris.close();
                }
                if(inscriereLaActivitate != null) {
                    inscriereLaActivitate.close();
                }
                if (queryActivitateForName != null) {
                    queryActivitateForName.close();
                }

                if (queryCatalogByStudent != null) {
                    queryCatalogByStudent.close();
                }
                if (signInStudentProcedure != null) {
                    signInStudentProcedure.close();
                }
                if (signInProfesorProcedure != null) {
                    signInProfesorProcedure.close();
                }
                if (signInAdministratorProcedure != null) {
                    signInAdministratorProcedure.close();
                }
                if (viewListaStudentiProfesor != null) {
                    viewListaStudentiProfesor.close();
                }
                if (schimbareNnota != null) {
                    schimbareNnota.close();
                }
                if (adaugaNnota != null) {
                    adaugaNnota.close();
                }
                if (schimbaPpondere != null) {
                    schimbaPpondere.close();
                }
                if (adaugaAactivitate != null) {
                    adaugaAactivitate.close();
                }
                if (queryProfesorByID != null) {
                    queryProfesorByID.close();
                }
                if (queryStudentByID != null)
                    queryStudentByID.close();
                if (queryAdministratorByID != null)
                    queryAdministratorByID.close();
                if (queryConturi != null)
                    queryConturi.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Couldn't close connection:" + e.getMessage());
            }
        }

        public Cont logIn(String username, String password) {
            try {
                queryConturi.setString(1, username);
                queryConturi.setString(2, password);

                ResultSet result = queryConturi.executeQuery();

                if (result.next()) {

                    Cont cont = new Cont();

                    cont.set_id(result.getInt(1));
                    cont.setID((result.getString(2)));
                    cont.setPassword(result.getString(3));
                    cont.setRestriction(result.getInt(4));
                    cont.setIdUtilizator(result.getInt(5));

                    return cont;
                }

                return null;// contul nu a fost gasit

            } catch (SQLException e) {
                System.out.println("Query logIn failed: " + e.getMessage());
                return null;
            }

        }

        //BY ID
        public String getQueryStudentForName(int idStudent) {
            try {
                queryStudentForName.setInt(1, idStudent);
                ResultSet result = queryStudentForName.executeQuery();

                if (result.next())
                    return result.getString(1);

                return null;

            } catch (SQLException e) {
                System.out.println("Query Student for name failed: " + e.getMessage());
                return null;
            }
        }

        //BY ID
        public String getQueryActivitateForName(int id) {
            try {
                queryActivitateForName.setInt(1, id);
                ResultSet result = queryActivitateForName.executeQuery();

                if (result.next())
                    return result.getString(1);

                return null;

            } catch (SQLException e) {
                System.out.println("Query Activitate for name failed: " + e.getMessage());
                return null;
            }
        }

        public Student queryStudentByID(int ID) {
            try {
                queryStudentByID.setInt(1, ID);

                ResultSet result = queryStudentByID.executeQuery();

                if (result.next()) {
                    Student student = new Student();

                    student.setCNP(result.getString(2));
                    student.setNume(result.getString(3));
                    student.setPrenume(result.getString(4));
                    student.setAdresa(result.getString(5));
                    student.setNr_telefon(result.getString(6));
                    student.setEmail(result.getString(7));
                    student.setIBAN(result.getString(8));
                    student.setNrContract(result.getInt(9));
                    student.setAnStudiu(result.getString(10));
                    student.setNrOre(result.getInt(11));

                    return student;
                }

                return null;

            } catch (SQLException e) {
                System.out.println("Query Student failed: " + e.getMessage());
                return null;
            }
        }

        public Profesor queryProfesorByID(int ID) {
            try {
                queryProfesorByID.setInt(1, ID);

                ResultSet result = queryProfesorByID.executeQuery();

                if (result.next()) {
                    Profesor profesor = new Profesor();

                    profesor.setCNP(result.getString(2));
                    profesor.setNume(result.getString(3));
                    profesor.setPrenume(result.getString(4));
                    profesor.setAdresa(result.getString(5));
                    profesor.setNr_telefon(result.getString(6));
                    profesor.setEmail(result.getString(7));
                    profesor.setIBAN(result.getString(8));
                    profesor.setNrContract(result.getInt(9));
                    profesor.setNrMinOre(result.getInt(10));
                    profesor.setNrMaxOre(result.getInt(11));
                    profesor.setDepartament(result.getString(12));

                    return profesor;
                }

                return null;

            } catch (SQLException e) {
                System.out.println("Query Profesor failed: " + e.getMessage());
                return null;
            }
        }
        public boolean deleteProfesor(int id){
            try{
                deleteProfesor.setInt(1,id);
                deleteProfesor.executeUpdate();
                return true;
            }catch (SQLException e){
                System.out.println("Delete Profesor failed : " + e.getMessage());
                return false;
            }
        }

        public Administrator queryAdministratorByID(int ID) {
            try {
                queryAdministratorByID.setInt(1, ID);

                ResultSet result = queryAdministratorByID.executeQuery();

                if (result.next()) {
                    Administrator administrator = new Administrator();

                    administrator.setCNP(result.getString(2));
                    administrator.setNume(result.getString(3));
                    administrator.setPrenume(result.getString(4));
                    administrator.setAdresa(result.getString(5));
                    administrator.setNr_telefon(result.getString(6));
                    administrator.setEmail(result.getString(7));
                    administrator.setIBAN(result.getString(8));
                    administrator.setNrContract(result.getInt(9));

                    return administrator;
                }

                return null;

            } catch (SQLException e) {
                System.out.println("Query Administrator failed: " + e.getMessage());
                return null;
            }
        }

        public List<String> queryStudentForMateri(int id) {
            try {
                queryStudentForMateri.setInt(1, id);

                ResultSet result = queryStudentForMateri.executeQuery();

                List<String> materi = new ArrayList<>();

                while (result.next()) {
                    String materie = new String();
                    materie = result.getString(1);
                    materi.add(materie);
                }

                return materi;

            } catch (SQLException e) {
                System.out.println("Query student for materi failed : " + e.getMessage());
                return null;
            }
        }

        public List<Integer> getStudentiByMaterie ( int idMaterie){
            try{
                queryStudentByMaterie.setInt(1, idMaterie);
                ResultSet result = queryStudentByMaterie.executeQuery();
                List<Integer> studenti = new ArrayList<>();
                while (result.next()){
                    int idStudent = result.getInt(1);

                    studenti.add(idStudent);
                }
                return  studenti;

            }catch(SQLException e){
                System.out.println("Query studenti By materie failed " + e.getMessage());
                return  null;
            }
        }

        public List<String> queryListaGrup(int idGrup) {
            try {
                queryMembriGrup.setInt(1, idGrup);

                ResultSet result = queryMembriGrup.executeQuery();

                List<String> membri = new ArrayList<>();

                while (result.next()) {
                    String membru = new String();
                    membru = result.getString(1);
                    membri.add(membru);
                }

                return membri;

            } catch (SQLException e) {
                System.out.println("Query lista grup failed : " + e.getMessage());
                return null;
            }
        }

        public int insertGrup(int idMaterie) {
            try {
                conn.setAutoCommit(false);

                insertGrup.setInt(1, idMaterie);
                int affectedrow1 = insertGrup.executeUpdate();
                if (affectedrow1 == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("Couldn t insert  grup  !");
                }
                ResultSet result = insertGrup.getGeneratedKeys();
                if (result.next()) {
                    int idGrup = result.getInt(1);
                    insertChat.setInt(1, idGrup);
                    int affectedrow2 = insertChat.executeUpdate();
                    if (affectedrow2 == 1) {
                        conn.commit();
                    } else {
                        throw new SQLException("Couldn t insert  chat  !");
                    }
                    return idGrup;
                }
                return 0;

            } catch (SQLException e) {
                System.out.println("Insert grup failed : " + e.getMessage());
                try {
                    System.out.println("Preforming rollback");
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println("Oh boy ! Things really are bad ! " + e2.getMessage());
                }
            } finally {
                try {
                    System.out.println("Resetting default coming behavior");
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.out.println("Couldn't reset auto-commit! " + e.getMessage());
                }

            }
            return -1;

        }

        public Grup queryGrupByIdMaterie(int idMaterie) {
            try {
                queryGrupuriByMaterie.setInt(1, idMaterie);
                ResultSet result = queryGrupuriByMaterie.executeQuery();

                if (result.next())
                    return new Grup(result.getInt(1), result.getInt(2));
                return null;

            } catch (SQLException e) {
                System.out.println("Query  grup dupa materie failed : " + e.getMessage());
                return null;
            }

        }

        public List<Grup> getGrupuriForStudent(int idStudent){
            try{
                queryGrupuriByStudent.setInt(1,idStudent);
                ResultSet result = queryGrupuriByStudent.executeQuery();

                List<Grup> grupuri = new ArrayList<>();
                while(result.next()){

                    Grup grup = new Grup();
                    grup.setId(result.getInt(1));
                    grup.setIdMaterie(result.getInt(2));

                    grupuri.add(grup);
                }
                return grupuri;

            }catch (SQLException e) {
                System.out.println("Query  grup dupa materie failed : " + e.getMessage());
                return null;
            }
        }

        public boolean insertListaMembriGrup(int idStudent, int idGrup) {
            try {
                queryMembriGrupById.setInt(1, idGrup);
                queryMembriGrupById.setInt(2, idStudent);
                ResultSet result = queryMembriGrupById.executeQuery();
                if (result.next()) {
                    System.out.println("Already added");
                    return false;

                } else {
                    insertListaGrup.setInt(1, idStudent);
                    insertListaGrup.setInt(2, idGrup);
                    int affectedrow = insertListaGrup.executeUpdate();

                    if (affectedrow != 1) {
                        throw new SQLException("Couldn t insert Student in grup list !");
                    }
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Insert lista membri failed :" + e.getMessage());
                return false;
            }
        }

        public List<ActivitateGrup> getActivitatiForStudent(int idStudent){
            try{
                queryActivitatiByStudent.setInt(1,idStudent);
                ResultSet result = queryActivitatiByStudent.executeQuery();

                List<ActivitateGrup> activitati = new ArrayList<>();
                while(result.next()){
                    ActivitateGrup activitateGrup = new ActivitateGrup();
                    activitateGrup.set_id(result.getInt(1));
                    activitateGrup .setName(result.getString(2));
                    activitateGrup.setMinPart(result.getInt(3));
                    activitateGrup.setTermenLimita(result.getDate(4));

                    activitati.add(activitateGrup);
                }
                return activitati;
            }catch (SQLException e) {
                System.out.println("Query activitati for student failed :" + e.getMessage());
                return null;
            }

        }

        public boolean postMesaj(String mesaj, int idStudent, int idChat) {
            try {
                insertMesaj.setString(1, mesaj);
                insertMesaj.setInt(2, idStudent);
                insertMesaj.setInt(3, idChat);

                insertMesaj.executeUpdate();

                return true;


            } catch (SQLException e) {
                System.out.println("Post mesaje failed :" + e.getMessage());
                return false;
            }
        }

        public List<Mesaj> queryMesaje(int idGrup) {
            try {
                queryChatByGrup.setInt(1, idGrup);
                ResultSet resultSet = queryChatByGrup.executeQuery();
                List<Mesaj> mesaje = new ArrayList<>();
                while (resultSet.next()) {
                    Mesaj mesaj = new Mesaj();
                    mesaj.setMess(resultSet.getString(1));
                    mesaj.setIdStudent(resultSet.getInt(2));
                    mesaje.add(mesaj);
                }
                return mesaje;
            } catch (SQLException e) {
                System.out.println("Query mesaje failed :" + e.getMessage());
                return null;
            }
        }

        public boolean insertActivitate(String nume , int nrMinParticipanti , Date termenLimita, int idGrup, int idStudent , Date data){

            try{
                insertActivitate.setString(1,nume);
                insertActivitate.setInt(2,nrMinParticipanti);
                insertActivitate.setDate(3,termenLimita);
                insertActivitate.setInt(4,idGrup);
                insertActivitate.setDate(5 , data);

                int affectedRow = insertActivitate.executeUpdate();

                if(affectedRow != 1)
                    throw new SQLException("Coudln t insert");
                else{
                    ResultSet result = insertActivitate.getGeneratedKeys();
                    if(result.next()){
                        int idActivitate = result.getInt(1);
                        inscriereActivitate(idStudent,idActivitate);
                    }

                }
                return true;

            }catch (SQLException e){
                System.out.println("Insert Activitate  failed :" + e.getMessage());
                return false;
            }
        }

        public List<ActivitateGrup> getActivitiesForGrup(int idGrup){
            try{
                queryActiivitateByGrup.setInt(1,idGrup);
                ResultSet result = queryActiivitateByGrup.executeQuery();
                List<ActivitateGrup> activitati = new ArrayList<>();
                while(result.next()){
                    ActivitateGrup activitate = new ActivitateGrup();
                    activitate.set_id(result.getInt(1));
                    activitate.setName(result.getString(2));
                    activitate.setMinPart(result.getInt(3));
                    activitate.setTermenLimita(result.getDate(4));

                    if(activitate != null)
                        activitati.add(activitate);
                }
                return activitati;

            }catch (SQLException e){
                System.out.println("Query Activitate  failed :" + e.getMessage());
                return null;
            }
        }

        public boolean inscriereActivitate(int idStudent, int idActivitate){
            try{
                queryParticipanti.setInt(1,idStudent);
                queryParticipanti.setInt(2,idActivitate);

                ResultSet result = queryParticipanti.executeQuery();
                if(result.next()){
                    System.out.println("Already added");
                    return false;
                }else {
                    insertParticipantiActivitate.setInt(1, idStudent);
                    insertParticipantiActivitate.setInt(2, idActivitate);

                    insertParticipantiActivitate.executeUpdate();
                }
                return true;
            }catch (SQLException e){
                System.out.println("Inscriere participant  failed :" + e.getMessage());
                return false;
            }
        }

        public List<Materie> getMateriByName(String name){
            try{
                name =  name.concat("%");
                queryMateriByNume.setString(1,name);
                ResultSet  result = queryMateriByNume.executeQuery();
                List<Materie> materii = new ArrayList<>();
                while(result.next()){
                    Materie materie = new Materie();

                    materie.setNume(result.getString(2));
                    materie.setIdProf(result.getInt(3));
                    materie.setTip(result.getInt(4));
                    materie.setPondere(result.getInt(5));

                    materii.add(materie);
                }
                return materii;
            }catch (SQLException e){
                System.out.println("Query Materi   failed :" + e.getMessage());
                return null ;
            }
        }

        public List<Materie> getMateriByProfesor(String name){
            try{
                name =  name.concat("%");
                queryMateriByProfesor.setString(1,name);
                ResultSet  result = queryMateriByProfesor.executeQuery();
                List<Materie> materii = new ArrayList<>();
                while(result.next()){
                    Materie materie = new Materie();

                    materie.setNume(result.getString(2));
                    materie.setIdProf(result.getInt(3));
                    materie.setTip(result.getInt(4));
                    materie.setPondere(result.getInt(5));

                    materii.add(materie);
                }
                return materii;
            }catch (SQLException e){
                System.out.println("Query Materi   failed :" + e.getMessage());
                return null ;
            }
        }

        public List<Materie> getMateriForStudent(String name){
            try{
                name =  name.concat("%");
                queryMateriByStudent.setString(1,name);
                ResultSet  result = queryMateriByStudent.executeQuery();
                List<Materie> materii = new ArrayList<>();
                while(result.next()){
                    Materie materie = new Materie();

                    materie.setNume(result.getString(2));
                    materie.setIdProf(result.getInt(3));
                    materie.setTip(result.getInt(4));
                    materie.setPondere(result.getInt(5));

                    materii.add(materie);
                }
                return materii;
            }catch (SQLException e){
                System.out.println("Query Materi   failed :" + e.getMessage());
                return null ;
            }
        }

        public List<Materie> getMateriByDate (String date){
            try{
                date = date.concat("%");
                queryMateriByDate.setString(1,date);
                ResultSet result = queryMateriByDate.executeQuery();
                List<Materie> materii = new ArrayList<>();
                while(result.next()){
                    Materie materie = new Materie();

                    materie.setId(result.getInt(1));
                    materie.setNume(result.getString(2));
                    materie.setIdProf(result.getInt(3));
                    materie.setDate(result.getDate(4));

                    materii.add(materie);
                }
                return materii;
            }catch (SQLException e){
                System.out.println("Query Materi   failed :" + e.getMessage());
                return null ;
            }
        }

        public int queryIdActivityByNameAndTypeAndIdProf(String nameActivity, int typeActivity, int idProf) {
            try {
                queryIdActivityByNameAndTypeAndIdProf.setNString(1, nameActivity);
                queryIdActivityByNameAndTypeAndIdProf.setInt(2, typeActivity);
                queryIdActivityByNameAndTypeAndIdProf.setInt(3, idProf);
                ResultSet resultSet = queryIdActivityByNameAndTypeAndIdProf.executeQuery();
                if (resultSet.next())
                    return resultSet.getInt("_id");
                return 0;
            } catch (SQLException e) {
                System.out.println("Query id activity by avtivity name, actiyy type and id professor failed :" + e.getMessage());
                return -1;
            }
        }

        public void adaugaNota(int idProfesor, int idStudent, int idActivitate, int nota) {
            try {


                adaugaNnota.setInt(1, idProfesor);
                adaugaNnota.setInt(2, idStudent);
                adaugaNnota.setInt(3, idActivitate);
                adaugaNnota.setInt(4, nota);

                ResultSet rs = adaugaNnota.executeQuery();
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
        }

        public void schimbareNota(int idProfesor, int idStudent, int idActiv, int nota) {
            try {


                schimbareNnota.setInt(1, idProfesor);
                schimbareNnota.setInt(2, idStudent);
                schimbareNnota.setInt(3, idActiv);
                schimbareNnota.setInt(4, nota);
                ResultSet rs = schimbareNnota.executeQuery();
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
        }

        public void adaugaActivitate(String numeActivitate, int idProfesor, int tip, int pondere, String descriere, int nrMaxStudenti, String dateStart, String dateEnd) {
            try {

                adaugaAactivitate.setNString(1, numeActivitate);
                adaugaAactivitate.setInt(2, idProfesor);
                adaugaAactivitate.setInt(3, tip);
                adaugaAactivitate.setInt(4, pondere);
                adaugaAactivitate.setNString(5, descriere);
                adaugaAactivitate.setInt(6, nrMaxStudenti);
                adaugaAactivitate.setNString(7, dateStart);
                adaugaAactivitate.setNString(8, dateEnd);
                ResultSet rs = adaugaAactivitate.executeQuery();
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }

        }

        public void schimbaPonere(int idActivitte, int pondere) {
            try {

                schimbaPpondere.setInt(1, idActivitte);
                schimbaPpondere.setInt(2, pondere);
                ResultSet rs = schimbaPpondere.executeQuery();
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }

        }

        public List<Activity> queryActivityByIdProf(int idProf) {
            List<Activity> activities = new ArrayList<>();
            try {
                queryActivityByIdProf.setInt(1, idProf);
                ResultSet resultSet = queryActivityByIdProf.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idProfesor");
                    queryProfesorByID.setInt(1, id);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    String profesor = "";
                    if (resultSet1.next())
                        profesor = resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("nume"), resultSet.getInt("tip"), profesor, resultSet.getInt("pondere"), resultSet.getString("descriere"), resultSet.getInt("nrMaxStudenti")));
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return activities;
        }

        public void giveUpActivity(String nameActivity, int idStudent) {
            try {

                giveUpActivity.setNString(1, nameActivity);
                giveUpActivity.setInt(2, idStudent);
                ResultSet rs = giveUpActivity.executeQuery();
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
        }

        public Catalog queryNoteStudenti(int id) {
            try {
                queryCatalogByStudent.setInt(1, id);
                ResultSet result = queryCatalogByStudent.executeQuery();

                String nume = getQueryStudentForName(id);

                Catalog catalog = new Catalog();
                while (result.next()) {
                    Nota nota = new Nota();
                    nota.setProfesor(result.getString(1));
                    nota.setStudent(nume);
                    nota.setMaterie(getQueryActivitateForName(result.getInt(3)));
                    nota.setGrade(result.getInt(4));
                    nota.setDate(result.getDate(5));
                    nota.setIdActivity(result.getInt(3));
                    catalog.addNota(nota);
                }
                return catalog;
            } catch (SQLException e) {
                System.out.println("Query Note Studenti failed : " + e.getMessage());
                return null;
            }

        }

        public List<Utilizator> queryUserByNameAndType(String userFirstName, String userLastName, String userType) {
            try {
                ResultSet result = null;
                if (userType.equals("student")) {
                    queryStudentByAllName.setNString(1, userFirstName);
                    queryStudentByAllName.setNString(2, userLastName);
                    result = queryStudentByAllName.executeQuery();

                    List<Utilizator> utilizatori = new ArrayList<>();
                    while (result.next()) {
                        Student student = new Student();

                        student.setCNP(result.getString(2));
                        student.setNume(result.getString(3));
                        student.setPrenume(result.getString(4));
                        student.setAdresa(result.getString(5));
                        student.setNr_telefon(result.getString(6));
                        student.setEmail(result.getString(7));
                        student.setIBAN(result.getString(8));
                        student.setNrContract(result.getInt(9));
                        student.setAnStudiu(result.getString(10));
                        student.setNrOre(result.getInt(11));

                        utilizatori.add(student);
                    }
                    return utilizatori;
                } else if (userType.equals("profesor")) {
                    queryProfesorByAllName.setNString(1, userFirstName);
                    queryProfesorByAllName.setNString(2, userLastName);
                    result = queryProfesorByAllName.executeQuery();

                    List<Utilizator> utilizatori = new ArrayList<>();
                    while (result.next()) {
                        Profesor profesor = new Profesor();

                        profesor.setCNP(result.getString(2));
                        profesor.setNume(result.getString(3));
                        profesor.setPrenume(result.getString(4));
                        profesor.setAdresa(result.getString(5));
                        profesor.setNr_telefon(result.getString(6));
                        profesor.setEmail(result.getString(7));
                        profesor.setIBAN(result.getString(8));
                        profesor.setNrContract(result.getInt(9));
                        profesor.setNrMinOre(result.getInt(10));
                        profesor.setNrMaxOre(result.getInt(11));
                        profesor.setDepartament(result.getString(12));

                        utilizatori.add(profesor);
                    }
                    return utilizatori;
                } else if (userType.equals("administrator")) {
                    queryAdministratorByAllName.setNString(1, userFirstName);
                    queryAdministratorByAllName.setNString(2, userLastName);
                    result = queryAdministratorByAllName.executeQuery();

                    List<Utilizator> utilizatori = new ArrayList<>();
                    while (result.next()) {
                        Administrator administrator = new Administrator();

                        administrator.setCNP(result.getString(2));
                        administrator.setNume(result.getString(3));
                        administrator.setPrenume(result.getString(4));
                        administrator.setAdresa(result.getString(5));
                        administrator.setNr_telefon(result.getString(6));
                        administrator.setEmail(result.getString(7));
                        administrator.setIBAN(result.getString(8));
                        administrator.setNrContract(result.getInt(9));

                        utilizatori.add(administrator);

                    }
                    return utilizatori;
                } else return null;

            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return null;
            }
        }

        public boolean registrationForTheActivity(int idStudent, String materie, int tip){
            try {
                queryActivityByNameAndTypeAscendingByNrStudent.setNString(1, materie);
                queryActivityByNameAndTypeAscendingByNrStudent.setInt(2, tip);
                ResultSet result = queryActivityByNameAndTypeAscendingByNrStudent.executeQuery();

                List<RegisterActivity> registerActivities = new ArrayList<>();
                while (result.next()){

                    RegisterActivity registerActivity = new RegisterActivity();

                    registerActivity.setIdActivitate(result.getInt(1));
                    registerActivity.setNrStudent(result.getInt(2));

                    registerActivities.add(registerActivity);
                }

                for(RegisterActivity registerActivity : registerActivities){
                    queryValidityDateActivity.setInt(1, idStudent);
                    queryValidityDateActivity.setInt(2, registerActivity.getIdActivitate());
                    queryValidityDateActivity.setInt(3, registerActivity.getIdActivitate());
                    ResultSet result1 = queryValidityDateActivity.executeQuery();

                    if(!result1.next()) {
                        inscriereLaActivitate.setInt(1, idStudent);
                        inscriereLaActivitate.setInt(2, registerActivity.getIdActivitate());
                        inscriereLaActivitate.setInt(3, registerActivity.getNrStudent() + 1);
                        int result2 = inscriereLaActivitate.executeUpdate();

                        if(result2 == 0)
                            return false;
                        return true;
                    }
                }

            } catch (SQLException e) {
                System.out.println("Query registration for the activity failed : " + e.getMessage());
                return false;
            }

            return false;
        }

        public List<Student> vizualizareListeStudentiPentruUnProfesorDat(int idProf) {
            List<Student> students = new ArrayList<>();
            try {
                viewListaStudentiProfesor.setInt(1, idProf);
                ResultSet resultSet = viewListaStudentiProfesor.executeQuery();
                while (resultSet.next()) {
                    students.add(new Student(resultSet.getString("CNP"), resultSet.getString("nume"), resultSet.getString("prenume"), resultSet.getString("adresa"), resultSet.getString("nr_telefon"), resultSet.getString("email"), resultSet.getString("IBAN"), resultSet.getInt("nr_Contract"), resultSet.getString("anStudiu"), resultSet.getInt("nrOre")));
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }

            return students;
        }

        public boolean signInStudentProcedure(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, String parola) {
            try {

                signInStudentProcedure.setNString(1, CNP);
                signInStudentProcedure.setNString(2, nume);
                signInStudentProcedure.setNString(3, prenume);
                signInStudentProcedure.setNString(4, adresa);
                signInStudentProcedure.setNString(5, nr_telefon);
                signInStudentProcedure.setNString(6, email);
                signInStudentProcedure.setNString(7, IBAN);
                signInStudentProcedure.setNString(8, parola);
                int rs = signInStudentProcedure.executeUpdate();

                if(rs != 0) return true;

            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return false;
            }
            return false;
        }

        public boolean signInProfesorProcedure(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrMinOre, int nrMaxOre, String departament, String parola) {
            try {

                signInProfesorProcedure.setNString(1, CNP);
                signInProfesorProcedure.setNString(2, nume);
                signInProfesorProcedure.setNString(3, prenume);
                signInProfesorProcedure.setNString(4, adresa);
                signInProfesorProcedure.setNString(5, nr_telefon);
                signInProfesorProcedure.setNString(6, email);
                signInProfesorProcedure.setNString(7, IBAN);
                signInProfesorProcedure.setInt(8, nrMinOre);
                signInProfesorProcedure.setInt(9, nrMaxOre);
                signInProfesorProcedure.setNString(10, departament);
                signInProfesorProcedure.setNString(11, parola);
                int rs = signInProfesorProcedure.executeUpdate();

                if(rs != 0) return true;
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return false;
            }
            return false;
        }

        public boolean signInAdministratorProcedure(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, String parola) {
            try {

                signInAdministratorProcedure.setNString(1, CNP);
                signInAdministratorProcedure.setNString(2, nume);
                signInAdministratorProcedure.setNString(3, prenume);
                signInAdministratorProcedure.setNString(4, adresa);
                signInAdministratorProcedure.setNString(5, nr_telefon);
                signInAdministratorProcedure.setNString(6, email);
                signInAdministratorProcedure.setNString(7, IBAN);
                signInAdministratorProcedure.setNString(8, parola);
                int rs = signInAdministratorProcedure.executeUpdate();

                if(rs != 0) return true;
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return false;
            }
            return false;
        }

        public int queryIDStudentByCNP(String CNP) {
            try {
                queryIDStudentByCNP.setNString(1, CNP);
                ResultSet resultSet = queryIDStudentByCNP.executeQuery();
                resultSet.next();
                return resultSet.getInt(1);
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return -1;
            }
        }

        public List<Activity> vizualizareCursuriInscris(int idStud) {
            List<Activity> activities = new ArrayList<>();
            try {

                vizualizareCursuriInscris.setInt(1, idStud);
                ResultSet resultSet = vizualizareCursuriInscris.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idProfesor");
                    queryProfesorByID.setInt(1, id);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    String profesor = "";
                    if (resultSet1.next())
                        profesor = resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("nume"), resultSet.getInt("tip"), profesor, resultSet.getInt("pondere"), resultSet.getString("descriere"), resultSet.getInt("nrMaxStudenti")));
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }

            return activities;
        }

        public List<Activity> vizualizareCursuriNeinscris(int idStud) {
            List<Activity> activities = new ArrayList<>();
            try {

                vizualizareCursuriNeinscris.setInt(1, idStud);
                ResultSet resultSet = vizualizareCursuriNeinscris.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idProfesor");
                    queryProfesorByID.setInt(1, id);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    String profesor = "";
                    if (resultSet1.next())
                        profesor = resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("nume"), resultSet.getInt("tip"), profesor, resultSet.getInt("pondere"), resultSet.getString("descriere"), resultSet.getInt("nrMaxStudenti")));
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return activities;
        }

        public List<Activity> vizualizareCursuri() {
            List<Activity> activities = new ArrayList<>();
            try {
                ResultSet resultSet = vizualizareCursuri.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idProfesor");
                    queryProfesorByID.setInt(1, id);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    String profesor = "";
                    if (resultSet1.next())
                        profesor = resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("nume"), resultSet.getInt("tip"), profesor, resultSet.getInt("pondere"), resultSet.getString("descriere"), resultSet.getInt("nrMaxStudenti")));
                }
            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return activities;
        }

        public List<ActivitateGrup> queryGroupActivitiesByStudentId(int idStud){
            List<ActivitateGrup> activitateGrups = new ArrayList<>();
            try{
                queryGruupActivitiesByIdStudent.setInt(1, idStud);
                ResultSet resultSet = queryGruupActivitiesByIdStudent.executeQuery();
                while(resultSet.next()){
                    ActivitateGrup activitate = new ActivitateGrup();

                    activitate.set_id(resultSet.getInt(1));
                    activitate.setName(resultSet.getString(2));
                    activitate.setMinPart(resultSet.getInt(3));
                    activitate.setTermenLimita(resultSet.getDate(4));
                    activitate.setData(resultSet.getDate(7));
                    activitate.setIdGrup(resultSet.getInt(5));
                    int idProf = resultSet.getInt(6);
                    String prof = null;
                    queryProfesorByID.setInt(1, idProf);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    if(resultSet1.next())
                        prof =  resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activitate.setProfesor(prof);

                    activitateGrups.add(activitate);
                }
            }catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return activitateGrups;
        }

        public List<ActivitateGrup> queryNonGroupActivitiesByStudentId(int idStud) {
            List<ActivitateGrup> activitateGrups = new ArrayList<>();
            try{
                queryGruupNonActivitiesByIdStudent.setInt(1, idStud);
                ResultSet resultSet = queryGruupNonActivitiesByIdStudent.executeQuery();
                while(resultSet.next()){
                    ActivitateGrup activitate = new ActivitateGrup();

                    activitate.set_id(resultSet.getInt(1));
                    activitate.setName(resultSet.getString(2));
                    activitate.setMinPart(resultSet.getInt(3));
                    activitate.setTermenLimita(resultSet.getDate(4));
                    activitate.setData(resultSet.getDate(7));
                    activitate.setIdGrup(resultSet.getInt(5));

                    int idProf = resultSet.getInt(6);
                    String prof = null;
                    queryProfesorByID.setInt(1, idProf);
                    ResultSet resultSet1 = queryProfesorByID.executeQuery();
                    if(resultSet1.next())
                        prof =  resultSet1.getString("nume") + " " + resultSet1.getString("prenume");
                    activitate.setProfesor(prof);

                    activitateGrups.add(activitate);
                }
            }catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return activitateGrups;
        }

        public boolean deleteGroupActivity(int idActivGroup){
            try {

                deleteParticipansInTheActivityWithId.setInt(1, idActivGroup);
                int rs = deleteParticipansInTheActivityWithId.executeUpdate();

                deleteGroupActivity.setInt(1, idActivGroup);
                rs = deleteGroupActivity.executeUpdate();
                if (rs == 0) return false;


                return true;
            }catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
                return false;
            }
        }

        public Timestamp queryFirstDateForThisActivity(int idActiv){
            try{
                queryFirstDateForThisActivity.setInt(1, idActiv);
                ResultSet resultSet = queryFirstDateForThisActivity.executeQuery();

                if(resultSet.next()) {
                    return resultSet.getTimestamp(1);
                }
            }catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
            return null;
        }

        public void deleteStudent(int idStud){
            try {
                //initial il sterg de la activitai -- aici il strege de peste tot ce deriva din student_activitatedidactica(catalog, grup, ...)
                List<Activity> activities = vizualizareCursuriInscris(idStud);
                for(Activity activity: activities)
                    giveUpActivity(activity.getNume(), idStud);

                //ii sterg contul
                deleteCont.setInt(1, idStud);
                deleteCont.setInt(2, 1);//studentul are tipul 1
                ResultSet resultSet = deleteCont.executeQuery();

                //il sterg din tabela student
                deleteStudentFromStudent.setInt(1, idStud);
                ResultSet resultSet1 = deleteStudentFromStudent.executeQuery();

            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
        }

        public void deleteAdmin(int idAdmin){
            try {
                //ii sterg contul
                deleteCont.setInt(1, idAdmin);
                deleteCont.setInt(2, 3);//administratorul are tipul 3
                ResultSet resultSet = deleteCont.executeQuery();

                //il sterg din tabela student
                deleteAdminFromAdministrator.setInt(1, idAdmin);
                int resultSet1 = deleteAdminFromAdministrator.executeUpdate();

            } catch (Exception e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
            }
        }

        public Catalog noteProf (int idProf){
            try {
                queryNoteProf.setInt(1 , idProf);
                ResultSet result = queryNoteProf.executeQuery();

                Catalog catalog = new Catalog();
                while (result.next()) {
                    String nume = getQueryStudentForName(result.getInt(3));

                    Nota nota = new Nota();
                    nota.setProfesor(result.getString(2));
                    nota.setStudent(nume);
                    nota.setMaterie(getQueryActivitateForName(result.getInt(4)));
                    nota.setGrade(result.getInt(5));
                    nota.setDate(result.getDate(6));
                    nota.setIdActivity(result.getInt(4));
                    catalog.addNota(nota);
                }
                return catalog;
            } catch (SQLException e) {
                System.out.println("Query Note Studenti failed : " + e.getMessage());
                return null;
            }
        }

        public List<Student> queryStudent() {
            List<Student> students = new ArrayList<>();
            try{
                ResultSet result = queryStudent.executeQuery();

                while (result.next()) {
                    Student student = new Student();

                    student.setCNP(result.getString(2));
                    student.setNume(result.getString(3));
                    student.setPrenume(result.getString(4));
                    student.setAdresa(result.getString(5));
                    student.setNr_telefon(result.getString(6));
                    student.setEmail(result.getString(7));
                    student.setIBAN(result.getString(8));
                    student.setNrContract(result.getInt(9));
                    student.setAnStudiu(result.getString(10));
                    student.setNrOre(result.getInt(11));

                    students.add(student);
                }
                return students;
            } catch (SQLException e) {
                System.out.println("Query Student failed: " + e.getMessage());
                return null;
            }

        }

        public List<Profesor> queryProfesor() {
            List<Profesor> profesors = new ArrayList<>();
            try {
                ResultSet result = queryProfesor.executeQuery();

                while (result.next()) {
                    Profesor profesor = new Profesor();

                    profesor.setCNP(result.getString(2));
                    profesor.setNume(result.getString(3));
                    profesor.setPrenume(result.getString(4));
                    profesor.setAdresa(result.getString(5));
                    profesor.setNr_telefon(result.getString(6));
                    profesor.setEmail(result.getString(7));
                    profesor.setIBAN(result.getString(8));
                    profesor.setNrContract(result.getInt(9));
                    profesor.setNrMinOre(result.getInt(10));
                    profesor.setNrMaxOre(result.getInt(11));
                    profesor.setDepartament(result.getString(12));

                    profesors.add(profesor);
                }
            } catch (SQLException e) {
                System.out.println("Query Profesor failed: " + e.getMessage());
            }
            return profesors;
        }

        public List<Administrator> queryAdministrator() {
            List<Administrator> administrators = new ArrayList<>();
            try {
                ResultSet result = queryAdministrator.executeQuery();

                while (result.next()) {
                    Administrator administrator = new Administrator();

                    administrator.setCNP(result.getString(2));
                    administrator.setNume(result.getString(3));
                    administrator.setPrenume(result.getString(4));
                    administrator.setAdresa(result.getString(5));
                    administrator.setNr_telefon(result.getString(6));
                    administrator.setEmail(result.getString(7));
                    administrator.setIBAN(result.getString(8));
                    administrator.setNrContract(result.getInt(9));

                    administrators.add(administrator);
                }
            } catch (SQLException e) {
                System.out.println("Query Administrator failed: " + e.getMessage());
            }
            return administrators;
        }

        // test..../**

        //de modificat data

        //netestate in java
/**
 public List<Activity> vizualizareCursuriInscris(int idStud){
 List<Activity> activities = new ArrayList<>();
 try {

 vizualizareCursuriInscris.setInt(1,idStud);
 ResultSet resultSet = vizualizareCursuriInscris.executeQuery();
 while (resultSet.next()) {
 System.out.println(resultSet.getString("a.nume") + " "+ resultSet.getString("tip") + " " + resultSet.getString("_data"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }

 return activities;
 }

 public List<Activity> vizualizareCursuriNeinscris(int idStud){
 List<Activity> activities = new ArrayList<>();
 try {

 vizualizareCursuriNeinscris.setInt(1,idStud);
 ResultSet resultSet = vizualizareCursuriNeinscris.executeQuery();
 while (resultSet.next()) {
 System.out.println(resultSet.getString("nume") + " "+ resultSet.getString("tip") + " " + resultSet.getString("_data"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }

 public List<Activity>vizualizareCursuri(){
 List<Activity> activities = new ArrayList<>();
 try {

 ResultSet resultSet = vizualizareCursuri.executeQuery();
 while (resultSet.next()) {
 System.out.println(resultSet.getString("nume") + " "+ resultSet.getString("tip") + " " + resultSet.getString("_data"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }



 public List<Activity> viewCurrentHourProfessorActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {
 viewCurrentHourProfessorActivity.setInt(1,idProf);
 ResultSet resultSet = viewCurrentHourProfessorActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }

 return activities;

 }

 public List<Activity> viewCurrentDayProfessorActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {
 viewCurrentDayProfessorActivity.setInt(1,idProf);
 ResultSet resultSet = viewCurrentDayProfessorActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }

 public List<Activity> viewFutureProfessorActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {

 viewFutureProfessorActivity.setInt(1, idProf);
 ResultSet resultSet = viewFutureProfessorActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }

 public List<Activity> viewCurrentHourStudentActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {
 viewCurrentHourStudentActivity.setInt(1,idProf);
 ResultSet resultSet = viewCurrentHourStudentActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("Prof_name") + "  " + resultSet.getString(" Prof_last_name"), resultSet.getInt("ponere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }

 public List<Activity> viewCurrentDayStudentActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {
 viewCurrentDayStudentActivity.setInt(1,idProf);
 ResultSet resultSet = viewCurrentDayStudentActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }

 public List<Activity> viewFutureStudentActivity(int idProf){
 List<Activity> activities = new ArrayList<>();
 try {

 viewFutureStudentActivity.setInt(1, idProf);
 ResultSet resultSet = viewFutureStudentActivity.executeQuery();
 while (resultSet.next()) {
 System.out.println("ID activitate:" + resultSet.getString("_id") + "     nume: "+ resultSet.getString("nume")+ "     ID profesor: "+ resultSet.getString("idProfesor")+ "       tip: " + resultSet.getString("tip")+ "     data: "+ resultSet.getString("_data")+ "     pondere: "+ resultSet.getString("pondere"));
 activities.add(new Activity(resultSet.getInt("_id"), resultSet.getString("a.nume"), resultSet.getString("tip"), resultSet.getString("_data"), resultSet.getString("p.nume") + "  " + resultSet.getString("p.prenume"), resultSet.getInt("pondere")));
 }
 } catch (Exception e) {
 System.out.println("SQLException: " + e.getMessage());
 System.out.println("SQLState: " + ((SQLException) e).getSQLState());
 System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
 }
 return activities;
 }
 */
        /**
         * Imprementare Catalog si dupa ...
         public void vizualizareCatalog(int idProf){
         try {

         PreparedStatement s= c.prepareStatement("select * from catalog where idProfesorAct = " + idProf+ ";");
         ResultSet resultSet = s.executeQuery();
         while (resultSet.next()) {
         System.out.println("ID Nota :" + resultSet.getString("_id") + "     ID profesor: "+ resultSet.getString("idProfesorAct")+ "     ID student: "+ resultSet.getString("idStudentAct")+ "       nota: " + resultSet.getString("nota")+ "     data: "+ resultSet.getString("data"));
         }
         } catch (Exception e) {
         System.out.println("SQLException: " + e.getMessage());
         System.out.println("SQLState: " + ((SQLException) e).getSQLState());
         System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
         }
         }


         */

    }