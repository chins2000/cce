package com.chenshiheng.permission.uitls;

import java.util.LinkedList;
import java.util.List;

import com.chenshiheng.permission.dto.Subject;

public class UsersManager {
	private static List<Subject> subjects = new LinkedList<Subject>();

	public static List<Subject> getSubjects() {
		return subjects;
	}
	
	public static void add(Subject subject){
		subjects.add(subject);
	}
	
	public static void remove(Subject subject){
		subjects.remove(subject);
	}
	
	public static boolean containsSubject(Subject subject){
		return subjects.contains(subject);
	}
	
	
}
