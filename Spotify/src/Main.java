public class Main {

    public static void main(String[] args) {

        Album album = new Album("Adele");
        Album.Song song = album.new Song("Soy genial", "Genial");
        System.out.println(album);
        System.out.println(song);

    }
}
