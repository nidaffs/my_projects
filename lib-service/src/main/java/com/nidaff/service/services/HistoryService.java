package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dao.IHistoryDao;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.mappers.BookMapper;
import com.nidaff.api.mappers.HistoryMapper;
import com.nidaff.api.mappers.UserMapper;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.History;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoryService implements IHistoryService {

    @Autowired
    private IDepartmentDao departmentDao;

    @Autowired
    IBookService bookService;

    @Autowired
    IUserService userService;

    @Autowired
    IBookDao bookDao;

    @Autowired
    private IHistoryDao historyDao;

    @Override
    public List<HistoryDto> getAllHistories() {
        return HistoryMapper.convertListHistory(historyDao.findAll());
    }

    @Override
    public History addHistory(Long id, Long principalId, DepartmentDto dto) {
        History history = new History();
        history.setUserFirstName(userService.getUserById(principalId).getFirstName());
        history.setUserLastName(userService.getUserById(principalId).getLastName());
        history.setUserEmail(userService.getUserById(principalId).getEmail());
        history.setBookAuthor(bookService.getBookById(id).getBookDetails().getAuthor());
        history.setBookTitle(bookService.getBookById(id).getBookDetails().getTitle());
        LocalDateTime date = LocalDateTime.now();
        history.setDateFrom(date);
        history.setDateTo(date.plusDays(10));
        history.setTaken(true);
        history.setDepartment(dto.getDepartmentName());
        history.setUser(UserMapper.dtoUserToMinEntity(userService.getUserById(principalId)));
        history.setBook(BookMapper.dtoBookToEntity(bookService.getBookById(id)));
        bookDao.findBookById(id).getDepartments().remove(departmentDao.findByDepartmentName(dto.getDepartmentName()));
        bookDao.findBookById(id).setQuantity(bookDao.findBookById(id).getQuantity() - 1);
        return historyDao.save(history);
    }

    @Override
    public List<HistoryDto> getHistoryByUserId(Long id) {
        return HistoryMapper.convertListHistory(historyDao.findHistoryByUserId(id));
    }

    @Override
    public void updateHistory(Long id, Long principalId) {
        History historyFromDao = getHistoryFromDao(id, principalId);
        historyFromDao.setDateTo(historyFromDao.getDateTo().plusDays(10));
        historyDao.save(historyFromDao);
    }

    @Override
    public History returnBook(Long id, Long principalId) {
        History historyFromDao = getHistoryFromDao(id, principalId);
        historyFromDao.setTaken(false);
        historyFromDao.getBook().setQuantity(historyFromDao.getBook().getQuantity() + 1);
        historyFromDao.getBook().getDepartments()
                .add(departmentDao.findByDepartmentName(historyFromDao.getDepartment()));
        return historyDao.save(historyFromDao);
    }

    private History getHistoryFromDao(Long id, Long principalId) {
        return Optional.ofNullable(historyDao.findHistoryByUserIdAndBookIdAndIsTaken(principalId, id, true))
                .orElseThrow(() -> new EntityNotFoundException("You did not take this book!"));
    }

}
