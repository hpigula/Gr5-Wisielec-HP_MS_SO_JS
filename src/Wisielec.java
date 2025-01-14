import java.util.Random;
import java.util.Scanner;
import java.util.*;

public class Wisielec {
    public static Map<String, List<String>> categories = new HashMap<>();

    // Inicjalizacja kategorii i słów
    static {
        categories.put("Zwierzęta", Arrays.asList("lew", "słoń", "tygrys", "zebra", "żyrafa", "wilk", "lis", "niedźwiedź", "kangur", "koala"));
        categories.put("Owoce", Arrays.asList("jabłko", "banan", "pomarańcza", "truskawka", "winogrono", "gruszka", "ananas", "kiwi", "malina", "brzoskwinia"));
        categories.put("Kolory", Arrays.asList("czerwony", "niebieski", "zielony", "żółty", "fioletowy", "pomarańczowy", "różowy", "brązowy", "czarny", "biały"));
        categories.put("Miasta", Arrays.asList("Warszawa", "Kraków", "Gdańsk", "Wrocław", "Poznań", "Łódź", "Szczecin", "Lublin", "Katowice", "Bydgoszcz"));
        categories.put("Sporty", Arrays.asList("piłka nożna", "koszykówka", "siatkówka", "tenis", "hokej", "bieganie", "pływanie", "kolarstwo", "golf", "boks"));
        categories.put("Pojazdy", Arrays.asList("samochód", "rower", "motocykl", "pociąg", "samolot", "statek", "autobus", "tramwaj", "hulajnoga", "ciężarówka"));
        categories.put("Warzywa", Arrays.asList("marchew", "ziemniak", "pomidor", "ogórek", "papryka", "cebula", "czosnek", "brokuł", "kalafior", "sałata"));
        categories.put("Zawody", Arrays.asList("lekarz", "nauczyciel", "inżynier", "prawnik", "kucharz", "mechanik", "architekt", "policjant", "strażak", "muzyk"));
        categories.put("Kraje", Arrays.asList("Polska", "Niemcy", "Francja", "Hiszpania", "Włochy", "USA", "Kanada", "Australia", "Japonia", "Chiny"));
        categories.put("Instrumenty", Arrays.asList("gitara", "pianino", "skrzypce", "trąbka", "flet", "perkusja", "saksofon", "harfa", "akordeon", "mandolina"));
    }

    // Losowanie kategorii i słowa
    public static Map.Entry<String, String> losujKategorieISlowo() {
        Random rand = new Random();
        List<String> categoryKeys = new ArrayList<>(categories.keySet());
        String chosenCategory = categoryKeys.get(rand.nextInt(categoryKeys.size()));
        List<String> words = categories.get(chosenCategory);
        String chosenWord = words.get(rand.nextInt(words.size()));
        return new AbstractMap.SimpleEntry<>(chosenCategory, chosenWord);
    }

    // Rysowanie wisielca
    public static void rysujWisielca(int bledy) {
        switch (bledy) {
            case 0 -> System.out.println("  -----");
            case 1 -> System.out.println("  -----\n  |   |");
            case 2 -> System.out.println("  -----\n  |   |\n  O");
            case 3 -> System.out.println("  -----\n  |   |\n  O\n  |");
            case 4 -> System.out.println("  -----\n  |   |\n  O\n /|");
            case 5 -> System.out.println("  -----\n  |   |\n  O\n /|\\");
            case 6 -> System.out.println("  -----\n  |   |\n  O\n /|\\\n / \\");
        }
    }

    // Rozpoczęcie gry
    public static void rozpocznijGre() {
        Scanner scanner = new Scanner(System.in);
        Map.Entry<String, String> kategoriaISlowo = losujKategorieISlowo();
        String kategoria = kategoriaISlowo.getKey();
        String slowo = kategoriaISlowo.getValue();
        char[] zgadnieteSlowo = new char[slowo.length()];
        Arrays.fill(zgadnieteSlowo, '_');

        int bledy = 0;
        boolean graTrwa = true;

        System.out.println("\nAutorzy: Sebastian Ormański, Horacy Piguła, Mateusz Serowik, Jakub Szymański ");
        System.out.println("\nSpróbuj odgadnąć słowo! Możesz zgadywać literę lub całe słowo.");
        System.out.println("Kategoria: " + kategoria);


        while (graTrwa && bledy < 6) {
            System.out.println("\nSłowo do zgadnięcia: " + new String(zgadnieteSlowo));
            System.out.print("Podaj literę lub całe słowo: ");
            String input = scanner.next();

            if (input.length() > 1) { // Sprawdzanie całego słowa
                if (input.equalsIgnoreCase(slowo)) {
                    System.out.println("\nGratulacje! Odgadłeś słowo: " + slowo);
                    graTrwa = false;
                } else {
                    System.out.println("Niestety, to nie jest poprawne słowo.");
                    bledy++;
                    rysujWisielca(bledy);
                }
            } else { // Sprawdzanie jednej litery
                char litera = input.charAt(0);
                boolean trafiona = false;

                for (int i = 0; i < slowo.length(); i++) {
                    if (String.valueOf(slowo.charAt(i)).equalsIgnoreCase(String.valueOf(litera))) {
                        zgadnieteSlowo[i] = slowo.charAt(i); // Zachowujemy oryginalną wielkość litery
                        trafiona = true;
                    }
                }

                if (!trafiona) {
                    System.out.println("Brak litery " + litera + " w słowie.");
                    bledy++;
                    rysujWisielca(bledy);
                }
            }

            if (new String(zgadnieteSlowo).equalsIgnoreCase(slowo)) {
                System.out.println("\nGratulacje! Odgadłeś słowo: " + slowo);
                graTrwa = false;
            }
        }

        if (bledy == 6) {
            System.out.println("\nPrzegrałeś! Słowo to było: " + slowo);
        }

        System.out.println("\nNaciśnij Enter, aby zagrać ponownie, lub wpisz 'exit', aby zakończyć grę.");
        scanner.nextLine(); // Wyczyść bufor
        String odp = scanner.nextLine();

        if (odp.equalsIgnoreCase("exit")) {
            System.out.println("Dziękujemy za grę!");
        } else {
            rozpocznijGre();
        }
    }

    // Metoda główna
    public static void main(String[] args) {
        rozpocznijGre();
    }
}