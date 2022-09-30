import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Multi-Response IVote Service
public class MRVoteService implements IVoteService {

	//the question to be voted on
	private Question question;
	//maps the people who voted to what their responses were
	private Map<Student, Set<String>> Votes;
	//maps each response to the number of times it was voted for
	private Map<String, Integer> numOfEachResponse;
	
	public MRVoteService(Question Q) {
		question = Q;
		Votes = new HashMap<>();
		numOfEachResponse = new HashMap<>();
		//set the number of each response to zero
		for(String response: question.getResponses()) {
			numOfEachResponse.put(response, 0);
		}
	}
	
	@Override
	public void vote(Student stu, String response) {
		// Check if response is valid
		if (question.hasResponse(response)) {
			// Check if student has voted
			if (!Votes.containsKey(stu)) {
				// If student hasn't voted create hash set to store their responses
				Votes.put(stu, new HashSet<>());
			}
			// Check if student has voted for this response
			if (!Votes.get(stu).contains(response)) {
				// Add student's response to the set
				Votes.get(stu).add(response);
				// Increment the number of times this response was given
				numOfEachResponse.put(response, numOfEachResponse.get(response).intValue() + 1);
			}
			// Do nothing of the student has already voted		
		}
		// Do nothing if response is invalid
	}

	// Function to display stats on responses
	@Override
	public String getResults() {
		StringBuilder results = new StringBuilder();
		results.append(question.getQuestion() + ":\n");
		for (String response : question.getResponses()) {
			results.append("\t" + response + ": " + numOfEachResponse.get(response).toString() + "\n");
		}
		
		return results.toString();
	}

}
