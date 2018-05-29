
package data;

import domain.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class XMLPersonManager {
    
    //variables
    private Document document;
    private Element root;
    private String path;

    public XMLPersonManager(String path) throws JDOMException, IOException {
        this.path = path;
        
        File fileStudent = new File(path);
        if(fileStudent.exists()){
            //1. El archivo ya existe, entonces lo cargo en memoria
            
            //toma la estructura de datos y las carga en memeoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            
            //cargar en memoria
            this.document = saxBuilder.build(this.path);
            this.root = this.document.getRootElement();
        }else{
            //2. El archivo no existe, lo creo y lo guardo
            
            //creamos el elemento raiz
            this.root = new Element("persons");
            
            //creamos el documento
            this.document = new Document(this.root);
            
            //guardar el archivo
            storeXML();
            
            //guardar el archivo en disco duro
        }//end method 
    }
    
    private void storeXML() throws FileNotFoundException, IOException{
            XMLOutputter xMLOutputter = new XMLOutputter();
            xMLOutputter.output(this.document, new PrintWriter(this.path));
        }
    
    //insertar un estudiante nuevo
    public void insertPerson(Person person) throws IOException{
        //debemos crear elemento respecto al estudiante.
      Element ePerson = new Element("Persons");
      ePerson.setAttribute("id",person.getId());
      
      //nombre
      Element eName = new Element("name");
      eName.addContent(person.getName());
      
      //apellido
      Element eLastname = new Element("lastname");
      eLastname.addContent(person.getLastname());
      
      //segundoApellido
      Element eSecondLastName = new Element("secondLastname");
      eSecondLastName.addContent(person.getSecondLastname());
      
      //fecha de nacimiento
      Element eBirthDate = new Element("birthDate");
      eBirthDate.addContent(person.getBirthDate());
      
      //nacionalidad
      Element eNationality = new Element("nationality");
      eNationality.addContent(person.getNationality());
      
      //cedula del padre
      Element eFathersId = new Element("fathersId");
      eFathersId.addContent(person.getFathersId());
      
      //agregaral elemento studenr el name y el admission
      ePerson.addContent(eName);
      ePerson.addContent(eLastname);
      ePerson.addContent(eSecondLastName);
      ePerson.addContent(eBirthDate);
      ePerson.addContent(eNationality);
      ePerson.addContent(eFathersId);
      
        for (int i = 0; i < person.getChild().size(); i++) 
            ePerson.addContent(addChild(person.getChild(i)));
     
      
      //agregarmos al root
      this.root.addContent(ePerson);
      
      
      //guardar al disco
      
      storeXML();
    }//end method
    
    private Element addChild(Person person) throws IOException{
        //debemos crear elemento respecto al estudiante.
      Element ePerson = new Element("Persons");
      ePerson.setAttribute("id",person.getId());
      
      //nombre
      Element eName = new Element("name");
      eName.addContent(person.getName());
      
      //apellido
      Element eLastname = new Element("lastname");
      eLastname.addContent(person.getLastname());
      
      //segundoApellido
      Element eSecondLastName = new Element("secondLastname");
      eSecondLastName.addContent(person.getSecondLastname());
      
      //fecha de nacimiento
      Element eBirthDate = new Element("birthDate");
      eBirthDate.addContent(person.getBirthDate());
      
      //nacionalidad
      Element eNationality = new Element("nationality");
      eNationality.addContent(person.getNationality());
      
      //cedula del padre
      Element eFathersId = new Element("fathersId");
      eFathersId.addContent(person.getFathersId());
      
      
      //agregaral elemento studenr el name y el admission
      ePerson.addContent(eName);
      ePerson.addContent(eLastname);
      ePerson.addContent(eSecondLastName);
      ePerson.addContent(eBirthDate);
      ePerson.addContent(eNationality);
      ePerson.addContent(eFathersId);
      //agreag los hijos de la persona
      for (int i = 0; i < person.getChild().size(); i++) 
            ePerson.addContent(addChild(person.getChild(i)));
      
      return ePerson;
    }
    
    public Element getPerson(Element element, String desiredId) {
        if (desiredId.equals(element.getAttributeValue("id"))) {
            return element;
        }
        for (int i = 0; i < element.getChildren().size(); i++) {
            Object node = (Element) element.getChildren().get(i);
            if (node instanceof Element) {
                Element child = getPerson((Element) node, desiredId);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }
    
    public void deletePerson(String id) throws IOException{
        
                if (id.equals(this.root.getAttributeValue("id"))) {
            this.root.getParentElement().removeContent(this.root);
            storeXML();
        }
        for (int i = 0; i < this.root.getChildren().size(); i++) {
            Object node = (Element) this.root.getChildren().get(i);
            if (node instanceof Element) {
                Element child = getPerson((Element) node, id);
                if (child != null) {
                    child.getParentElement().removeContent(child);
                    storeXML();
                }
            }
        }
    }
    
    //leer todos los hijos de la raiz
    public Person[] getAllPersons(){
        int personsQuantity = this.root.getContentSize();
        
        //obtenemos una lista con todos los elementos de ruth
        List elementList = this.root.getChildren();
        
        //definir el tamanno del arreglo
        Person[] personsArray = new Person[personsQuantity];
        
        //recorrer la lista para ir insertando en el arreglo
        int count = 0;
        for (Object currentObject: elementList){
            //casting de object a element
            Element currentElement = (Element) currentObject;
            
            //crear estudiante
            Person currentStudent = new Person();
            
            //id
            currentStudent.setId(
                    currentElement.getAttributeValue("id")
            );
            
            //name
            currentStudent.setName(
                    currentElement.getChild("name").getValue()
            );
            
            //lastName
            currentStudent.setLastname(
                    currentElement.getChild("lastname").getValue()
            );
            
            //secondLastName
            currentStudent.setSecondLastname( 
                    currentElement.getChild("secondLastname").getValue()
            );
            
            //birthDate
            currentStudent.setBirthDate( 
                    currentElement.getChild("birthDate").getValue()
            );
            
            //nationality
            currentStudent.setNationality( 
                    currentElement.getChild("nationality").getValue()
            );
            
            //fathers
            currentStudent.setFathersId( 
                    currentElement.getChild("fathersId").getValue()
            );
            
            personsArray[count++] = currentStudent;
        }
        return personsArray;
    }
    
    public boolean hasFather(ArrayList<Person> list, Person person){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(person.getFathersId())){
               list.get(i).addChild(person);
               return true;
            }   
            if(list.get(i).getChild().size() > 0)
               if(hasFather(list.get(i).getChild(), person))
                   return true;
        }
        return false;
    }
}
