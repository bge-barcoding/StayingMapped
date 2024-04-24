# SSSOM Mapper

### Pre-requisite
* Java 17

### How to run?
Do you have the SSSOM input tsv already? Please refer SSSOM TSV to JSON
##### Two column mapping to SSSOM TSV to JSON
```
java -jar bge-sssom-mapper-0.0.1.jar \
--source=BCDM \
--target=DWC \
--field.mapping.file=<field-mapping-file-path>/<field-mapping-file-name>.tsv \
--sssom.input.file=<sssom-input-file-path>/<sssom-input-file-name>.tsv \
--output.path=<output-path>
```
E.g
* field-mapping-file-name = mapping_BCDM_to_DWC.tsv
* sssom-input-file-name = bcdm-dwc-sssom-mapping.tsv

##### SSSOM TSV to JSON

```
java -jar bge-sssom-mapper-0.0.1.jar \
--source=BCDM \
--target=UNITE \ 
--sssom.input.file=<sssom-input-file-path>/<sssom-input-file-name>.tsv \ 
--output.path=<output-path>
```
E.g
* sssom-input-file-name = bcdm-unite-sssom-mapping.tsv
