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

    public static EntityAuthor parseAuthor(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String name = null;
        String bDay = null;
        String dDay = null;
        String sex = null;
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case "name":
                        tokenizer.nextToken();
                        name = tokenizer.nextToken();
                        break;
                    case "dateOfBirth":
                        tokenizer.nextToken();
                        bDay = tokenizer.nextToken();
                        break;
                    case "dateOfDeath":
                        tokenizer.nextToken();
                        dDay = tokenizer.nextToken();
                        if (dDay.equals("null")) {
                            dDay = null;
                        }
                        break;
                    case "sex":
                        tokenizer.nextToken();
                        sex = tokenizer.nextToken();
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Authors data is incorrect.", e);
        }
        return new EntityAuthor(name, bDay, dDay, sex);
    }

    public static EntityBook parseBook(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String nameB = null;
        String dateB = null;
        List<String> authorsId = new ArrayList<>();
        List<EntityAuthor> authors = new ArrayList<>();
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case "Book":
                        String token = tokenizer.nextToken();
                        while (!token.equals("endBook")) {
                            switch (token) {
                                case "name":
                                    tokenizer.nextToken();
                                    nameB = tokenizer.nextToken();
                                    break;
                                case "releaseDate":
                                    tokenizer.nextToken();
                                    dateB = tokenizer.nextToken();
                                    break;
                                case "ListIdAuthors":
                                    tokenizer.nextToken();
                                    token = tokenizer.nextToken();
                                    while (!token.equals("}")) {
                                        authorsId.add(token);
                                        token = tokenizer.nextToken();
                                    }
                                    break;
                            }
                            token = tokenizer.nextToken();
                        }
                        break;
                    case "Author":
                        String t = tokenizer.nextToken();
                        StringBuilder q = new StringBuilder();
                        while (!t.equals("endAuthor")) {
                            q.append(t).append(" ");
                            t = tokenizer.nextToken();
                        }
                        authors.add(EntityParser.parseAuthor(q.toString()));
                        break;
                }
            }
        } catch (NoSuchElementException | ParseException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Books data is incorrect.", e);
        }
        return new EntityBook(nameB, dateB, authors);
    }

    public static EntityPublisher parsePublisher(String text)
            throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(text);
        String nameP = null;
        List<String> booksId = new ArrayList<>();
        List<EntityAuthor> authors = new ArrayList<>();
        List<EntityBook> books = new ArrayList<>();
        try {
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case "Publisher":
                        String token = tokenizer.nextToken();
                        while (!token.equals("endPublisher")) {
                            switch (token) {
                                case "name":
                                    tokenizer.nextToken();
                                    nameP = tokenizer.nextToken();
                                    break;
                                case "ListIdBooks":
                                    tokenizer.nextToken();
                                    token = tokenizer.nextToken();
                                    while (!token.equals("}")) {
                                        booksId.add(token);
                                        token = tokenizer.nextToken();
                                    }
                                    break;
                            }
                            token = tokenizer.nextToken();
                        }
                        break;
                    case "Book":
                        String nameB = null;
                        String dateB = null;
                        List<String> authorsId = new ArrayList<>();
                        String tok = tokenizer.nextToken();
                        while (!tok.equals("endBook")) {
                            switch (tok) {
                                case "name":
                                    tokenizer.nextToken();
                                    nameB = tokenizer.nextToken();
                                    break;
                                case "releaseDate":
                                    tokenizer.nextToken();
                                    dateB = tokenizer.nextToken();
                                    break;
                                case "ListIdAuthors":
                                    tokenizer.nextToken();
                                    token = tokenizer.nextToken();
                                    while (!token.equals("}")) {
                                        authorsId.add(token);
                                        token = tokenizer.nextToken();
                                    }
                                    break;
                            }
                            tok = tokenizer.nextToken();
                        }
                        EntityBook b = new EntityBook(nameB, dateB);
                        b.setListIdAuthors(authorsId);
                        books.add(b);
                        break;
                    case "Author":
                        String t = tokenizer.nextToken();
                        StringBuilder q = new StringBuilder();
                        while (!t.equals("endAuthor")) {
                            q.append(t).append(" ");
                            t = tokenizer.nextToken();
                        }
                        authors.add(EntityParser.parseAuthor(q.toString()));
                        break;
                }
            }
        } catch (NoSuchElementException | ParseException e) {
            System.out.println(e.getMessage());
            throw new ParseException("Publisher data is incorrect.", e);
        }
        for (EntityAuthor author : authors){
            for (EntityBook book : books){
                if(book.getListIdAuthors().contains(
                        String.valueOf(author.hashCode()))){
                    book.addAuthor(author);
                }
            }
        }
        return new EntityPublisher(nameP, books);
    }
}
