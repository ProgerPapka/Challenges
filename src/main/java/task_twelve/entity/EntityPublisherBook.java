package task_twelve.entity;

public class EntityPublisherBook {

    private int idPublisher;
    private int idBook;

    public EntityPublisherBook(int idPublisher, int idBook) {
        this.idPublisher = idPublisher;
        this.idBook = idBook;
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityPublisherBook that = (EntityPublisherBook) o;

        if (idPublisher != that.idPublisher) return false;
        return idBook == that.idBook;
    }

    @Override
    public int hashCode() {
        int result = idPublisher;
        result = 31 * result + idBook;
        return result;
    }
}
