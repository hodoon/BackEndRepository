package com.example.spopiaproj.service;

import com.example.spopiaproj.dao.GameScheduleDao;
import com.example.spopiaproj.model.GameScheduleDto;

import java.util.List;

public class GameScheduleService {
    private final GameScheduleDao gameScheduleDao;

    public GameScheduleService(GameScheduleDao gameScheduleDao) {
        this.gameScheduleDao = gameScheduleDao;
    }

    public List<GameScheduleDto> getGameSchedulesByUserEmail(String userEmail) {
        return gameScheduleDao.getGameSchedulesByUserEmail(userEmail);
    }
}