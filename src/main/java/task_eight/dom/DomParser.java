package task_eight.dom;

import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    private static Logger logger = Logger.getLogger(DomParser.class);

    private List<Author> authors;
    private List<Book> books = new ArrayList<>();
    private String namePublisher;

    public Publisher parsePublisher(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xml));
            logger.info("DomParser start parse XML...");
            NodeList nodeList = doc.getChildNodes();
            parsingChildNodes(nodeList);
            logger.info("DomParser stop parse XML...");
            return new Publisher(namePublisher,books);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage(), e);
        } catch (Throwable e){
            logger.fatal(e.getMessage(),e);
        }
        return null;
    }

    private void parsingChildNodes(NodeList nodes) {
        if (nodes != null) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeName().equals("book")){
                    authors = new ArrayList<>();
                }
                parsingChildNodes(node.getChildNodes());
                switch (node.getNodeName()) {
                    case "author":
                        authors.add(parseAuthor(node.getChildNodes()));
                        break;
                    case "book":
                        books.add(parseBook(node.getChildNodes(), authors));
                        break;
                    case "publisher":
                        namePublisher = node.getAttributes()
                                .getNamedItem("name").getNodeValue();
                        break;
                }

            }
        }
    }

    private Author parseAuthor(NodeList childNodes) {
        String name = null;
        String bDay = null;
        String dDay = null;
        String sex = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            switch (node.getNodeName()) {
                case "name":
                    name = node.getFirstChild().getNodeValue();
                    break;
                case "birth_date":
                    bDay = node.getFirstChild().getNodeValue();
                    break;
                case "death_date":
                    dDay = node.getFirstChild().getNodeValue();
                    break;
                case "sex":
                    sex = node.getFirstChild().getNodeValue();
                    break;
            }
        }
        LocalDate date = null;
        if (dDay != null) {
            date = LocalDate.parse(dDay);
        }
        return new Author(name, LocalDate.parse(bDay),
                date, Author.Sex.valueOf(sex));
    }

    private Book parseBook(NodeList childNodes, List<Author> authors) {
        String name = null;
        String rDate = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            switch (node.getNodeName()) {
                case "name":
                    name = node.getFirstChild().getNodeValue();
                    break;
                case "release_date":
                    rDate = node.getFirstChild().getNodeValue();
                    break;
            }
        }
        return new Book(name,LocalDate.parse(rDate),authors);
    }
}
