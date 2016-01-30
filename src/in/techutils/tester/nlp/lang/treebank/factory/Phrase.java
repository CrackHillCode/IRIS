package in.techutils.tester.nlp.lang.treebank.factory;

import in.techutils.tester.nlp.lang.treebank.phrases.ADJP;
import in.techutils.tester.nlp.lang.treebank.phrases.ADVP;
import in.techutils.tester.nlp.lang.treebank.phrases.CONJP;
import in.techutils.tester.nlp.lang.treebank.phrases.FRAG;
import in.techutils.tester.nlp.lang.treebank.phrases.INTJ;
import in.techutils.tester.nlp.lang.treebank.phrases.LST;
import in.techutils.tester.nlp.lang.treebank.phrases.NAC;
import in.techutils.tester.nlp.lang.treebank.phrases.NP;
import in.techutils.tester.nlp.lang.treebank.phrases.NX;
import in.techutils.tester.nlp.lang.treebank.phrases.PP;
import in.techutils.tester.nlp.lang.treebank.phrases.PRNT;
import in.techutils.tester.nlp.lang.treebank.phrases.PRT;
import in.techutils.tester.nlp.lang.treebank.phrases.QP;
import in.techutils.tester.nlp.lang.treebank.phrases.RRC;
import in.techutils.tester.nlp.lang.treebank.phrases.UCP;
import in.techutils.tester.nlp.lang.treebank.phrases.VP;
import in.techutils.tester.nlp.lang.treebank.phrases.WHADJP;
import in.techutils.tester.nlp.lang.treebank.phrases.WHAVP;
import in.techutils.tester.nlp.lang.treebank.phrases.WHNP;
import in.techutils.tester.nlp.lang.treebank.phrases.WHPP;
import in.techutils.tester.nlp.lang.treebank.phrases.X;
import opennlp.tools.parser.Parse;

public abstract class Phrase extends Element {
	public enum TYPE {
		ADJP("ADJP"), // - Adjective Phrase.
		ADVP("ADVP"), // - Adverb Phrase.
		CONJP("CONJP"), // - Conjunction Phrase.
		FRAG("FRAG"), // - Fragment.
		INTJ("INTJ"), // - Interjection. Corresponds approximately to the part-of-speech tag UH.
		LST("LST"), // - List marker. Includes surrounding punctuation.
		NAC("NAC"), // - Not a Constituent; used to show the scope of certain prenominal modifiers within an NP.
		NP("NP"), // - Noun Phrase.
		NX("NX"), // - Used within certain complex NPs to mark the head of the NP. Corresponds very roughly to N-bar level but used quite differently.
		PP("PP"), // - Prepositional Phrase.
		PRN("PRN"), // - Parenthetical.
		PRT("PRT"), // - Particle. Category for words that should be tagged RP.
		QP("QP"), // - Quantifier Phrase (i.e. complex measure/amount phrase); used within NP.
		RRC("RRC"), // - Reduced Relative Clause.
		UCP("UCP"), // - Unlike Coordinated Phrase.
		VP("VP"), // - Vereb Phrase.
		WHADJP("WHADJP"), // - Wh-adjective Phrase. Adjectival phrase containing a wh-adverb, as in how hot.
		WHAVP("WHAVP"), // - Wh-adverb Phrase. Introduces a clause with an NP gap. May be null (containing the 0 complementizer) or lexical, containing a wh-adverb such as how or why.
		WHNP("WHNP"), // - Wh-noun Phrase. Introduces a clause with an NP gap. May be null (containing the 0 complementizer) or lexical, containing some wh-word, e.g. who, which book, whose daughter, none of which, or how many leopards.
		WHPP("WHPP"), // - Wh-prepositional Phrase. Prepositional phrase containing a wh-noun phrase (such as of which or by whose authority) that either introduces a PP gap or is contained by a WHNP.
		X("X"); // - Unknown, uncertain, or unbracketable. X is often used for bracketing typos and in bracketing the...the-constructions.
		TYPE(String s) {
			val = s;
		}

		private String val;

		public String getType() {
			return val;
		}
	}

	public Phrase(Parse p) {
		super(p);
		this.setType(ELEM_TYPE.PHRASE);
	}

	public static Phrase getPhrase(Parse phrase) {
		Phrase ph = null;
		try {
			switch (TYPE.valueOf(phrase.getType())) {
			case ADJP:
				ph = new ADJP(phrase);
				break;
			case ADVP:
				ph = new ADVP(phrase);
				break;
			case CONJP:
				ph = new CONJP(phrase);
				break;
			case FRAG:
				ph = new FRAG(phrase);
				break;
			case INTJ:
				ph = new INTJ(phrase);
				break;
			case LST:
				ph = new LST(phrase);
				break;
			case NAC:
				ph = new NAC(phrase);
				break;
			case NP:
				ph = new NP(phrase);
				break;
			case NX:
				ph = new NX(phrase);
				break;
			case PP:
				ph = new PP(phrase);
				break;
			case PRN:
				ph = new PRNT(phrase);
				break;
			case PRT:
				ph = new PRT(phrase);
				break;
			case QP:
				ph = new QP(phrase);
				break;
			case RRC:
				ph = new RRC(phrase);
				break;
			case UCP:
				ph = new UCP(phrase);
				break;
			case VP:
				ph = new VP(phrase);
				break;
			case WHADJP:
				ph = new WHADJP(phrase);
				break;
			case WHAVP:
				ph = new WHAVP(phrase);
				break;
			case WHNP:
				ph = new WHNP(phrase);
				break;
			case WHPP:
				ph = new WHPP(phrase);
				break;
			case X:
				ph = new X(phrase);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			//			System.out.println("No Such Phrase Found: " + e);
		}
		return ph;
	}

}
