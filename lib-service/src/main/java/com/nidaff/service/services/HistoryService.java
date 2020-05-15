package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IHistoryDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.HistoryMapper;
import com.nidaff.api.services.IHistoryService;
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
    public HistoryDto addHistory(UserDto userDto, Long id) {
        History history = new History();
        history.setUserFirstName(userDto.getFirstName());
        history.setUserLastName(userDto.getLastName());
        history.setUserEmail(userDto.getEmail());
        history.setBookAuthor(bookDao.getOne(id).getBookDetails().getAuthor());
        history.setBookTitle(bookDao.getOne(id).getBookDetails().getTitle());
        LocalDateTime date = LocalDateTime.now();
        history.setDateFrom(date);
        history.setDateTo(date.plusDays(10));
        history.setTaken(true);
        history.setUser(userDao.getOne(userDto.getId()));
        history.setBook(bookDao.getOne(id));
        return HistoryMapper.entityToHistoryDto(historyDao.save(history));
    }

    @Override
    public HistoryDto getHistoryById(Long id) {
        return HistoryMapper.entityToHistoryDto(historyDao.getOne(id));
    }

    @Override
    public void deleteHistoryById(Long id) {
        historyDao.deleteById(id);
        logger.info("History deleted");
    }

    @Override
    public void updateHistory(Long id, HistoryDto historyDto) {
        // TODO Auto-generated method stub

    }

//	@Override
//	public void updateHistory(Long id, HistoryDetailsDto bookDetailsDto) {
//		History existingHistory = Optional.ofNullable(bookDao.get(id)).orElse(new History());
//		existingHistory.setTaken(bookDto.isTaken());
//		bookDao.update(existingHistory);
//		logger.info("History successfully updated");
//		
//	}
    
}
