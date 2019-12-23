package cn.tedu.entity;

public class Link {
	private String title;
	private String address;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Link(String title, String address) {
		super();
		this.title = title;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Link [title=" + title + ", address=" + address + "]";
	}
	
	

}
