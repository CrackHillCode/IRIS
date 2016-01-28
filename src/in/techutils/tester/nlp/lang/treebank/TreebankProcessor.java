package in.techutils.tester.nlp.lang.treebank;

import java.io.FileInputStream;
import java.io.InputStream;

import in.techutils.tester.nlp.Entity;
import in.techutils.tester.nlp.MessageCorpus;
import in.techutils.tester.nlp.lang.treebank.factory.Clause;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import opennlp.tools.util.model.BaseModel;

public class TreebankProcessor {
	public static final String MODEL_PATH = "models/";
	private static SentenceDetectorME sentenceDetector;
	private static Tokenizer tokenizer;
	private static NameFinderME nameFinder, placeFinder, timeFinder, currencyFinder, orgFinder;
	private static Parser parser;

	public TreebankProcessor() {
		InputStream modelIn = null;
		try {
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-sent.bin");
				BaseModel model = new SentenceModel(modelIn);
				sentenceDetector = new SentenceDetectorME((SentenceModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-token.bin");
				BaseModel model = new TokenizerModel(modelIn);
				tokenizer = new TokenizerME((TokenizerModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-ner-person.bin");
				BaseModel model = new TokenNameFinderModel(modelIn);
				nameFinder = new NameFinderME((TokenNameFinderModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-ner-location.bin");
				BaseModel model = new TokenNameFinderModel(modelIn);
				placeFinder = new NameFinderME((TokenNameFinderModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-ner-date.bin");
				BaseModel model = new TokenNameFinderModel(modelIn);
				timeFinder = new NameFinderME((TokenNameFinderModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-ner-money.bin");
				BaseModel model = new TokenNameFinderModel(modelIn);
				currencyFinder = new NameFinderME((TokenNameFinderModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-ner-organization.bin");
				BaseModel model = new TokenNameFinderModel(modelIn);
				orgFinder = new NameFinderME((TokenNameFinderModel) model);
			}
			{
				modelIn = new FileInputStream(MODEL_PATH + "en-parser-chunking.bin");
				BaseModel model = new ParserModel(modelIn);
				parser = ParserFactory.create((ParserModel) model);
			}

		} catch (Exception e) {
			System.out.println("Error in initialization: " + e);
		} finally {
			try {
				modelIn.close();
			} catch (Exception e2) {
			}
		}
	}

	public void processCorpus(MessageCorpus mc) {
		String corpus = mc.getCorpus();
		String[] statements = detectSentences(corpus);
		Entity source = mc.getSource();

		for (String statement : statements) {
			System.out.println(source.getName() + " says: " + statement);

			try {
				dumpObjectData("nameFinder", detectObjects(statement, getNameFinder()));
				dumpObjectData("placeFinder", detectObjects(statement, getPlaceFinder()));
				dumpObjectData("timeFinder", detectObjects(statement, getTimeFinder()));
				dumpObjectData("currencyFinder", detectObjects(statement, getCurrencyFinder()));
				dumpObjectData("orgFinder", detectObjects(statement, getOrgFinder()));
			} catch (Exception e) {
				// System.out.println(e);
			}

			parseStatement(statement);
			System.out.println("------------------------------\n");
		}
	}

	private void dumpObjectData(String type, String[] objects) {
		for (String string : objects) {
			System.out.println(type + "--->" + string);
		}
	}

	public void parseStatement(String statement) {
		Parse topParses[] = ParserTool.parseLine(statement, parser, 1);
		for (Parse parse : topParses) {

			for (Parse psc : parse.getChildren()) {
				processClause(psc);
			}
		}
	}

	private void processClause(Parse clause) {
		/**
		 * <pre>
		 * - find Subject
		 * - find Predicate
		 * - break Predicate
		 * </pre>
		 */
		clause.show();
		System.out.println(clause.getType());

		for (Parse subs : clause.getChildren()) {
			System.out.print("Subs: ");
			subs.show();
			Clause cl = Clause.getClause(subs);
			if (cl == null) {

			}
			System.out.println();
			//			if (cl != null) {
			//				System.out.println("Data: " + cl.getClauseParse().getCoveredText());
			//				System.out.println("Data: " + cl.getClauseParse().getType() + "\n");
			//			}
		}

	}

	public String[] detectObjects(String sentence, NameFinderME finder) {
		String[] tokens = tokenize(sentence);
		Span nameSpans[] = finder.find(tokens);
		String[] detectedObjects = new String[nameSpans.length];
		for (int i = 0; i < nameSpans.length; i++) {
			Span span = nameSpans[i];
			detectedObjects[i] = "";
			for (int j = span.getStart(); j <= span.getEnd(); j++) {
				detectedObjects[i] += tokens[j] + " ";
			}
			detectedObjects[i] = detectedObjects[i].trim();
		}
		finder.clearAdaptiveData();
		return detectedObjects;
	}

	public String[] tokenize(String corpus) {
		return tokenizer.tokenize(corpus);
	}

	public String[] detectSentences(String corpus) {
		String[] statements = sentenceDetector.sentDetect(corpus);
		return statements;
	}

	public SentenceDetectorME getSentenceDetector() {
		return sentenceDetector;
	}

	public Tokenizer getTokenizer() {
		return tokenizer;
	}

	public NameFinderME getNameFinder() {
		return nameFinder;
	}

	public NameFinderME getPlaceFinder() {
		return placeFinder;
	}

	public NameFinderME getTimeFinder() {
		return timeFinder;
	}

	public NameFinderME getCurrencyFinder() {
		return currencyFinder;
	}

	public NameFinderME getOrgFinder() {
		return orgFinder;
	}

	public Parser getParser() {
		return parser;
	}
}
