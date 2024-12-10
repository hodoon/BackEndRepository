package com.example.spopiaproj.dao;

import com.example.spopiaproj.model.GameScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameScheduleDao {
    private final Connection connection;

    public GameScheduleDao(Connection connection) {
        this.connection = connection;
    }

    public List<GameScheduleDto> getGameSchedulesByUserEmail(String userEmail) {
        List<GameScheduleDto> gameSchedules = new ArrayList<>();
        String query = "SELECT * FROM game_schedules WHERE user_email = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GameScheduleDto gameSchedule = new GameScheduleDto();
                gameSchedule.setId(rs.getInt("id"));
                gameSchedule.setUserEmail(rs.getString("user_email"));
                gameSchedule.setGameName(rs.getString("game_name"));
                gameSchedule.setGameDate(rs.getDate("game_date"));
                gameSchedules.add(gameSchedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gameSchedules;
    }
}