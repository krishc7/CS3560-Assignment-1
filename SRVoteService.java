import java.util.HashMap;
import java.util.Map;

//Single Response implementation of IVoteService
public class SRVoteService implements IVoteService {
	
	// Question to be voted on
	private Question question;
	// Map who has and hasn't voted yet
	private Map<Student, String> hasVoted;
	// Maps each response to the number of times it was chosen
	private Map<String, Integer> responses;
	
	public SRVoteService(Question Q) {
		question = Q;
		hasVoted = new HashMap<>();
		responses = new HashMap<>();
		// Initialize each response count to 0
		for(String response: question.getResponses()) {
			responses.put(response, 0);
		}
	}
	
	@Override
	public void vote(Student stu, String response) {
		// Check if response is valid
		if (question.hasResponse(response)) {
			// Check if student has voted
			if (hasVoted.containsKey(stu)) {
				// Decrement number of stored responses because it will be incremented again later
				String prevResponse = hasVoted.get(stu);
				responses.put(prevResponse, responses.get(prevResponse).intValue() - 1);
				
				// Change the students responses in the hash map
				hasVoted.put(stu, response);
			}
			else {
				// If they haven't voted, input their response in the hash map
				hasVoted.put(stu, response);
			}
			// Increment the number corresponding to that response
			responses.put(response, responses.get(response).intValue() + 1);
		}
		// Nothing is done if response is invalid
	}

	@Override
	public String getResults() {
		StringBuilder results = new StringBuilder();
		results.append(question.getQuestion() + ":\n");
		for (String response : question.getResponses()) {
			results.append("\t" + response + ": " + responses.get(response).toString() + "\n");
		}
		
		return results.toString();
	}
	
}
