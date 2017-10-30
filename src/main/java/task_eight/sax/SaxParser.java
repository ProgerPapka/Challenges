package task_eight.sax;

import org.apache.log4j.Logger;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import task_four.second.domain.Publisher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaxParser {
    private static Logger logger = Logger.getLogger(SaxParser.class);


    public Publisher parsePublisher(String xml) {
        try {
            SaxHandler saxHandler = new SaxHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(saxHandler);
            reader.parse(new InputSource(new FileInputStream(xml)));
            return saxHandler.getPublisher();
        } catch (SAXException e) {
            logger.error("Error parsing!", e);
        } catch (FileNotFoundException e) {
            logger.error("File don't found!", e);
        } catch (IOException e) {
            logger.error("Error data stream!",e);
        }
        return null;
    }

}
