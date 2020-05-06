package com.nidaff.service.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IBookRatingDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IBookRatingService;
import com.nidaff.entity.entities.BookRating;

@Service
@Transactional
public class BookRatingService implements IBookRatingService{

	@Autowired
	private IBookDao bookDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IBookRatingDao bookRatingDao;
	
	@Override
	public BookRating addBookRating(Long id, UserDto dto, Integer rating) {
		BookRating bookRating = new BookRating();
		bookRating.setRating(rating);
		bookRating.setBook(bookDao.getOne(id));
		bookRating.setUser(userDao.getOne(dto.getId()));
		return bookRatingDao.save(bookRating);
	}
}