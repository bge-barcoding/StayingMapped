package uk.ac.ebi.bge.sssom.bgesssommapper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Entity {
    ENA("ena"),
    BCDM("bcdm"),
    DWC("dwc"),
    UNITE("unite");
    private final String name;

}
