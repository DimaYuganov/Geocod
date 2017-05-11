package MainGeocod;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * Created by 1234 on 4/8/2017.
 */
public class Out {


    String path;
    public double volume;

    public Out(String path) {
        this.path = path;
    }


    public void setOutPutData(int i, int numberOfRecodrs, String[] inputData, String area, String lat, String lng, String city) throws IOException {

try {


    String excelFilePath = path;

    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(excelFilePath)));


    HSSFWorkbook wb = new HSSFWorkbook(fs);
    CreationHelper createHelper = wb.getCreationHelper();
    HSSFSheet sheet = wb.getSheetAt(0);

    Row row = sheet.createRow((short) i + 1);

try {
    volume = Double.valueOf(inputData[2]);
} catch (Exception e){volume =0;}


    String clientType = null;

    if (volume < 10000) clientType = "C";
    if (10000 < volume & volume < 20000) clientType = "B";
    if (20000 < volume) clientType = "A";


    String str1 = "[ '" + inputData[0] + "', " + lat + ", " + lng + ", '" + inputData[3] + "', '" + inputData[1] + "', '" + inputData[2] + "', '" + area + "', '" + clientType + "' ],";


    row.createCell(1).setCellValue(createHelper.createRichTextString(inputData[0]));
    row.createCell(2).setCellValue(createHelper.createRichTextString(lat + ", " + lng));
    row.createCell(3).setCellValue(createHelper.createRichTextString(inputData[1]));
    row.createCell(4).setCellValue(createHelper.createRichTextString(area));
    row.createCell(5).setCellValue(Double.valueOf(inputData[2]));
    row.createCell(6).setCellValue(createHelper.createRichTextString(str1));
    row.createCell(7).setCellValue(createHelper.createRichTextString(inputData[3]));
    row.createCell(8).setCellValue(createHelper.createRichTextString(clientType));
    row.createCell(9).setCellValue(createHelper.createRichTextString(city));


    System.out.println(i);
    System.out.println(str1);


    FileOutputStream fileOut = new FileOutputStream(path);
    wb.write(fileOut);
    fileOut.close();

}catch (Exception e){
    System.out.println("can't create a record");
}



    }

    public void fileCreation() throws Exception{

        try{
            Workbook wb = new HSSFWorkbook();
            CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("new sheet");
            Row row = sheet.createRow((short)0);
            FileOutputStream fileOut = new FileOutputStream(path);
            wb.write(fileOut);
            fileOut.close();

        }catch (Exception e){
            System.out.println("Cant't Create a file");}



    }


}
