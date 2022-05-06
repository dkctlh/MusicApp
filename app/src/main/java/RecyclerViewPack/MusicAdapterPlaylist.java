package RecyclerViewPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;

import java.util.ArrayList;

import DB.DBHelper2;

public class MusicAdapterPlaylist extends RecyclerView.Adapter<MusicAdapterPlaylist.MyViewHolder> {
    DBHelper2 db;
    ArrayList<Music> mMusicList;
    LayoutInflater inflater;
    Context context;


    int count=0;

    public MusicAdapterPlaylist(Context context, ArrayList<Music> musics) {
        inflater = LayoutInflater.from(context);
        this.mMusicList = musics;
        this.context=context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_playlistcardobjects, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        db=new DBHelper2(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Music selectedMusic = mMusicList.get(position);
        holder.setData(selectedMusic, position);

    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView songName, songDesc;
        ImageView musicImage, addmusic;

        public MyViewHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.textView20);
            songDesc = (TextView) itemView.findViewById(R.id.textView21);
            musicImage = (ImageView) itemView.findViewById(R.id.imageView20);
            addmusic = (ImageView) itemView.findViewById(R.id.imageView21);
            addmusic.setOnClickListener(this);

        }

        public void setData(Music selectedMusic, int position) {

            this.songName.setText(selectedMusic.getSongName());
            this.songDesc.setText(selectedMusic.getSongDesc());
            this.musicImage.setImageResource(selectedMusic.getImageID());


        }



        @Override
        public void onClick(View v) {

            if(db.checkmusicpath(songName.getText().toString())==false ){
                        db.insertData("pl",songName.getText().toString());
                addmusic.setVisibility(View.INVISIBLE);

            }




            //playlist ismini DB ye yazmadan nasıl farklı playlist oluşturabilirim
            //countu db de kullanıp playlist isimlerini sayılardan oluşturabilirim

        }
        }


    }

