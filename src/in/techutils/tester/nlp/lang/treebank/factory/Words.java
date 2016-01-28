package in.techutils.tester.nlp.lang.treebank.factory;

import in.techutils.tester.nlp.lang.treebank.words.*;
import opennlp.tools.parser.Parse;

public class Words {
	public enum TYPE {
		CC("CC"), // - Coordinating conjunction
		CD("CD"), // - Cardinal number
		DT("DT"), // - Determiner
		EX("EX"), // - Existential there
		FW("FW"), // - Foreign word
		IN("IN"), // - Preposition or subordinating conjunction
		JJ("JJ"), // - Adjective
		JJR("JJR"), // - Adjective, comparative
		JJS("JJS"), // - Adjective, superlative
		LS("LS"), // - List item marker
		MD("MD"), // - Modal
		NN("NN"), // - Noun, singular or mass
		NNS("NNS"), // - Noun, plural
		NNP("NNP"), // - Proper noun, singular
		NNPS("NNPS"), // - Proper noun, plural
		PDT("PDT"), // - Predeterminer
		POS("POS"), // - Possessive ending
		PRP("PRP"), // - Personal pronoun
		PRP$("PRP$"), // - Possessive pronoun (prolog version PRP-S)
		RB("RB"), // - Adverb
		RBR("RBR"), // - Adverb, comparative
		RBS("RBS"), // - Adverb, superlative
		RP("RP"), // - Particle
		SYM("SYM"), // - Symbol
		TO("TO"), // - to
		UH("UH"), // - Interjection
		VB("VB"), // - Verb, base form
		VBD("VBD"), // - Verb, past tense
		VBG("VBG"), // - Verb, gerund or present participle
		VBN("VBN"), // - Verb, past participle
		VBP("VBP"), // - Verb, non-3rd person singular present
		VBZ("VBZ"), // - Verb, 3rd person singular present
		WDT("WDT"), // - Wh-determiner
		WP("WP"), // - Wh-pronoun
		WP$("WP$"), // - Possessive wh-pronoun (prolog version WP-S)
		WRB("WRB");// - Wh-adverb
		private String val;

		TYPE(String s) {
			this.val = s;
		}

		public String getType() {
			return val;
		}
	}

	private Parse wordParse;

	public Words(Parse wp) {
		wordParse = wp;

	}

	public Parse getWordParse() {
		return wordParse;
	}

	public void setWordParse(Parse wordParse) {
		this.wordParse = wordParse;
	}

	public static Words getWords(Parse words) {
		Words ws = null;

		try {
			switch (TYPE.valueOf(words.getType())) {
				case CC:
					ws = new CC(words);
					break;
				case CD:
					ws = new CD(words);
					break;
				case DT:
					ws = new DT(words);
					break;
				case EX:
					ws = new EX(words);
					break;
				case FW:
					ws = new FW(words);
					break;
				case IN:
					ws = new IN(words);
					break;
				case JJ:
					ws = new JJ(words);
					break;
				case JJR:
					ws = new JJR(words);
					break;
				case JJS:
					ws = new JJS(words);
					break;
				case LS:
					ws = new LS(words);
					break;
				case MD:
					ws = new MD(words);
					break;
				case NN:
					ws = new NN(words);
					break;
				case NNS:
					ws = new NNS(words);
					break;
				case NNP:
					ws = new NNP(words);
					break;
				case NNPS:
					ws = new NNPS(words);
					break;
				case PDT:
					ws = new PDT(words);
					break;
				case POS:
					ws = new POS(words);
					break;
				case PRP:
					ws = new PRP(words);
					break;
				case PRP$:
					ws = new PRP$(words);
					break;
				case RB:
					ws = new RB(words);
					break;
				case RBR:
					ws = new RBR(words);
					break;
				case RBS:
					ws = new RBS(words);
					break;
				case RP:
					ws = new RP(words);
					break;
				case SYM:
					ws = new SYM(words);
					break;
				case TO:
					ws = new TO(words);
					break;
				case UH:
					ws = new UH(words);
					break;
				case VB:
					ws = new VB(words);
					break;
				case VBD:
					ws = new VBD(words);
					break;
				case VBG:
					ws = new VBG(words);
					break;
				case VBN:
					ws = new VBN(words);
					break;
				case VBP:
					ws = new VBP(words);
					break;
				case VBZ:
					ws = new VBZ(words);
					break;
				case WDT:
					ws = new WDT(words);
					break;
				case WP:
					ws = new WP(words);
					break;
				case WP$:
					ws = new WP$(words);
					break;
				case WRB:
					ws = new WRB(words);
					break;

				default:
					break;
			}
		} catch (Exception e) {

		}
		return ws;
	}
}
