package task_seven.validation;

import task_four.second.domain.Author;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    public boolean isValidAuthor(EntityAuthor author) {
        if(author == null){
            return false;
        }
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

    public boolean isValidBook(EntityBook book) {
        if (book == null){
            return false;
        }
        if (book.getName() == null || book.getReleaseDate() == null ||
                book.getListIdAuthors().isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(book.getReleaseDate());
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isValidPublisher(EntityPublisher publisher) {
        if(publisher == null){
            return false;
        }
        if (publisher.getName() == null || publisher.getListIdBooks().isEmpty()) {
            return false;
        }
        return true;
    }
}
