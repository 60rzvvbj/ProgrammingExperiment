package router;
// �����ӱ�������ͼ/����
public class ALGraph {
	private static int MAXSIZE = 20; // ��󶥵���
	public VNode[] vexs; // ����ͼ�ж���
	public int vexNum; // ��������
	public int edgeNum; // ������
	
	public ALGraph() {
		this.vexs  = new VNode[MAXSIZE];		
		this.vexNum = 0;	
		this.edgeNum = 0;
	}
	public ALGraph(VNode[] vexs, int vexNum, int arcNum) {	
		this.vexs = vexs;	
		this.vexNum = vexNum;
		this.edgeNum = arcNum;		
	}
	//���ض�������vexs������λ��
	public int findNode(int id) {
		int index = -1;
		for(int i=0; i<vexNum; i++) {
			if(id == vexs[i].id) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * ���ܣ��б��Ƿ�����Ч���ڶ���
	 *  ������
	 * g -- ͼ
	 * u,v -- ���ڶ���id
	 * ����ֵ��	
	 * false -- �������ڶ���
	 * true -- �����ڶ��� 	
	*/ 
	public boolean isAdjVertex( int u, int v) {		
		int index = findNode(u); // ����u�����vexs������λ��
		if( index<0 ) return false;	
		boolean yn = false;
		ArcNode p = vexs[index].firstArc;
		// �����߱�
		while ( p != null) {
			if(v == p.adjVex) {
				yn = true;
				break;
			}
			p = p.nextArc;
		}
		return yn;
	}
	/**
	 * ���ܣ���ͼ�����ӽڵ�
	 *  ������
	 * id -- ����id
	 * info -- ������Ϣ
	 * ����ֵ��	
	 * false -- ����ʧ��
	 * true -- ���ӳɹ� 	
	*/ 
	public boolean addNode(int id,String info) {
		if(findNode(id) >= 0) return false;
		VNode node = new VNode(id,info);
		vexs[vexNum++] = node;
		return true;
	}
	
	/**
	 * ���ܣ���ͼ�����ӣ����򣩱�
	 *  ������
	 * u,v -- ����id
	 * ����ֵ��	
	 * false -- ����ʧ��
	 * true -- ���ӳɹ� 	
	*/ 
	public boolean addEdge(int u,int v) {
		if(u == v) return false;
		int uIndex = findNode(u);
		int vIndex = findNode(v);
		if(uIndex<0 || vIndex<0) return false;
		// ��ӱ�ʱ���ڽӱ��еĶ�����Ӧ���������㶼��Ҫ������Ӧ�ı߽��
		ArcNode arc = new ArcNode(vIndex);
		arc.nextArc=vexs[uIndex].firstArc;
		vexs[uIndex].firstArc=arc;		
		arc = new ArcNode(uIndex);
		arc.nextArc=vexs[vIndex].firstArc;
		vexs[vIndex].firstArc=arc;
		
		edgeNum ++;
		return true;
	}
	/**
	 * ���ܣ�ɾ��ͼ�У����򣩱�
	 *  ������
	 * u,v -- ����id
	 * ����ֵ��	
	 * false -- ʧ��
	 * true -- �ɹ� 	
	*/ 
	public boolean removeEdge(int u,int v) {
		if(u == v) return false;
		int uIndex = findNode(u);
		int vIndex = findNode(v);
		if(uIndex<0 || vIndex<0) return false;
		
		ArcNode pre,p;
		// ����u�ı߱��е��ҵ��߽��v��ǰ����
		pre = vexs[uIndex].firstArc;
		p = pre;
		while(p != null) {
			if(p.adjVex == v) 
				break;
			pre = p;
			p = p.nextArc;
		}
		if(p == null) return false;
		// ɾ���߽��
		if(pre == vexs[uIndex].firstArc)
			vexs[uIndex].firstArc = p.nextArc; 
		else
			pre.nextArc = p.nextArc;
		// ����v�ı߱��е��ҵ��߽��u��ǰ����
		pre = vexs[vIndex].firstArc;
		p = pre;
		while(p != null) {
			if(p.adjVex == v) 
				break;
			pre = p;
			p = p.nextArc;
		}
		if(p == null) return false;	
		// ɾ���߽��
		if(pre == vexs[vIndex].firstArc)
			vexs[vIndex].firstArc = p.nextArc;
		else
			pre.nextArc = p.nextArc;
		edgeNum --;
		
		return true;
	}
	/**
	 * ���ܣ�ɾ��ͼ�ж���
	 *  ������
	 * u -- ����id
	 * ����ֵ��	
	 * false -- ʧ��
	 * true -- �ɹ� 	
	*/ 
	public boolean removeNode(int u) {
		int uIndex = findNode(u); 
		if(uIndex < 0) return false;
		// �������ɾ��������u
		for(int i=uIndex+1; i<vexNum; i++) {
			vexs[i-1] = vexs[i];
		}
		vexNum -- ;
		ArcNode pre,p;
		for(int i=0; i<vexNum; i++) {			
			pre = vexs[i].firstArc;
			p = pre;
			while(p != null) {				
				if(p.adjVex == uIndex) { // �ھӶ���Ϊ����u����ɾ��
					p = p.nextArc;
					if(pre == vexs[i].firstArc)
						vexs[i].firstArc = p;
					else
						pre.nextArc = p;
					edgeNum -- ;
				}
				else {
					if(p.adjVex > uIndex) // �ھӶ��������>����u����������Ҫ�޸�
						p.adjVex -- ;
					pre = p;
					p = p.nextArc;
				}				
			}			
		}		
		return true;
	}
	// ��ʾ�ڽӱ�
	public void showGraph() {
		
	}
	
	
	public VNode[] getVexs() {
		return vexs;
	}	
	public int getVexNum() {
		return vexNum;
	}
	public int getArcNum() {
		return edgeNum;
	}
}
