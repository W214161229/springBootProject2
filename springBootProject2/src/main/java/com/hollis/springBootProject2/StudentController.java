package com.hollis.springBootProject2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController{
	@GetMapping("/search/{name}")
	public Student searchByName(@PathVariable String name) throws IOException
	{
		ArrayList<Student> students = readData();
		for(int x = 0; x < students.size(); x++)
		{
			if(students.get(x).getFirstName().equals(name))
				return students.get(x);
		}
		return new Student(0l, "Student not found.", "0", "null", "null");
	}
	@GetMapping("/search")
	public Student searchByGpaAndGender(@RequestParam String gpa, @RequestParam String gender) throws IOException
	{
		ArrayList<Student> students = readData();
	
		for(int x = 0; x < students.size(); x++)
		{
			if(students.get(x).getGPA() == Double.parseDouble(gpa) && students.get(x).getGender().equals(gender))
				return students.get(x);
		}
		return new Student(0l, "Student not found.", "0", "null", "null");
	}
	@GetMapping("/gpa")
	public double getAverageGPA() throws IOException
	{
		ArrayList<Student> students = readData();
		double total = 0.;
		for(int x = 0; x < students.size(); x++)
		{
			total += students.get(x).getGPA();
		}
		total = total / (double)students.size();
		return total;
	}
    public ArrayList<Student> readData() throws IOException {
        FileReader fileReader = new FileReader("C:/Users/Conns/Downloads/springBootProject2/src/main/resources/studentData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<Student> studentList = new ArrayList<Student>();

        bufferedReader.readLine(); // read the names of the data fields (redundant info, discard)
        String line = bufferedReader.readLine();
        while (line != null){
            String[] data = line.split(",");
            Student current = new Student(Long.parseLong(data[0]), data[1], data[2], data[3], data[4]);
            studentList.add(current);
            line = bufferedReader.readLine();
        }
        return studentList;
    }
}
class Student
{
    private long id;
    private String firstName;
    private double gpa;
    private String email;
    private String gender;
    public Student(long i, String n, String g, String e, String d)
    {
        id = i;
        firstName = n;
        gpa = Double.parseDouble(g);
        email = e;
        gender = d;
    }
    public long getID()
    {
        return id;
    }
    public double getGPA()
    {
        return gpa;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getEmail()
    {
        return email;
    }
    public String getGender()
    {
        return gender;
    }
    @Override 
    public String toString()
    {
        return firstName + ", ID#" + id + ". " + gpa + " GPA, " + gender + ", " + email + ".";
    }
}
