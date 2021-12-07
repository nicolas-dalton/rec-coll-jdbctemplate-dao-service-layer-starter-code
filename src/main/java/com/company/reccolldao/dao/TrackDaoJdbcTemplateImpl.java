package com.company.reccolldao.dao;

import com.company.reccolldao.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrackDaoJdbcTemplateImpl implements TrackDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_TRACK_SQL =
            "insert into track (album_id, title, runtime) values (?, ?, ?)";

    private static final String SELECT_TRACK_SQL =
            "select * from track where track_id = ?";

    private static final String SELECT_ALL_TRACKS_SQL =
            "select * from track";

    private static final String  SELECT_TRACKS_BY_ALBUM_SQL =
            "select * from track where album_id = ?";

    private static final String UPDATE_TRACK_SQL =
            "update track set album_id = ?, title = ?, runtime = ? where track_id = ?";

    private static final String DELETE_TRACK_SQL =
            "delete from track where track_id =  ?";

    @Autowired
    public TrackDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Track addTrack(Track track) {

        jdbcTemplate.update(
                INSERT_TRACK_SQL,
                track.getAlbumId(),
                track.getTitle(),
                track.getRunTime());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        track.setId(id);

        return track;

    }

    @Override
    public Track getTrack(int id) {

        try {
            return jdbcTemplate.queryForObject(
                    SELECT_TRACK_SQL,
                    this::mapRowToTrack,
                    id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no entry with the given id, just return null
            return null;
        }
    }

    @Override
    public List<Track> getAllTracks() {

        return jdbcTemplate.query(
                SELECT_ALL_TRACKS_SQL,
                this::mapRowToTrack);
    }

    @Override
    public List<Track> getTracksByAlbum(int albumId) {

        return jdbcTemplate.query(
                SELECT_TRACKS_BY_ALBUM_SQL,
                this::mapRowToTrack,
                albumId);
    }

    @Override
    public void updateTrack(Track track) {

        jdbcTemplate.update(
                UPDATE_TRACK_SQL,
                track.getAlbumId(),
                track.getTitle(),
                track.getRunTime(),
                track.getId());
    }

    @Override
    public void deleteTrack(int id) {

        jdbcTemplate.update(DELETE_TRACK_SQL, id);

    }

    private Track mapRowToTrack(ResultSet rs, int rowNum) throws SQLException {
        Track track = new Track();
        track.setId(rs.getInt("track_id"));
        track.setAlbumId(rs.getInt("album_id"));
        track.setRunTime(rs.getInt("runtime"));
        track.setTitle(rs.getString("title"));

        return track;
    }
}
