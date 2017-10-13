package task_four.second;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    public void logic() {
        List<Author> list = new ArrayList<>();
        list.add(new Author("James",
                LocalDate.of(1995, 8, 12),
                Author.Sex.MALE));
        list.add(new Author("Sue",
                LocalDate.of(1948, 7, 11),
                LocalDate.of(2002, 1, 1),
                Author.Sex.FEMALE));
        list.add(new Author("Hank",
                LocalDate.of(1980, 2, 28),
                Author.Sex.MALE));
        list.add(new Author("Anna",
                LocalDate.of(1911, 3, 13),
                LocalDate.of(1988, 11, 22),
                Author.Sex.FEMALE));
        list.add(new Author("Harry",
                LocalDate.of(1807, 3, 13),
                LocalDate.of(1895, 11, 22),
                Author.Sex.MALE));

        System.out.println("Average year of authors:");
        averageAuthorYear(list);
        System.out.println();

        System.out.println("Sorted author by age:");
        sortAuthorByAgeAscending(list);
        System.out.println();

        System.out.println("List pensioners:");
        listOfAuthorsOfPensioners(list);
        System.out.println();

    }

    private void averageAuthorYear(List<Author> list) {
        list
                .stream()
                .mapToInt(value -> {
                    if (value.isDeath()) {
                        return value.getDateOfDeath().getYear() -
                                value.getDateOfBirth().getYear();
                    } else {
                        return LocalDate.now().getYear() -
                                value.getDateOfBirth().getYear();
                    }
                }).average().ifPresent(s -> {System.out.println(Math.round(s));});
    }

    private void sortAuthorByAgeAscending(List<Author> list) {
        list
                .stream()
                .sorted((o1, o2) -> {
                    int age1, age2;
                    if (o1.isDeath()) {
                        age1 = o1.getDateOfDeath().getYear() -
                                o1.getDateOfBirth().getYear();
                    } else {
                        age1 = LocalDate.now().getYear() -
                                o1.getDateOfBirth().getYear();
                    }
                    if (o2.isDeath()) {
                        age2 = o2.getDateOfDeath().getYear() -
                                o2.getDateOfBirth().getYear();
                    } else {
                        age2 = LocalDate.now().getYear() -
                                o2.getDateOfBirth().getYear();
                    }
                    if (age1 > age2) {
                        return 1;
                    } else {
                        return -1;
                    }

                }).forEach(s -> System.out.println(s.getName()));
    }

    private void listOfAuthorsOfPensioners(List<Author> list) {
        list
                .stream()
                .forEach(author -> {
                    int age;
                    if(author.isDeath()){
                        age = author.getDateOfDeath().getYear() -
                                author.getDateOfBirth().getYear();
                    } else {
                        age = LocalDate.now().getYear() -
                                author.getDateOfBirth().getYear();
                    }
                    Author.Sex sex = author.getSex();
                    if((sex == Author.Sex.MALE && age > 65) ||
                            (sex == Author.Sex.FEMALE && age > 63)){
                        System.out.println(author.getName());
                    }
                });
    }


}
