package task_eight;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ValidationXmlByXsd {

    private static Logger logger = Logger.getLogger(ValidationXmlByXsd.class);

    public static boolean validateXml(String xsd_path, String xml_path) {
        try {
            File schemaFile = Paths.get(xsd_path).toFile();
            File dataFile = Paths.get(xml_path).toFile();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new FileInputStream(dataFile)));
            logger.info("XML valid by XSD!");
            return true;
        } catch (SAXException e) {
            logger.error("SAX Error!", e);
            return false;
        } catch (IOException e) {
            logger.error("IO Error", e);
            return false;
        }
    }
}
