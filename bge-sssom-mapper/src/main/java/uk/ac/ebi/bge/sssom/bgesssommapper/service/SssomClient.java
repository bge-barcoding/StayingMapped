package uk.ac.ebi.bge.sssom.bgesssommapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.incenp.obofoundry.sssom.TSVReader;
import org.incenp.obofoundry.sssom.model.MappingSet;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

@Component
public class SssomClient {

    private final ObjectMapper objectMapper;

    public SssomClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void process(ApplicationArguments args) {
        System.out.println("converting the SSSOM input format (tsv) to JSON");
        try {
            String source = args.getOptionValues(Parameter.SOURCE.getName()).get(0);
            String target = args.getOptionValues(Parameter.TARGET.getName()).get(0);
            String mappingFile = args.getOptionValues(Parameter.SSSOM_INPUT_FILE.getName()).get(0);
            String outputPath = args.getOptionValues(Parameter.OUTPUT_PATH.getName()).get(0);
            System.out.println("SSSOM mapping file: " + mappingFile);
            File file = new File(mappingFile);
            InputStream metaFile = SssomClient.class.getResourceAsStream("/sssom-metadata" + File.separator
                    + source.toLowerCase() + "-"
                    + target.toLowerCase()
                    + "-mapping-meta.yml");
            TSVReader tsvReader = new TSVReader(new FileInputStream(file), metaFile);
            MappingSet mappingSet = tsvReader.read(true);
            String fileName = outputPath + File.separator + source + "-" + target + "-mapping-output.json";
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                System.out.println("Output JSON path: " + fileName);
                fileWriter.write(objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(mappingSet.getMappings()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
