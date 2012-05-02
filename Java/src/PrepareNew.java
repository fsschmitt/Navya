import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class PrepareNew {
	
	public static void main(String[] args) throws IOException {
		
		if(args.length != 3)
		{
			System.out.println("Error! Wrong number of arguments");
			return;
		}
		
		TextReader t1 = new TextReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
		TextReader t2 = new TextReader(new InputStreamReader(new FileInputStream(args[1]), "UTF-8"));
		
		TreeMap words1 = new TreeMap();
		TreeMap words2 = new TreeMap();
		
		WordCount.readWords(t1, words1);
		WordCount.readWords(t2, words2);
		
		List wordsList1 = new ArrayList(words1.values());
		List wordsList2 = new ArrayList(words2.values());
		
		Iterator iter = wordsList1.iterator();
		Iterator iter2 = wordsList2.iterator();
		
		List finalWords = new ArrayList();
		
		int i = 0;
		while (iter.hasNext()) {
	         WordCount.WordData data = (WordCount.WordData)iter.next();
	         while(iter2.hasNext())
	         {
	        	 WordCount.WordData data2 = (WordCount.WordData)iter2.next();
	        	 if(data2.word.equals(data.word))
	        		 finalWords.add(data2);
	         }
	         iter2 = wordsList2.iterator();
	    }
		
		PrintWriter out = new PrintWriter(new FileWriter(args[2]));
		
		
		out.println("@RELATION nova\n");
	      

	      
	      iter = finalWords.iterator();
	      while (iter.hasNext()) {
	         WordCount.WordData data = (WordCount.WordData)iter.next();
	         out.println("@ATTRIBUTE "+ data.word + " REAL");
	      }
	      
	      out.println("\n@ATTRIBUTE class 	{'Economia e Politica', 'Desporto', 'Cultura e Lazer', 'Ciencia e Tecnologia', 'Sociedade'}\n");
	      
	      out.println("@DATA");

	    	  String categ = "?";
		      iter = finalWords.iterator();
		      while (iter.hasNext()) {
		    	  WordCount.WordData data = (WordCount.WordData) iter.next();
		    	  out.print(data.count + ",");
		      }
		      out.print("'"+categ+"'");

		
		System.out.println(finalWords.size());
		
		out.close();

	}

}
