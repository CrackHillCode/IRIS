package in.techutils.tester.nlp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import in.techutils.tester.nlp.lang.treebank.TreebankProcessor;

public class Tester {

	public static void main(String[] args) {

		// String corpus = "There was a boy. His name was Alex Mathews";
		// String corpus = "This is it";
		String corpus = "My Name is Alex Mathews, and I work for myself";
		// String corpus = "I was born on 18 June, 1988. ";//
		// String corpus = "My birth place is India. ";//
		// String corpus = "I get a salary of Rs. 5000. ";//
		// String corpus = "I work for Oracle India pvt. " ;//
		// String corpus = "Oracle India pvt. is located in Hyderabad,
		// India.";//
		Entity s = null;// retrieveEntity();
		Entity source = s == null ? new Entity(Entity.TYPE.USER) : s;

		MessageCorpus mc = new MessageCorpus();
		mc.setCorpus(corpus);
		mc.setMsgTime(new Date());
		mc.setSource(source);

		TreebankProcessor t = new TreebankProcessor();
		t.processCorpus(mc);

		// saveEntity(source);
	}

	public static void saveEntity(Entity e) {
		try {
			FileOutputStream fileOut = new FileOutputStream("source.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in source.ser");
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public static Entity retrieveEntity() {
		Entity source = null;
		try {
			FileInputStream fileIn = new FileInputStream("source.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			source = (Entity) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception i) {
		}
		return source;
	}

}