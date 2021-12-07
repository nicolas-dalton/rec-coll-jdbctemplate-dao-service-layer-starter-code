package com.company.reccolldao.viewmodel;

import com.company.reccolldao.model.Artist;
import com.company.reccolldao.model.Label;
import com.company.reccolldao.model.Track;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlbumViewModel {

    private int id;
    private String title;
    private Artist artist;
    private LocalDate releaseDate;
    private Label label;
    private BigDecimal listPrice;
    private List<Track> tracks = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumViewModel that = (AlbumViewModel) o;
        return getId() == that.getId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getArtist(), that.getArtist()) && Objects.equals(getReleaseDate(), that.getReleaseDate()) && Objects.equals(getLabel(), that.getLabel()) && Objects.equals(getListPrice(), that.getListPrice()) && Objects.equals(getTracks(), that.getTracks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArtist(), getReleaseDate(), getLabel(), getListPrice(), getTracks());
    }
}
