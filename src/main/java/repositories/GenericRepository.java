package repositories;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericRepository {

    protected Workbook workbook;
    protected Sheet firstSheet;

    protected void LoadFirstSheet(String filePath) {

        try {
            // Faz o programa ler o arquivo XLSX (XLS não é suportado)
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            firstSheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {

        StringBuilder exit = new StringBuilder();

        for (Row row : firstSheet) {
            if(row.getRowNum() > 0) {

                List<String> listCell = getListCell(row);

                exit.append("\nRow " + row.getRowNum() + ": ");
                for (String content : listCell)
                    exit.append("/ ").append(content);
            }
        }
        return exit.toString();
    }

    protected List<String> getListCell(Row row) {
        List<String> list = new ArrayList<>();
        for (Cell cell : row) {
            DataFormatter dataFormatter = new DataFormatter();
            String formattedCellStr = dataFormatter.formatCellValue(cell);
            list.add(formattedCellStr);
        }
        return list;
    }
}
