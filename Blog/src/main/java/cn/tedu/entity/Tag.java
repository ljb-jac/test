package cn.tedu.entity;

public class Tag {
	private int oId;
	private String title;
	private int referenceCount;
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReferenceCount() {
		return referenceCount;
	}
	public void setReferenceCount(int referenceCount) {
		this.referenceCount = referenceCount;
	}
	public Tag(int oId, String title, int referenceCount) {
		super();
		this.oId = oId;
		this.title = title;
		this.referenceCount = referenceCount;
	}
	@Override
	public String toString() {
		return "Tag [oId=" + oId + ", title=" + title + ", referenceCount=" + referenceCount + "]";
	}
	
	
	

}
