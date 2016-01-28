package in.techutils.tester.nlp.lang.treebank.factory;

import opennlp.tools.parser.Parse;
import in.techutils.tester.nlp.lang.treebank.clauses.*;

public abstract class Clause {
	public enum TYPE {
		S("S"), // - simple declarative clause, i.e. one that is not introduced
				// by a (possible empty) subordinating conjunction or a wh-word
				// and that does not exhibit subject-verb inversion.
		SBAR("SBAR"), // - Clause introduced by a (possibly empty) subordinating
						// conjunction.
		SBARQ("SBARQ"), // - Direct question introduced by a wh-word or a
						// wh-phrase. Indirect questions and relative clauses
						// should be bracketed as SBAR, not SBARQ.
		SINV("SINV"), // - Inverted declarative sentence, i.e. one in which the
						// subject follows the tensed verb or modal.
		SQ("SQ");// - Inverted yes/no question, or main clause of a wh-question,
					// following the wh-phrase in SBARQ.
		TYPE(String s) {
			val = s;
		}

		private String val;

		public String getType() {
			return val;
		}
	}

	private Parse clauseParse;

	public Clause(Parse p) {
		clauseParse = p;
		if (p.getChildren() != null && p.getChildCount() > 0) {
			for (Parse cx : p.getChildren()) {
				if (Clause.getClause(cx) != null) {
					Clause clx = Clause.getClause(cx);
					System.out.print("Clause: " + clx.getClauseParse().getType() + ": ");
					System.out.println(clx.getClauseParse().getCoveredText());
				} else if (Phrase.getPhrase(cx) != null) {
					Phrase phx = Phrase.getPhrase(cx);
					System.out.print("Phrase: " + phx.getPhraseParse().getType() + ": ");
					System.out.println(phx.getPhraseParse().getCoveredText());
				}

			}
		}
	}

	public final static Clause getClause(Parse t) {
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

	public Parse getClauseParse() {
		return clauseParse;
	}

	public void setClauseParse(Parse cl) {
		this.clauseParse = cl;
	}
}
