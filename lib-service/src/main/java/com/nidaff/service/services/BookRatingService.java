package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IBookRatingDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IBookRatingService;
import com.nidaff.entity.entities.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.DecimalFormat;

@Service
@Transactional
public class BookRatingService implements IBookRatingService {

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBookRatingDao bookRatingDao;

    @Override
    public void addBookRating(UserDto dto, String rate, Long id) {
        BookRating bookRatingFromDao = bookRatingDao.findByUserIdAndBookId(dto.getId(), id);
        if (bookRatingFromDao != null) {
            bookRatingFromDao.setRating(Integer.parseInt(rate));
            bookRatingDao.save(bookRatingFromDao);
            String formatRating = new DecimalFormat("#0.0").format(bookDao.getAvgRating(id));
            bookDao.findBookById(id).setAvgRating(formatRating);
        } else {
            BookRating bookRating = new BookRating();
            bookRating.setRating(Integer.parseInt(rate));
            bookRating.setBook(bookDao.getOne(id));
            bookRating.setUser(userDao.getOne(dto.getId()));
            bookRatingDao.save(bookRating);
            String formatRating = new DecimalFormat("#0.0").format(bookDao.getAvgRating(id));
            bookDao.findBookById(id).setAvgRating(formatRating);
        }
    }

}
