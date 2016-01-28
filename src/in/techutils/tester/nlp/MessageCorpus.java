package in.techutils.tester.nlp;

import java.util.Date;

public class MessageCorpus {
	private final String msgId;
	private String corpus;
	private Entity source;
	private Date msgTime;
	private static int msgSeq;

	public MessageCorpus() {
		msgId = "Msg#" + (msgSeq++);
	}

	public String getCorpus() {
		return corpus;
	}

	public void setCorpus(String corpus) {
		this.corpus = corpus;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public Entity getSource() {
		return source;
	}

	public void setSource(Entity source) {
		this.source = source;
	}

	public String getMsgId() {
		return msgId;
	}
}