package com.energizeglobal.web.controller;

import com.energizeglobal.common.model.entity.Book;
import com.energizeglobal.service.service.BookService;
import com.energizeglobal.web.container.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class BookPagination {
	private static final int PAGE_SIZE = 10;
	private static final String BOOK_LIST_BASEURL = "/books/page/";
	private static final String MODEL_ATTRIBUTE_PAGER = "pager";

	private static final String MODEL_ATTRIBUTE_BOOKS = "books";

	@Autowired
	BookService bookService;

	public Pager currentPage(PagedListHolder<?> pagedListHolder) {
		int currentIndex = pagedListHolder.getPage() + 1;
		int beginIndex = Math.max(1, currentIndex - PAGE_SIZE);
		int endIndex = Math.min(beginIndex + 5, pagedListHolder.getPageCount());
		int totalPageCount = pagedListHolder.getPageCount();
		int totalItems = pagedListHolder.getNrOfElements();
		String baseUrl = BOOK_LIST_BASEURL;

		Pager pager = new Pager();
		pager.setBeginIndex(beginIndex);
		pager.setEndIndex(endIndex);
		pager.setCurrentIndex(currentIndex);
		pager.setTotalPageCount(totalPageCount);
		pager.setTotalItems(totalItems);
		pager.setBaseUrl(baseUrl);
		return pager;
	}


	public void pagination(int pageNumber, Model model) {
		PagedListHolder<Book> pagedListHolder = new PagedListHolder<>();
		List<Book> list = bookService.findAll();


		pagedListHolder.setPageSize(PAGE_SIZE);


		pagedListHolder.setSource(list);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
			pagedListHolder.setPage(goToPage);

		}

//        request.getSession().setAttribute(SESSION_ATTRIBUTE_BOOK_LIST, pagedListHolder);

		model.addAttribute(MODEL_ATTRIBUTE_PAGER, currentPage(pagedListHolder));
		model.addAttribute(MODEL_ATTRIBUTE_BOOKS, pagedListHolder);
	}

}
