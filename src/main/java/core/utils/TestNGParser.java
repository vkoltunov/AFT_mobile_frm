package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.xml.IFileParser;
import org.testng.xml.IPostProcessor;
//import org.testng.xml.ISuiteParser;
import org.testng.xml.Parser;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

/**
 *
 * @author vkoltunov
 */
public class TestNGParser extends Parser {

    private final String m_fileName;
    private final InputStream m_inputStream;
    private final IPostProcessor m_postProcessor;
    private final boolean m_loadClasses = true;
    private static final SuiteXmlParser DEFAULT_FILE_PARSER = new SuiteXmlParser();
    //private static final List<SuiteXmlParser> PARSERS = Lists.newArrayList(DEFAULT_FILE_PARSER);

    public TestNGParser(String fileName) {
        super(fileName);
        m_fileName = fileName;
        m_inputStream = null;
        m_postProcessor = null;
    }

    /**
     * Parses the TestNG test suite and returns the corresponding XmlSuite,
     * and possibly, other XmlSuite that are pointed to by <suite-files>
     * tags.
     *
     * @return the parsed TestNG test suite.
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException                  if an I/O error occurs while parsing the test suite file or
     *                                      if the default testng.xml file is not found.
     */
    @Override
    public Collection<XmlSuite> parse() throws ParserConfigurationException, SAXException, IOException {
        // Each suite found is put in this list, using their canonical
        // path to make sure we don't add a same file twice
        // (e.g. "testng.xml" and "./testng.xml")
        List<String> processedSuites = Lists.newArrayList();
        XmlSuite resultSuite = null;

        List<String> toBeParsed = Lists.newArrayList();
        List<String> toBeAdded = Lists.newArrayList();
        List<String> toBeRemoved = Lists.newArrayList();

        if (m_fileName != null) {
            File mainFile = new File(m_fileName);
            toBeParsed.add(mainFile.getCanonicalPath());
        }

        /*
         * Keeps a track of parent XmlSuite for each child suite
         */
        Map<String, XmlSuite> childToParentMap = Maps.newHashMap();
        while (toBeParsed.size() > 0) {

            for (String currentFile : toBeParsed) {
                File currFile = new File(currentFile);
                File parentFile = currFile.getParentFile();
                InputStream inputStream = m_inputStream != null ? m_inputStream : new FileInputStream(currentFile);

                IFileParser<XmlSuite> fileParser =  getParser(currentFile);
                XmlSuite currentXmlSuite = fileParser.parse(currentFile, inputStream, m_loadClasses);
                processedSuites.add(currentFile);
                toBeRemoved.add(currentFile);

                if (childToParentMap.containsKey(currentFile)) {
                    XmlSuite parentSuite = childToParentMap.get(currentFile);
                    //Set parent
                    currentXmlSuite.setParentSuite(parentSuite);
                    //append children
                    parentSuite.getChildSuites().add(currentXmlSuite);
                }

                if (null == resultSuite) {
                    resultSuite = currentXmlSuite;
                }

                List<String> suiteFiles = currentXmlSuite.getSuiteFiles();
                if (suiteFiles.size() > 0) {
                    for (String path : suiteFiles) {
                        String pathFull = Common.checkFileExtension(path, "xml");
                        String canonicalPath;
                        if (parentFile != null && new File(parentFile, pathFull).exists()) {
                            canonicalPath = new File(parentFile, pathFull).getCanonicalPath();
                        } else {
                            canonicalPath = new File(pathFull).getCanonicalPath();
                        }
                        if (!processedSuites.contains(canonicalPath)) {
                            toBeAdded.add(canonicalPath);
                            childToParentMap.put(canonicalPath, currentXmlSuite);
                        }
                    }
                    currentXmlSuite.setSuiteFiles(new LinkedList<String>());
                }
            }

            //
            // Add and remove files from toBeParsed before we loop
            //
            for (String s : toBeRemoved) {
                toBeParsed.remove(s);
            }
            toBeRemoved = Lists.newArrayList();

            for (String s : toBeAdded) {
                toBeParsed.add(s);
            }
            toBeAdded = Lists.newArrayList();

        }

        //returning a list of single suite to keep changes minimum
        List<XmlSuite> resultList = Lists.newArrayList();
        resultList.add(resultSuite);

        boolean postProcess = true;

        if (postProcess && m_postProcessor != null) {
            return m_postProcessor.process(resultList);
        } else {
            return resultList;
        }
    }

    private static IFileParser getParser(String fileName) {
//        for (ISuiteParser parser : PARSERS) {
//            if (parser.accept(fileName)) {
//                return parser;
//            }
//        }
        return DEFAULT_FILE_PARSER;
    }

    @Override
    public List<XmlSuite> parseToList() {
        try {
            List<XmlSuite> result = Lists.newArrayList();
            Collection<XmlSuite> suites = parse();
            for (XmlSuite suite : suites) {
                result.add(suite);
            }

            return result;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            core.utils.Logger.getInstance().fatal("Не задан тестовый комплект или набор: " + ex.getMessage());
        }
        return null;
    }
}
