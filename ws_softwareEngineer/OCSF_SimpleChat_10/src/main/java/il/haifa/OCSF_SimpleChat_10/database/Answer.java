package il.haifa.OCSF_SimpleChat_10.database;

import java.util.Calendar; 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "answer")
public class Answer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
	
	private String answer;
	
	
	private int counterUpdated = -1;
	
	private Calendar firstAppearance;
	
	private Calendar lastUpdated;
	
	
	///Constructors
	public Answer(String answer)
	{
		this.answer = answer;
		firstAppearance = Calendar.getInstance();
		lastUpdated = firstAppearance;
	}
	
	public Answer() {
	}

	
	///Methods
	public String getAnswer() {
		return answer;
	}

	
	public void setAnswer(String answer) {                    //remember to fix this
		this.answer = answer;
		counterUpdated++;
		lastUpdated = Calendar.getInstance();
	}
	
	public void setQuestion(Question q) {
		this.question = q;
	}
	
	/*
	public Question getQuestion() {
		return question;
	}
	*/
	public int getId() {
		return id;
	}

	public int getCounterUpdated() {
		return counterUpdated;
	}

	public Calendar getFirstAppearance() {
		return firstAppearance;
	}

	public Calendar getLastUpdated() {
		return lastUpdated;
	}
	
}
