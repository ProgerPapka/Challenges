package task_four.second.domain;

import task_four.second.exception.OperationDontPossibleException;

import java.io.Serializable;
import java.time.LocalDate;

public class Author implements Serializable{

    private final String name;
    private final LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Sex sex;

    public enum Sex implements Serializable{
        MALE,
        FEMALE;

        public static Sex parseSex(String text){
            if(text.equals(Sex.MALE.name())){
                return Sex.MALE;
            }
            if(text.equals(Sex.FEMALE.name())){
                return Sex.FEMALE;
            }
            return null;
        }
    }

    public Author(String name, LocalDate dateOfBirth, Sex sex) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
    }

    public Author(String name, LocalDate dateOfBirth, LocalDate dateOfDeath, Sex sex) {
        this(name, dateOfBirth, sex);
        this.dateOfDeath = dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) throws OperationDontPossibleException {
        if (dateOfDeath != null)
            throw new OperationDontPossibleException("Author has already died");
        this.dateOfDeath = dateOfDeath;
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

    public boolean isDead() {
        return dateOfDeath != null;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfDeath=" + dateOfDeath +
                ", sex=" + sex +
                '}';
    }
}
