import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Vector;

	import javax.xml.stream.XMLInputFactory;
	import javax.xml.stream.XMLOutputFactory;
	import javax.xml.stream.XMLStreamConstants;
	import javax.xml.stream.XMLStreamException;
	import javax.xml.stream.XMLStreamReader;
	import javax.xml.stream.XMLStreamWriter;
	
public class FromXml {
		Vector<Vino> magazzino;
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
			Vino tmp=null;
			String reference="",valuta="",data="";
			while(reader.hasNext()){
				switch(reader.next()){
					case XMLStreamConstants.START_DOCUMENT:
						System.out.println("Start reading Doc");
						break;
						
					case XMLStreamConstants.START_ELEMENT:
						if("wines".equals(reader.getLocalName())){
							magazzino = new Vector<Vino>();
							System.out.println("Start reading tree");
						}
						if("wine".equals(reader.getLocalName())){
							tmp=new Vino();
						}
		
						if("price".equals(reader.getLocalName())){
							if("val".equals(reader.getLocalName())){
								valuta = reader.getAttributeValue(0).toString().trim();
							}
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						//System.out.println(reader.toString());
						if(reader.getText().trim().length()>0){
							data= reader.getText().toString();
						}
						break;
						
					case XMLStreamConstants.END_ELEMENT:
						switch(reader.getLocalName()){
							case "wines":
								System.out.println("Acquiring wines, completed!");
								break;
							case "wine":
								magazzino.add(tmp);
								tmp=null;
								break;
							case "name":
								if(tmp!=null)
									tmp.setName(data);
								break;
							case "cont":
								tmp.setCont(Integer.parseInt(data));
							case "price":
								//TODO maybe node still not exist
								tmp.setPrezzo(Double.parseDouble(data));
								tmp.setValuta(valuta);
								valuta="";
								break;
							case "date":
								data.trim();
								tmp.setData(Integer.parseInt(data.trim()));
								break;
							case "geo":
								tmp.setLocalità(data);
								break;
							case "farmer":
								tmp.setProduttore(data);
								break;
						}
						break;
					
					case XMLStreamConstants.END_DOCUMENT:
						System.out.println("End reading Doc");
						break;
					
				}
			}
			
		}
		
		/**
		 *
		 * @return
		 */
		public boolean avaiable(){
			return lastimport;
		}
		public Vector<Vino> getTree(){
			return magazzino;
		}
		/*private Vino getNodeByLabel(String id){
			/*for(Node i:tree)
				if(i.getID().equals(id)){
					return i;
				}
			for(int i =0; i<tree.size();i++){
				if(tree.get(i).getLabel().equals(id)){
					return tree.get(i);
				}
			}
			return null;
		}*/
		/**
		 * 
		 */
		@Override
		public String toString(){
			String data="";
			for(Vino i : magazzino)
				data+="\n"+i.toString();
			return data;
		}
		
		/*public boolean save(String filename) throws XMLStreamException{
			System.out.println("Scrittura su file");
			XMLOutputFactory output = XMLOutputFactory.newInstance();
			XMLStreamWriter writer;
			try {
				writer = output.createXMLStreamWriter(new FileWriter(filename));

				writer.writeComment("data saved");
				//writer.writeStartDocument("utf-8","1.0");
				//writer.setPrefix("", "");
				writer.writeStartElement("tree");
				for(Node i: tree){
					writer.writeStartElement("node");
					if(i.isStart()){
						writer.writeAttribute("start","true");
					}
					if(i.isEnd()){
						writer.writeAttribute("end","true");
					}
					writer.writeStartElement("label");
					writer.writeCharacters(i.getLabel());
					writer.writeEndElement();// End label
					if(i.hasEdge()){
						writer.writeStartElement("edges");
						for(Node j: i.getEdge()){
							writer.writeStartElement("edge");
							writer.writeAttribute("weight",i.getWeightEdge(j.getID()));
							writer.writeCharacters(j.getLabel());
							writer.writeEndElement();//end Edge
						}
						writer.writeEndElement(); // End edges
					}
					writer.writeEndElement(); // End node
				}
				writer.writeEndElement(); // End Tree
				writer.writeEndDocument(); //End Document
				writer.flush();
				writer.close();
				System.out.println("End!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("Vecchio, problema!");
				e.printStackTrace();
				return false;
			}
			return true;
		}*/
}
