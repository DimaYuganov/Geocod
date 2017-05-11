package MainGeocod;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 1234 on 4/8/2017.
 */
public class In {

    String path;
    String[] inputLine = new String[4];

    public In(String path) {
        this.path = path;
    }


    public String[] getInputLine(int i) throws IOException {

        try {
            String excelFilePath = path;

            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(excelFilePath)));


            HSSFWorkbook wb = new HSSFWorkbook(fs);

            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            inputLine[0] = String.valueOf(sheet.getRow(i).getCell(0));
            inputLine[1] = String.valueOf(sheet.getRow(i).getCell(1));
            inputLine[2] = String.valueOf(sheet.getRow(i).getCell(2));
            inputLine[3] = String.valueOf(sheet.getRow(i).getCell(3));
        }catch (Exception e){
            inputLine[0] = "None";
            inputLine[1] = "None";
            inputLine[2] = "None";
            inputLine[3] = "None";

        }



        return inputLine;
    }

    public int getNumberOfLines()throws IOException {


        String excelFilePath = path;



        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(excelFilePath)));


        HSSFWorkbook wb = new HSSFWorkbook(fs);

        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;

        int rows; // No of rows
        rows = sheet.getPhysicalNumberOfRows();

        return rows;
    }


}
