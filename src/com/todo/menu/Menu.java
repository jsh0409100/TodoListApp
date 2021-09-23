package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("♡  Todo List Program  ♡\n투두리스트를 관리하는 프로그램입니다.\n프로그램을 실행하려면 화살표 다음의 명령어를 입력하세요.\n");
        System.out.println("＊ 이 사용법을 다시 보려면\t→ help");
        System.out.println("＊ 새 할일을 추가하려면\t→ add");
        System.out.println("＊ 목록에서 할일을 삭제하려면\t→ del");
        System.out.println("＊ 목록에서 할일을 수정하려면\t→ edit");
        System.out.println("＊ 목록을 보려면\t\t→ ls");
        System.out.println("＊ 할일 목록을 이름 오름차순으로 보려면\t→ ls_name_a");
        System.out.println("＊ 할일 목록을 이름 내림차순으로 보려면\t→ ls_name_d");
        System.out.println("＊ 할일 목록을 추가한 순서대로 보려면\t→ ls_date");
        System.out.println("＊ 프로그램을 종료하고 싶으면\t→ exit");
        
    }
    
    public static void promt() {
    	System.out.println("\n♥ Command");
    
    }
}
