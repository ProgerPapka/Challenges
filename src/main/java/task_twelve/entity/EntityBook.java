package task_twelve.entity;

import java.time.LocalDate;

public class EntityBook {

    private int id;
    private String name;
    private LocalDate rDay;

    public EntityBook(int id, String name, LocalDate rDay) {
        this.id = id;
        this.name = name;
        this.rDay = rDay;
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

    public LocalDate getrDay() {
        return rDay;
    }

    public void setrDay(LocalDate rDay) {
        this.rDay = rDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBook book = (EntityBook) o;

        if (name != null ? !name.equals(book.name) : book.name != null)
            return false;
        return rDay != null ? rDay.equals(book.rDay) : book.rDay == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (rDay != null ? rDay.hashCode() : 0);
        return result;
    }
}
