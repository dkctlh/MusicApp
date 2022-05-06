 package RecyclerViewPack;

import android.os.Environment;

import com.example.musicapp.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

 public class Music {


    private String songName;
    private String songDesc;
    private int imageID;

    public Music() {
    }

    public Music(int imageID, String songName, String songDesc) {
        this.imageID = imageID;
        this.songName = songName;
        this.songDesc = songDesc;
    }
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDesc() {
        return songDesc;
    }

    public void setSongDesc(String songDesc) {
        this.songDesc = songDesc;
    }

    public static ArrayList<Music> getData() {
        ArrayList<String> mp3file=new ArrayList<String>();
        ArrayList<Music> musicList=new ArrayList<>();
        File directory = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Download")));
        File[] mp3files=directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".mp3");
            }
        });
        for(File f:mp3files){
            mp3file.add(f.getName());


        }
        String[] songNames = mp3file.toArray(new String[0]);
        int musicImages = R.drawable.music_icon;
        Arrays.sort(songNames);



        for (int i = 0; i < songNames.length; i++) {
            Music temp = new Music();
            temp.setImageID(musicImages);
            temp.setSongName(songNames[i]);


            musicList.add(temp);

        }


        return musicList;


    }
}
