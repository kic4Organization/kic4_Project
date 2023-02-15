package hewon;

//1. 웹상에서 데이터를 입력 or 검색해서 출력할 목적(DTO->VO라고 불리고 있다.)
//DAO => 테이블을 만들때마다 무조건 작성 X -> 상황에 따라 작성하면 된다.
//ZipcodeDAO->우편번호 검색하는 메서드
public class ZipcodeDTO {
					//    우편번호  | 시,도  | 구,소도시| 동,면리| 나머지 주소 
	private String zipcode, area1, area2, area3, area4;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getArea3() {
		return area3;
	}

	public void setArea3(String area3) {
		this.area3 = area3;
	}

	public String getArea4() {
		return area4;
	}

	public void setArea4(String area4) {
		this.area4 = area4;
	}

}
