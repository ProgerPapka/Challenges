package task_four.second.validation;

import task_four.second.domain.Author;
import task_four.second.entity.EntityAuthor;
import task_four.second.entity.EntityBook;
import task_four.second.entity.EntityPublisher;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    public static boolean isValidAuthor(EntityAuthor author) {
        if (author.getName() == null ||
                author.getDateOfBirth() == null) {
            return false;
        }
        try {
            LocalDate.parse(author.getDateOfBirth());
            if (author.getDateOfDeath() != null) {
                LocalDate.parse(author.getDateOfDeath());
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return author.getSex().equals(Author.Sex.MALE.name()) ||
                author.getSex().equals(Author.Sex.FEMALE.name());
    }

    public static boolean isValidBook(EntityBook book) {
        if (book.getName() == null || book.getReleaseDate() == null ||
                book.getAuthors().size() == 0){
            return false;
        }
        try {
            LocalDate.parse(book.getReleaseDate());
        } catch (DateTimeParseException e){
            return false;
        }
        for (EntityAuthor author : book.getAuthors()){
            if(!isValidAuthor(author))
                return false;
        }
        return true;
    }

    public static boolean isValidPublisher(EntityPublisher publisher) {
        if(publisher.getName() == null || publisher.getBooks().size() == 0){
            return false;
        }
        for(EntityBook book : publisher.getBooks()){
            if(!isValidBook(book)){
                return false;
            }
        }
        return true;
    }
}
