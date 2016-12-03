import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class xmlLogger {
	Document document;
	public xmlLogger(){
		loadXMLToMemory();
	}
	
	private Document initXMLFile(){
		Document doc = null;
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Event");
			doc.appendChild(rootElement);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File("Event.xml"));
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
		return doc;
	}
	
	private void checkFileExists(){
		File xmlFile = new File("Event.xml");
		if (!xmlFile.exists()){
			initXMLFile();
		}
	}
	
	public void loadXMLToMemory(){
		checkFileExists();
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			document = docBuilder.parse(new File("Event.xml"));	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void addEventName(String eventName){
		try{
			Element root = document.getDocumentElement();
			
			Element event = document.createElement("event_name");
			event.setTextContent(eventName);
			
			root.appendChild(event);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File("Event.xml"));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
	
	public void updateEventName(String eventName){
		try{
			NodeList nList = document.getElementsByTagName("event_name");
			Element root = document.getDocumentElement();
			root.removeChild(nList.item(0));
			
			addEventName(eventName);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File("Event.xml"));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
}
