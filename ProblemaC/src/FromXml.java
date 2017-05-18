import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;

	import java.util.Vector;

	import javax.xml.stream.XMLInputFactory;
	import javax.xml.stream.XMLStreamConstants;
	import javax.xml.stream.XMLStreamException;
	import javax.xml.stream.XMLStreamReader;

	
public class FromXml {
		Vector<Integer> base = new Vector<Integer>();
		Vector<Integer> exp = new Vector<Integer>();
		boolean lastimport=false;
		File filename;
		//COSTRUTTORE
		public FromXml(String filename) throws FileNotFoundException, XMLStreamException{
			try{
				this.filename=new File(filename);
			}catch(Exception e){
				System.out.println("File at "+filename+" is not avaiable or correctly patthed");
				return;
			} 
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
			//String data="";
			while(reader.hasNext()){
				switch(reader.next()){
					case XMLStreamConstants.START_DOCUMENT:
						System.out.println("Start reading Doc");
						break;
						
					case XMLStreamConstants.START_ELEMENT:
						if("candidates".equals(reader.getLocalName())){
							System.out.println("Starting read candidates");
						}
						if("candiate".equals(reader.getLocalName())){
							base.add(Integer.parseInt(reader.getAttributeValue(0).toString().trim()));
							exp.add(Integer.parseInt(reader.getAttributeValue(1).toString().trim()));
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						//System.out.println(reader.toString());
						if(reader.getText().trim().length()>0){
							//data= reader.getText().toString();
						}
						break;
						
					case XMLStreamConstants.END_ELEMENT:
						switch(reader.getLocalName()){
							case "candidates":
								System.out.println("Acquiring candidates, completed!");
								break;
						}
						break;
					
					case XMLStreamConstants.END_DOCUMENT:
						System.out.println("End reading Doc");
						break;
					
				}
			}
			
		}
		public Vector<Integer> getBase() {
			return base;
		}
		public Vector<Integer> getExp() {
			return exp;
		}
		
}
