import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class PrepareNew {

	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.out.println("Error! Wrong number of arguments");
			return;
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream("news.arff"), "UTF-8"));

		TextReader t1 = new TextReader(new InputStreamReader(
				new FileInputStream(args[0]), "UTF-8"));
		TextReader t2 = new TextReader(new InputStreamReader(
				new FileInputStream(args[1]), "UTF-8"));

		TreeMap words1 = new TreeMap();
		TreeMap words2 = new TreeMap();

		WordCount.readWords(t1, words1);
		WordCount.readWords(t2, words2);

		List wordsList1 = new ArrayList(words1.values());
		List wordsList2 = new ArrayList(words2.values());

		Iterator iter;

		out.write("@RELATION news\n\n");

		iter = wordsList1.iterator();
		while (iter.hasNext()) {
			WordCount.WordData data = (WordCount.WordData) iter.next();
			out.write("@ATTRIBUTE " + data.word + " REAL\n");
		}

		out.write("\n@ATTRIBUTE class 	{'Economia e Politica', 'Desporto', 'Cultura e Lazer', 'Ciencia e Tecnologia', 'Sociedade'}\n\n");

		out.write("@DATA\n");

		iter = wordsList1.iterator();
		while (iter.hasNext()) {
			WordCount.WordData data = (WordCount.WordData) iter.next();
			if (words2.containsKey(data.word)) {
				WordCount.WordData data2 = (WordCount.WordData) words2
						.get(data.word);
				out.write(data2.count + ",");
			} else
				out.write("0,");
		}
		out.write("?\n");
		out.close();
	}

}
