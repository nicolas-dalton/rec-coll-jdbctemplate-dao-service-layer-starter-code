package com.company.reccolldao.service;

import com.company.reccolldao.dao.AlbumDao;
import com.company.reccolldao.dao.ArtistDao;
import com.company.reccolldao.dao.LabelDao;
import com.company.reccolldao.dao.TrackDao;
import com.company.reccolldao.model.Album;
import com.company.reccolldao.model.Artist;
import com.company.reccolldao.model.Label;
import com.company.reccolldao.model.Track;
import com.company.reccolldao.viewmodel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {

    private AlbumDao albumDao;
    private ArtistDao artistDao;
    private LabelDao labelDao;
    private TrackDao trackDao;

    // abum api

    @Autowired
    public ServiceLayer(AlbumDao albumDao, ArtistDao artistDao, LabelDao labelDao, TrackDao trackDao) {
        this.albumDao = albumDao;
        this.artistDao = artistDao;
        this.labelDao = labelDao;
        this.trackDao = trackDao;
    }
        // viewmodel is the request body
    public AlbumViewModel saveAlbum(AlbumViewModel viewModel) {

        // persist the album
        Album a = new Album();
        a.setTitle(viewModel.getTitle());
        a.setReleaseDate(viewModel.getReleaseDate());
        a.setListPrice(viewModel.getListPrice());
        a.setLabelId(viewModel.getLabel().getId());
        a.setArtistId(viewModel.getArtist().getId());

        a = albumDao.addAlbum(a);
        viewModel.setId(a.getId());

        // add album id to tracks and persist tracks

        List<Track> tracks = viewModel.getTracks();

        tracks.stream()
                .forEach(t -> {
                    t.setAlbumId(viewModel.getId());
                    trackDao.addTrack(t);
                });

        tracks = trackDao.getTracksByAlbum(viewModel.getId());
        viewModel.setTracks(tracks);

        return  viewModel;
    }

    public AlbumViewModel findAlbum(int id) {

        Album album = albumDao.getAlbum(id);

        return buildAlbumViewModel(album);

    }

    public List<AlbumViewModel> findAllAlbums() {

        List<Album> albumList = albumDao.getAllAlbums();

        List<AlbumViewModel> avmList = new ArrayList<>();

        for(Album album: albumList) {
            AlbumViewModel avm = buildAlbumViewModel(album);

            avmList.add(avm);
        }
        return avmList;
    }
    @Transactional
    public void updateAlbum(AlbumViewModel viewModel) {

        Album album = new Album();
        album.setId(viewModel.getId());
        album.setTitle(viewModel.getTitle());
        album.setReleaseDate(viewModel.getReleaseDate());
        album.setListPrice(viewModel.getListPrice());
        album.setLabelId(viewModel.getLabel().getId());
        album.setArtistId(viewModel.getArtist().getId());

        albumDao.updateAlbum(album);

        List<Track> trackList = trackDao.getTracksByAlbum(album.getId());
        trackList.stream()
                .forEach(track -> trackDao.deleteTrack(track.getId()));

        List<Track> tracks = viewModel.getTracks();
        tracks.stream()
                .forEach(t -> {
                    t.setAlbumId(viewModel.getId());
                    t = trackDao.addTrack(t);
                });
    }

    @Transactional
    public void removeAlbum(int id) {

        List<Track> trackList = trackDao.getTracksByAlbum(id);
        trackList.stream()
                .forEach(track -> trackDao.deleteTrack(track.getId()));

        albumDao.deleteAlbum(id);

    }
    // helper method
    private AlbumViewModel buildAlbumViewModel(Album album) {

        Artist artist = artistDao.getArtist(album.getArtistId());

        Label label = labelDao.getLabel(album.getLabelId());

        List<Track> trackList = trackDao.getTracksByAlbum(album.getId());

        AlbumViewModel avm = new AlbumViewModel();

        avm.setId(album.getId());
        avm.setTitle(album.getTitle());
        avm.setReleaseDate(album.getReleaseDate());
        avm.setListPrice(album.getListPrice());
        avm.setArtist(artist);
        avm.setLabel(label);
        avm.setTracks(trackList);

        return avm;
    }

    // Artist API
    //

    public Artist saveArtist(Artist artist) {

        return artistDao.addArtist(artist);
    }

    public Artist findArtist(int id) {

        return artistDao.getArtist(id);
    }

    public List<Artist> findAllArtists() {

        return artistDao.getAllArtists();
    }

    public void updateArtist(Artist artist) {

        artistDao.updateArtist(artist);
    }

    public void removeArtist(int id) {

        artistDao.deleteArtist(id);
    }

    // Label API
    //

    public Label saveLabel(Label label) {

        return labelDao.addLabel(label);
    }

    public Label findLabel(int id) {

        return labelDao.getLabel(id);
    }

    public List<Label> findAllLabels() {

        return labelDao.getAllLabels();
    }

    public void updateLabel(Label label) {

        labelDao.updateLabel(label);
    }

    public void removeLabel(int id) {

        labelDao.deleteLabel(id);
    }

    // track

    public Track saveTrack(Track track) {
        return trackDao.addTrack(track);
    }

    public Track findTrack (int id) {
        return trackDao.getTrack(id);
    }

    public List<Track> findAllTracks() {

        return trackDao.getAllTracks();
    }

    public void updateTrack(Track track) {

        trackDao.updateTrack(track);
    }

    public void removeTrack(int id) {

        trackDao.deleteTrack(id);
    }

}








