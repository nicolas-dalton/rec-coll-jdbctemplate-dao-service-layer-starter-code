package com.company.reccolldao.model;

import java.util.Objects;

public class Track {

    private int id;
    private int albumId;
    private String title;
    private int runTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return getId() == track.getId() &&
                getAlbumId() == track.getAlbumId() &&
                getRunTime() == track.getRunTime() &&
                Objects.equals(getTitle(), track.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAlbumId(), getTitle(), getRunTime());
    }
}
