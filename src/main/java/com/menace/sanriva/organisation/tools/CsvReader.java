package com.menace.sanriva.organisation.tools;

import com.menace.sanriva.organisation.dao.OrganisationDao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class CsvReader {

    private final OrganisationDao organisationDao;

    public CsvReader(OrganisationDao organisationDao) {
        this.organisationDao = organisationDao;
    }

    public void read(String path, Charset charset) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(path), charset);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create()
                .setDelimiter(';')
                .setHeader()
                .setIgnoreSurroundingSpaces(true)
                .setIgnoreHeaderCase(true)
                .build());

        for (CSVRecord csvRecord : csvParser) {
            String name = csvRecord.get("Enhedsnavn");
            String type = csvRecord.get("Enhedstype");
            String region = csvRecord.get("P_Region");
            String specialization = csvRecord.get("Hovedspeciale");
            String cvrStr = csvRecord.get("CVR");
            Long cvr = cvrStr != null && !cvrStr.isEmpty() ? Long.valueOf(cvrStr) : null; // Handle nullable CVR
            Long sorCode = Long.parseLong(csvRecord.get("SOR-kode"));
            String parentSorCodeStr = csvRecord.get("Parent-SOR-kode");
            Long parentSorCode = parentSorCodeStr != null && !parentSorCodeStr.isEmpty() ? Long.valueOf(parentSorCodeStr) : null;

            Long rootSorCode = null;
            organisationDao.insert(name, type, region, specialization, cvr, sorCode, parentSorCode, rootSorCode);
        }

        csvParser.close();
        reader.close();
    }

}
