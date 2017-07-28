package common;

import api.android.Android;
import core.MyLogger;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

/**
 * Created by User on 6/1/2017.
 */

public class Toasts {

    public static String getToastMessage() throws InterruptedException {

        String filePath=System.getProperty("user.dir")+"\\toastmessages";
        File file = new File(filePath);
        file.mkdir();
        //wait(20000);
        captureScreenshot(filePath);
        String str = "";
        BytePointer outText;
        tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();

        if (api.Init(".", "ENG") != 0) {
            MyLogger.log.info("Could not initialize tesseract.");
            System.exit(1);
        }

        lept.PIX image = pixRead(filePath+"\\toastmessage1.png");
        api.SetImage(image);

        // Get OCR result
        outText = api.GetUTF8Text();
        str = outText.getString();
        //Assert.assertTrue(!str.isEmpty());
        MyLogger.log.info("OCR output:\n" + str);

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return str;
    }

    public static void captureScreenshot(String path) {
        File scrFile = ((TakesScreenshot) Android.driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            String filePath=path+"\\toastmessage1.png";
            FileUtils.copyFile(scrFile,  new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
