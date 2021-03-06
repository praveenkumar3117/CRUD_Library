package CRUD;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

public class Xml {

    public static String getStringFromDocument(Document document) {            //converting the query file to XML
		try {
			DOMSource domSource = new DOMSource(document);
			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(domSource, streamResult);
			return stringWriter.toString();
		}
		catch(TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

    public static HashMap<String, String> extractXml() {        //extracting the XML file and creating a hashmap with key as query id and value as the query
		File file;
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document document;
		try {
            HashMap<String, String> map1 = new HashMap<>();
			file = new File("D:/cs305_2022/sample.xml");
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(file);
			document.getDocumentElement().normalize();
			String x1 = "<![CDATA[";       //used to skip these words
			
			String x2 = "]]>";
			String key = "<" + document.getDocumentElement().getNodeName() + ">";
			String fileString = getStringFromDocument(document);
			int index = fileString.indexOf(key) + key.length();
			fileString = fileString.substring(index, fileString.length());
			fileString = fileString.substring(0, fileString.length()-key.length());
			int a1 = fileString.indexOf(x1);
			while (a1>=0) {
				int temp=fileString.indexOf("sql id=") + 8;
				int temp2=fileString.indexOf("\" paramType");
                String id = fileString.substring(temp,temp2);
				fileString = fileString.substring(a1 + x1.length(),fileString.length());
				String command = fileString.substring(0, fileString.indexOf(x2));
                map1.put(id.trim(), command.trim());
				a1 = fileString.indexOf(x1);
			}
            return map1;
		}
		catch (Exception e) {
			System.out.println(e);
            return null;
		}
    }

}