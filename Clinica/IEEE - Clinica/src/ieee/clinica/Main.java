package ieee.clinica;

import ieee.utils.Input;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static String menu = "____________________________________________\n"
            + "Possibili scelte:\n"
            + "\t01 Aggiungere un medico\n"
            + "\t02 Aggiungere un paziente\n"
            + "\t03 Aggiungere un appuntamento\n"
            + "\t04 Cambiare un orario di lavoro ad un medico\n"
            + "\t05 Visualizzare tutti i medici\n"
            + "\t06 Visualizzare gli orari di lavoro di un medico\n"
            + "\t07 Visualizzare gli appuntamenti di un medico\n"
            + "\t08 Visualizzare la lista dei pazienti nella clinica\n"
            + "\t09 Visualizzare la lista degli appuntamenti in un rangers\n"
            + "\t10 Informarsi sul numero di appuntamenti di un certo medico\n"
            + "\t11 Esci\n"
            + "Inserire la scelta: ";

    private static Clinica c;

    public static Dottore cercaDottore() {
        Dottore d = new Dottore("Default", 0, 0);
        String s;
        int n, bandieretta;
        System.out.println("Scegli il dottore per nome o per matricola (1 o 2)?");
        System.out.print("Scelta: ");
        bandieretta = Input.inputNumero();
        while (bandieretta != 1 && bandieretta != 2) {
            System.out.println("La scelta deve essere o 1 o 2");
            System.out.print("Scelta: ");
            bandieretta = Input.inputNumero();
        }
        switch (bandieretta) {
            case 1:
                int i;
                ArrayList<Dottore> ds = null;
                System.out.print("Inserisci il nome del Dottore: ");
                s = Input.inputRiga();
                ds = c.getDottorePerNome(s);
                while (ds == null) {
                    System.out.println("Il Dottore " + s + " non è tra i dottori di questa clinica.");
                    System.out.print("Inserisci il nome del Dottore: ");
                    s = Input.inputRiga();
                    ds = c.getDottorePerNome(s);
                }
                if (ds.size() > 1) {
                    System.out.print("Sono presenti più dottori con lo stesso nome: ");
                    for (i = 0; i < ds.size(); i++) {
                        System.out.println(i + ") " + ds.get(i).toString());
                    }
                    System.out.print("Inserire il numero del dottore che si vuole selezionare: ");
                    n = Input.inputNumero();
                    while (n < 0 || n >= ds.size()) {
                        System.out.println("Numero sbagliato, reinserire");
                        n = Input.inputNumero();
                    }
                    d = ds.get(n);
                }else
                    d=ds.get(0);
                break;
            case 2:
                System.out.print("Inserisci la matricola del Dottore: ");
                n = Input.inputNumero();
                while ((d = c.getDottorePerMatricola(n)) == null) {
                    System.out.println("Il Dottore " + n + " non è tra i dottori di questa clinica.");
                    System.out.print("Inserisci la matricola del Dottore: ");
                    n = Input.inputNumero();
                }
                break;
        }
        return d;
    }

    public static Paziente cercaPaziente() {
        int i;
        ArrayList<Paziente> ps;
        Paziente p = new Paziente("Default", 0);
        String s;
        int n, bandieretta;
        System.out.println("Scegli il paziente per nome o per ID (1 o 2)?");
        System.out.print("Scelta: ");
        bandieretta = Input.inputNumero();
        while (bandieretta != 1 && bandieretta != 2) {
            System.out.println("La scelta deve essere o 1 o 2");
            System.out.print("Scelta: ");
            bandieretta = Input.inputNumero();
        }
        switch (bandieretta) {
            case 1:
                System.out.print("Inserisci il nome del Paziente: ");
                s = Input.inputRiga();
                ps = c.getPazientePerNome(s);
                while (ps == null) {
                    System.out.println("Il Paziente " + s + " non è tra i pazienti di questa clinica.");
                    System.out.print("Inserisci il nome del Paziente: ");
                    s = Input.inputRiga();
                    ps = c.getPazientePerNome(s);
                }
                if (ps.size() > 1) {
                    System.out.print("Sono presenti più pazienti con lo stesso nome: ");
                    for (i = 0; i < ps.size(); i++) {
                        System.out.println(i + ") " + ps.get(i).toString());
                    }
                    System.out.print("Inserire il numero del paziente che si vuole selezionare: ");
                    n = Input.inputNumero();
                    while (n < 0 || n > ps.size()) {
                        n = Input.inputNumero();
                    }
                    p = ps.get(n);

                }else
                    p=ps.get(0);
                
                break;
            case 2:
                System.out.print("Inserisci l'ID del Paziente: ");
                n = Input.inputNumero();
                while ((p = c.getPazientePerID(n)) == null) {
                    System.out.println("Il Paziente con ID " + n + " non è tra i pazienti di questa clinica.");
                    System.out.print("Inserisci la matricola del Paziente corretta: ");
                    n = Input.inputNumero();
                }
                break;
        }
        return p;
    }

    public static void modificaGiornoFerie(Dottore d) {

        int g1, o1, o2;
        System.out.print("Inserire giorno\n1)Lunedì\n2)Martedì\n3)Mercoledi\n4)Giovedì\n5)Venerdì\n6)Sabato\n7)Domenica\nScelta:  ");
        g1 = Input.inputNumero();
        while (g1 < 1 || g1 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g1 = Input.inputNumero();
        }
        System.out.print("Inserire orario compreso tra 0 e 23 :");
        o1 = Input.inputNumero();
        while (o1 < 0 || o1 > 23) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 23 :");
            o1 = Input.inputNumero();
        }
        System.out.print("Inserire secondo orario compreso tra 0 e 24 :");
        o2 = Input.inputNumero();
        while (o2 < 0 || o2 > 24) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 24 :");
            o2 = Input.inputNumero();
        }
        d.getOrario().setOrarioDiFerie(g1-1, o1, o2);
    }

    public static void modificaGiornoLavoro(Dottore d) {

        int g1, o1, o2;
        System.out.print("Inserire giorno\n1)Lunedì\n2)Martedì\n3)Mercoledi\n4)Giovedì\n5)Venerdì\n6)Sabato\n7)Domenica\nScelta:  ");
        g1 = Input.inputNumero();
        while (g1 < 1 || g1 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g1 = Input.inputNumero();
        }
        System.out.print("Inserire orario compreso tra 0 e 23 :");
        o1 = Input.inputNumero();
        while (o1 < 0 || o1 > 23) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 23 :");
            o1 = Input.inputNumero();
        }
        System.out.print("Inserire secondo orario compreso tra 0 e 24 :");
        o2 = Input.inputNumero();
        while (o2 < 0 || o2 > 24) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 24 :");
            o2 = Input.inputNumero();
        }
        d.getOrario().setOrarioDiLavoro(g1-1, o1, o2);

    }

    public static void modificaSettimanaFerie(Dottore d) {

        int g1, g2, o1, o2;
        System.out.print("Inserire data d'inizio\n1)Lunedì\n2)MArtedì\n3)Mercoledi\n4)Giovedì\n5)Venerdì\n6)Sabato\n7)Domenica\nScelta:  ");
        g1 = Input.inputNumero();
        while (g1 < 1 || g1 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g1 = Input.inputNumero();
        }
        g2 = Input.inputNumero();
        while (g2 < 1 || g2 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g2 = Input.inputNumero();
        }
        System.out.print("Inserire orario compreso tra 0 e 23 :");
        o1 = Input.inputNumero();
        while (o1 < 0 || o1 > 23) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 23 :");
            o1 = Input.inputNumero();
        }
        System.out.print("Inserire secondo orario compreso tra 0 e 24 :");
        o2 = Input.inputNumero();
        while (o2 < 0 || o2 > 24) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 24 :");
            o2 = Input.inputNumero();
        }
        d.getOrario().setOrarioDiFeriePerGiorni(g1-1, g2-1, o1, o2);

    }

    public static void modificaSettimanaLavoro(Dottore d) {

        int g1, g2, o1, o2;
        System.out.print("Inserire data d'inizio\n1)Lunedì\n2)MArtedì\n3)Mercoledi\n4)Giovedì\n5)Venerdì\n6)Sabato\n7)Domenica\nScelta:  ");
        g1 = Input.inputNumero();
        while (g1 < 1 || g1 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g1 = Input.inputNumero();
        }
        g2 = Input.inputNumero();
        while (g2 < 1 || g2 > 7) {
            System.out.print("Inserimento errato. Inserire giorno: ");
            g2 = Input.inputNumero();
        }
        System.out.print("Inserire orario compreso tra 0 e 23 :");
        o1 = Input.inputNumero();
        while (o1 < 0 || o1 > 23) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 23 :");
            o1 = Input.inputNumero();
        }
        System.out.print("Inserire secondo orario compreso tra 0 e 24 :");
        o2 = Input.inputNumero();
        while (o2 < 0 || o2 > 24) {
            System.out.print("Errore nel orario ,inserire orario compreso tra 0 e 24 :");
            o2 = Input.inputNumero();
        }
        d.getOrario().setOrarioDiLavoroPerGiorni(g1-1, g2-1, o1, o2);

    }

    public static void main(String[] args) {        
        String s, s2;
        int n, a, u = 0;
        boolean bool;
        Dottore d;
        c = new Clinica();
//        ISTANZE DI TEST INIZIALI PER PROVARE QUELLO CHE VUOI
        Paziente ptest = new Paziente("Pingu",15);
        c.aggiungiPaziente(ptest);
        Dottore dtest = new Dottore("Cioffola", 19,1998);
        c.aggiungiDottore(dtest);
        Data datatest = new Data(2017,5,23,9);
        Data datatest2 = new Data(2015,5,22,9);
        c.aggiungiAppuntamento(ptest, dtest, datatest, 0);
        c.aggiungiAppuntamento(ptest, dtest, datatest2, 0);
        int bandierina = -1, bandieretta = -1;
        do {
            System.out.print(menu);
            bandierina = Input.inputNumero();
            switch (bandierina) {
                case 1:
                    System.out.print("Nome: ");
                    s = Input.inputRiga();
                    System.out.print("Matricola: ");
                    n = Input.inputNumero();
                    System.out.print("Anno di laurea: ");
                    a = Input.inputNumero();
                    while (!c.aggiungiDottore(s, n, a)) {
                        System.out.println("Matricola già esistente");
                        System.out.print("Matricola: ");
                        n = Input.inputNumero();
                    }
                    break;
                case 2:
                    System.out.print("Nome: ");
                    s = Input.inputRiga();
                    System.out.print("ID: ");
                    n = Input.inputNumero();
                    while (!c.aggiungiPaziente(s, n)) {
                        System.out.println("ID già esistente");
                        System.out.print("ID: ");
                        n = Input.inputNumero();
                    }
                    break;
                case 3:
                    Paziente p;
                    Data data,
                     data2;

                    if (c.getPazientiSize() != 0 && c.getDottoriSize() != 0) {
                        System.out.print("Scegli il livello di gravità che può essere Rosso, Giallo, Nero, Marrone (0 1 2 3)\nScelta: ");
                        u = Input.inputNumero();
                        while (u < 0 || u > 3) {
                            System.out.print("Codice errato, Scelta: ");
                            u = Input.inputNumero();
                        }
                        p = cercaPaziente();
                        d = cercaDottore();
                        int anno, mese, giorno, ora;
                        System.out.print("Inserire l'anno dell'appuntamento: ");
                        anno = Input.inputNumero();
                        System.out.print("Mese (tra 1 e 12): ");
                        mese = Input.inputNumero();
                        while (mese > 12 || mese < 1) {
                            System.out.print("Mese Errato, reinserire mese corretto: ");
                            mese = Input.inputNumero();
                        }
                        System.out.print("Giorno (tra 1 e 31): ");
                        giorno = Input.inputNumero();
                        while (giorno > 31 || giorno < 1) {
                            System.out.print("Giorno Errato, reinserire giorno corretto: ");
                            giorno = Input.inputNumero();
                        }
                        System.out.print("Ora (tra 1 e 24): ");
                        ora = Input.inputNumero();
                        while (ora > 24 || ora < 1) {
                            System.out.print("Ora Errata, reinserire ora corretta: ");
                            ora = Input.inputNumero();
                        }
                        data = new Data(anno, mese, giorno, ora);
                        while (!c.aggiungiAppuntamento(p, d, data, u)) {

                            if (c.getDottoriSize() > 1) {
                                //voui cambiare dottore?
                                System.out.print("Il dottore non è disponibile per ricevere un appuntamento\nVuoi modificare il dottore o la data (1 o 2)\nScelta: ");
                                bandieretta = Input.inputNumero();
                                switch (bandieretta) {
                                    case 1:
                                        d = cercaDottore();
                                        break;
                                    case 2:
                                        System.out.print("Inserire l'anno dell'appuntamento: ");
                                        anno = Input.inputNumero();
                                        System.out.print("Mese (tra 1 e 12): ");
                                        mese = Input.inputNumero();
                                        while (mese > 12 || mese < 1) {
                                            System.out.print("Mese Errato, reinserire mese corretto: ");
                                            mese = Input.inputNumero();
                                        }
                                        System.out.print("Giorno (tra 1 e 31): ");
                                        giorno = Input.inputNumero();
                                        while (giorno > 31 || giorno < 1) {
                                            System.out.print("Giorno Errato, reinserire giorno corretto: ");
                                            giorno = Input.inputNumero();
                                        }
                                        System.out.print("Ora (tra 1 e 24): ");
                                        ora = Input.inputNumero();
                                        while (ora > 24 || ora < 1) {
                                            System.out.print("Ora Errata, reinserire ora corretta: ");
                                            ora = Input.inputNumero();
                                        }
                                        data = new Data(anno, mese, giorno, ora);
                                        break;
                                }
                            } else {
                                System.out.println("Il dottore non è disponibile per ricevere appunatamento\nReinserire una data valida");
                                System.out.print("Inserire l'anno dell'appuntamento: ");
                                anno = Input.inputNumero();
                                System.out.print("Mese (tra 1 e 12): ");
                                mese = Input.inputNumero();
                                while (mese > 12 || mese < 1) {
                                    System.out.print("Mese Errato, reinserire mese corretto: ");
                                    mese = Input.inputNumero();
                                }
                                System.out.print("Giorno (tra 1 e 31): ");
                                giorno = Input.inputNumero();
                                while (giorno > 31 || giorno < 1) {
                                    System.out.print("Giorno Errato, reinserire giorno corretto: ");
                                    giorno = Input.inputNumero();
                                }
                                System.out.print("Ora (tra 1 e 24): ");
                                ora = Input.inputNumero();
                                while (ora > 24 || ora < 1) {
                                    System.out.print("Ora Errata, reinserire ora corretta: ");
                                    ora = Input.inputNumero();
                                }
                                data = new Data(anno, mese, giorno, ora);
                            }

                        }

                    } else {
                        if (c.getPazientiSize() == 0) {
                            System.out.println("Nessun paziente registrato.");
                        } else if (c.getDottoriSize() == 0) {
                            System.out.println("Nessun dottore registrato");
                        } else {
                            System.out.println("CACCOLA");
                        }
                    }
                    break;
                case 4:
                    int g1, g2, o1, b, o2;

                    d = cercaDottore();
                    System.out.print("Vuole aggiungere ferie o ore di lavoro ? (1 o 2): ");
                    bandieretta = Input.inputNumero();
                    switch (bandieretta) {
                        case 1:
                            System.out.print("Ferie - Modificare per singolo giorno o per arco settiamanale (1 o 2): ");
                            b = Input.inputNumero();
                            switch (b) {
                                case 1:
                                    modificaGiornoFerie(d);
                                    break;
                                case 2:
                                    modificaSettimanaFerie(d);
                                    break;
                            }
                            break;
                        case 2:
                            System.out.print("Lavoro - Modificare per singolo giorno o per arco settiamanale (1 o 2): ");
                            b = Input.inputNumero();
                            switch (b) {
                                case 1:
                                    modificaGiornoLavoro(d);
                                    break;
                                case 2:
                                    modificaSettimanaLavoro(d);
                                    break;
                            }
                            break;
                    }
                    break;
                case 5:
                    System.out.println("\n" + c.stampaMedici());
                    break;
                case 6:
                    d = cercaDottore();
                    System.out.println("" + d.getOrario().toString());
                    break;
                case 7:
                    d = cercaDottore();
                    for (Appuntamento app : c.stampaAppuntamenti(d.getMatricola())) {
                        System.out.println("" + app.toString());
                    }
                    break;
                case 8:
                    System.out.println("\n" + c.stampaPazienti());
                    break;
                case 9:
                    int anno,
                     anno2,
                     mese,
                     mese2,
                     giorno,
                     giorno2,
                     ora,
                     ora2;
                    System.out.print("Inserire l'anno d'inizio: ");
                    anno = Input.inputNumero();
                    System.out.print("Mese d'inizio: ");
                    mese = Input.inputNumero();
                    while (mese > 12 || mese < 1) {
                        System.out.print("Mese Errato, reinserire mese corretto: ");
                        mese = Input.inputNumero();
                    }
                    System.out.print("Giorno d'inizio: ");
                    giorno = Input.inputNumero();
                    while (giorno > 31 || giorno < 1) {
                        System.out.print("Giorno Errato, reinserire giorno corretto: ");
                        giorno = Input.inputNumero();
                    }
                    System.out.print("Ora d'inizio: ");
                    ora = Input.inputNumero();
                    while (ora > 24 || ora < 1) {
                        System.out.print("Ora Errata, reinserire ora corretta: ");
                        ora = Input.inputNumero();
                    }
                    data = new Data(anno, mese, giorno, ora);
                    System.out.print("Inserire l'anno di fine: ");
                    anno2 = Input.inputNumero();
                    System.out.print("Mese di fine: ");
                    mese2 = Input.inputNumero();
                    while (mese2 > 12 || mese2 < 1) {
                        System.out.print("Mese Errato, reinserire mese corretto: ");
                        mese2 = Input.inputNumero();
                    }
                    System.out.print("Giorno di fine: ");
                    giorno2 = Input.inputNumero();
                    while (giorno2 > 31 || giorno2 < 1) {
                        System.out.print("Giorno Errato, reinserire giorno corretto: ");
                        giorno2 = Input.inputNumero();
                    }
                    System.out.print("Ora di fine: ");
                    ora2 = Input.inputNumero();
                    while (ora2 > 24 || ora2 < 1) {
                        System.out.print("Ora Errata, reinserire ora corretta: ");
                        ora2 = Input.inputNumero();
                    }
                    data2 = new Data(anno2, mese2, giorno2, ora2);
                    for (Appuntamento app : c.stampaAppuntamenti(data, data2)) {
                        System.out.println("" + app.toString());
                        ;
                    }

                    break;
                case 10:
                    d = cercaDottore();
                    System.out.println("" + c.stampaNumAppuntamenti(d.getMatricola()));
                    break;
            }
        } while (bandierina != 11);
    }

}
