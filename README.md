# StayingMapped
Mapping between different standards relevant to BGE

This repository contains scripts and files to facilitate the process of mapping data between different standards relevant BGE. 

**NOTE:** The mapping files are a work in progress. The values and structure reflect initial ideas that emerged during the April 2024 hackathon. Further discussion will take place on this matter. We are also exploring use cases and understanding where in the data flow these mapping files will be useful. 

Currently, we have based our work on creating a common 'Excel'-like structure comprised of a delimited text-based format. From this base file, which can be a format common to all mappings in BGE, we can utilise the Java tool (https://github.com/bge-bioscan/StayingMapped/tree/main/bge-sssom-mapper) to generate the desired output, in this instance, JSON format. 

**Motivation for SSSOM-Based Mapping:**

- **Standardization and Interoperability:** 
    * We currently have existing mappings in TSV and Excel formats. 
    * By structuring these mappings using the SSSOM, we can achieve greater standardization across different mapping efforts.
    * This standardization promotes better interoperability between tools and systems. 

- **FAIR Principles:** 
    * SSSOM provides a framework for capturing metadata and context around the mappings. 
    * This alings with the FAIR (Findable, Accessible, Interoperable, Reusable) principles, making the mappings more discoverable and usable by researchers and developers. 

- **Machine Readability:** 
    * The structured nature of SSSOM allows machines to understand the relationships between BGE-related data elements across various standards. 
    * This enables the development of automated tools that can leverage these mappings for data translation and integration.




Existing resources: 

- [BCDM mappings](https://github.com/DNAdiversity/BCDM/tree/main)
- [A DwC to MIxS mapping](https://github.com/tdwg/gbwg/tree/main/dwc-mixs/mapping)
- SSSOM: [Datamodel for Simple Standard for Sharing Ontological Mappings](https://mapping-commons.github.io/sssom/)


Example: 
BCDM to DwC 

```
{
      "subject_id": "bcdm:process_id",
      "subject_label": "bcdm:process_id",
      "predicate_id": "skos:exactMatch",  # Assuming this is the intended predicate
      "object_id": "dwc:materialSampleID",
      "object_label": "dwc:materialSampleID",
      "mapping_justification": "semapv:ManualMappingCuration",
      "mapping_date": "2022-01-01",  # YYYY-MM-DD format
      "author_id": "https://orcid.org/0000-1234-0000-1234",  # Replace with your ORCID
      "subject_source": "bcdm_bold_uri",  # Assuming this is the BCDM schema URI
      "subject_source_version": "1",  # Assuming version is 1
      "object_source": "https://dwc.tdwg.org/terms/",
      "object_source_version": "latest",
      "confidence": "0.5",
      "comment": "default for bcdm coming from frictionless data"
    },
```
BCDM to ENA 


```
{
      "subject_id": "bcdm_field",
      "subject_label": "bcdm_field",
      "predicate_id": "skos:exactMatch",  # Assuming exact match for field names
      "object_id": "ena_field",
      "object_label": "ena_field",
      "mapping_justification": "semapv:ManualMappingCuration",
      "mapping_date": "2024-04-23",  # Today's date
      "author_id": "https://orcid.org/0000-1234-0000-1234",  # Replace with your ORCID
      "confidence": ".95"
    },
    {
      "subject_id": "associated_specimens",
      "subject_label": "associated_specimens",
      "predicate_id": "",  
      "object_id": "",
      "object_label": "",
      "mapping_justification": "semapv:UnspecifiedMatching",
      "mapping_date": "2024-04-23",  # Today's date
      "author_id": "https://orcid.org/0000-1234-0000-1234",  # Replace with your ORCID
      "confidence": ".10"
    },

```

...


