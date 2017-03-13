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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlLogger {
	Document document;
	Event e;
	public xmlLogger(Event event){
		this.e = event;
//		loadXMLToMemory();
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
			
			StreamResult result = new StreamResult(new File(e.filename));
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
		return doc;
	}
	
	private void checkFileExists(){
		File xmlFile = new File(e.filename);
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
			document = docBuilder.parse(new File(e.filename));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void createNewXML(){
		initXMLFile();
		loadXMLToMemory();
		addEventBasics();
	}
	
	public void loadFromPreviousXML(String filepath){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			document = docBuilder.parse(filepath);
			
			NodeList nList = document.getElementsByTagName("event_date");
			e.date = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("event_name");
			e.eventName = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("event_location");
			e.eventLocation = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("car_make_model");
			e.carMakeModel = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("class");
			e.competingClass = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("adjustment_coeff");
			e.adjustmentCoeff = Double.valueOf(nList.item(0).getTextContent());
			
			nList = document.getElementsByTagName("car_weight_lbs");
			e.carWeight = Double.valueOf(nList.item(0).getTextContent());
			
			nList = document.getElementsByTagName("tire_name");
			e.tireName = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("tire_size");
			e.tireSize = nList.item(0).getTextContent();
			
			nList = document.getElementsByTagName("FR_s");
			e.frSetup[0] = Double.valueOf(nList.item(0).getAttributes().item(0).getTextContent());
			e.frSetup[1] = Double.valueOf(nList.item(0).getAttributes().item(1).getTextContent());
			
			nList = document.getElementsByTagName("FL_s");
			e.flSetup[0] = Double.valueOf(nList.item(0).getAttributes().item(0).getTextContent());
			e.flSetup[1] = Double.valueOf(nList.item(0).getAttributes().item(1).getTextContent());

			nList = document.getElementsByTagName("RL_s");
			e.rlSetup[0] = Double.valueOf(nList.item(0).getAttributes().item(0).getTextContent());
			e.rlSetup[1] = Double.valueOf(nList.item(0).getAttributes().item(1).getTextContent());

			nList = document.getElementsByTagName("RR_s");
			e.rrSetup[0] = Double.valueOf(nList.item(0).getAttributes().item(0).getTextContent());
			e.rrSetup[1] = Double.valueOf(nList.item(0).getAttributes().item(1).getTextContent());

			nList = document.getElementsByTagName("driver_name");
			for (int i = 0; i < nList.getLength(); i++){
				Driver loadedDriver = new Driver(nList.item(i).getTextContent());
				e.drivers.add(loadedDriver);
			}
			
//			nList = document.getElementsByTagName("BTC");
//			e.BTC = Double.valueOf(nList.item(0).getTextContent());
//			
//			nList = document.getElementsByTagName("BTD");
//			e.BTD = Double.valueOf(nList.item(0).getTextContent());
//			
//			nList = document.getElementsByTagName("percent_btc");
//			e.percent_BTC = Double.valueOf(nList.item(0).getTextContent());
//			
//			nList = document.getElementsByTagName("percent_btd");
//			e.percent_BTD = Double.valueOf(nList.item(0).getTextContent());
			
			nList = document.getElementsByTagName("driver");
			for (int i = 0; i < nList.getLength(); i++){
				NodeList lapList = nList.item(i).getChildNodes(); //generate list of laps for each driver

				for (int k = 0; k < lapList.getLength(); k++){ //for each lap in list of laps
					Lap newLap = new Lap(e.dl); //make new lap
					
					if (lapList.item(k).getNodeName().equals("lap")){
						
						newLap.lapNumber = Integer.valueOf(lapList.item(k).getAttributes().item(0).getTextContent());
						NodeList dataList = lapList.item(k).getChildNodes(); //and make list of data for each respective lap
						for (int m = 0; m < dataList.getLength(); m++){
							
							if (dataList.item(m).getNodeName().equals("raw_laptime")){
								newLap.rawLaptime = Double.valueOf(dataList.item(m).getTextContent());
							}
							if (dataList.item(m).getNodeName().equals("adjusted_laptime")){
								newLap.adjustedLaptime = Double.valueOf(dataList.item(m).getTextContent());
							}
							if (dataList.item(m).getNodeName().equals("cones_hit")){
								newLap.cones = Integer.valueOf(dataList.item(m).getTextContent());
							}
							if (dataList.item(m).getNodeName().equals("data_packet")){
								NodeList packetList = dataList.item(m).getChildNodes();
								
								dataPack dp = new dataPack();								
								for (int j = 0; j < packetList.getLength(); j++){
									
									if (packetList.item(j).getNodeName().equals("speed")){
										dp.speed = Double.valueOf(packetList.item(j).getTextContent());
									}
									if (packetList.item(j).getNodeName().equals("steering_angle")){
										dp.steeringAngle = Double.valueOf(packetList.item(j).getTextContent());
									}
									if (packetList.item(j).getNodeName().equals("coolant_temp")){
										dp.coolantTemp = Double.valueOf(packetList.item(j).getTextContent());
									}
									if (packetList.item(j).getNodeName().equals("engine_rpm")){
										dp.engineRPM = Double.valueOf(packetList.item(j).getTextContent());
									}
									if (packetList.item(j).getNodeName().equals("tire_temps")){
										NodeList tt = packetList.item(j).getChildNodes();
										for (int v = 0; v < tt.getLength(); v++){
											
											if (tt.item(v).getNodeName().equals("FR_t")){
												dp.frTemp[2] = Double.valueOf(tt.item(v).getAttributes().item(0).getTextContent());
												dp.frTemp[1] = Double.valueOf(tt.item(v).getAttributes().item(1).getTextContent());
												dp.frTemp[0] = Double.valueOf(tt.item(v).getAttributes().item(2).getTextContent());
											}
											if (tt.item(v).getNodeName().equals("FL_t")){
												dp.flTemp[2] = Double.valueOf(tt.item(v).getAttributes().item(0).getTextContent());
												dp.flTemp[1] = Double.valueOf(tt.item(v).getAttributes().item(1).getTextContent());
												dp.flTemp[0] = Double.valueOf(tt.item(v).getAttributes().item(2).getTextContent());
											}
											if (tt.item(v).getNodeName().equals("RR_t")){
												dp.rrTemp[2] = Double.valueOf(tt.item(v).getAttributes().item(0).getTextContent());
												dp.rrTemp[1] = Double.valueOf(tt.item(v).getAttributes().item(1).getTextContent());
												dp.rrTemp[0] = Double.valueOf(tt.item(v).getAttributes().item(2).getTextContent());
											}
											if (tt.item(v).getNodeName().equals("RL_t")){
												dp.rlTemp[2] = Double.valueOf(tt.item(v).getAttributes().item(0).getTextContent());
												dp.rlTemp[1] = Double.valueOf(tt.item(v).getAttributes().item(1).getTextContent());
												dp.rlTemp[0] = Double.valueOf(tt.item(v).getAttributes().item(2).getTextContent());
											}
										}
									}
									if (packetList.item(j).getNodeName().equals("g-force")){
										NodeList gF = packetList.item(j).getChildNodes();
										for (int v = 0; v < gF.getLength(); v++){
											
											if (gF.item(v).getNodeName().equals("lat_g")){
												dp.latG = Double.valueOf(gF.item(v).getTextContent());
											}
											if (gF.item(v).getNodeName().equals("lon_g")){
												dp.lonG = Double.valueOf(gF.item(v).getTextContent());
											}
										}
									}
								}
								newLap.data.add(dp);
							}
						}
						e.drivers.get(i).addLap(newLap);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void addEventBasics(){
		try{
			Element root = document.getDocumentElement();
			
			Element eventDate = document.createElement("event_date");
			eventDate.setTextContent(e.date);
			root.appendChild(eventDate);
			
			Element eventName = document.createElement("event_name");
			eventName.setTextContent(e.eventName);
			root.appendChild(eventName);
			
			Element eventLocation = document.createElement("event_location");
			eventLocation.setTextContent(e.eventLocation);
			root.appendChild(eventLocation);
			
			Element carMakeModel = document.createElement("car_make_model");
			carMakeModel.setTextContent(e.carMakeModel);
			root.appendChild(carMakeModel);
			
			Element eventClass = document.createElement("class");
			eventClass.setTextContent(e.competingClass);
			root.appendChild(eventClass);
			
			Element adjustCoeff = document.createElement("adjustment_coeff");
			adjustCoeff.setTextContent(String.valueOf(e.adjustmentCoeff));
			root.appendChild(adjustCoeff);
			
			Element tires = document.createElement("tire_option");
			Element tireSize = document.createElement("tire_size");
			Element tireName = document.createElement("tire_name");
			tireSize.setTextContent(e.tireSize);
			tireName.setTextContent(e.tireName);
			tires.appendChild(tireName);
			tires.appendChild(tireSize);
			root.appendChild(tires);
			
			Element weight = document.createElement("car_weight_lbs");
			weight.setTextContent(String.valueOf(e.carWeight));
			root.appendChild(weight);
			
			Element suspension = document.createElement("suspension");
			Element fr_s = document.createElement("FR_s");
			Element fl_s = document.createElement("FL_s");
			Element rl_s = document.createElement("RL_s");
			Element rr_s = document.createElement("RR_s");
			fr_s.setAttribute("camber", String.valueOf(e.frSetup[0]));
			fr_s.setAttribute("toe", String.valueOf(e.frSetup[1]));
			
			fl_s.setAttribute("camber", String.valueOf(e.flSetup[0]));
			fl_s.setAttribute("toe", String.valueOf(e.flSetup[1]));
			
			rr_s.setAttribute("camber", String.valueOf(e.rrSetup[0]));
			rr_s.setAttribute("toe", String.valueOf(e.rrSetup[1]));
			
			rl_s.setAttribute("camber", String.valueOf(e.rlSetup[0]));
			rl_s.setAttribute("toe", String.valueOf(e.rlSetup[1]));
			suspension.appendChild(fr_s);
			suspension.appendChild(fl_s);
			suspension.appendChild(rl_s);
			suspension.appendChild(rr_s);
			root.appendChild(suspension);
			
			Element drivers = document.createElement("drivers");
			for (int i = 0; i < e.drivers.size(); i++){
				Element driver_name = document.createElement("driver_name");
				driver_name.setTextContent(e.drivers.get(i).name);
				drivers.appendChild(driver_name);
			}
			root.appendChild(drivers);
			
			Element data = document.createElement("data");
			for (int i = 0; i < e.drivers.size(); i++){
				Element driver = document.createElement("driver");
				driver.setAttribute("name", e.drivers.get(i).name);
				data.appendChild(driver);
			}
			root.appendChild(data);
						
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(e.filename));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
	
	public void addBTC_BTD_percentages(){
		try{
			Element root = document.getDocumentElement();
			
			Element btc = document.createElement("BTC");
			btc.setTextContent(String.valueOf(e.BTC));
			root.appendChild(btc);
			
			Element percentBTC = document.createElement("percent_btc");
			percentBTC.setTextContent(String.valueOf(e.percent_BTC));
			root.appendChild(percentBTC);
			
			Element btd = document.createElement("BTD");
			btd.setTextContent(String.valueOf(e.BTD));
			root.appendChild(btd);
			
			Element percentBTD = document.createElement("percent_btd");
			percentBTD.setTextContent(String.valueOf(e.percent_BTD));
			root.appendChild(percentBTD);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(e.filename));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
	
	public void addLap(String driver){
		try{
			NodeList nList = document.getElementsByTagName("driver");
			Element root = null;
			for (int i = 0; i < e.drivers.size(); i++){
				Node n = nList.item(i);
				root = (Element) n;
				if (root.getAttribute("name").equalsIgnoreCase(driver)){
					Driver temp = e.drivers.get(i);
					
					for (int k = temp.numberOfLaps - 1; k < temp.laps.size(); k++){
						Element lap = document.createElement("lap");
						lap.setAttribute("number", String.valueOf(temp.numberOfLaps));
						
						Element rawLaptime = document.createElement("raw_laptime");
						rawLaptime.setTextContent(String.valueOf(temp.laps.get(k).rawLaptime));
						lap.appendChild(rawLaptime);
						
						Element adjustedLaptime = document.createElement("adjusted_laptime");
						adjustedLaptime.setTextContent(String.valueOf(temp.laps.get(k).adjustedLaptime));
						lap.appendChild(adjustedLaptime);
						
						Element conesHit = document.createElement("cones_hit");
						conesHit.setTextContent(String.valueOf(temp.laps.get(k).cones));
						lap.appendChild(conesHit);
						
						for (int d = 0; d < temp.laps.get(k).data.size(); d++){
							Element dataPack = document.createElement("data_packet");
							dataPack.setAttribute("id", String.valueOf(d));
							
							Element speed = document.createElement("speed");
							speed.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).speed));
							dataPack.appendChild(speed);
							
							Element steeringAngle = document.createElement("steering_angle");
							steeringAngle.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).steeringAngle));
							dataPack.appendChild(steeringAngle);
							
							Element coolantTemp = document.createElement("coolant_temp");
							coolantTemp.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).coolantTemp));
							dataPack.appendChild(coolantTemp);
							
							Element oilTemp = document.createElement("oil_temp");
							oilTemp.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).oilTemp));
							
							Element engineRPM = document.createElement("engine_rpm");
							engineRPM.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).engineRPM));
							dataPack.appendChild(engineRPM);
							
							Element tireTemps = document.createElement("tire_temps");
							Element fr_t = document.createElement("FR_t");
							fr_t.setAttribute("i", String.valueOf(temp.laps.get(k).data.get(d).frTemp[2]));
							fr_t.setAttribute("m", String.valueOf(temp.laps.get(k).data.get(d).frTemp[1]));
							fr_t.setAttribute("o", String.valueOf(temp.laps.get(k).data.get(d).frTemp[0]));
							tireTemps.appendChild(fr_t);
							
							Element fl_t = document.createElement("FL_t");
							fl_t.setAttribute("i", String.valueOf(temp.laps.get(k).data.get(d).flTemp[2]));
							fl_t.setAttribute("m", String.valueOf(temp.laps.get(k).data.get(d).flTemp[1]));
							fl_t.setAttribute("o", String.valueOf(temp.laps.get(k).data.get(d).flTemp[0]));
							tireTemps.appendChild(fl_t);
							
							Element rr_t = document.createElement("RR_t");
							rr_t.setAttribute("i", String.valueOf(temp.laps.get(k).data.get(d).rrTemp[2]));
							rr_t.setAttribute("m", String.valueOf(temp.laps.get(k).data.get(d).rrTemp[1]));
							rr_t.setAttribute("o", String.valueOf(temp.laps.get(k).data.get(d).rrTemp[0]));
							tireTemps.appendChild(rr_t);
							
							Element rl_t = document.createElement("RL_t");
							rl_t.setAttribute("i", String.valueOf(temp.laps.get(k).data.get(d).rlTemp[2]));
							rl_t.setAttribute("m", String.valueOf(temp.laps.get(k).data.get(d).rlTemp[1]));
							rl_t.setAttribute("o", String.valueOf(temp.laps.get(k).data.get(d).rlTemp[0]));
							tireTemps.appendChild(rl_t);
							dataPack.appendChild(tireTemps);
							
							Element gForce = document.createElement("g-force");
							Element latG = document.createElement("lat_g");
							latG.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).latG));
							gForce.appendChild(latG);
							
							Element lonG = document.createElement("lon_g");
							lonG.setTextContent(String.valueOf(temp.laps.get(k).data.get(d).lonG));
							gForce.appendChild(lonG);
							dataPack.appendChild(gForce);
							
							//...
							
							lap.appendChild(dataPack);
						}
						root.appendChild(lap);
					}
				}
			}			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(e.filename));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
	
	public void updateSuspensionSettings(){
		try{
			NodeList nList = document.getElementsByTagName("FR_s");
			Node n = nList.item(0);
			Element fr_s = (Element) n;
			fr_s.setAttribute("camber", String.valueOf(e.frSetup[0]));
			fr_s.setAttribute("toe", String.valueOf(e.frSetup[1]));
			
			nList = document.getElementsByTagName("FL_s");
			n = nList.item(0);
			Element fl_s = (Element) n;
			fl_s.setAttribute("camber", String.valueOf(e.flSetup[0]));
			fl_s.setAttribute("toe", String.valueOf(e.flSetup[1]));
			
			nList = document.getElementsByTagName("RL_s");
			n = nList.item(0);
			Element rl_s = (Element) n;
			rl_s.setAttribute("camber", String.valueOf(e.rlSetup[0]));
			rl_s.setAttribute("toe", String.valueOf(e.rlSetup[1]));
			
			nList = document.getElementsByTagName("RR_s");
			n = nList.item(0);
			Element rr_s = (Element) n;
			rr_s.setAttribute("camber", String.valueOf(e.rrSetup[0]));
			rr_s.setAttribute("toe", String.valueOf(e.rrSetup[1]));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(e.filename));
			transformer.transform(source, result);
		}
		catch (Exception  tfe) {
			tfe.printStackTrace();
		}
	}
}
