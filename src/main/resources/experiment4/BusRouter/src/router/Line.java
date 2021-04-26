package router;

import java.util.ArrayList;
import java.util.Scanner;
// ��������·��
public class Line {	
	private static int MAXSIZE = 10; // ��·�����վ������
	public ALGraph g = null; 
	public VNode[] vexs; // ��·�е�վ��
	public String lineNo; // ��·��
	public int size; // ��·��վ������
	
	public Line(ALGraph g) {
		this.g = g;
		this.vexs = new VNode[MAXSIZE];		
		this.lineNo = null;
		this.size = 0;
	}
	/**
	 * ���ܣ�վ������·�е�λ��
	 *  ������
	 * staId -- վ��id
	 * ����ֵ��	
	 * int -- λ������
	*/ 
	public int inLine(int staId) {
		
	}
	
	/**
	 * ���ܣ��ɼ�����·�ĺ�ѡվ��
	 *  ������
	* staId -- վ��id
	 * ����ֵ��	
	 * ArrayList<VNode> -- ��ѡվ��
	*/ 
	public ArrayList<VNode> candidateStation(int staId) {
		int index = g.findNode(staId);
		if(index < 0) return null;
		ArrayList<VNode> cands = new ArrayList<VNode>();
		ArcNode p = g.vexs[index].firstArc;
		while(p != null) {
			if( inLine(p.adjVex) < 0) 
				cands.add(g.vexs[p.adjVex]);
			p = p.nextArc;
		}
		return cands;
	}
	/**
	 * ���ܣ����һ��վ�㣬ֻ������·ĩβ����
	 *  ������
	 *  site -- ����վ���λ��
	 *  staId -- վ��id 
	 * ����ֵ��
	 * 
	*/ 
	public boolean addStation(int site, int staId) {
		if(size == MAXSIZE) {
			//System.out.println("��·�Ѵﵽ���վ���������޷�������վ�㣡");
			return false;
		}
		if(site<0 || site>size) {
			//System.out.println("վ��λ�÷Ƿ����޷�����վ�㣡");
			return false;
		}
		if( g.findNode(staId) < 0) {
			//System.out.println("վ��Ƿ����޷����ӣ�");
			return false;
		}
		// ��·��û��վ��
		if(size == 0) {
			vexs[size++] = g.vexs[g.findNode(staId)];
			return true;
		}
		boolean yn = true;
		// �ж�����վ����site-1λ��վ���Ƿ�Ϊ�ھӹ�ϵ
		if(site > 0 ) 
			yn = yn && g.isAdjVertex(vexs[site-1].id, staId);	
		// �ж�����վ����siteλ��վ���Ƿ�Ϊ�ھӹ�ϵ
		if(site < size)
			yn = yn && g.isAdjVertex(staId, vexs[site].id);	
		if( !yn) {
			//System.out.println("վ����Ч���޷����ӣ�");
			return false;
		}
		else {
			// site�����վ�����
			for(int i=size; i<site; i--)
				vexs[i] = vexs[i-1];
			// ����վ��
			vexs[size++] = g.vexs[g.findNode(staId)];
			return true;
		}		
	}
	/**
	 * ���ܣ��޸���·��վ��
	 * ������
	 * oldId -- ��վ��Id
	 * newId -- ��վ��Id
	 * ����ֵ��	
	 * false -- ʧ��
	 * true -- �ɹ� 	
	*/ 
	public boolean modifyStation(int oldId, int newId) {
		int index = inLine(oldId);	
		if( index < 0) return false; // ��վ�㲻����·��
		if( inLine(newId) >= 0) return false; // ��վ��������·��
		boolean yn = true;
		// �¡���վ����Ҫ��һ�µ�ǰ������վ�� 
		if(index > 0)
			yn = yn && g.isAdjVertex(vexs[index-1].id, newId);
		if(index < size)
			yn = yn && g.isAdjVertex(newId, vexs[index+1].id);
		if(yn) { // վ���滻
			vexs[index] = g.vexs[g.findNode(newId)];
		}
		return yn;
	}
	
	// ��ʾ��·վ��
	public void show() {		
		System.out.print(lineNo+"��·��վ���б�");
		for (int i = 0; i < size; i ++) {
			if (i == 0)
				System.out.print(vexs[i].id);
			else
				System.out.print("->"+ vexs[i].id);
		}
		System.out.println();
	}
}
