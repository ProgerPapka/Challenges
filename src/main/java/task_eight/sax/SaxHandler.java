package task_eight.sax;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private Logger logger = Logger.getLogger(SaxHandler.class);

    private boolean levelPublisher = false;
    private boolean levelBook = false;
    private boolean levelAuthor = false;
    private String thisElement;

    private List<Author> authors;
    private String namePublisher;
    private List<Book> books;

    private String nameBook;
    private String releaseDate;
    private String nameAuthor;
    private String bDay;
    private String dDay;
    private String sex;

    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    public SaxHandler() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        books = new ArrayList<>();
        logger.info("SaxParser start parse XML...");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("SaxParser stop parse XML...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        switch (thisElement){
            case "publisher":
                levelPublisher = true;
                namePublisher = attributes.getValue("name");
                break;
            case "book":
                levelBook = true;
                authors = new ArrayList<>();
                break;
            case "author":
                levelBook = false;
                levelAuthor = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "publisher":
                if(namePublisher != null && books != null){
                    publisher = new Publisher(namePublisher,books);
                }
                levelPublisher = false;
                namePublisher = null;
                books = null;
                authors = null;
                break;
            case "book":
                books.add(new Book(nameBook,
                        LocalDate.parse(releaseDate), authors));
                levelBook = false;
                nameBook = null;
                releaseDate = null;
                break;
            case "author":
                LocalDate date = null;
                if(dDay != null){
                    date = LocalDate.parse(dDay);
                }
                authors.add(new Author(nameAuthor, LocalDate.parse(bDay),
                        date, Author.Sex.valueOf(sex)));
                levelAuthor = false;
                nameAuthor = null;
                bDay = null;
                dDay = null;
                sex = null;
                break;
        }
        thisElement = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(thisElement == null){
            return;
        }
        if(levelBook){
            switch (thisElement){
                case "name":
                    nameBook = new String(ch,start,length);
                    break;
                case "release_date":
                    releaseDate = new String(ch,start,length);
                    break;
            }
        }
        if(levelAuthor){
            switch (thisElement){
                case "name":
                    nameAuthor = new String(ch,start,length);
                    break;
                case "birth_date":
                    bDay = new String(ch,start,length);
                    break;
                case "death_date":
                    dDay = new String(ch,start,length);
                    break;
                case "sex":
                    sex = new String(ch,start,length);
                    break;
            }
        }
    }
}
