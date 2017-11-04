package task_eleven;

import org.apache.log4j.Logger;
import task_eight.Parser;
import task_four.second.domain.Publisher;

import java.io.File;
import java.io.IOException;

public class ConvertFromXmlToJson {

    private Logger logger = Logger.getLogger(ConvertFromXmlToJson.class);
    private ConverterToJson converter = new ConverterToJson();

    public boolean convertPublisher(String pathXml, Parser parser,
                                    String pathJson) {
        Publisher publisher = parser.parsePublisher(pathXml);
        if (publisher == null)
            return false;
        File file = new File(pathJson);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            return converter.convertPublisherToJSON(publisher,file);
        } catch (IOException e) {
            logger.error(e);
            return false;
        }
    }

}
