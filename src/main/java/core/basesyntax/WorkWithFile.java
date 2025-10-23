package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        int suply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].isEmpty() && parts[0].toLowerCase().startsWith("s")) {
                    suply += Integer.parseInt(parts[1]);
                } else {
                    if (!parts[0].isEmpty() && parts[0].toLowerCase().startsWith("b")) {
                        buy += Integer.parseInt(parts[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }

        String resultLine = "supply," + suply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (suply - buy);
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
        writer.write(resultLine);
        writer.close();
    }
}
