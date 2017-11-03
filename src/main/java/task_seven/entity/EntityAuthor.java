package task_seven.entity;

import task_seven.representation.EntityAuthorAsString;

import java.io.Serializable;

public class EntityAuthor implements Serializable, EntityAuthorAsString {

    private int id;
    private final String name;
    private final String dateOfBirth;
    private String dateOfDeath;
    private final String sex;

    public EntityAuthor(int id, String name, String dateOfBirth, String dateOfDeath, String sex) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.sex = sex;
    }

    public EntityAuthor(String name, String dateOfBirth, String dateOfDeath, String sex) {
        this(0,name,dateOfBirth,dateOfDeath,sex);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

        EntityAuthor author = (EntityAuthor) o;

        if (!name.equals(author.name)) return false;
        if (!dateOfBirth.equals(author.dateOfBirth)) return false;
        if (dateOfDeath != null ? !dateOfDeath.equals(author.dateOfDeath) : author.dateOfDeath != null)
            return false;
        return sex.equals(author.sex);
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
        return "EntityAuthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfDeath='" + dateOfDeath + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public String valueAsString() {
        StringBuilder res = new StringBuilder();
        res.append(START_AUTHOR).append("\n\t");
        res.append(ID).append(EQUALITY).append(id).append("\n\t");
        res.append(NAME).append(EQUALITY).append(name).append("\n\t");
        res.append(BIRTH_DAY).append(EQUALITY).append(dateOfBirth).append("\n\t");
        res.append(DEATH_DAY).append(EQUALITY).append(dateOfDeath).append("\n\t");
        res.append(SEX).append(EQUALITY).append(sex).append("\n");
        res.append(END_AUTHOR);
        return res.toString();
    }
}
