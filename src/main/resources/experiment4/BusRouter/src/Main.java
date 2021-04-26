

import java.util.Scanner;

import router.ALGraph;
import router.ArcNode;
import router.Line;
import router.Lines;
import router.VNode;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void showMenu() {
		System.out.println("\n===========�����ڽӱ��ʾ��ͼ===========");
		System.out.println("\t1. ��ӽ��");
		System.out.println("\t2. ��ӱ�");
		System.out.println("\t3. ɾ�����");
		System.out.println("\t4. ɾ����");
		System.out.println("\t5. ��ʾ�ڽӱ�");
		System.out.println("     ----------------------");
		System.out.println("\t6. �����·");
		System.out.println("\t7. �޸���·");
		System.out.println("\t8. ɾ����·");
		System.out.println("\t9. ���������·");
		System.out.println("\t10. ��·��ѯ");
		System.out.println("\t11. �˳�");
		
		System.out.println("\n\t��ѡ���������(1~11)��");	
		
	}
	
	public static void main(String[] args) {		
		ALGraph graph = new ALGraph();
		Lines lines = new Lines(graph);
		Action action = new Action(graph,lines);
		while(true) {
			showMenu();
			int menu = scanner.nextInt();
			switch(menu) {
				case 1:action.addNode();break;
				case 2:action.addEdge();break;
				case 3:action.removeNode();break;
				case 4:action.removeEdge();break;
				case 5:action.showGraph();break;
				case 6:action.addLine();break;
				case 7:action.modifyLine();break;
				case 8:action.removeLine();break;
				case 9:action.showLines();break;
				case 10:action.queryLine();break;
				case 11:System.exit(0);
				default:break;
			}
		}

	}

	