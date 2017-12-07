package br.com.searchmove.model;

/**
 * Created by marcelo on 07/12/17.
 */

public class FilmeMock {

        private String name;
        private int numOfSongs;
        private int thumbnail;

        public FilmeMock() {
        }

        public FilmeMock(String name, int numOfSongs, int thumbnail) {
            this.name = name;
            this.numOfSongs = numOfSongs;
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumOfSongs() {
            return numOfSongs;
        }

        public void setNumOfSongs(int numOfSongs) {
            this.numOfSongs = numOfSongs;
        }

        public int getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(int thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

