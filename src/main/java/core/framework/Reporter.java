package core.framework;

import api.android.Android;
import com.gargoylesoftware.htmlunit.javascript.host.xml.XMLDocument;
import core.MyLogger;
import core.TestInfo;
import core.framework.base.BaseEntity;
import core.utils.Common;
import core.utils.Config;
import core.utils.GlobalDict;
import javafx.scene.shape.PathElement;
import org.openqa.selenium.logging.LogEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import static core.managers.DriverManager.sdCard;
import static core.utils.Config.Keys.DATA_DIR;
import static core.utils.Config.Keys.DATA_FILE;


/**
 * Created by User on 8/2/2017.
 */
public class Reporter {

    private File file;
    private String folder;
    private DocumentBuilder builder;

    public Reporter(File file) throws ParserConfigurationException {
        this.file = file;
        folder = file.getParent();
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public void update(String suiteName, String testName, String testResult) throws Exception {

        try{
            Document doc;
            if(!file.exists()){
                Files.createDirectories(file.toPath().getParent());
                Files.createFile(file.toPath());
                doc = create_new_xml(suiteName, testName, testResult);
            }else doc = update_existing_xml(suiteName, testName, testResult);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        }catch (TransformerException | IOException | SAXException e){
            e.printStackTrace();
        }
    }

    private Document create_new_xml(String suiteName, String testName, String testResult) throws Exception {
        Document doc = builder.newDocument();

        Element testsuites = doc.createElement("testsuites");

        Element testsuite = doc.createElement("testsuite");
        testsuite.setAttribute("name", suiteName);
        testsuite.setAttribute("tests", "1");
        testsuite.setAttribute("failures", testResult.equals("FAIL") ? "1": "0");
        testsuite.setAttribute("pass", testResult.equals("PASS") ? "1": "0");

        Element testcase = doc.createElement("testcase");
        testcase.setAttribute("name", testName);
        testcase.setAttribute("status", testResult);

        if(testResult.equals("FAIL")){
            takeScreenshot(suiteName);
            saveLogcat(suiteName);
            Element failure = doc.createElement("failure");
            testcase.appendChild(failure);
        }

        doc.appendChild(testsuites);
        testsuites.appendChild(testsuite);
        testsuite.appendChild(testcase);
        return doc;
    }

    private Document update_existing_xml(String suiteName, String testName, String testResult) throws Exception {

        Document doc = builder.parse(file);
        Node testsuites = doc.getFirstChild();

        NodeList suites = doc.getElementsByTagName("testsuite");
        for(int index = 0; index < suites.getLength(); index++){
            Node suite = suites.item(index);
            String existitng_suite = suite.getAttributes().getNamedItem("name").getNodeValue();
            if(existitng_suite.equals(suiteName)){
                System.out.println("Suite already exists. Making changes to it...");
                Element testcase = doc.createElement("testcase");
                testcase.setAttribute("name", testName);
                testcase.setAttribute("status", testResult);

                Node test = suite.getAttributes().getNamedItem("tests");
                Node pass = suite.getAttributes().getNamedItem("pass");
                Node failures = suite.getAttributes().getNamedItem("failures");
                int t = Integer.valueOf(test.getNodeValue()) + 1;
                int p = Integer.valueOf(pass.getNodeValue());
                int f = Integer.valueOf(failures.getNodeValue());

                if (testResult.equals("FAIL")){
                    f++;
                    takeScreenshot(suiteName);
                    saveLogcat(suiteName);
                    Element failure = doc.createElement("failure");
                    testcase.appendChild(failure);
                }else p++;

                test.setNodeValue(String.valueOf(t));
                failures.setNodeValue(String.valueOf(f));
                pass.setNodeValue(String.valueOf(p));
                suite.appendChild(testcase);
                return doc;
            }
        }

        Element testsuite = doc.createElement("testsuite");
        testsuite.setAttribute("name", suiteName);
        testsuite.setAttribute("tests", "1");
        testsuite.setAttribute("failures", testResult.equals("FAIL") ? "1": "0");
        testsuite.setAttribute("pass", testResult.equals("PASS") ? "1": "0");

        Element testcase = doc.createElement("testcase");
        testcase.setAttribute("name", testName);
        testcase.setAttribute("status", testResult);

        if(testResult.equals("FAIL")){
            Element failure = doc.createElement("failure");
            testcase.appendChild(failure);
        }
        testsuites.appendChild(testsuite);
        testsuite.appendChild(testcase);
        return doc;
    }

    private void takeScreenshot(String suiteName){
        if (!folder.isEmpty()) {
            Android.adb.takeScreenshot("/storage/"+sdCard+"/Pictures/screen1.png");
            Android.adb.pullFile("/storage/"+sdCard+"/Pictures/screen1.png", folder+"\\"+suiteName.replace(" ", "_")+".png");
            Android.adb.deleteFile("/storage/"+sdCard+"/Pictures/screen1.png");
        }
    }

    public void saveLogcat(String suiteName) {
        try{
            MyLogger.log.info("LOGCAT PATH COMP: "+ folder+"\\"+"log_"+suiteName.replace(" ", "_")+".txt");
            List<LogEntry> logEntries = Android.driver.manage().logs().get("logcat").filter(Level.ALL);
            File logFile = new File(folder+"\\"+"log_"+suiteName.replace(" ", "_")+".txt");
            PrintWriter log_file_writer = new PrintWriter(logFile);
            log_file_writer.println(logEntries );
            log_file_writer.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
