package uk.ac.ebi.bge.sssom.bgesssommapper.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sssom Mapping class
 * It should be used only for converting the two column mapping to SSSOM TSV
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sssom {

    private final static String BCDM_FIELD = "bcdm_field";
    private final static String BCDM_TYPE = "bcdm_type";
    private final static String BCDM_FORMAT = "bcdm_format";

    private final static String ENA_FIELD = "ena_field";
    private final static String ENA_TYPE = "ena_field";
    private final static String ENA_FORMAT = "ena_format";

    private final static String DWC_FIELD = "dwc_field";
    private final static String DWC_TYPE = "dwc_type";
    private final static String DWC_FORMAT = "dwc_format";

    private final static String UNITE_FIELD = "unite_field";
    private final static String UNITE_TYPE = "unite_type";
    private final static String UNITE_FORMAT = "unite_format";

    private final static String IBOL_FIELD = "iboleu_field";
    private final static String IBOL_TYPE = "iboleu_type";
    private final static String IBOL_FORMAT = "iboleu_format";


    @JsonAlias({BCDM_FIELD, ENA_FIELD})
    private String sourceField;
    @JsonAlias({DWC_FIELD, UNITE_FIELD, IBOL_FIELD})
    private String targetField;
    @JsonAlias({BCDM_TYPE, ENA_TYPE})
    private String sourceType;
    @JsonAlias({DWC_TYPE, UNITE_TYPE, IBOL_TYPE})
    private String targetType;
    @JsonAlias({BCDM_FORMAT, ENA_FORMAT})
    private String sourceFormat;
    @JsonAlias({DWC_FORMAT, UNITE_FORMAT, IBOL_FORMAT})
    private String targetFormat;

}
