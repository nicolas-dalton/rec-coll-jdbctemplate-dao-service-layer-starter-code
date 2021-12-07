package com.company.reccolldao.service;

import com.company.reccolldao.dao.*;
import com.company.reccolldao.model.Album;
import com.company.reccolldao.model.Artist;
import com.company.reccolldao.model.Label;
import com.company.reccolldao.model.Track;
import com.company.reccolldao.viewmodel.AlbumViewModel;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;

    AlbumDao albumDao;

    ArtistDao artistDao;

    LabelDao labelDao;

    TrackDao trackDao;

    @Before
    public void setUp() throws Exception {
        setUpAlbumDaoMock();
        setUpArtistDaoMock();
        setUpLabelDaoMock();
        setUpTrackDaoMock();

        service = new ServiceLayer(albumDao, artistDao, labelDao, trackDao);

    }

    @Test
    public void saveFindAlbum() {
        AlbumViewModel avm = new AlbumViewModel();

        avm.setListPrice(new BigDecimal("14.99"));
        avm.setReleaseDate(LocalDate.of(1999, 05, 15));
        avm.setTitle("Greatest Hits");

        Artist artist = new Artist();
        artist.setInstagram("@RockStar");
        artist.setName("The GOAT");
        artist.setTwitter("@TheRockStar");
        artist = service.saveArtist(artist);

        avm.setArtist(artist);

        Label label = new Label();
        label.setName("Blue Note");
        label.setWebsite("www.bluenote.com");
        label = service.saveLabel(label);

        avm.setLabel(label);

        Track track = new Track();
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");
        List<Track> tList = new ArrayList<>();
        tList.add(track);

        avm.setTracks(tList);

        avm = service.saveAlbum(avm);

        AlbumViewModel fromService = service.findAlbum(avm.getId());

        assertEquals(avm, fromService);

    }

    @Test
    public void saveFindAllAlbums() {
        List<AlbumViewModel> fromService = service.findAllAlbums();

        assertEquals(1, fromService.size());
    }
    @Test
    public void saveFindFindAllArtist() {
        Artist artist = new Artist();
        artist.setInstagram("@RockStar");
        artist.setName("The GOAT");
        artist.setTwitter("@TheRockStar");

        artist = service.saveArtist(artist);
        Artist fromService = service.findArtist(artist.getId());
        assertEquals(artist, fromService);

        List<Artist> aList = service.findAllArtists();
        assertEquals(1, aList.size());
        assertEquals(artist, aList.get(0));

    }










    // helper methods
    private void setUpAlbumDaoMock() {
        albumDao = mock(AlbumDaoJdbcTemplateImpl.class);
        // simualted album comes in
        Album album = new Album();
        album.setId(1);
        album.setArtistId(45);
        album.setLabelId(10);
        album.setTitle("Greatest Hits");
        album.setListPrice(new BigDecimal("14.90"));
        album.setReleaseDate(LocalDate.of(1999, 05, 15));

        Album album2 = new Album();
        album.setArtistId(45);
        album.setLabelId(10);
        album.setTitle("Greatest Hits");
        album.setListPrice(new BigDecimal("14.90"));
        album.setReleaseDate(LocalDate.of(1999, 05, 15));

        List<Album> aList = new ArrayList<>();
        aList.add(album);

        doReturn(album).when(albumDao).addAlbum(album2);

        doReturn(album).when(albumDao).getAlbum(1);

        doReturn(aList).when(albumDao).getAllAlbums();


    }
    private void setUpLabelDaoMock() {
        labelDao = mock(LabelDaoJdbcTemplateImpl.class);
        Label label = new Label();
        label.setId(10);
        label.setName("Blue Note");
        label.setWebsite("www.bluenote.com");

        Label label2 = new Label();
        label2.setName("Blue Note");
        label2.setWebsite("www.bluenote.com");

        List<Label> lList = new ArrayList<>();
        lList.add(label);

        doReturn(label).when(labelDao).addLabel(label2);
        doReturn(label).when(labelDao).getLabel(10);
        doReturn(lList).when(labelDao).getAllLabels();
    }

    private void setUpTrackDaoMock() {
        trackDao = mock(TrackDaoJdbcTemplateImpl.class);
        Track track = new Track();
        track.setId(1);
        track.setAlbumId(1);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        Track track2 = new Track();
        track.setAlbumId(1);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        List<Track> tList = new ArrayList<>();
        tList.add(track);

        doReturn(track).when(trackDao).addTrack(track2);
        doReturn(track).when(trackDao).getTrack(1);
        doReturn(tList).when(trackDao).getAllTracks();
        doReturn(tList).when(trackDao).getTracksByAlbum(1);
    }

    private void setUpArtistDaoMock() {
        artistDao = mock(ArtistDaoJdbcTemplateImpl.class);
        Artist artist = new Artist();
        artist.setId(45);
        artist.setInstagram("@RockStar");
        artist.setName("The GOAT");
        artist.setTwitter("@TheRockStar");

        Artist artist2 = new Artist();
        artist2.setInstagram("@RockStar");
        artist2.setName("The GOAT");
        artist2.setTwitter("@TheRockStar");

        List<Artist> aList = new ArrayList();
        aList.add(artist);

        doReturn(artist).when(artistDao).addArtist(artist2);
        doReturn(artist).when(artistDao).getArtist(45);
        doReturn(aList).when(artistDao).getAllArtists();
    }

}