package uk.ac.ebi.bge.sssom.bgesssommapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Entity;
import uk.ac.ebi.bge.sssom.bgesssommapper.model.Parameter;
import uk.ac.ebi.bge.sssom.bgesssommapper.service.MapperService;
import uk.ac.ebi.bge.sssom.bgesssommapper.service.SssomClient;

import java.util.Arrays;

@Component
@Slf4j
public class MainRunner implements ApplicationRunner {

    private final SssomClient sssomClient;

    private final MapperService mapperService;

    public MainRunner(SssomClient sssomClient, MapperService mapperService) {
        this.sssomClient = sssomClient;
        this.mapperService = mapperService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Given Input");
        args.getOptionNames().forEach(option -> {
            System.out.println(option + " = " + args.getOptionValues(option).get(0));
        });
        boolean skipFieldMapping = validateParameters(args);
        if (!skipFieldMapping) {
            System.out.println("converting the fields mapping to SSSOM input format (tsv)");
            mapperService.convertMapperToSssomMapping(args);
        }
        sssomClient.process(args);
    }


    /**
     * Validate parameters
     *
     * @param args - Application Parameters
     * @return
     */
    private boolean validateParameters(ApplicationArguments args) throws Exception {
        boolean skipFieldMapping = false;
        // Validate all parameters
        for (String option : args.getOptionNames()) {
            try {
                Parameter.valueOf(option.replace(".", "_").toUpperCase());
            } catch (IllegalArgumentException exception) {
                throw new Exception("Invalid Parameter : " + option);
            }
        }

        // Validate mandatory fields
        for (Parameter mandatoryParam : Parameter.values()) {
            if (mandatoryParam.isMandatory()) {
                if (!args.getOptionNames().contains(mandatoryParam.getName())) {
                    throw new Exception("Missing Parameter : " + mandatoryParam.getName());
                }
            }
        }
        if (CollectionUtils.isEmpty(args.getOptionValues("field.mapping.file")) ||
                !StringUtils.hasLength(args.getOptionValues("field.mapping.file").get(0))) {
            skipFieldMapping = true;
        }

        // Validate entity
        String entityStr = args.getOptionValues("source").get(0).toUpperCase();
        try {
            Entity.valueOf(entityStr);
        } catch (IllegalArgumentException exception) {
            throw new Exception("Invalid source/target given: " + entityStr +
                    "Supported formats are " + Arrays.toString(Entity.values()));
        }
        return skipFieldMapping;
    }

}
