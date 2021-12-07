package com.company.reccolldao.dao;

import com.company.reccolldao.model.Artist;

import java.util.List;

public interface ArtistDao {

    Artist addArtist(Artist artist);

    Artist getArtist(int id);

    List<Artist> getAllArtists();

    void updateArtist(Artist artist);

    void deleteArtist(int id);

}
