package task_seven.serialization;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;
import task_seven.exception.ParseException;
import task_seven.exception.SerializeException;
import task_seven.exception.ValidateException;
import task_seven.parsing.EntityParser;
import task_seven.representation.StringRepresentationOfObject;
import task_seven.transformation.Transformer;
import task_seven.validation.Validator;

import java.io.*;
import java.util.*;

public class TextSerializer implements ObjectSerializer {

    private final Validator validator = new Validator();
    private final Transformer transformer = new Transformer();

    private PrintStream printStream;
    private FileOutputStream fileOutputStream;
    private Scanner scanner;
    private FileReader fileReader;

    @Override
    public boolean writeAuthor(Author author, File file) throws SerializeException {
        EntityAuthor a = transformer.transformAuthorToEntity(author);
        try {
            openStreamToWrite(file);
            writeObject(a);
            closeStreamToWrite();
            return true;
        } catch (IOException e) {
            throw new SerializeException("IOException", e);
        }
    }

    @Override
    public Author readAuthor(File file) throws SerializeException {
        try {
            openStreamToRead(file);
            List<EntityAuthor> entityAuthors = new ArrayList<>();
            parseAll(new ArrayList<>(), new ArrayList<>(), entityAuthors);
            closeStreamToRead();
            EntityAuthor entity = entityAuthors.get(0);
            if (!validator.isValidAuthor(entity)) {
                throw new ValidateException("Author isn't valid!");
            }
            return new Transformer().transformEntityToAuthor(entity);
        } catch (ParseException | ValidateException | IOException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Author!", e);
        }
    }

    private void appendDataAuthor(StringBuilder values) {
        String curElement = scanner.next();
        while (!Objects.equals(curElement, EntityAuthor.END_AUTHOR)) {
            values.append(curElement).append(" ");
            curElement = scanner.next();
        }
    }

    @Override
    public boolean writeBook(Book book, File file)
            throws SerializeException {
        List<EntityAuthor> authors = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            authors.add(transformer.transformAuthorToEntity(author));
        }
        EntityBook b = transformer.transformBookToEntity(book, authors);
        try {
            openStreamToWrite(file);
            writeObject(b);
            for (EntityAuthor author : authors) {
                writeObject(author);
            }
            closeStreamToWrite();
            return true;
        } catch (IOException e) {
            throw new SerializeException("IOException", e);
        }
    }

    @Override
    public Book readBook(File file)
            throws SerializeException {
        try {
            openStreamToRead(file);
            List<EntityBook> entityBooks = new ArrayList<>();
            List<EntityAuthor> entityAuthors = new ArrayList<>();
            parseAll(new ArrayList<>(), entityBooks, entityAuthors);
            closeStreamToRead();
            EntityBook book = entityBooks.get(0);
            if (!validator.isValidBook(book)) {
                throw new ValidateException("Book isn't valid!");
            }
            validationAuthors(entityAuthors);
            return new Transformer().transformEntityToBook(book, entityAuthors);
        } catch (IOException | ValidateException | ParseException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Book!", e);
        }
    }

    @Override
    public boolean writePublisher(Publisher publisher, File file)
            throws SerializeException {
        Set<EntityAuthor> authors = new HashSet<>();
        List<EntityBook> books = new ArrayList<>();

        for (Book book : publisher.getPublishedBooks()) {
            List<EntityAuthor> bookAuthors = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                bookAuthors.add(transformer.transformAuthorToEntity(author));
            }
            books.add(transformer.transformBookToEntity(book, bookAuthors));
            authors.addAll(bookAuthors);
        }
        EntityPublisher p = transformer
                .transformPublisherToEntity(publisher, books);
        try {
            openStreamToWrite(file);
            writeObject(p);
            for (EntityBook book : books) {
                writeObject(book);
            }
            for (EntityAuthor author : authors) {
                writeObject(author);
            }
            closeStreamToWrite();
            return true;
        } catch (IOException e) {
            throw new SerializeException("IOException", e);
        }

    }

    @Override
    public Publisher readPublisher(File file)
            throws SerializeException {
        try {
            openStreamToRead(file);
            List<EntityPublisher> entityPublishers = new ArrayList<>();
            List<EntityBook> entityBooks = new ArrayList<>();
            List<EntityAuthor> entityAuthors = new ArrayList<>();
            parseAll(entityPublishers, entityBooks, entityAuthors);
            EntityPublisher publisher = entityPublishers.get(0);
            closeStreamToRead();
            if (!validator.isValidPublisher(publisher)) {
                throw new ValidateException("Publisher isn't valid!");
            }
            for (EntityBook book : entityBooks) {
                if (!validator.isValidBook(book)) {
                    throw new ValidateException("Book isn't valid!");
                }
            }
            validationAuthors(entityAuthors);
            return new Transformer().transformEntityToPublisher(
                    publisher,
                    entityBooks,
                    entityAuthors
            );
        } catch (IOException | ValidateException | ParseException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Publisher!", e);
        }
    }

    private void parseAll(List<EntityPublisher> publisher,
                          List<EntityBook> entityBooks,
                          List<EntityAuthor> entityAuthors) throws ParseException {
        while (scanner.hasNext()) {
            String curElement = scanner.next();
            StringBuilder values = new StringBuilder();
            switch (curElement) {
                case EntityPublisher.START_PUBLISHER:
                    appendDataPublisher(values);
                    publisher.add(EntityParser.parsePublisher(values.toString()));
                    break;
                case EntityBook.START_BOOK:
                    appendDataBook(values);
                    entityBooks.add(EntityParser.parseBook(values.toString()));
                    break;
                case EntityAuthor.START_AUTHOR:
                    appendDataAuthor(values);
                    entityAuthors.add(EntityParser.parseAuthor(values.toString()));
                    break;
            }
        }
    }

    private void appendDataBook(StringBuilder values) {
        String curElement = scanner.next();
        while (!Objects.equals(curElement, EntityBook.END_BOOK)) {
            values.append(curElement).append(" ");
            curElement = scanner.next();
        }
    }

    private void appendDataPublisher(StringBuilder values) {
        String curElement = scanner.next();
        while (!Objects.equals(curElement, EntityPublisher.END_PUBLISHER)) {
            values.append(curElement).append(" ");
            curElement = scanner.next();
        }
    }

    private void validationAuthors(List<EntityAuthor> entityAuthors) throws ValidateException {
        for (EntityAuthor author : entityAuthors) {
            if (!validator.isValidAuthor(author)) {
                throw new ValidateException("Author isn't valid!");
            }
        }
    }

    private void openStreamToRead(File file) throws SerializeException, IOException {
        try {
            fileReader = new FileReader(file);
            scanner = new Scanner(fileReader);
        } catch (FileNotFoundException e) {
            fileReader.close();
            System.out.println(e.getMessage());
            throw new SerializeException("Error serialize Publisher!", e);
        }

    }

    private void closeStreamToRead() throws IOException {
        fileReader.close();
    }

    private void openStreamToWrite(File file) throws SerializeException, IOException {
        try {
            fileOutputStream = new FileOutputStream(file);
            printStream = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            fileOutputStream.close();
            System.out.println(e.getMessage());
            throw new SerializeException("Error serialize Publisher!", e);
        }
    }

    private void closeStreamToWrite() throws IOException {
        fileOutputStream.close();
        printStream.close();
    }

    private void writeObject(StringRepresentationOfObject o) {
        printStream.println(o.valueAsString());
    }

}
