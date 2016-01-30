package in.techutils.tester.nlp.lang.treebank.factory;

import opennlp.tools.parser.Parse;

public abstract class Element {
	public enum ELEM_TYPE {
		CLAUSE, PHRASE, WORD;
	}

	private Parse parseObject;
	private ELEM_TYPE type;

	public Element(Parse p) {
		this.setParseObject(p);
		if (p.getChildren() != null && p.getChildCount() > 0) {
			for (Parse cx : p.getChildren()) {
				Element ex = Element.getElement(cx);
				if (ex != null) {

				}
			}
		}
	}

	public static Element getElement(Parse t) {
		Element ex = null;
		Clause cx = Clause.getClause(t);
		Phrase px = Phrase.getPhrase(t);
		Words wx = Words.getWords(t);

		if (cx != null) {
			ex = cx;
			System.out.print("Clause: " + ex.getParseObject().getType() + ": ");
			System.out.println(ex.getParseObject().getCoveredText());
		} else if (px != null) {
			ex = px;
			System.out.print("Phrase: " + ex.getParseObject().getType() + ": ");
			System.out.println(ex.getParseObject().getCoveredText());
		} else if (wx != null) {
			ex = wx;
			System.out.print("-->Word: " + ex.getParseObject().getType() + ": ");
			System.out.println(ex.getParseObject().getCoveredText());
		}
		//		else {
		//			System.out.print("Check Parse Type: ");
		//			t.show();
		//		}
		return ex;
	}

	public Parse getParseObject() {
		return parseObject;
	}

	public void setParseObject(Parse parseObject) {
		this.parseObject = parseObject;
	}

	public ELEM_TYPE getType() {
		return type;
	}

	protected void setType(ELEM_TYPE type) {
		this.type = type;
	}

}
