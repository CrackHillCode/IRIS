//package in.techutils.tester.nlp;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//import opennlp.tools.cmdline.parser.ParserTool;
//import opennlp.tools.namefind.NameFinderME;
//
//import opennlp.tools.namefind.TokenNameFinderModel;
//import opennlp.tools.parser.Parse;
//import opennlp.tools.parser.Parser;
//import opennlp.tools.parser.ParserFactory;
//import opennlp.tools.parser.ParserModel;
//import opennlp.tools.sentdetect.SentenceDetectorME;
//import opennlp.tools.sentdetect.SentenceModel;
//import opennlp.tools.tokenize.Tokenizer;
//import opennlp.tools.tokenize.TokenizerME;
//import opennlp.tools.tokenize.TokenizerModel;
//import opennlp.tools.util.Span;
//import opennlp.tools.util.model.BaseModel;
//
//public class OpenNLPTester {
//	private SentenceDetectorME sentenceDetector;
//	private Parser parser;
//	private Tokenizer tokenizer;
//	private NameFinderME nameFinder, placeFinder, timeFinder;
//
//	public OpenNLPTester() {
//		InputStream modelIn = null;
//		BaseModel model;
//		try {
//
//			modelIn = new FileInputStream("models/en-sent.bin");
//			model = new SentenceModel(modelIn);
//			this.sentenceDetector = new SentenceDetectorME((SentenceModel) model);
//
//			modelIn = new FileInputStream("models/en-ner-location.bin");
//			model = new TokenNameFinderModel(modelIn);
//			this.placeFinder = new NameFinderME((TokenNameFinderModel) model);
//
//			modelIn = new FileInputStream("models/en-ner-date.bin");
//			model = new TokenNameFinderModel(modelIn);
//			this.timeFinder = new NameFinderME((TokenNameFinderModel) model);
//
//			modelIn = new FileInputStream("models/en-ner-person.bin");
//			model = new TokenNameFinderModel(modelIn);
//			this.nameFinder = new NameFinderME((TokenNameFinderModel) model);
//
//			modelIn = new FileInputStream("models/en-token.bin");
//			model = new TokenizerModel(modelIn);
//			this.tokenizer = new TokenizerME((TokenizerModel) model);
//
//			modelIn = new FileInputStream("models/en-parser-chunking.bin");
//			model = new ParserModel(modelIn);
//			this.parser = ParserFactory.create((ParserModel) model);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (modelIn != null) {
//				try {
//					modelIn.close();
//				} catch (Exception e) {
//				}
//			}
//		}
//
//	}
//
//	public static void main(String[] args) {
//		OpenNLPTester t = new OpenNLPTester();
//		String corpus = "My name is Alex Mathews. " + "My friends are Simon, Frank and Max. "
//				+ "My date of birth is June 18th, 1988. " + "My country is India. " + "My wish is to be alive. "
//				+ "His name is Max. " + "He is my best friend.";
//		// String corpus = "My name is Alex Mathews. I was born on June 18th,
//		// 1988. "
//		// + "I am trying to build an Artificial Intelligence Module named Iris"
//		// + ", which should be able to listen, interpret and understand what I
//		// am saying. "
//		// + "It should be able to respond back as normal human beings do. "
//		// + "I hope I will be successful in this. It should be launched in USA
//		// first.";
//		t.processCorpus(corpus);
//	}
//
//	public void processCorpus(String corpus) {
//
//		String[] sentences = detectSentences(corpus);
//		for (String sentence : sentences) {
//			System.out.println("Statement: " + sentence);
//			String[] names = detectObjects(sentence, this.nameFinder);
//			String[] places = detectObjects(sentence, this.placeFinder);
//			String[] times = detectObjects(sentence, this.timeFinder);
//			if (names.length > 0) {
//				System.out.println("Names Detected: ");
//				for (String name : names) {
//					System.out.println("Name -->" + name);
//				}
//			}
//			if (places.length > 0) {
//				System.out.println("Places Detected: ");
//				for (String place : places) {
//					System.out.println("Place -->" + place);
//				}
//			}
//			if (times.length > 0) {
//				System.out.println("Times Detected: ");
//				for (String time : times) {
//					System.out.println("Time -->" + time);
//				}
//			}
//			System.out.println("Parsing Statements: ");
//			understandStatement(sentence);
//			System.out.println();
//		}
//
//	}
//
//	private void understandStatement(String sentence) {
//		Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);
//		for (Parse parse : topParses) {
//			parse.show();
//			// parse.showCodeTree();
//			// processSubParse(parse);
//		}
//	}
//
//	private String[] detectSentences(String corpus) {
//		String[] sentences = null;
//		sentences = sentenceDetector.sentDetect(corpus);
//		return sentences;
//	}
//
//	private String[] tokenize(String sentence) {
//		String[] tokens = null;
//		tokens = tokenizer.tokenize(sentence);
//		return tokens;
//	}
//
//	private String[] detectObjects(String sentence, NameFinderME finder) {
//		String[] names = null;
//		String[] tokens = tokenize(sentence);
//		Span[] nameSpans = finder.find(tokens);
//		names = new String[nameSpans.length];
//		for (int i = 0; i < nameSpans.length; i++) {
//			Span span = nameSpans[i];
//			// System.out.println("Object " + span);
//			names[i] = "";
//			for (int j = span.getStart(); j < span.getEnd(); j++) {
//				names[i] += tokens[j] + " ";
//			}
//			System.out.println(
//					"Text: " + names[i] + ", Prob: " + Math.round(span.getProb() * 100) + "%, Type: " + span.getType());
//			names[i] = names[i].trim();
//			// names[i] = sentence.substring(span.getStart(), span.getEnd() +
//			// 1);
//		}
//		finder.clearAdaptiveData();
//		return names;
//	}
//
//	public static void processSubParse(Parse px) {
//		for (Parse parse : px.getChildren()) {
//			if (px.getChildCount() == 0) {
//				System.out.print(parse.getType() + "--->");
//			}
//			parse.show();
//			for (Parse parses : parse.getChildren()) {
//				processSubParse(parses);
//			}
//		}
//	}
//}