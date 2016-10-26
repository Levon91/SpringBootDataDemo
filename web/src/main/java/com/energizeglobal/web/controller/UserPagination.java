package com.energizeglobal.web.controller;

import com.energizeglobal.common.model.entity.User;
import com.energizeglobal.service.service.UserService;
import com.energizeglobal.web.container.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UserPagination {
    private static final int PAGE_SIZE = 10;
    private static final String USER_LIST_BASEURL = "/users/page/";
    private static final String MODEL_ATTRIBUTE_PAGER = "pager";

    private static final String MODEL_ATTRIBUTE_USERS = "users";


    @Autowired
    UserService userService;

    public Pager currentPage(PagedListHolder<?> pagedListHolder) {
        int currentIndex = pagedListHolder.getPage() + 1;
        int beginIndex = Math.max(1, currentIndex - PAGE_SIZE);
        int endIndex = Math.min(beginIndex + 5, pagedListHolder.getPageCount());
        int totalPageCount = pagedListHolder.getPageCount();
        int totalItems = pagedListHolder.getNrOfElements();
        String baseUrl = USER_LIST_BASEURL;

        Pager pager = new Pager();
        pager.setBeginIndex(beginIndex);
        pager.setEndIndex(endIndex);
        pager.setCurrentIndex(currentIndex);
        pager.setTotalPageCount(totalPageCount);
        pager.setTotalItems(totalItems);
        pager.setBaseUrl(baseUrl);
        return pager;
    }


    public void pagination(HttpServletRequest request, int pageNumber, Model uiModel) {
        PagedListHolder<User> pagedListHolder = new PagedListHolder<>();
        List<User> list = userService.findAll();


        pagedListHolder.setPageSize(PAGE_SIZE);


        pagedListHolder.setSource(list);
        final int goToPage = pageNumber - 1;
        if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
            pagedListHolder.setPage(goToPage);

        }

        uiModel.addAttribute(MODEL_ATTRIBUTE_PAGER, currentPage(pagedListHolder));
        uiModel.addAttribute(MODEL_ATTRIBUTE_USERS, pagedListHolder);
    }

}
