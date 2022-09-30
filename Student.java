import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Student {
	private String ID;
	
	public Student(String SID) {
		ID = SID;
	}
	
	public static List<Student> createList(int x) {
		List<Student> list = new ArrayList<>();
		Random random = new Random();
		for(int i = 1; i < x; i++) {
			String id = String.format("%04d", random.nextInt(10000));
			// adds a student with a random 4 digit ID to the list
			list.add(new Student(id));
		}
		return list;
	}
	
	public String GetID() {
		return ID;
	}
}

