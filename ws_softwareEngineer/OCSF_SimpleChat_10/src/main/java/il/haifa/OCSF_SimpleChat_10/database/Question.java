package il.haifa.OCSF_SimpleChat_10.database;

import java.util.ArrayList; 
import java.util.Calendar; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;





@Entity
@Table(name = "question")
public class Question {
	
			
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		String questionString;
		
		@OneToMany (cascade = CascadeType.ALL, mappedBy = "question")
		private List<Answer> answers ;
		
		@OneToOne (cascade = CascadeType.ALL)
		@JoinColumn(name = "question_id",referencedColumnName = "id")
		private Answer correctAnswer;
		
		private int counterUpdated = 0;
		
		private Calendar firstAppearance;
		
		private Calendar lastUpdated;

		
		//Constructors
		
		public Question(String question,Answer answer1,Answer answer2,Answer answer3, Answer answer4,int flagCorrect)
		{
			answers = new  ArrayList<Answer>();
			switch(flagCorrect)
			{
			case 1:
				correctAnswer = answer1;
				break;
			case 2:
				correctAnswer = answer2;
				break;
			case 3:
				correctAnswer = answer3;
				break;
			case 4:
				correctAnswer = answer4;
				break;
			default:
				 System.out.println("Correct answer must be from 1 to 4");
			}
			
			this.questionString = question;
			
			answers.add(answer1);
			answers.add(answer2);
			answers.add(answer3);
			answers.add(answer4);
			
			firstAppearance = Calendar.getInstance();
			lastUpdated = firstAppearance;
			
			answer1.setQuestion(this);
			answer2.setQuestion(this);
			answer3.setQuestion(this);
			answer4.setQuestion(this);	
			
			
		}
		
		public Question() {
		}
		
		
		//Methods

		public int getId() {
			return id;
		}
		
		
		public String getQuestion() {
			return questionString;
		}
		
		
		public void setQuestion(String questionStr) 
		{
			this.questionString = questionStr;
			counterUpdated++;
			lastUpdated = Calendar.getInstance();
		}
		
		
		public List<Answer> getAnswers() {
			return answers;
		}
		
		/*
		public void setAnswers(List<Answer> answers) {        ////////remember to add this to the final project
			if (answers.size())
			this.answers = answers;
		}
		*/
		
		
		public Answer getCorrectAnswer() {
			return correctAnswer;
		}
		
		/*
		public void setCorrectAnswer(Answer correctAnswer) { /////////////////remember to change it
			this.correctAnswer = correctAnswer;
		}
		*/
		
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
