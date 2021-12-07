package com.company.reccolldao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Album {

    private int id;
    private String title;
    private int artistId;
    private LocalDate releaseDate;
    private int labelId;
    private BigDecimal listPrice;

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

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return getId() == album.getId() &&
                getArtistId() == album.getArtistId() &&
                getLabelId() == album.getLabelId() &&
                Objects.equals(getTitle(), album.getTitle()) &&
                Objects.equals(getReleaseDate(), album.getReleaseDate()) &&
                Objects.equals(getListPrice(), album.getListPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArtistId(), getReleaseDate(), getLabelId(), getListPrice());
    }
}
