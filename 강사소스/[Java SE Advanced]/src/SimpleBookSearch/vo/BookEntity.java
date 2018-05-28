package SimpleBookSearch.vo;

/* 데이터를 전달하기 위한 목적의 class를 작성하고 있어요
 * Entity,DTO(Data Transfer Object),VO(Value Object),DO(Domain Object)
 * 중요한 데이터는 필드라서..private으로 설정하는게 원칙.
 * default 생성자는 기본으로 명시적으로 작성
 * private으로 설정되어 있는 field를 access하기위한 특수한 method가 필요. => getter & setter ( 툴의 도움을 받자 )
 * 
 * 
 */
public class BookEntity {

	private String bisbn;
	private String btitle;
	private String bauthor;
	private int bprice;
	private String bdate;
	
	// 객체를 생성하기 위한 생성자.
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
