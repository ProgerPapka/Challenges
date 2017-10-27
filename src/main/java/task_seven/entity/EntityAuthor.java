package task_seven.entity;

import task_four.second.domain.Author;

import java.io.Serializable;
import java.time.LocalDate;

public class EntityAuthor implements Serializable {

    private String name;
    private String dateOfBirth;
    private String dateOfDeath;
    private String sex;

    public EntityAuthor(String name, String dateOfBirth, String dateOfDeath, String sex) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityAuthor that = (EntityAuthor) o;

        if (!name.equals(that.name)) return false;
        if (!dateOfBirth.equals(that.dateOfBirth)) return false;
        if (dateOfDeath != null ? !dateOfDeath.equals(that.dateOfDeath) : that.dateOfDeath != null)
            return false;
        return sex.equals(that.sex);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + (dateOfDeath != null ? dateOfDeath.hashCode() : 0);
        result = 31 * result + sex.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Author \n\t");
        res.append("id = ");
        res.append(hashCode());
        res.append("\n\t");
        res.append("name = ");
        res.append(name);
        res.append("\n\t");
        res.append("dateOfBirth = ");
        res.append(dateOfBirth);
        res.append("\n\t");
        res.append("dateOfDeath = ");
        res.append(dateOfDeath);
        res.append("\n\t");
        res.append("sex = ");
        res.append(sex);
        res.append('\n');
        res.append("endAuthor");
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new EntityAuthor("Igor",
                LocalDate.of(1895, 12, 1).toString(),
                LocalDate.of(1990, 1, 12).toString(),
                Author.Sex.MALE.name()
        ).toString());
    }
}
