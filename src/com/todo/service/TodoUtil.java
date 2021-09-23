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
				+ ">>> ���ο� �׸� �߰�\n"
				+ "> ���ο� ������ �̸��� �Է��ϼ���. ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("\n[Error] �߰��Ǿ� �ִ� ���Ͽ� �ߺ��Ǵ� �̸��� �ֽ��ϴ�.\n�ٸ� �̸����� ���ο� ������ �߰��ϼ���.\n");
			return;
		}
		
		sc.nextLine();
		System.out.println("\n> ���Ͽ� ���� ������ �Է��ϼ���. ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("\n�� ! �߰� �Ϸ� ! ��");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ �̸��� ��Ȯ�ϰ� �Է��ϼ���.");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("\n�� ! ���� �Ϸ� ! ��");
				break;
			}
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ ">>> �׸� ����\n"
				+ "> ������ ������ �̸��� �Է��ϼ���. ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("\n[Error] �������� �ʴ� �����Դϴ�. �߰��Ǿ� �ִ� ������ �̸��� �Է��ϼ���.");
			return;
		}
		
		

		System.out.println("\n> �ش� ������ ���ο� �̸��� �Է��ϼ���. ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n[Error] �߰��Ǿ� �ִ� ���Ͽ� �ߺ��Ǵ� �̸��� �ֽ��ϴ�.\n�ٸ� �̸����� ������ �ּ���.");
			return;
		}
		
		sc.nextLine();
		System.out.println("\n> �ش� ������ ���ο� ������ �Է��ϼ���. ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("\n�� ! ���� �Ϸ� ! ��");
			}
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("\n"
				+ "�� ! ���� ��� ! ��");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		//filename��
		//����Ʈ�� �ִ� �������� '�̸� ##���� ##��¥'�� �ǰ� ���Ͽ� ���پ� ���� �ִ´�.
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("�� ! ��� ���� �Ϸ� ! ��");
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		//������ �ҷ��ͼ� ���پ� �д´�
		//���پ� �ҷ��� ������ ##�� �������� ����߷��� �̸�, ����, ��¥�� ����Ʈ-�����ۿ� �ִ´�.(tokenizer)
		//�������� �߰��� ������ �� ������ ī��Ʈ�Ͽ� "�� ���� �׸��� �ҷ��Խ��ϴ�" �� �� �ְ� �����.
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
		System.out.println("�� ! ���� �ҷ����� �Ϸ� ! ��");
	} catch (FileNotFoundException e) {
		//e.printStackTrace();
		System.out.println("����Ǿ� �ִ� ���� ����� �����ϴ�.\n");
	}catch (IOException e) {
		e.printStackTrace();
	}
}
}