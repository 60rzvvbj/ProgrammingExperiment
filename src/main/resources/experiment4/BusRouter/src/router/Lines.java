package router;

import java.util.ArrayList;
import java.util.Scanner;
// ������·�����ɶ�����·���
public class Lines {
	private static int MAXSIZE = 10; // ������·���ֵ
	public ALGraph g; 
	public Line[] routes; // ��Ź�����·������
	public int size; // ��ǰ������·����
	private Scanner scanner = new Scanner(System.in);
	
	public Lines() {
		this(null);
	}
	public Lines(ALGraph g) {		
		this.routes = new Line[MAXSIZE];
		this.size = 0;
		this.g = g;
	}
	// ������·
	public int findLineNo(String lineNo) {
		int index = -1;
		for(int i=0; i<size; i++) {
			if(lineNo.equals(routes[i].lineNo)) {
				index = i;
				break;
			}
		}
		return index;
	}
	// ��ʾ���й�����·��Ϣ
	public void showLines() {
		System.out.println("���й�����·��Ϣ��");
		for (int i = 0; i < size; i ++) {
			System.out.print("   "+(i+1)+"   " );
			routes[i].show();
		}
	}
	/**
	 * ���ܣ����һ������·
	 * ������
	 * 	 
	 * ����ֵ��
	 * Line -- ��·
	*/ 
	private Line createLine() {
		Line line = new Line(g);
		System.out.println("����������·��ţ�");
		while( true) {
			String lineNo = scanner.next();
			if( findLineNo(lineNo) < 0) {
				line.lineNo = lineNo;
				break;
			}
			System.out.println("��·����Ѵ��ڣ���������������·��ţ�");			
		}		
		int id;		
		// ѭ�����վ��
		while(true) {
			if( line.size > 0) {
				// ��ǰվ��Ŀɼ�����·�ĺ�ѡվ��
				ArrayList<VNode> cands = line.candidateStation(line.vexs[line.size-1].id);			
				System.out.println("��ѡվ���б�");
				for(int i=0; i<cands.size(); i++) {
					System.out.print(cands.get(i).id+"\t");
				}
				System.out.println();
			}
			// ������һ��վ�㣬Ҳ��������-1:��ֹ 
			System.out.println("������վ����(-1��ʾ��ֹ����·��������վ��)��"); 
			id = scanner.nextInt();
			if(id == -1) break; // ��ֹ����·��������վ��	
			boolean yn = line.addStation(line.size, id);			
			if ( !yn)
				System.out.println("���վ��ʧ�ܣ�");			
			else // �ɹ�����һ��վ�� 
				line.show();
		}
		return line;
	}
	/**
	 * ���ܣ���ӹ�����·����·վ������������5��
	 * ������
	 * 	 
	 * ����ֵ��
	 * false -- ʧ��
	 * true -- �ɹ� 
	 */ 
	public boolean addLine() {
		if(size == MAXSIZE) return false;		
		Line line = createLine();
		if(line.size < 5) {
			System.out.println("��·վ��������5����");
			return false;
		}
		routes[size++] = line;
		//line.show();
		return true;		
	}
	/**
	 * ���ܣ��޸Ĺ�����·
	 * ������
	 *  index -- ��·����	
	 *  oldId -- ��վ��Id
	 *  newId -- ��վ��Id
	 * ����ֵ��	
	 */ 
	public int modifyLine(int index, int oldId, int newId) {
		if(index<0 || index>=size) return -1;
		if(routes[index].inLine(oldId) < 0) return -2;
		if(g.findNode(newId) < 0 ) return -3;
		if( routes[index].modifyStation(oldId, newId)) 
			return 1;
		else
			return 0;
	}
	/**
	 * ���ܣ�ɾ��������·
	 * ������
	 *  index -- ��·λ������
	 * ����ֵ��
	 * false -- ʧ��
	 * true -- �ɹ� 
	 */ 
	public boolean removeLine(int index) {
		if(size == 0) return false;
		if(index < 0 || index >= size) return false;
		for (int j = index; j < size - 1; j ++)
			routes[j] = routes[j + 1];
		size --;

		return true;
	}
	/**
	 * ���ܣ���ѯ��·����ѯ����ʼվ��-->Ŀ��վ�����·
	 * ������
	 * 	 
	 * ����ֵ��
	 * false -- ʧ��
	 * true -- �ɹ� 
	 */ 
	public boolean queryLine(int begin, int end) {
		if (begin == end) return false; // ��ʼ��Ŀ��վ��ʱͬһվ��
		boolean yn = false;
		for(int i =0; i<size; i++) {
			Line line = routes[i];
			int beginIndex = line.inLine(begin);
			int endIndex = line.inLine(end);
			if(beginIndex >=0 && endIndex >=0) {
				// ��ʼվ������λ��>Ŀ��վ������λ�ã����н���
				if(beginIndex > endIndex) {
					int temp = beginIndex;
					beginIndex = endIndex;
					endIndex = temp;
				}
				// �������ʼվ��-->Ŀ��վ�����·
				System.out.println("������·Ϊ��");
				for(int j=beginIndex; j<=endIndex; j++) {
					System.out.print(line.vexs[j].id);
					if(j != endIndex)
						System.out.print("-");
				}
				System.out.println();
				yn = true;
			}
		}		
		return yn;
	}
}
