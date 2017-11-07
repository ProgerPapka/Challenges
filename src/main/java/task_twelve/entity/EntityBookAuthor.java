package task_twelve.entity;

public class EntityBookAuthor {

    private int idBook;
    private int idAuthor;

    public EntityBookAuthor(int idBook, int idAuthor) {

        this.idBook = idBook;
        this.idAuthor = idAuthor;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBookAuthor that = (EntityBookAuthor) o;

        if (idBook != that.idBook) return false;
        return idAuthor == that.idAuthor;
    }

    @Override
    public int hashCode() {
        int result = idBook;
        result = 31 * result + idAuthor;
        return result;
    }
}
