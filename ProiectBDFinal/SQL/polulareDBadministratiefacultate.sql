use administratiefacultate;

-- populare baza de date

-- populare tabela student
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1263650640303", "Avram", "Vasile","Zalau, str. Calea Clujului", "0722343767", "Avaram.Vasile@utcluj.ro", "RO69RNCB1971959919719599", '1', '2', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1433451243363", "Margin", "Ioan","Zalau, str. Simion Barnutiu", "0774611180", "Margin.Ioan@utcluj.ro", "RO69RNCB21386417113864171", '2', '1', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1592079592079", "Eminovici", "Cristian","Arad, str. Observatorului", "0785262130", "Eminovici.Cristian@utcluj.ro", "RO69RNCBRO69RNCB138641713864171", '3', '3', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1564384126561", "Rusu", "Ioan","Cluj-Napoca, str. Unirii", "0722343375", "Rusu.Ioan@utcluj.ro", "RO69RNCB3779554937795549", '4', '1', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1126561259275", "Pop", "Valentin","Cluj-Napoca, str. Simion Barnutiu", "0745890609", "Pop.Valentin@utcluj.ro", "RO69RNCB4998369349983693", '5', '2', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("1858318210436", "Bulgarean", "Andrei","Zalau, str. 21 Decembrie", "0764942898", "Bulgarean.Andrei@utcluj.ro", "RO69RNCB8235827382358273", '6', '1', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("2359730242894", "Pestean", "Camelia","Oradea, str. Primaverii", "0713343008", "Pestean.Camelia@utcluj.ro", "RO69RNCB8223113582231135", '7', '3', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("2127894799285", "Ardelean", "Denisa","Iasi, str. Ciresilor", "0713343008", "Ardelean.Denisa@utcluj.ro", "RO69RNCB6796563567965635", '8', '2', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("2940639318301", "Puscas", "Rodica","Craiova, str. Traian Vuia", "0721465009", "Puscas.Rodica@utcluj.ro", "RO69RNCB6865715768657157", '9', '1', '30');
insert into student(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, anStudiu, nrOre)
 values ("2946577864080", "Margin", "Alina","Bucuresti, str. Simion Barnutiu", "0725106422", "Margin.Alina@utcluj.ro", "RO69RNCB2510642225106422", '10', '2', '30');

-- populare tabela profesor
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("1291467040303", "Pojar", "Eduard","Craiova, str. Traian Vuia", "0722343767", "Pojar.Eduard@utcluj.ro", "RO69RNCB1971959919719599", '11', '4', '35', "Matematica");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract,nrMinOre, nrMaxOre, departament)
 values ("1731183228693", "Barnutiu", "Denis","Iasi, str. Ciresilor", "0722343767", "Barnutiu.Denis@utcluj.ro", "RO69RNCB1971959919719599", '12', '18', '39', "Calcuatoare");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("1263650640303", "Opris", "Nelu","Zalau, buevardul Mihai Viteazu", "0722343767", "Opris.Nelu@utcluj.ro", "RO69RNCB1971959919719599", '13', '15', '21', "Fizica");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("1960469142215", "Lingurar", "Ionut","Zalau, str. Calea Clujului", "0722343767", "Lingurar.Ionut@utcluj.ro", "RO69RNCB1971959919719599", '14', '20', '34', "Calcuatoare");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("1985849858411", "Jula", "Eusebiu","Arad, str. Observatorului", "0722343767", "Jula.Eusebiu@utcluj.ro", "RO69RNCB1971959919719599", '15', '3', '38', "Calcuatoare");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("1887307887307", "Ghiurco", "Vlad","Suceava, str. Calea Vinului", "0722343767", "Ghiurco.Vlad@utcluj.ro", "RO69RNCB1971959919719599", '16', '8', '35', "Limbi straine");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract, nrMinOre, nrMaxOre, departament)
 values ("2840774840774", "Barbanta", "Aexandra","Braila, str. Crinului", "0722343767", "Barbanta.Aexandra@utcluj.ro", "RO69RNCB1971959919719599", '17', '7', '32', "Matematica");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract,  nrMinOre, nrMaxOre, departament)
 values ("2697303573176", "Alexa", "Petruta","Constanta, str. Emil Cioran", "0722343767", "Alexa.Petruta@utcluj.ro", "RO69RNCB1971959919719599", '18', '2', '20', "Calcuatoare");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract,  nrMinOre, nrMaxOre, departament)
 values ("2263650640303", "Dunca", "Daniela","Zimbor, str. Principala", "0722343767", "Dunca.Daniela@utcluj.ro", "RO69RNCB1971959919719599", '19', '20', '39', "Calcuatoare");
