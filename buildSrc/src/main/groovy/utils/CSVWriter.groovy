package utils

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.io.FileUtils

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class CSVWriter {
    private String fileName
    private List<String> fileHeader

    CSVWriter(String filename, List<String> fileHeader) {
        this.fileName = new File(filename)
        this.fileHeader = fileHeader
    }

    /**
     * Create a CSV file with default file name and default file header.
     */
    void createCSVFile() {
        File file = new File(this.fileName)
        if (!file.exists()) {
            FileUtils.touch(file)
        }
        file.withWriter {
            CSVPrinter csv = new CSVPrinter(it, CSVFormat.DEFAULT)
            csv.printRecord(this.fileHeader)
        }
    }

    /**
     * Writes the record to the CSV.
     */
    void writeRecord(List<String> attributes) {
        Files
                .newBufferedWriter(Paths.get(this.fileName), StandardOpenOption.APPEND, StandardOpenOption.CREATE)
                .withWriter {
                    CSVPrinter csv = new CSVPrinter(it, CSVFormat.DEFAULT)
                    csv.printRecord(attributes)
                }
    }
}
