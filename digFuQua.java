import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class digFuQua {

    static Spieler auswahlErstellung() {
        System.out.println("Möchtest du deinen Spieler manuell (m) oder zufällig (z) erstellen?");
        Scanner scanner = new Scanner(System.in);
        String auswahl = scanner.nextLine();
        if (auswahl.equals("m")) {
            return erstelleSpieler();
        } else if (auswahl.equals("z")) {
            return zufälligerSpieler();
        } else {
            return auswahlErstellung();
        }
    }
    static class Spieler {
        String Vorname;
        String Nachname;
        float Größe;
        int Passen;
        int Schießen;
        int Schnelligkeit;
        int Zweikampf;
        int Kondition;

        Spieler (String Vorname, String Nachname, float Größe, int Passen, int Schießen, int Schnelligkeit, int Zweikampf, int Kondition) {
            this.Vorname = Vorname;
            this.Nachname = Nachname;
            this.Größe = Größe;
            this.Passen = Passen;
            this.Schießen = Schießen;
            this.Schnelligkeit = Schnelligkeit;
            this.Zweikampf = Zweikampf;
            this.Kondition = Kondition;
        }
    }

    static Spieler erstelleSpieler() {
        String Vorname = stringFrage("Wie soll dein Spieler mit Vornamen heißen? ");
        String Nachname = stringFrage("Wie soll dein Spieler mit Nachnnamen heißen? ");

        float Größe = floatFrage("Wie groß soll der Spieler sein (in Meter, Beispiel: 1.80)? ");

        int Passen = intFrage("Wie gut soll dein Spieler passen (0 - 100)? ");
        int Schießen = intFrage("Wie gut soll dein Spieler schießen (0 - 100)? ");
        int Schnelligkeit = intFrage("Wie schnell soll dein Spieler sein (0 - 100)? ");
        int Zweikampf = intFrage("Wie gut soll dein Spieler im Zweikampf sein (0 - 100)? ");
        int Kondition = intFrage("Wie gut soll die Kondition deines Spielers sein (0 - 100)? ");

        return new Spieler(
            Vorname,
            Nachname,
            Größe,
            Passen,
            Schießen,
            Schnelligkeit,
            Zweikampf,
            Kondition
        );
    }


    static Spieler zufälligerSpieler() {
        String Vorname = zufallsName(ladeListe("Vornamen.txt"));
        String Nachname = zufallsName(ladeListe("Nachnamen.txt"));

        Random random = new Random();
        float Größe = (float) (Math.round(Math.random() * 121 + 120) ) / 100f;

        int Passen = (int) (Math.random()*100);
        int Schießen = (int) (Math.random()*100);
        int Schnelligkeit = (int) (Math.random()*100);
        int Zweikampf = (int) (Math.random()*100);
        int Kondition = (int) (Math.random()*100);

        return new Spieler(
            Vorname,
            Nachname,
            Größe,
            Passen,
            Schießen,
            Schnelligkeit,
            Zweikampf,
            Kondition
        );
    }

    
    static void erstelleSpielerkarte (Spieler spieler) {
        System.out.println("-".repeat(47));
        System.out.println("|" + center((spieler.Vorname + " " + spieler.Nachname), 44) + "|");
        System.out.println("-".repeat(47));
        System.out.printf("| Größe:\t\t %4s | Schnelligkeit:\t %4s |\n", spieler.Größe, spieler.Schnelligkeit);
        System.out.printf("| Passen:\t\t %4s | Schießen:\t\t %4s |\n", spieler.Passen, spieler.Schießen);
        System.out.printf("| Zweikampf:\t %4s | Kondition:\t\t %4s |\n", spieler.Zweikampf, spieler.Kondition);
        System.out.println("-".repeat(47));
    }


    static void kartenDuell(Spieler spielerEins, Spieler spielerZwei) {
        erstelleSpielerkarte(spielerEins);

        System.out.println();
        System.out.println(center("          ___ ", 47));
        System.out.println(center("\\      / |    ", 47));
        System.out.println(center(" \\    /  |___ ", 47));
        System.out.println(center("  \\  /       |", 47));
        System.out.println(center("   \\/     ___|", 47));
        System.out.println();

        System.out.println("-".repeat(47));
        System.out.println("|" + center((spielerZwei.Vorname + " " + spielerZwei.Nachname), 46) + "|");
        System.out.println("-".repeat(46));
        System.out.printf("| Größe:\t\t %4s | Schnelligkeit:\t %4s |\n", spielerZwei.Größe, spielerZwei.Schnelligkeit);
        System.out.printf("| Passen:\t\t %4s | Schießen:\t\t %4s |\n", spielerZwei.Passen, spielerZwei.Schießen);
        System.out.printf("| Zweikampf:\t %4s | Kondition:\t\t %4s |\n", spielerZwei.Zweikampf, spielerZwei.Kondition);
        System.out.println("-".repeat(47));
    }

    static String center (String text, int width) {
        if (text.length() > width) {
            return text;
        } else {
            int space = width - text.length();
            int toAdd = space/2;
            if (space%2 == 0) {
                text = " ".repeat(toAdd) + text + " ".repeat(toAdd+1);
            } else {
                text = " ".repeat(toAdd) + text + " ".repeat(toAdd);
            }
            return text;
        }
    }


    static List<String> ladeListe(String dateiName) {
        try {
            return Files.readAllLines(Path.of("C:\\Users\\Stefa\\Nextcloud\\IT\\Java\\src\\digFuQua\\Files\\", dateiName))
                        .stream()
                        .map(String::trim)
                        .filter(zeile -> !zeile.isEmpty())
                        .toList();
        } catch (IOException e) {
            System.out.println("Konnte Datei nicht lesen: " + dateiName + " (" + e.getMessage() + ")");
            return List.of();  // leere Liste zurückgeben, damit das Spiel weiterlaufen kann
        }
    }


    static java.util.Random rng = new java.util.Random();


    static String zufallsName(List<String> namen) {
        int zufallsZahl = rng.nextInt(namen.size());  
        return namen.get(zufallsZahl);                
    }


    static int intFrage (String frage) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(frage);

            if (!scanner.hasNextInt()) {
                System.out.println("Bitte eine gültige Zahl eingeben!");
                scanner.next();
                continue;
            }

            int wert = scanner.nextInt();
            if (wert >= 0 && wert <= 100) {
                return wert;
            }

            System.out.println("Bitte einen Wert zwischen 0 und 100 eingeben");
        }
    }


    static float floatFrage (String frage) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(frage);

            if (!scanner.hasNextFloat()) {
                System.out.println("Bitte eine gültige Zahl eingeben!");
                scanner.next();
                continue;
            }

            float wert = scanner.nextFloat();
            if (wert >= 1.20 && wert <= 2.40) {
                return wert;
            }
            System.out.println("Bitte einen Wert zwischen 1.20 und 2.40 eingeben.");
        }
    }


    static String stringFrage (String frage) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(frage);

            String eingabe = scanner.nextLine().trim();

            if (!eingabe.isEmpty() && eingabe.matches("[A-Za-zÄOÜäöüß-]+")) {
                return eingabe;
            }
            System.out.println("Ungültige Eingabe - bitte nur Buchstaben und Bindestrich verwenden.");
        }
    }

    static String auswahlAttribut(Spieler spielerEins, Spieler spielerZwei) {
        String zweikampfErgebnis = "";
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Wähle deine Vergleichskategorie:\n" +
                "'1' für Größe,\n" +
                "'2' für Passen,\n" +
                "'3' für Schießen,\n" +
                "'4' für Schnelligkeit,\n" +
                "'5' für Zweikampf,\n" +
                "'6' für Kondition\n");
        if (!scanner.hasNextInt()) {
            System.out.println("Bitte eine Zahl von 1-6 eingeben!");
            scanner.next();
            auswahlAttribut(spielerEins, spielerZwei);
        }
        int attribut = scanner.nextInt();
        switch (attribut) {
            case 1:
                zweikampfErgebnis = zweikampfFloat(spielerEins.Größe, spielerZwei.Größe);
                break;
            case 2:
                zweikampfErgebnis = zweikampf(spielerEins.Passen, spielerZwei.Passen);
                break;
            case 3:
                zweikampfErgebnis = zweikampf(spielerEins.Schießen, spielerZwei.Schießen);
                break;
            case 4:
                zweikampfErgebnis = zweikampf(spielerEins.Schnelligkeit, spielerZwei.Schnelligkeit);
                break;
            case 5:
                zweikampfErgebnis = zweikampf(spielerEins.Zweikampf, spielerZwei.Zweikampf);
                break;
            case 6:
                zweikampfErgebnis = zweikampf(spielerEins.Kondition, spielerZwei.Kondition);
                break;
            default:
                System.out.println("Falsche Eingabe");
                auswahlAttribut(spielerEins, spielerZwei);
        }
        kartenDuell(spielerEins, spielerZwei);
        return zweikampfErgebnis;
    }

    static String zweikampf(int wertSpielerEins, int wertSpielerZwei) {
        System.out.println("SpielerEins: " + wertSpielerEins + ", SpielerZwei: " + wertSpielerZwei);
        String ergebnis = "";
        if (wertSpielerEins > wertSpielerZwei) {
            System.out.println("Spieler 1 gewinnt");
            ergebnis = "Spieler1";
        } else if (wertSpielerEins < wertSpielerZwei) {
            System.out.println("Spieler 2 gewinnt");
            ergebnis = "Spieler2";
        } else {
            System.out.println("Untentschieden. Das nächste Duell entscheidet");
            ergebnis = "Unentschieden";
        }
        return ergebnis;
    }


    static String zweikampfFloat(float wertSpielerEins, float wertSpielerZwei) {
        System.out.println("SpielerEins: " + wertSpielerEins + ", SpielerZwei: " + wertSpielerZwei);
        String ergebnis = "";
        if (Float.compare(wertSpielerEins, wertSpielerZwei) > 0) {
            System.out.println("Spieler 1 gewinnt");
            ergebnis = "Spieler1";
        } else if (Float.compare(wertSpielerEins, wertSpielerZwei) < 0) {
            System.out.println("Spieler 2 gewinnt");
            ergebnis = "Spieler2";
        } else {
            System.out.println("Untentschieden. Das nächste Duell entscheidet");
            ergebnis = "Unentschieden";
        }
        return ergebnis;
    }

    static void speichereInKartenset(Spieler spielerDaten) {
        String zeile = "%s;%s;%s;%s;%s;%s;%s;%s".formatted(
                        spielerDaten.Vorname,
                        spielerDaten.Nachname,
                        spielerDaten.Größe,
                        spielerDaten.Passen,
                        spielerDaten.Schießen,
                        spielerDaten.Schnelligkeit,
                        spielerDaten.Zweikampf,
                        spielerDaten.Kondition);

        String dateiPfad = "C:\\Users\\Stefa\\Nextcloud\\IT\\Java\\src\\digFuQua\\Files\\Spielerkarten.csv";
        try {
            Files.write(Path.of(dateiPfad), List.of(zeile), StandardOpenOption.APPEND);
            System.out.println("Karte gespeichert: " + dateiPfad);
        } catch (IOException e) {
            System.out.println("Speichern fehlgeschlagen: " + e.getMessage());
        }
    }

    static Spieler zeileZuSpieler(String zeile) {
        String[] teile = zeile.split(";");

        return new Spieler(
                teile[0],                                    // Vorname
                teile[1],                                    // Nachname
                Float.parseFloat(teile[2]),                  // Größe
                Integer.parseInt(teile[3]),                  // Passen
                Integer.parseInt(teile[4]),                  // Schießen
                Integer.parseInt(teile[5]),                  // Schnelligkeit
                Integer.parseInt(teile[6]),                  // Zweikampf
                Integer.parseInt(teile[7])                   // Kondition
        );
    }


    static ArrayList kartenGeben(ArrayList handSpieler){
        List<String> kartenListe = new ArrayList<>(ladeListe("Spielerkarten.csv"));

        for (int i = 0; i < 16; i++) {
            int zufallsZahl = rng.nextInt(kartenListe.size());
            handSpieler.add(zeileZuSpieler(kartenListe.get(zufallsZahl)));
        }
        return handSpieler;
    }


    static void startMenü() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Spieler erstellen (e)");
            System.out.println("Kartenset erstellen (k)");
            System.out.println("Spiel starten (s)");
            String userInput = scanner.nextLine();

            if (userInput.equals("e")) {
                speichereInKartenset(auswahlErstellung());
                startMenü();
            } else if (userInput.equals("s")) {
                spielAblauf();
                return;
            } else if (userInput.equals("k")) {
                erstelleKartenset();
                return;
            } else {
                System.out.println("Falsche Eingabe. Spieler erstellen (e) oder Spiel starten (s)");
            }
        }
    }


    static void spielAblauf() {
        System.out.println("Das Spiel beginnt!");
        ArrayList<Spieler> handSpielerEins = new ArrayList<>();
        ArrayList<Spieler> handSpielerZwei = new ArrayList<>();
        handSpielerEins = kartenGeben(handSpielerEins);
        handSpielerZwei = kartenGeben(handSpielerZwei);

        boolean amZug = false;
        int i = 0;
        int j = 0;
        String ergebnis = "";

        while (handSpielerEins.toArray().length > 0 && handSpielerZwei.toArray().length > 0) {
            if (i == handSpielerEins.toArray().length) i = 0;
            if (j == handSpielerZwei.toArray().length) j = 0;

            System.out.println();
            System.out.println("Anzahl Karten Spieler 1: " + handSpielerEins.toArray().length);
            System.out.println("Anzahl Karten Spieler 2: " + handSpielerZwei.toArray().length);
            if (amZug == false) {
                erstelleSpielerkarte(handSpielerEins.get(i));
                System.out.println("Spieler 1 ist am Zug.");
                ergebnis = auswahlAttribut(handSpielerEins.get(i), handSpielerZwei.get(j));

            } else {
                erstelleSpielerkarte(handSpielerZwei.get(j));
                System.out.println("Spieler 2 ist am Zug.");
                ergebnis = auswahlAttribut(handSpielerEins.get(i), handSpielerZwei.get(j));
            }

            if (ergebnis.equals("Spieler1")) {
                handSpielerEins.add(handSpielerZwei.get(j));
                handSpielerZwei.remove(handSpielerZwei.get(j));
                amZug = false;
                i++;
            } else if (ergebnis.equals("Spieler2")) {
                handSpielerZwei.add(handSpielerEins.get(i));
                handSpielerEins.remove(handSpielerEins.get(i));
                amZug = true;
                j++;
            } else if (ergebnis.equals("Unentschieden")) {
                i++;
                j++;
                continue;
            }
        }

        if (handSpielerEins.toArray().length == 0) {
            System.out.println("Spieler 2 hat gewonnen!");
            System.out.println("Herzlichen Glückwunsch!");
            System.out.println();
        } else if (handSpielerZwei.toArray().length == 0) {
            System.out.println("Spieler 1 hat gewonnen!");
            System.out.println("Herzlichen Glückwunsch!");
            System.out.println();
        }
        startMenü();
    }


    static void erstelleKartenset() {
        int größeKartenset = 32;
        for (int i = 0; i < größeKartenset; i++) {
            speichereInKartenset(zufälligerSpieler());
        }
        startMenü();
    }

    public static void main(String[] args) {
        System.out.println("Herzlich willkommen zum digitalen Fußballquartett");
        System.out.println("-".repeat(49));
        startMenü();
    }        
}


