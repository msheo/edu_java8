package simplebooksearch.vo;

public class BookEntity {

	/*
	 * 데이터를 전달하기 위한 목적의 class를 작성
	 * Entity, DTO(Data Transfer Object), VO(Value Object), DO(Domain Object) 
	 * 중요한 데이터 -> private -> getter/setter
	 * default 생성자는 기본으로 명시적으로 작성
	 * 
	 */

	private String bisbn;
	private String btitle;
	private String bauthor;
	private int bprice;
	private String bdate;
	
	public BookEntity() {
		
	}
	public String getBisbn() {
		return bisbn;
	}
	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public int getBprice() {
		return bprice;
	}
	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
}
