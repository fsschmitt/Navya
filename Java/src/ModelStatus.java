import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class ModelStatus {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("news2.arff"), "UTF-8"));

		TextReader t1 = new TextReader(new InputStreamReader(
				new FileInputStream("words.txt"), "UTF-8"));

		
		
		File dir = new File("Noticias2");
		
  	  TreeMap<File, String> files = new TreeMap<File, String>();
  	  ArrayList<File> listFiles = new ArrayList<File>();
  	  File[] categories = dir.listFiles();
  	  if (categories == null) {
  	      // Either dir does not exist or is not a directory
  	  } else {
  	      for (int i=0; i<categories.length; i++) {
  	    	  // Get filename of file or directory
  	          //String filename = categories[i].getName();
  	          if(categories[i] == null || categories[i].getName().contains(".")){
  	        	  
  	          } else {
  	        	  File[] file = categories[i].listFiles();
  	        	for(int j=0; j<file.length;j++){
  	        		if(file[j] == null || file[j].getName().startsWith(".")){
  	        			
  	        		} else {
  	        			listFiles.add(file[j]);
  	        			files.put(file[j],categories[i].getName());
  	        		}
  	        	}
  	          }
  	      }
  	  }
		
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
		
		out.write("\n@ATTRIBUTE class 	{'EconomiaePolitica', 'Desporto', 'CulturaeLazer', 'CienciaeTecnologia', 'Sociedade'}\n\n");

		out.write("@DATA\n");
		
		
		
		for(int i = 0; i < listFiles.size(); i++)
		{
			TextReader t2 = new TextReader(new InputStreamReader(new FileInputStream(listFiles.get(i)), "UTF-8"));
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
			out.write(files.get(listFiles.get(i)) + "\n");
		}
		out.close();

		classifyNews();

	}

	private static int[] classifyNews() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("smo.model"));
		Classifier cls = (Classifier) ois.readObject();
		ois.close();
		// TODO Auto-generated method stub
		// load unlabeled data
		Instances unlabeled = new Instances(
				new BufferedReader(
						new FileReader("news2.arff")));

		// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

		// create copy
		int[] classification = new int[unlabeled.numInstances()]; 
		// label instances
		//double[] clsLabel2 = null;
		double error = 0.0, correct = 0.0;
		double[] errorPerClass = {0.0,0.0,0.0,0.0,0.0};
		double[] correctPerClass = {0.0,0.0,0.0,0.0,0.0};
		String[] cat = {"EconomiaePolitica", "Desporto", "CulturaeLazer", "CienciaeTecnologia", "Sociedade"};
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		for (int i = 0; i < unlabeled.numInstances(); i++) {
			double clsLabel = cls.classifyInstance(unlabeled.instance(i));
			//clsLabel2 = cls.distributionForInstance(unlabeled.instance(i));
			classification[i] = (int) clsLabel;
			
			/*System.out.println("Real: " + cat[(int) unlabeled.instance(i).classValue()]);
			System.out.println("Prediction: " + cat[(int) clsLabel]);
			System.out.println("Prediction Probability: " + clsLabel2[(int) clsLabel]);
			System.out.println();*/
			
			if(cat[(int) unlabeled.instance(i).classValue()] == cat[(int) clsLabel])
				correctPerClass[(int) unlabeled.instance(i).classValue()]++;
			else
				errorPerClass[(int) unlabeled.instance(i).classValue()]++;
			
			if(clsLabel == unlabeled.instance(i).classValue())
				correct++;
			else
				error++;
		}
		
		out.println("Classificações corretas " + (int) correct +"<br/>");
		out.println("Classificações incorretas: " + (int) error+"<br/>");
		out.println("Percentagem de sucesso: " + round((correct/unlabeled.numInstances())*100, 2) + "%<br/>"+"<br/>");
		
		out.println("Economia e Política (Correto/Incorreto): " + (int) correctPerClass[0] + "/" + (int) errorPerClass[0] +"<br/>");
		out.println("Desporto (Correto/Incorreto): " + (int) correctPerClass[1] + "/" + (int) errorPerClass[1] +"<br/>");
		out.println("Cultura e Lazer (Correto/Incorreto): " + (int) correctPerClass[2] + "/" + (int) errorPerClass[2] +"<br/>");
		out.println("Ciência e Tecnologia (Correto/Incorreto): " + (int) correctPerClass[3] + "/" + (int) errorPerClass[3] +"<br/>");
		out.println("Sociedade (Correto/Incorreto): " + (int) correctPerClass[4] + "/" + (int) errorPerClass[4] +"<br/>");
		return classification;
		
		

	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}

