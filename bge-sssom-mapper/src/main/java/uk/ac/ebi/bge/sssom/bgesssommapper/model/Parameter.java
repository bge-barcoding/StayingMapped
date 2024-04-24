package uk.ac.ebi.bge.sssom.bgesssommapper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Parameter {
    SOURCE("source", true),
    TARGET("target", true),
    FIELD_MAPPING_FILE("field.mapping.file", false),
    SSSOM_INPUT_FILE("sssom.input.file", false),
    OUTPUT_PATH("output.path", true);
    private final String name;
    private final boolean mandatory;

}
