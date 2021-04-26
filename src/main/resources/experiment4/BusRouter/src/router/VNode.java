package router;
// ��������
public class VNode {
	public int id; // ����id
	public String info; // ������Ϣ
	public ArcNode firstArc; // ��һ���߽��
	
	public VNode() {		
		this(-1, null, null);
	}
	public VNode(int id, String info) {		
		this(id, info, null);
	}
	public VNode(int id, String info, ArcNode firstArc) {		
		this.id = id;
		this.info = info;
		this.firstArc = firstArc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ArcNode getFirstArc() {
		return firstArc;
	}
	public void setFirstArc(ArcNode firstArc) {
		this.firstArc = firstArc;
	}
}
