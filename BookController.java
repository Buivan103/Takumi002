package vn.com.vti.springexam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.vti.springexam.entity.Book;
import vn.com.vti.springexam.entity.BookCustomized;
import vn.com.vti.springexam.entity.BookExample;
import vn.com.vti.springexam.entity.Category;
import vn.com.vti.springexam.entity.CategoryExample;
import vn.com.vti.springexam.entity.Publisher;
import vn.com.vti.springexam.entity.PublisherExample;
import vn.com.vti.springexam.form.BookForm;
import vn.com.vti.springexam.mapper.BookCustomMapper;
import vn.com.vti.springexam.mapper.BookMapper;
import vn.com.vti.springexam.mapper.CategoryMapper;
import vn.com.vti.springexam.mapper.PublisherMapper;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private PublisherMapper publisherMapper;
	//入力画面 book/search jsp:book/searchInput
	@RequestMapping("search")
	public String search() {
		return "book/searchInput";
	}
	//結果画面 book/searchResult
	@RequestMapping("searchResult")
	public String searchResult(@RequestParam("bookId") String bookId ,Model model) {
		Book book= bookMapper.selectByPrimaryKey(bookId);
		model.addAttribute("book",book);
		return "book/searchResult";
	}
	@RequestMapping("list")
	public String listBook(Model model) {
		BookExample bookExample = new BookExample();
		bookExample.createCriteria().andPriceBetween(1000, 2000);
		List<Book> books=bookMapper.selectByExample(bookExample);
		
		model.addAttribute("books",books);
		return "book/list";
	}
	@RequestMapping("booklist")
	public String booklistBook(Model model) {
		BookExample bookExample = new BookExample();
		bookExample.createCriteria();
		List<Book> books=bookMapper.selectByExample(bookExample);
		model.addAttribute("books",books);
		return "book/booklist";
	}
	
	//UPDATE BOOK//update?id=...(selected id)
	@RequestMapping("update")
	public String update(@RequestParam String bookId, BookForm bookForm, Model model) {
		Book book=bookMapper.selectByPrimaryKey(bookId);//SQL: select * where id=//input value
		bookForm.setBookId(book.getBookId());
		bookForm.setBookName(book.getBookName());
		bookForm.setPrice(book.getPrice());
		bookForm.setDiscount(book.getDiscount());
		
		//Category
		CategoryExample categoryExample = new CategoryExample();
		categoryExample.setOrderByClause("categoryId");
		List<Category> categoryList=
		categoryMapper.selectByExample(categoryExample);
		model.addAttribute("categoryList",categoryList);
		
		//Publisher
		PublisherExample publisherExample = new PublisherExample();
		publisherExample.setOrderByClause("publisherId");
		List<Publisher> publisherList=
		publisherMapper.selectByExample(publisherExample);
		model.addAttribute("publisherList",publisherList);
		
		//BOOK
		bookForm.setPageCount(book.getPageCount());
		bookForm.setIsbn13(book.getIsbn13());
		bookForm.setOnSaleDate(book.getOnSaleDate());
		
		return "book/update";
}
	@RequestMapping("updateConfirm")
	public String updateConfirm(BookForm bookForm,Model model) {
		List<Category> categoryList=new ArrayList<Category>();
		for(String categoryId:bookForm.getCategoryList()) {
			Category category=categoryMapper.selectByPrimaryKey(categoryId);
			categoryList.add(category);
		}
		model.addAttribute("categoryList",categoryList);
		
		List<Publisher> publisherList=new ArrayList<Publisher>();
		for(String publisherId:bookForm.getPublisherList()) {
			Publisher publisher=publisherMapper.selectByPrimaryKey(publisherId);
			publisherList.add(publisher);
		}
		model.addAttribute("publisherList",publisherList);
		
		return "book/updateConfirm";
	}
	
	@RequestMapping("updateExecute")
	public String updateExecute(BookForm bookForm, Model model) {
		Book book=new Book();
		book.setBookId(bookForm.getBookId());
		book.setBookName(bookForm.getBookName());
		book.setDiscount(bookForm.getDiscount());
		
		bookMapper.updateByPrimaryKey(book);
		
		return "redirect:./list";
	}
	@Autowired
	private BookCustomMapper bookCustomMapper;
	
	@RequestMapping("searchByNameInput")
	public String searchByNameInput() {
		return "book/searchByNameInput";
	}
	
	@RequestMapping("searchByNameResult")
	public String searchByNameResult(@RequestParam String bookName,Model model) {
		List<BookCustomized> bookList2=
bookCustomMapper.selectByNamePart("%"+bookName+"%");
		model.addAttribute("bookList2", bookList2);
		return "book/searchByNameResult";
	}
	
}


