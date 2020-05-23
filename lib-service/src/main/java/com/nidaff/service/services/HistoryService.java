package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IHistoryDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.HistoryMapper;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.entity.entities.BookRating;
import com.nidaff.entity.entities.History;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class HistoryService implements IHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    IBookDao bookDao;

    @Autowired
    private IHistoryDao historyDao;

    @Override
    public List<HistoryDto> getAllHistories() {
        return HistoryMapper.convertListHistory(historyDao.findAll());
    }

    @Override
    public History addHistory(Long id, Long principalId) {
        History historyFromDao = historyDao.findHistoryByUserIdAndBookIdAndIsTaken(principalId, id, true);
        if (historyFromDao != null) {
            historyFromDao.setTaken(false);
            historyFromDao.getBook().setQuantity(historyFromDao.getBook().getQuantity()+1);
            return historyDao.save(historyFromDao);
        }
        History history = new History();
        history.setUserFirstName(userDao.getOne(principalId).getFirstName());
        history.setUserLastName(userDao.getOne(principalId).getLastName());
        history.setUserEmail(userDao.getOne(principalId).getEmail());
        history.setBookAuthor(bookDao.getOne(id).getBookDetails().getAuthor());
        history.setBookTitle(bookDao.getOne(id).getBookDetails().getTitle());
        LocalDateTime date = LocalDateTime.now();
        history.setDateFrom(date);
        history.setDateTo(date.plusDays(10));
        history.setTaken(true);
        //TODO if quantity > 0, else книги закончились 
        history.setUser(userDao.getOne(principalId));
        history.setBook(bookDao.getOne(id));
        history.getBook().setQuantity(history.getBook().getQuantity()-1);
        return historyDao.save(history);
    }
        
    @Override
    public HistoryDto getHistoryById(Long id, Long principalId) {
        return HistoryMapper.entityToHistoryDto(historyDao.getOne(id));
    }

    @Override
    public void deleteHistoryById(Long id) {
        historyDao.deleteById(id);
        logger.info("History deleted");
    }

	@Override
	public void updateHistory(Long id, Long principalId) {
	    History historyFromDao = historyDao.findHistoryByUserIdAndBookIdAndIsTaken(principalId, id, true);
        if (historyFromDao != null) {
            historyFromDao.setDateTo(historyFromDao.getDateTo().plusDays(10));
           historyDao.save(historyFromDao);
        }
        //TODO exception
//		History existingHistory = Optional.ofNullable(bookDao.get(id)).orElse(new History());
//		existingHistory.setTaken(bookDto.isTaken());
//		bookDao.update(existingHistory);
//		logger.info("History successfully updated");
//		
	}

    @Override
    public History returnBook(Long id, Long principalId) {
        History historyFromDao = historyDao.findHistoryByUserIdAndBookIdAndIsTaken(principalId, id, true);
        if (historyFromDao != null) {
            historyFromDao.setTaken(false);
            historyFromDao.getBook().setQuantity(historyFromDao.getBook().getQuantity()+1);
            return historyDao.save(historyFromDao);
        }
        //TODO exception
        return null;
    }
    
}
