package task_seven.parsing;

import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;
import task_seven.exception.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class EntityParser {

    private static final String NULL = "null";

    public static EntityAuthor parseAuthor(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String name = null;
        String bDay = null;
        String dDay = null;
        String sex = null;
        int id = 0;
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case EntityAuthor.ID:
                        tokenizer.nextToken();
                        id = Integer.parseInt(tokenizer.nextToken());
                        break;
                    case EntityAuthor.NAME:
                        tokenizer.nextToken();
                        name = tokenizer.nextToken();
                        break;
                    case EntityAuthor.BIRTH_DAY:
                        tokenizer.nextToken();
                        bDay = tokenizer.nextToken();
                        break;
                    case EntityAuthor.DEATH_DAY:
                        tokenizer.nextToken();
                        dDay = tokenizer.nextToken();
                        if (dDay.equals(NULL)) {
                            dDay = null;
                        }
                        break;
                    case EntityAuthor.SEX:
                        tokenizer.nextToken();
                        sex = tokenizer.nextToken();
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Authors data is incorrect.", e);
        }
        return new EntityAuthor(id, name, bDay, dDay, sex); //TODO id
    }

    public static EntityBook parseBook(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String nameB = null;
        String dateB = null;
        int id = 0;
        List<String> authorsId = new ArrayList<>();
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case EntityBook.ID:
                        tokenizer.nextToken();
                        id = Integer.parseInt(tokenizer.nextToken());
                        break;
                    case EntityBook.NAME:
                        tokenizer.nextToken();
                        nameB = tokenizer.nextToken();
                        break;
                    case EntityBook.RELEASE_DAY:
                        tokenizer.nextToken();
                        dateB = tokenizer.nextToken();
                        break;
                    case EntityBook.START_LIST_ID_AUTHORS:
                        tokenizer.nextToken();
                        String token = tokenizer.nextToken();
                        while (!token.equals(EntityBook.END_LIST_ID_AUTHORS)) {
                            authorsId.add(token);
                            token = tokenizer.nextToken();
                        }
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Books data is incorrect.", e);
        }
        return new EntityBook(id, nameB, dateB, authorsId);
    }

    public static EntityPublisher parsePublisher(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String nameP = null;
        int id = 0;
        List<String> booksId = new ArrayList<>();
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case EntityBook.ID:
                        tokenizer.nextToken();
                        id = Integer.parseInt(tokenizer.nextToken());
                        break;
                    case EntityPublisher.NAME:
                        tokenizer.nextToken();
                        nameP = tokenizer.nextToken();
                        break;
                    case EntityPublisher.START_LIST_ID_BOOKS:
                        tokenizer.nextToken();
                        String token = tokenizer.nextToken();
                        while (!token.equals(EntityPublisher.END_LIST_ID_BOOKS)) {
                            booksId.add(token);
                            token = tokenizer.nextToken();
                        }
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Publisher data is incorrect.", e);
        }
        return new EntityPublisher(id, nameP, booksId);
    }
}
