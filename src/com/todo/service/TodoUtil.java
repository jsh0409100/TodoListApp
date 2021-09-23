package com.todo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> 새로운 항목 추가\n"
				+ "> 새로운 할일의 이름을 입력하세요. ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("\n[Error] 추가되어 있는 할일에 중복되는 이름이 있습니다.\n다른 이름으로 새로운 할일을 추가하세요.\n");
			return;
		}
		
		sc.nextLine();
		System.out.println("\n> 할일에 대한 설명을 입력하세요. ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("\n♡ ! 추가 완료 ! ♡");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ ">>> 항목 삭제\n"
				+ "> 삭제할 할일의 이름을 정확하게 입력하세요.");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("\n♡ ! 삭제 완료 ! ♡");
				break;
			}
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> 항목 수정\n"
				+ "> 수정할 할일의 이름을 입력하세요. ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("\n[Error] 존재하지 않는 할일입니다. 추가되어 있는 할일의 이름을 입력하세요.");
			return;
		}
		
		

		System.out.println("\n> 해당 할일의 새로운 이름을 입력하세요. ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n[Error] 추가되어 있는 할일에 중복되는 이름이 있습니다.\n다른 이름으로 수정해 주세요.");
			return;
		}
		
		sc.nextLine();
		System.out.println("\n> 해당 할일의 새로운 설명을 입력하세요. ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("\n♡ ! 수정 완료 ! ♡");
			}
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("\n"
				+ "♡ ! 할일 목록 ! ♡");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		//filename에
		//리스트에 있는 아이템을 '이름 ##설명 ##날짜'가 되게 파일에 한줄씩 집어 넣는다.
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("♡ ! 목록 저장 완료 ! ♡");
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		//파일을 불러와서 한줄씩 읽는다
		//한줄씩 불러올 때마다 ##을 기준으로 떨어뜨려서 이름, 설명, 날짜를 리스트-아이템에 넣는다.(tokenizer)
		//아이템을 추가할 때마다 몇 개인지 카운트하여 "몇 개의 항목을 불러왔습니다" 뜰 수 있게 만든다.
		try {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String oneline;
		
		while ((oneline = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(oneline, "##");
			String title  = st.nextToken();
			String desc = st.nextToken();
			String date  = st.nextToken();
			
			TodoItem item = new TodoItem (title, desc, date);
			l.addItem(item);
		}
		br.close();
		System.out.println("♡ ! 할일 불러오기 완료 ! ♡");
	} catch (FileNotFoundException e) {
		//e.printStackTrace();
		System.out.println("저장되어 있는 할일 목록이 없습니다.\n");
	}catch (IOException e) {
		e.printStackTrace();
	}
}
}