package task_twelve.entity;

import java.time.LocalDate;

public class EntityAuthor {
    private int id;
    private String name;
    private LocalDate bDay;
    private LocalDate dDay;
    private String sex;

    public EntityAuthor(int id, String name, LocalDate bDay, LocalDate dDay, String sex) {
        this.id = id;
        this.name = name;
        this.bDay = bDay;
        this.dDay = dDay;
        this.sex = sex;
    }

    public EntityAuthor(int id, String name, LocalDate bDay, String sex) {
        this(id,name,bDay,null,sex);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getbDay() {
        return bDay;
    }

    public void setbDay(LocalDate bDay) {
        this.bDay = bDay;
    }

    public LocalDate getdDay() {
        return dDay;
    }

    public void setdDay(LocalDate dDay) {
        this.dDay = dDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityAuthor author = (EntityAuthor) o;

        if (name != null ? !name.equals(author.name) : author.name != null)
            return false;
        if (bDay != null ? !bDay.equals(author.bDay) : author.bDay != null)
            return false;
        if (dDay != null ? !dDay.equals(author.dDay) : author.dDay != null)
            return false;
        return sex != null ? sex.equals(author.sex) : author.sex == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (bDay != null ? bDay.hashCode() : 0);
        result = 31 * result + (dDay != null ? dDay.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
