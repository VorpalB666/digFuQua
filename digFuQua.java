import java.util.Arrays;
import java.util.Scanner;

public class digFuQua {
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
        System.out.println("Spieler manuell (m) oder zufällig (z) erstellen?");

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

    
    static void erstelleSpielerkarte (Spieler spielerAttribute) {
        System.out.println("-".repeat(47));
        System.out.println("|" + center((spielerAttribute.Vorname + " " + spielerAttribute.Nachname), 46) + "|");
        System.out.println("-".repeat(46));        
        System.out.printf("| Größe:\t %4s | Schnelligkeit:\t %4s |\n", spielerAttribute.Größe, spielerAttribute.Schnelligkeit);
        System.out.printf("| Passen:\t %4s | Schießen:\t %4s |\n", spielerAttribute.Passen, spielerAttribute.Schießen);
        System.out.printf("| Zweikampf:\t %4s | Kondition:\t %4s |\n", spielerAttribute.Zweikampf, spielerAttribute.Kondition);
        System.out.println("-".repeat(47));

        System.out.println();
        System.out.println(center("          ___ ", 47));
        System.out.println(center("\\      / |    ", 47));
        System.out.println(center(" \\    /  |___ ", 47));
        System.out.println(center("  \\  /       |", 47));
        System.out.println(center("   \\/     ___|", 47));
        System.out.println();

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

            if (!eingabe.isEmpty() && eingabe.matches("[A-Za-zÄOÜäouß-]+")) {
                return eingabe;
            }
            System.out.println("Ungültige Eingabe - bitte nur Buchstaben und Bindestrich verwenden.");
        }
        
    }


    public static void main(String[] args) {
        System.out.println("Herzlich willkommen zum digitalen Fußballquartett");
        System.out.println("-".repeat(49));
        Spieler meinSpieler = erstelleSpieler();
        erstelleSpielerkarte(meinSpieler);
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

