import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimulationDriver {

	public static void main(String[] args) {

		Scanner myObj = new Scanner(System.in);

		Random random = new Random();

		// Create random list of students to give responses
		List<Student> students = Student.createList(random.nextInt(20) + 5);

		System.out.println("Welcome to iVote Simulator!\n\n");

		System.out.println("Start by setting up your question.");

		System.out.println("Please choose a question type. (Input 1 for Multiple Choice    2 for Single Choice");

		int qType = myObj.nextInt();

		if(qType == 1) {

			System.out.println("Please input the question you want to ask:");
			String userQuestion = myObj.nextLine();
			List<String> responses = newList("A", "B","C","D");
			Question question = new Question(userQuestion, responses);
			IVoteService IVS = new MRVoteService(question);

			// Each student in the list we created submits a response		
			for (Student student : students) {
				System.out.println("\nHello Student " + student.GetID());
				System.out.print("\nWhat is your response to this question?: ");
				String response = myObj.nextLine();  // Read user input
				IVS.vote(student, response);
			}
			//print out results of the vote
			System.out.println(IVS.getResults());
		}

		if(qType == 2) {
			System.out.println("Please input the question you want to ask:");
			String userQuestion = myObj.nextLine();
			List<String> responses = newList("1");
			Question question = new Question(userQuestion, responses);
			IVoteService IVS = new SRVoteService(question);

			// Each student in the list we created submits a response		
			for (Student student : students) {
				System.out.println("\nHello Student " + student.GetID());
				System.out.print("\nWhat is your response to this question?: ");
				String response = myObj.nextLine();  // Read user input
				IVS.vote(student, response);
			}
			//print out results of the vote
			System.out.println(IVS.getResults());
		}

		//create question and list of responses
		List<String> responses = newList("A", "B","C","D");
		Question question = new Question("Who was the first man on the moon? \n A - Neil DeGrasse Tyson \n B - Neil Armstrong \n C - Donald Trump \n D - Yuri Gagarin", responses);
		
		
		
		//create IVS object
		IVoteService IVS = new MRVoteService(question);
		
	}
	//clean code function
	@SafeVarargs
	public static <T> List<T> newList(T... items) {
		List<T> list = new ArrayList<>();
		for(T item: items) {
			list.add(item);
		}
		
		return list;
	}
	
}
