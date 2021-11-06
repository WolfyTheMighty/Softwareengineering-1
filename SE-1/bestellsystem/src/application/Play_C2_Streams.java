package application;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Play_C2_Streams {
    public static void main(String[] args) {

        Stream<String> names = List.of("Hendricks", "Raymond", "Pena", "Gonzalez",
                "Nielsen", "Hamilton", "Graham", "Gill", "Vance", "Howe", "Ray", "Talley",
                "Brock", "Hall", "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty",
                "Strong", "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
                "Casey", "Buckner", "Hardin", "Marquez", "Navarro").stream();

        names
                .sorted((s1, s2) -> s1.compareTo(s2))
                .sorted((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .forEach(System.out::println);
        Stream<String> names2 = List.of("Hendricks", "Raymond", "Pena", "Gonzalez",
                "Nielsen", "Hamilton", "Graham", "Gill", "Vance", "Howe", "Ray", "Talley",
                "Brock", "Hall", "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty",
                "Strong", "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
                "Casey", "Buckner", "Hardin", "Marquez", "Navarro").stream();

        System.out.println("====================");
        List<String> ls = names2.filter(s -> s.endsWith("ez"))
                .collect(Collectors.toList());

        for (String s :ls) {
            System.out.print(s + ", ");
        }
    }
}
