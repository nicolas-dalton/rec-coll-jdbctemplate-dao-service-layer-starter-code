package com.company.reccolldao.dao;

import com.company.reccolldao.model.Track;

import java.util.List;

public interface TrackDao {

    Track addTrack(Track track);

    Track getTrack(int id);

    List<Track> getAllTracks();

    List<Track> getTracksByAlbum(int albumId);

    void updateTrack(Track track);

    void deleteTrack(int id);
}
