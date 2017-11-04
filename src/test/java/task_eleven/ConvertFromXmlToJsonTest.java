package task_eleven;

import org.junit.Test;
import task_eight.Parser;
import task_eight.dom.DomParser;

import static org.junit.Assert.*;

public class ConvertFromXmlToJsonTest {

    @Test
    public void convertPublisher() throws Exception {
        String pathJson = "D:\\json\\publisher.json";
        String pathXml = "src\\main\\resources\\thebestofpublisher.xml";
        Parser parser = new DomParser();
        ConvertFromXmlToJson convert = new ConvertFromXmlToJson();
        assertTrue(convert.convertPublisher(pathXml,parser,pathJson));
    }

}