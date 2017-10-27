package task_seven.serialization;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.exception.SerializeException;

import java.io.*;

public class StandardSerializer implements ObjectSerializer {

    @Override
    public void writeAuthor(Author author, File file)
            throws SerializeException{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file))) {
            outputStream.writeObject(author);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Author readAuthor(File file)
            throws SerializeException{
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file)
        )) {
            return (Author) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void writeBook(Book book, File file)
            throws SerializeException{
        try (ObjectOutputStream inputStream = new ObjectOutputStream(
                new FileOutputStream(file)
        )) {
            inputStream.writeObject(book);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Book readBook(File file)
            throws SerializeException{
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file)
        )) {
            return (Book) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void writePublisher(Publisher publisher, File file)
            throws SerializeException{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file)
        )) {
            outputStream.writeObject(publisher);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Publisher readPublisher(File file)
            throws SerializeException {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file)
        )) {
            return (Publisher) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
