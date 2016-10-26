package com.energizeglobal.web.controller;

import com.energizeglobal.common.model.entity.Book;
import com.energizeglobal.common.model.entity.TakenBook;
import com.energizeglobal.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static com.energizeglobal.common.Constants.getPath;


/**
 * Created by test on 7/26/2016.
 */
@Controller
@RequestMapping
public class BookController {
    private static final String CMD_NAME = "bookCmd";
    private static final String VIEW_NAME = "/admin/addBook";

    @Autowired
    BookService bookService;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "addbooks", method = RequestMethod.GET)
    public String addBook(@ModelAttribute(CMD_NAME) Book bookCmd, Model model, HttpSession session) {
        return VIEW_NAME;
    }


    @RequestMapping(value = "addbooks", method = RequestMethod.POST)
    public String addBookInit(@Valid @ModelAttribute(CMD_NAME) Book bookCmd,
                              BindingResult result, Model model,
                              @RequestParam(name = "file") MultipartFile file,
                              @RequestParam(name = "image") MultipartFile image
    ) {

        if (result.hasErrors()) {
            return VIEW_NAME;
        }
        if (file.isEmpty() || image.isEmpty()) {
            return VIEW_NAME;
        }
        if (!file.getContentType().equalsIgnoreCase("application/pdf")
                && !image.getContentType().equalsIgnoreCase("application/msword")
                && !image.getContentType().equalsIgnoreCase("image/vnd.djvu")
                ) {
            model.addAttribute("errorMessage", "fileMessage");
            return VIEW_NAME;
        }

        if (!image.getContentType().equalsIgnoreCase("image/jpg")
                && !image.getContentType().equalsIgnoreCase("image/jpeg")
                && !image.getContentType().equalsIgnoreCase("image/png") &&
                !image.getContentType().equals("image/gif")
                ) {
            model.addAttribute("errorMessage", "imageMessage");
            return VIEW_NAME;
        }


        bookService.save(bookCmd, file, image);
        model.addAttribute("bookCmd", bookCmd);
        model.addAttribute("message", "Your book successfully added");
        return "redirect:/admin";
    }

    @ModelAttribute("categories")
    public List<String> getCategories() {
        List<String> list = new LinkedList<>();
        list.add("JAVA");
        list.add("C#");
        list.add("C++");
        list.add("PHP");
        list.add("ENGLISH");
        return list;
    }

    @RequestMapping(value = "/books/download", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile(@Param(value = "name") String name) {
        String nestedPath = "\\web\\src\\main\\resources\\static\\";
        String path = nestedPath + name;
        return new FileSystemResource(new File(getPath() + path));
    }

    @RequestMapping(value = "/books/remove", method = RequestMethod.GET)
    @ResponseBody
    public String deleteFile(@Param(value = "id") Long id) {
        bookService.delete(id);

        return "/admin";
    }


    @RequestMapping(value = "/books/move", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String moveBook(@Param(value = "id") Long id) {
        bookService.moveToBasket(id);

        return "Book Added into your basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String showBasket(Model model) {
        List<TakenBook> books = bookService.findAllFromBasket();
        model.addAttribute(books);

        return "basket";
    }
}
