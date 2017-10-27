package task_four.second.comporator;

import task_four.second.domain.Author;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class AuthorComparator implements Comparator<Author> {

    public AuthorComparator() {
    }

    private int getAge(LocalDate after, LocalDate before) {
        return Period.between(before, after).getYears();
    }

    @Override
    public int compare(Author o1, Author o2) {
        int age1, age2;
        if (o1.isDead()) {
            age1 = getAge(o1.getDateOfDeath(),o1.getDateOfBirth());
        } else {
            age1 =getAge(LocalDate.now(),o1.getDateOfBirth());
        }
        if (o2.isDead()) {
            age2 = getAge(o2.getDateOfDeath(),o2.getDateOfBirth());
        } else {
            age2 = getAge(LocalDate.now(),o2.getDateOfBirth());
        }
        if (age1 > age2) {
            return 1;
        } else {
            return -1;
        }
    }
}
