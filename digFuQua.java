import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    
    static void erstelleSpielerkarte (Spieler spielerEins, Spieler spielerZwei) {
        System.out.println("-".repeat(47));
        System.out.println("|" + center((spielerEins.Vorname + " " + spielerEins.Nachname), 46) + "|");
        System.out.println("-".repeat(46));        
        System.out.printf("| Größe:\t %4s | Schnelligkeit:\t %4s |\n", spielerEins.Größe, spielerEins.Schnelligkeit);
        System.out.printf("| Passen:\t %4s | Schießen:\t %4s |\n", spielerEins.Passen, spielerEins.Schießen);
        System.out.printf("| Zweikampf:\t %4s | Kondition:\t %4s |\n", spielerEins.Zweikampf, spielerEins.Kondition);
        System.out.println("-".repeat(47));

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
        System.out.printf("| Größe:\t %4s | Schnelligkeit:\t %4s |\n", spielerZwei.Größe, spielerZwei.Schnelligkeit);
        System.out.printf("| Passen:\t %4s | Schießen:\t %4s |\n", spielerZwei.Passen, spielerZwei.Schießen);
        System.out.printf("| Zweikampf:\t %4s | Kondition:\t %4s |\n", spielerZwei.Zweikampf, spielerZwei.Kondition);
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


    public static void main(String[] args) {
        System.out.println("Herzlich willkommen zum digitalen Fußballquartett");
        System.out.println("-".repeat(49));
       
        Spieler spielerEins = auswahlErstellung();
        Spieler spielerZwei = auswahlErstellung();
        erstelleSpielerkarte(spielerEins, spielerZwei);
    }        
}

/*
ToDo
- Zufällige Spielerzeugung erstellen
- Spieler in Datei speichern (csv?)
- Attributswahl (nach Anzeige aktuelle Spielerkarte)
- Zugzuteilung (Player 1, Player 2)
- Namenseingabe der Spieler
- Vergleichsfunktion für Attribute
- Verteilung der Karten
- Speicherung der Karten auf der Hand
*/

/*
 cd C:\Users\Stefa\Nextcloud\IT\Java\src\digFuQua
>> javac -encoding UTF-8 digFuQua.java
>> java digFuQua
 */

