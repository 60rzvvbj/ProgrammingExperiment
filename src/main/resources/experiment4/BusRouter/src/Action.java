import java.util.Scanner;

import router.ALGraph;
import router.ArcNode;
import router.Lines;
import router.VNode;

public class Action {
	private ALGraph graph;
	private Lines lines;
	private Scanner scanner = new Scanner(System.in);

	public Action(ALGraph graph, Lines lines) {
		this.graph = graph;
		this.lines = lines;
	}

	// 1. ��ӽ��
	public void addNode() {
		// ��ӽ�� 
		System.out.println("���������ţ�");
		int id = scanner.nextInt();
		System.out.println("�������㸽����Ϣ��");
		String info = scanner.next();
		if (graph.addNode(id, info))
			System.out.println("��ӽ��ɹ���");
		else
			System.out.println("��ӽ��ʧ�ܣ�");
	}
	// 2. ��ӱ�
	public void addEdge() {
		// ��ӽ�� 
		System.out.println("�������1������ţ�");
		int u = scanner.nextInt();
		System.out.println("�������2������ţ�");
		int v = scanner.nextInt();
		if (graph.addEdge(u, v))
			System.out.println("��ӱ߳ɹ���");
		else
			System.out.println("��ӱ�ʧ�ܣ�");
	}
	// 3. ɾ�����
	public void removeNode() {
		System.out.println("������Ҫɾ������ţ�");
		int u = scanner.nextInt();
		if (graph.removeNode(u))
			System.out.println("ɾ���ڵ�ɹ���");
		else
			System.out.println("ɾ���ڵ�ʧ�ܣ�");		
	}
	// 4. ɾ����
	public void removeEdge() {
		System.out.println("������Ҫɾ���ߵĵ�1������ţ�");
		int u = scanner.nextInt();
		System.out.println("������Ҫɾ���ߵĵ�1������ţ�");
		int v = scanner.nextInt();
		if (graph.removeEdge(u, v))
			System.out.println("ɾ���߳ɹ���");
		else
			System.out.println("ɾ����ʧ�ܣ�");	
	}
	// 5. ��ʾ�ڽӱ�
	public void showGraph() {
		graph.showGraph();
	}
	// 6. �����·
	public void addLine() {
		
		if (lines.addLine())
			System.out.println("��·��ӳɹ���");
		else
			System.out.println("��·���ʧ�ܣ�");	
	}
	// 7. �޸���·
	public void modifyLine() {
		// lines.modifyLine();
		
		int index = -1;
		while (index < 0) {
			System.out.println("��������Ҫ�޸ĵ���·���(-1��ʾ��ֹ�޸Ĳ���)��");
			String lineNo = scanner.next();
			if(lineNo==null || lineNo.equals(""))
				continue;
			if ("-1".equals(lineNo)) {
				return;
			}
			index = lines.findLineNo(lineNo);
			if(index < 0)
				System.out.println("��·�����Ч��");
		}
		
		// ѭ��ʵ�ֶ��վ��ĸĶ�
		while(true) {
			// �Ȼ����Ҫ������վ��
			System.out.println("��������Ҫ������վ����(-1��ʾ��ֹ�޸�վ�����)��");
			int oldId = scanner.nextInt();			
			if(oldId == -1)	break;
			
			System.out.println("��������վ���ţ�");
			int newId = scanner.nextInt();
			int result = lines.modifyLine(index, oldId, newId);
			if(result == 1) 
				System.out.println("վ���޸ĳɹ�!");
			else if(result == 0) 
				System.out.println("վ���޸�ʧ��!");
			else if(result == -1)
				System.out.println("��·�����Ч������������!");
			else if(result == -2)
				System.out.println("վ������Ч������������!");
			else if(result == -3)
				System.out.println("��վ������Ч������������!");			
		}
		
	}
	// 8. ɾ����·
	public void removeLine() {
		System.out.println("��������·��ţ�");
		Scanner scanner = new Scanner(System.in);
		String lineNo = scanner.next();	
		int index = lines.findLineNo(lineNo);
		if( index < 0) {
			System.out.println("��Ч����·��ţ�");
			return;
		}
		System.out.println("ȷ��Ҫɾ����·"+lineNo+"��");
		System.out.println("\t1. ȷ��\n\t2. ����");
		int flag = scanner.nextInt();
		if (flag == 2) return ;
		
		if (lines.removeLine(index))
			System.out.println("��·ɾ���ɹ���");
		else
			System.out.println("��·ɾ��ʧ�ܣ�");		
	}
	// 9. ���������·
	public void showLines() {
		lines.showLines();
	}
	// 10. ��·��ѯ
	public void queryLine() {
		int begin, end;
		System.out.println("��������ʼվ���ţ�");
		begin = scanner.nextInt();
		System.out.println("������Ŀ��վ���ţ�");
		end = scanner.nextInt();
		if( !lines.queryLine(begin, end)) {
			System.out.println("û�д�["+begin+"]վ�㵽["+end+"]վ�����·");
		}
	}
}
