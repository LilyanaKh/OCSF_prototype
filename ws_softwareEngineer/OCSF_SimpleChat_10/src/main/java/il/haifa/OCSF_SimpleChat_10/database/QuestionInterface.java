package il.haifa.OCSF_SimpleChat_10.database;

import java.util.List;
import java.util.Random;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class QuestionInterface {
	
	private static Session session;

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(Answer.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void initializeData() {

			Answer answer1_1 = new Answer();
			Answer answer2_1= new Answer();
			Answer answer3_1 = new Answer();
			Answer answer4_1 = new Answer();

			
			Question q1 = new Question("What is the capital of Italy ?",answer1_1,answer2_1,answer3_1,answer4_1,1);
			answer1_1.setAnswer("rome");
			answer2_1.setAnswer("Prague");
			answer3_1.setAnswer("Tel-Aviv");
			answer4_1.setAnswer("Kfr-Kna");
			
			session.save(q1);
			
			session.save(answer1_1);
			session.save(answer2_1);
			session.save(answer3_1);
			session.save(answer4_1);

			session.flush();
		
		
		
			Answer answer1_2 = new Answer();
			Answer answer2_2= new Answer();
			Answer answer3_2 = new Answer();
			Answer answer4_2 = new Answer();

			
			Question q2 = new Question("which of the following numbers are primary ?",answer1_2,answer2_2,answer3_2,answer4_2,3);
			answer1_2.setAnswer("39");
			answer2_2.setAnswer("21");
			answer3_2.setAnswer("2");
			answer4_2.setAnswer("145");
			
			session.save(q2);
			
			session.save(answer1_2);
			session.save(answer2_2);
			session.save(answer3_2);
			session.save(answer4_2);

			session.flush();


			Answer answer1_3 = new Answer();
			Answer answer2_3= new Answer();
			Answer answer3_3 = new Answer();
			Answer answer4_3 = new Answer();

			
			Question q3 = new Question("Which year the War World 2 ended?",answer1_3,answer2_3,answer3_3,answer4_3,3);
			answer1_3.setAnswer("1943");
			answer2_3.setAnswer("1944");
			answer3_3.setAnswer("1945");
			answer4_3.setAnswer("1946");
			
			session.save(q3);
			
			session.save(answer1_3);
			session.save(answer2_3);
			session.save(answer3_3);
			session.save(answer4_3);

			session.flush();


		
		
		
			Answer answer1_4 = new Answer();
			Answer answer2_4= new Answer();
			Answer answer3_4 = new Answer();
			Answer answer4_4 = new Answer();

			
			Question q4 = new Question("Which team has the most Champion Leagues titles?",answer1_4,answer2_4,answer3_4,answer4_4,4);
			answer1_4.setAnswer("Man-Utd");
			answer2_4.setAnswer("AC-Milan");
			answer3_4.setAnswer("Liverpool");
			answer4_4.setAnswer("Real Madrid");
			
			session.save(q4);
			
			session.save(answer1_4);
			session.save(answer2_4);
			session.save(answer3_4);
			session.save(answer4_4);

			session.flush();


		
			Answer answer1_5 = new Answer();
			Answer answer2_5= new Answer();
			Answer answer3_5 = new Answer();
			Answer answer4_5 = new Answer();

			
			Question q5 = new Question("Which of these movies has the most Oscar awards ?",answer1_5,answer2_5,answer3_5,answer4_5,2);
			answer1_5.setAnswer("Avengers EndGame");
			answer2_5.setAnswer("Titanic");
			answer3_5.setAnswer("Interstellar");
			answer4_5.setAnswer("Harry Potter and the Chamber of secrets");
			
			session.save(q5);
			
			session.save(answer1_5);
			session.save(answer2_5);
			session.save(answer3_5);
			session.save(answer4_5);

			session.flush();


		
		
			Answer answer1_6 = new Answer();
			Answer answer2_6= new Answer();
			Answer answer3_6 = new Answer();
			Answer answer4_6 = new Answer();

			
			Question q6 = new Question("Which of these actors has never won the oscar?",answer1_6,answer2_6,answer3_6,answer4_6,3);
			answer1_6.setAnswer("Leonardo Di Caprio");
			answer2_6.setAnswer("Johny Depp");
			answer3_6.setAnswer("Al pacino");
			answer4_6.setAnswer("Brad Pitt");
			
			session.save(q6);
			
			session.save(answer1_6);
			session.save(answer2_6);
			session.save(answer3_6);
			session.save(answer4_6);

			session.flush();


		
		
		session.getTransaction().commit();
	}

	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	public static String getAllQuestions() 
	{
		List<Question> questions = getAll(Question.class);
		String allQuestionString = "";
		for (Question question : questions) 
		{
			allQuestionString  = allQuestionString + "<"+question.getId()+">"+question.getQuestion()+ "/n" ;	
		}

		return allQuestionString;
	}

	
	public static void LoadData(String[] args) {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			initializeData();

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}
	

}
