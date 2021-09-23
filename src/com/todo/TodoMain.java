package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todoList.txt");
		
		Menu.displaymenu();
		do {
			Menu.promt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				
				TodoUtil.listAll(l);
				break;

			case "ls_name_a":
				System.out.println(">>> �̸� ������������ ������ ����");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_d":
				System.out.println(">>> �̸� ������������ ����");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println(">>> ��¥ ������ ����");
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			default:
				System.out.println("[Error] ��Ȯ�� ��ɾ �Է��ϼ���. (��ɾ ������ help�� �Է��ϼ���.)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todoList.txt");
	}
}
