package in.techutils.tester.nlp.lang.treebank.factory;

import in.techutils.tester.nlp.lang.treebank.clauses.S;
import in.techutils.tester.nlp.lang.treebank.clauses.SBAR;
import in.techutils.tester.nlp.lang.treebank.clauses.SBARQ;
import in.techutils.tester.nlp.lang.treebank.clauses.SINV;
import in.techutils.tester.nlp.lang.treebank.clauses.SQ;
import opennlp.tools.parser.Parse;

public abstract class Clause extends Element {
	public enum TYPE {
		S("S"), // - simple declarative clause, i.e. one that is not introduced by a (possible empty) subordinating conjunction or a wh-word and that does not exhibit subject-verb inversion.
		SBAR("SBAR"), // - Clause introduced by a (possibly empty) subordinating conjunction.
		SBARQ("SBARQ"), // - Direct question introduced by a wh-word or a wh-phrase. Indirect questions and relative clauses should be bracketed as SBAR, not SBARQ.
		SINV("SINV"), // - Inverted declarative sentence, i.e. one in which the subject follows the tensed verb or modal.
		SQ("SQ");// - Inverted yes/no question, or main clause of a wh-question, following the wh-phrase in SBARQ.

		TYPE(String s) {
			val = s;
		}

		private String val;

		public String getType() {
			return val;
		}
	}

	public Clause(Parse p) {
		super(p);
		this.setType(ELEM_TYPE.CLAUSE);
	}

	protected final static Clause getClause(Parse t) {
		Clause c = null;

		try {
			switch (Clause.TYPE.valueOf(t.getType())) {
			case S:
				c = new S(t);
				break;
			case SBAR:
				c = new SBAR(t);
				break;
			case SBARQ:
				c = new SBARQ(t);
				break;
			case SINV:
				c = new SINV(t);
				break;
			case SQ:
				c = new SQ(t);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			//			System.out.println("No Such Clause Found: " + e);
		}
		return c;
	}
}
