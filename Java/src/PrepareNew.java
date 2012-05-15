import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class PrepareNew {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("news.arff"), "UTF-8"));

		TextReader t1 = new TextReader(new InputStreamReader(
				new FileInputStream("words.txt"), "UTF-8"));

		File dir = new File("NoticiasNovas");
		File[] files = dir.listFiles();

		TreeMap words1 = new TreeMap();

		WordCount.readWords(t1, words1);

		List wordsList1 = new ArrayList(words1.values());


		Iterator iter;

		out.write("@RELATION news\n\n");

		iter = wordsList1.iterator();
		while (iter.hasNext()) {
			WordCount.WordData data = (WordCount.WordData) iter.next();
			out.write("@ATTRIBUTE " + data.word + " REAL\n");
		}

		out.write("\n@ATTRIBUTE class 	{'Economia e Politica', 'Desporto', 'Cultura e Lazer', 'Ciencia e Tecnologia', 'Sociedade'}\n\n");

		out.write("@DATA\n");

		for(int i = 0; i < files.length; i++)
		{
			TextReader t2 = new TextReader(new InputStreamReader(
					new FileInputStream(files[i]), "UTF-8"));
			TreeMap words2 = new TreeMap();
			WordCount.readWords(t2, words2);
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
		}
		out.close();

		int[] classification = classifyNews();
		Writer news = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("news.rb"), "UTF-8"));


		for(int i = 0; i < classification.length; i++)
		{
			try {

				StringBuffer cont = new StringBuffer();

				Scanner s = new Scanner(new FileInputStream(files[i]), "UTF-8");
				cont.append("News.seed do |n|\n");

				cont.append("n.title = ");
				cont.append('"' + s.nextLine().replace('"', '\'') + '"' + '\n');

				cont.append("n.url = ");
				cont.append('"' + s.nextLine().replace('"', '\'') + '"' + '\n');

				cont.append("n.category = ");
				cont.append(String.valueOf(classification[i]+1));
				cont.append("\n");

				cont.append("n.text = ");

				cont.append('"');

				while(s.hasNextLine()) {
					cont.append(s.nextLine().replace('"', '\''));
				}

				cont.append('"');
				cont.append("\n");

				cont.append("end\n\n");
				s.close();

				news.write(cont.toString());

			}
			catch(Exception e)
			{
				continue;
			}

		}
		news.close();

	}

	private static int[] classifyNews() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("nb.model"));
		Classifier cls = (Classifier) ois.readObject();
		ois.close();
		// TODO Auto-generated method stub
		// load unlabeled data
		Instances unlabeled = new Instances(
				new BufferedReader(
						new FileReader("news.arff")));

		// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

		// create copy
		int[] classification = new int[unlabeled.numInstances()]; 
		// label instances
		for (int i = 0; i < unlabeled.numInstances(); i++) {
			double clsLabel = cls.classifyInstance(unlabeled.instance(i));
			classification[i] = (int) clsLabel;

		}
		return classification;

	}

}
