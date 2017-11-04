package task_seven.serialization;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.exception.SerializeException;

import java.io.*;

public class StandardSerializer implements ObjectSerializer {

    @Override
    public boolean writeAuthor(Author author, File file)
            throws SerializeException {
        return writeObject(author, file);
    }

    @Override
    public Author readAuthor(File file)
            throws SerializeException {
        Object author = readObject(file);
        return (author != null) ? (Author) author : null;
    }

    @Override
    public boolean writeBook(Book book, File file)
            throws SerializeException {
        return writeObject(book, file);
    }

    @Override
    public Book readBook(File file)
            throws SerializeException {
        Object book = readObject(file);
        return (book != null) ? (Book) book : null;
    }

    @Override
    public boolean writePublisher(Publisher publisher, File file)
            throws SerializeException {
        return writeObject(publisher, file);
    }

    @Override
    public Publisher readPublisher(File file)
            throws SerializeException {
        Object publisher = readObject(file);
        return (publisher != null) ? (Publisher) publisher : null;
    }

    private boolean writeObject(Object object, File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file))) {
            outputStream.writeObject(object);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Object readObject(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file)
        )) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
