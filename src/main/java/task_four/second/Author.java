package task_four.second;

import java.time.LocalDate;

public class Author {

    private final String name;
    private final LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private boolean death;
    private Sex sex;

    public enum Sex{
        MALE,
        FEMALE
    }

    public Author(String name, LocalDate dateOfBirth, Sex sex) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.death = false;
        this.sex = sex;
    }

    public Author(String name, LocalDate dateOfBirth, LocalDate dateOfDeath, Sex sex) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.death = true;
        this.sex = sex;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public boolean isDeath() {
        return death;
    }

    public Sex getSex() {
        return sex;
    }
}
