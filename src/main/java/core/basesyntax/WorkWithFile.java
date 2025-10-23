package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String supplyWord = "supply,";
    private static final String buyWord = "buy,";
    private static final String resultWord = "result,";
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        processData(fromFileName);
        writeToFile(toFileName);
    }

    private void processData(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equalsIgnoreCase("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName) {
        String resultLine = supplyWord + supply + System.lineSeparator()
                + buyWord + buy + System.lineSeparator()
                + resultWord + (supply - buy);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultLine);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }

        supply = 0;
        buy = 0;
    }
}
