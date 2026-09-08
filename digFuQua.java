
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

        SpielerAttribute (String Vorname, String Nachname, float Größe, int Passen, int Schießen, int Schnelligkeit, int Zweikampf, int Kondition) {
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

    static String[] erstelleSpieler() {
        System.out.println("Spieler manuell (m) oder zufällig (z) erstellen?");
        Scanner scanner = new Scanner(System.in);
        Spieler[] spieler = new Spieler[1];
        System.out.println("Wie soll dein Spieler mit Vornamen heißen? ");
        String Vorname = scanner.next();
        System.out.println("Wie soll dein Spieler mit Nachnnamen heißen? ");
        String Nachname = scanner.next();
        System.out.println("Wie groß soll der Spieler sein (in Meter)? ");
        float Größe = scanner.nextFloat();
        System.out.println("Wie gut soll dein Spieler passen (0 - 100)? ");
        int Passen = scanner.nextInt();
        System.out.println("Wie gut soll dein Spieler schießen (0 - 100)? ");
        int Schießen = scanner.nextInt();
        System.out.println("Wie schnell soll dein Spieler sein (0 - 100)? ");
        int Schnelligkeit = scanner.nextInt();
        System.out.println("Wie gut soll dein Spieler im Zweikampf sein (0 - 100)? ");
        int Zweikampf = scanner.nextInt();
        System.out.println("Wie gut soll die Kondition deines Spielers sein (0 - 100)? ");
        int Kondition = scanner.nextInt();        

        spieler[0] = new SpielerAttribute(
            Vorname,
            Nachname,
            Größe,
            Passen,
            Schießen,
            Schnelligkeit,
            Zweikampf,
            Kondition
        );
        return spieler;
    }

    public static void main(String[] args) {
        System.out.println("| Herzlich willkommen zum digitalen Fußballquartett |");
        System.out.println("-".repeat(53));
        Spieler meinSpieler = erstelleSpieler();
    }        
}