insert into profesor(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract,nrMinOre, nrMaxOre, departament)
 values ("2640336758103", "Goia", "Ioana","Huedin str. Mesteacanului", "0722343767", "Goia.Ioana@utcluj.ro", "RO69RNCB1971959919719599", '20', '17', '29', "Fizica");
 
 
 -- popuare tabela administrator
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1265979760303", "Matioc", "Bogdan","Craiova, str. Traian Vlad", "0721773120", "Matioc.Bogdan@utcluj.ro", "RO69RNCB9090721959721959", '21');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1263650597976", "Tibrea", "Andrei","Arad, str. Traian Vuia", "0733742259", "Tibrea.Andrei@utcluj.ro", "RO69RNCB6156153536135361", '22');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1704905704905", "Pop", "Marius","Timisoara, str. Tineretului", "0711487804", "Pop.Marius@utcluj.ro", "RO69RNCB7303973039623623", '23');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1387132387132", "Sigoiu", "Razvan","Satu Marea, str. Odorheiului", "0782884551", "Sigoiu.Razvan@utcluj.ro", "RO69RNCB6239623963496349", '24');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1260303519601", "Madar", "George","Baia Mare, str. Traian Vuia", "0775825201", "Madar.Razvan@utcluj.ro", "RO69RNCB5661545661547979", '25');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1266990266990", "Patac", "Daniela","Carei, str. Ciucas", "0729633751", "Patac.Daniela@utcluj.ro", "RO69RNCB8924389243772772", '26');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1363603240324", "Stoica", "Ramona","Craiova, str. Calin Popescu", "0789613718", "Stoica.Ramona@utcluj.ro", "RO69RNCB2901429014022022", '27');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1884608846033", "Lobont", "Mara","Tasnad, str. Garii", "0791473511", "Lobont.Mara@utcluj.ro", "RO69RNCB4548863454886366", '28');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1499324499324", "Dunca", "Calin","Medias, str. Fabricii", "0755819495", "Dunca.Calin@utcluj.ro", "RO69RNCB5473547319841984", '29');
insert into administrator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nr_Contract)
 values ("1966978473972", "Pojar", "Alina","Sibiu, str. Libertatii", "0794839629", "Pojar.Alina@utcluj.ro", "RO69RNCB7483490474834904", '30');
 
 
 -- POPULARE TABELA activitatedidactica + profesor+activitateDidactica
 
call adauga_activitate("BD", 1, 3, 20, "", 30);
call adauga_activitate("BD", 1, 2, 40, "", 30);
call adauga_activitate("BD", 1, 1, 40, "", 30);
call adauga_activitate("POO", 6, 1, 50, "", 30);
call adauga_activitate("POO", 6, 2, 50, "", 30);
call adauga_activitate("AF", 7, 3, 20, "", 30);
call adauga_activitate("CAN", 3, 1, 70, "", 30);
call adauga_activitate("AM", 10, 1, 90, "", 30);
call adauga_activitate("PC", 5, 3, 10, "", 30);
call adauga_activitate("PC", 5, 2, 45, "", 30);


-- populare tabela student_activitatedidactica

call inscriereLaActivitate(1, 1);
call inscriereLaActivitate(2, 5);
call inscriereLaActivitate(2, 4);
call inscriereLaActivitate(1, 2);
call inscriereLaActivitate(6, 6);
call inscriereLaActivitate(1, 3);
call inscriereLaActivitate(7, 8);
call inscriereLaActivitate(4, 7);
call inscriereLaActivitate(5, 10);
call inscriereLaActivitate(5, 9);

-- populare tabela catalog
call adaugare_nota(1, 1, 1, 5);
call adaugare_nota(1, 1, 2, 7);
call adaugare_nota(1, 1, 3, 6);
call adaugare_nota(6, 2, 5, 10);
call adaugare_nota(6, 2, 4, 7);
call adaugare_nota(10, 7, 8, 5);