package prs.crazyk.cmm.model;


/**
*
* @Class Name : CommonPagignConditions.java
* @Description : 페이징 처리된 목록을 출력하기 위해 자주 사용되는 조건들의 일반화 클래스
* @Modification Information
* @
* @     수정일     	  수정자                 수정내용
* @  ----------   	--------    ---------------------------
* @  2015.12.16     김동한       최초 생성
*
* @author 김동한
* @since 2015.12.16
* @version 1.0
* @see
*
*/
public class CommonPagingConditions extends BaseConditions {
	/** 현재의 페이지 번호 */
	protected long currentPageNo;

	/** 조회할 페이지의 시작 행번호 */
	protected long startRowNo;

	/** 조회할 페이지의 끝 행번호 */
	protected long endRowNo;

	/** 하나의 페이지에 나타낼 행의 수 */
	protected long fetchCount;

	/** 조회 후 한번에 표현해줄 링크 가능한 페이지의 수 */
	protected long linksPerPage;

	/**
	 * 조회할 페이지 번호를 반환한다.
	 * @return
	 */
	public long getCurrentPageNo() {
		if(currentPageNo<=0) currentPageNo = 1l;
		return currentPageNo;
	}

	/**
	 * 조회할 페이지 번호를 설정한다.
	 * @param currentPageNo
	 */
	public void setCurrentPageNo(long currentPageNo) {
		this.currentPageNo = currentPageNo;
	}


	/**
	 * 조회할 페이지의 시작 행번호를 반환한다.
	 * @return
	 */
	public long getStartRowNo() {
		return startRowNo;
	}

	/**
	 * 조회할 페이지의 시작 행번호를 설정한다.
	 * @param startRowNo 조회할 페이지의 시작 행번호
	 */
	public void setStartRowNo(long startRowNo) {
		this.startRowNo = startRowNo;
	}

	/**
	 * 조회할 페이지의 끝 행번호를 반환한다.
	 * @return
	 */
	public long getEndRowNo() {
		return endRowNo;
	}

	/**
	 * 조회할 페이지의 끝 행번호를 설정한다.
	 * @param endRowNo
	 */
	public void setEndRowNo(long endRowNo) {
		this.endRowNo = endRowNo;
	}

	/**
	 * 하나의 페이지에 나타낼 행의 수를 반환한다.
	 * @return
	 */
	public long getFetchCount() {
//		if(fetchCount==0) return PagedListValuesGenerator.DEFAULT_FETCH_COUNT;
		return fetchCount;
	}

	/**
	 * 하나의 페이지에 나타낼 행의 수를 설정한다.
	 * @param fetchCount
	 */
	public void setFetchCount(long fetchCount) {
		this.fetchCount = fetchCount;
	}

	/**
	 * 조회 후 한번에 표현해줄 링크 가능한 페이지의 수를 반환한다.
	 * @return
	 */
	public long getLinksPerPage() {

		return linksPerPage;
	}

	/**
	 * 조회 후 한번에 표현해줄 링크 가능한 페이지의 수를 설정한다.
	 * @param linksPerPage 조회 후 한번에 표현해줄 링크 가능한 페이지의 수
	 */
	public void setLinksPerPage(long linksPerPage) {
		this.linksPerPage = linksPerPage;
	}

	private boolean pageByIndex;

	public boolean getPageByIndex() {
		return pageByIndex;
	}

	public void setPageByIndex(boolean pageByIndex) {
		this.pageByIndex = pageByIndex;
	}
}
