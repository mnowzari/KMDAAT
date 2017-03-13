import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;

public class portIdentifier {
		  protected ArrayList<String> list() {
		    Enumeration<?> pList = CommPortIdentifier.getPortIdentifiers();
		    ArrayList<String> availCom = new ArrayList<>();

		    while (pList.hasMoreElements()) {
		        CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
		        if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
			      availCom.add(cpi.getName());
		        }
		      }
		    return availCom;
		  }
}
