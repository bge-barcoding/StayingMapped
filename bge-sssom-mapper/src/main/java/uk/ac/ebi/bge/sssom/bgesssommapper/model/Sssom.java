package uk.ac.ebi.bge.sssom.bgesssommapper.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sssom {

    @JsonAlias("bcdm_field")
    private String sourceField;
    @JsonAlias({"dwc_field", "unite_field"})
    private String targetField;
    @JsonAlias("bcdm_type")
    private String sourceType;
    @JsonAlias({"dwc_type", "unite_type"})
    private String targetType;
    @JsonAlias("bcdm_format")
    private String sourceFormat;
    @JsonAlias({"dwc_format", "unite_format"})
    private String targetFormat;

}
