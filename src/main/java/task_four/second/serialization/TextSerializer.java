package task_four.second.serialization;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_four.second.entity.EntityAuthor;
import task_four.second.entity.EntityBook;
import task_four.second.entity.EntityPublisher;
import task_four.second.exception.ParseException;
import task_four.second.exception.SerializeException;
import task_four.second.exception.ValidateException;
import task_four.second.parsing.EntityParser;
import task_four.second.transformation.Transformer;
import task_four.second.validation.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextSerializer implements ObjectSerializer {

    @Override
    public void writeAuthor(Author author, File file)
            throws SerializeException {
        try (PrintStream printStream = new PrintStream(
                new FileOutputStream(file)
        )) {
            EntityAuthor a = Transformer.transformAuthorToEntity(author);
            printStream.print(a.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error serialize Author!", e);
        }
    }

    @Override
    public Author readAuthor(File file)
            throws SerializeException {
        try (FileReader reader = new FileReader(file)) {
            String allFile = new String(
                    Files.readAllBytes(Paths.get(file.toURI())));
            EntityAuthor entity = EntityParser.parseAuthor(allFile);
            if (!Validator.isValidAuthor(entity)) {
                throw new ValidateException("Author isn't valid!");
            }
            return Transformer.transformEntityToAuthor(entity);
        } catch (IOException | ParseException | ValidateException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Author!", e);
        }
    }

    @Override
    public void writeBook(Book book, File file)
            throws SerializeException {
        try (PrintStream printStream = new PrintStream(
                new FileOutputStream(file)
        )) {
            EntityBook b = Transformer.transformBookToEntity(book);
            printStream.print(b);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error serialize Book!", e);
        }
    }

    @Override
    public Book readBook(File file)
            throws SerializeException {
        try (FileReader reader = new FileReader(file)) {
            String allFile = new String(
                    Files.readAllBytes(Paths.get(file.toURI())));
            EntityBook entity = EntityParser.parseBook(allFile);
            if (!Validator.isValidBook(entity)) {
                throw new ValidateException("Book isn't valid!");
            }
            return Transformer.transformEntityToBook(entity);
        } catch (IOException | ValidateException | ParseException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Book!", e);
        }
    }

    @Override
    public void writePublisher(Publisher publisher, File file)
            throws SerializeException {
        try (PrintStream printStream = new PrintStream(
                new FileOutputStream(file)
        )) {
            EntityPublisher p = Transformer
                    .transformPublisherToEntity(publisher);
            printStream.print(p.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error serialize Publisher!", e);
        }
    }

    @Override
    public Publisher readPublisher(File file)
            throws SerializeException {
        try (FileReader reader = new FileReader(file)) {
            String allFile = new String(
                    Files.readAllBytes(Paths.get(file.toURI())));
            EntityPublisher entity = EntityParser.parsePublisher(allFile);
            if (!Validator.isValidPublisher(entity)) {
                throw new ValidateException("Publisher isn't valid!");
            }
            return Transformer.transformEntityToPublisher(entity);
        } catch (IOException | ValidateException | ParseException e) {
            System.out.println(e.getMessage());
            throw new SerializeException("Error deserialize Publisher!", e);
        }
    }

}
