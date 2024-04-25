package uk.ac.ebi.bge.sssom.bgesssommapper.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Entity;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Parameter;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Sssom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {

    public void convertMapperToSssomMapping(ApplicationArguments args) {
        Entity source = Entity.valueOf(args.getOptionValues(Parameter.SOURCE.getName()).get(0).toUpperCase());
        Entity target = Entity.valueOf(args.getOptionValues(Parameter.TARGET.getName()).get(0));
        String fieldMappingFile = args.getOptionValues(Parameter.FIELD_MAPPING_FILE.getName()).get(0);
        String sssomMappingFile = args.getOptionValues(Parameter.SSSOM_INPUT_FILE.getName()).get(0);

        CsvSchema schema = CsvSchema.builder()
                .addColumn(source.getName() + "_field")
                .addColumn(target.getName() + "_field")
                .addColumn(source.getName() + "_type")
                .addColumn(source.getName() + "_format")
                .addColumn(target.getName() + "_format")
                .addColumn(target.getName() + "_type")
                .setColumnSeparator('\t')
                .build();

        List<Sssom> sssoms;
        try {
            sssoms = convertTsvToObject(new FileInputStream(fieldMappingFile), schema);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fileWriter = new FileWriter(sssomMappingFile)) {
            fileWriter.write("subject_id\tsubject_label\tpredicate_id\tobject_id\tobject_label\tmapping_justification\tmapping_date\tauthor_id\tsubject_source\tsubject_source_version\tobject_source\tobject_source_version\tconfidence\tcomment\n");
            for (Sssom sssom : sssoms) {
                fileWriter.write(
                        (StringUtils.hasLength(sssom.getSourceField())
                                ? source.getName() + ":" + sssom.getSourceField() : "") + "\t"
                                + (StringUtils.hasLength(sssom.getSourceField())
                                ? source.getName() + ":" + sssom.getSourceField() : "") + "\t"
                                + "skos:exactMatch" + "\t"
                                + (StringUtils.hasLength(sssom.getTargetField())
                                ? target.getName() + ":" + sssom.getTargetField() : "") + "\t"
                                + (StringUtils.hasLength(sssom.getSourceField())
                                ? target.getName() + ":" + sssom.getTargetField() : "") + "\t"
                                + "semapv:ManualMappingCuration" + "\t"
                                + "2022-01-01" + "\t"
                                + "0000-1234-0000-1234" + "\t"
                                + "bcdm_bold_uri" + "\t"
                                + 1 + "\t"
                                + "https://dwc.tdwg.org/terms/" + "\t"
                                + 1 + "\t"
                                + 0.95 + "\t"
                                + "string default for bcdm coming from frictionless data"
                                + "\n"
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sssom> convertTsvToObject(InputStream fieldMappingFile, CsvSchema schema) {
        List<Sssom> objects = new ArrayList<>();
        try {
            CsvMapper mapper = new CsvMapper();
            MappingIterator<Sssom> it = mapper.readerFor(Sssom.class).with(schema).readValues(fieldMappingFile);
            while (it.hasNext()) {
                Sssom obj = it.next();
                objects.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }
}
