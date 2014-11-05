package ch1_23;
import java.util.SortedMap;
import java.util.Iterator;
import java.nio.charset.Charset;

//import java.util.TreeMap;

public class CharsetTest {
    public static void main(String[] args){
        SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();

       System.out.println("Charsets available: "+charsetsMap.size());
        for(String name : charsetsMap.keySet()){
            Charset charset = charsetsMap.get(name);
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(" (");
            for(Iterator<String> aliases = charset.aliases().iterator();aliases.hasNext();){
                sb.append(aliases.next());
                if (aliases.hasNext())
                    sb.append(",");
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
        
        //Теж саме можна зробити через TreeMap
        /*TreeMap<String,Charset> charsetTreeMap = new TreeMap<>();
        for(String name: charsetsMap.keySet()){
        	charsetTreeMap.put(name, charsetsMap.get(name));
        }
        
        System.out.println("Charsets available: "+charsetTreeMap.size());
        for(String name : charsetTreeMap.keySet()){
            Charset charset = charsetTreeMap.get(name);
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(" (");
            for(Iterator<String> aliases = charset.aliases().iterator();aliases.hasNext();){
                sb.append(aliases.next());
                if (aliases.hasNext())
                    sb.append(",");
            }
            sb.append(")");
            System.out.println(sb.toString());
        }*/
    }
}