package com.epam.lab.util;

import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class CSVExelParser {
    public static List<User> readUsersFromCSV(String file) {
        List<User> users = new ArrayList<User>();
        try {
            CSVParser records = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : records) {
                User user = new User();
                user.setLogin(record.get("EMAIL"));
                user.setPassword(record.get("PASSWORD"));
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Message> readMessagesFromXLSXFile(String file) throws IOException {
        List<Message> messages = new ArrayList<>();

        InputStream ExcelFileToRead = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();
        int counter = 0;
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();

            Message message = new Message();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    if (counter == 0) {
                        message.setReceiver(cell.getStringCellValue());
                    } else if (counter == 1) {
                        message.setSubject(cell.getStringCellValue());
                    }
                    else if (counter == 2) {
                        message.setText(cell.getStringCellValue());
                    }
                    ++counter;
                }
            }
            counter = 0;
            messages.add(message);
        }
        // deletes last value wich is null
        return messages.stream().filter(message -> !(message.getText() == null)).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        readUsersFromCSV("data.csv").forEach(System.out::println);
//        try {
//            readMessagesFromXLSXFile("gmail.xlsx").forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}
