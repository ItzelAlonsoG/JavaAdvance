import java.util.ArrayList;

public class Album {

    public String albumName;

    public  class Song{
        private String lyrincs;
        private String songName;

        Song (String lyrincs ,String songName){
            this.lyrincs = lyrincs;
            this.songName = songName;

        }

        public String getLyrincs() {
            return lyrincs;
        }

        public void setLyrincs(String lyrincs) {
            this.lyrincs = lyrincs;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        @Override
        public String toString() {
            return  "lyrincs: " + lyrincs + "\n" +
                    "songName: " + songName;
        }
    }

    Album (String albumName){
        this.albumName = albumName;

    }


    public String toString() {
        return "AlbumName: " + albumName;
    }
}
