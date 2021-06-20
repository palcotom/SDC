package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Tomáš Palčo
 */

public class SDC{
    /**
     * Function checking if given number is a prime
     * @param n number to be checked for primality
     * @return true if n was prime, false otherwise
     */
    static public boolean isPrime(int n){
        if (n == 1){
            return false;
        }
        if (n == 2){
            return true;
        }
        if (n % 2 == 0){
            return false;
        }
        for (int i = 3; i < Math.sqrt(n); i += 2){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * main method which handles loading and reading of xlsx file
     * @param args expecting exactly one input argument which should be a path to an xlsx file
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 1){
            File excelFile = new File(args[0]);
            FileInputStream inputStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            if (workbook.getNumberOfSheets() > 0){
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row:sheet){
                    if (row.getRowNum()==0){
                        continue;
                    }
                    Cell cell = row.getCell(1);
                    if (cell.getCellType() != CellType.STRING){
                        continue;
                    }
                    int number = Integer.parseInt(cell.getStringCellValue());
                    if ((number > 0) && isPrime(number)){
                        System.out.println(number);
                    }
                }
            }
        }
    }
}
