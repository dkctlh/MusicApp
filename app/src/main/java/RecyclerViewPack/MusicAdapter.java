 package RecyclerViewPack;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.PlayingSong;
import com.example.musicapp.R;

import java.io.File;
import java.util.ArrayList;

 public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

        ArrayList<Music> mMusicList;
        LayoutInflater inflater;
        Context context;

        public MusicAdapter(Context context, ArrayList<Music> musics) {
            inflater = LayoutInflater.from(context);
            this.mMusicList = musics;
            this.context=context;

        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.recyclerview_cardobjects, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
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
            ImageView musicImage, deletemusic,sharemusic;

            public MyViewHolder(View itemView) {
                super(itemView);
                songName = (TextView) itemView.findViewById(R.id.textView14);
                songDesc = (TextView) itemView.findViewById(R.id.textView16);
                musicImage = (ImageView) itemView.findViewById(R.id.imageView6);
                deletemusic = (ImageView) itemView.findViewById(R.id.view7);
                sharemusic = (ImageView) itemView.findViewById(R.id.imageView24);
                deletemusic.setOnClickListener(this);
                musicImage.setOnClickListener(v -> {
                    Intent plays_i = new Intent(v.getContext(), PlayingSong.class);
                    plays_i.putExtra("SongName", songName.getText());

                    v.getContext().startActivity(plays_i);
                });
                sharemusic.setOnClickListener(v -> {
                    String pathName= "/sdcard/Download/" +songName.getText().toString();
                    File f= new File(pathName);

                    Uri myUri = Uri.parse(pathName);

                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    share.setType("audio/mp3");
                    share.putExtra(Intent.EXTRA_STREAM, myUri);

                    v.getContext().startActivity(Intent.createChooser(share, "Send song"));
                });

            }

            public void setData(Music selectedMusic, int position) {

                this.songName.setText(selectedMusic.getSongName());
                this.songDesc.setText(selectedMusic.getSongDesc());
                this.musicImage.setImageResource(selectedMusic.getImageID());


            }



            @Override
            public void onClick(View v) {
                {
                    String pathName= "/sdcard/Download/" +songName.getText().toString();

                    File f= new File(pathName);

                    Log.i("Delete", "onClick:" + pathName);
                    boolean exist=false;
                    if(f.exists())  exist=true;
                    Log.i("Delete","Exist=" + exist);
                    boolean del=f.delete();
                    Log.i("Delete","Result=" + del);



                    if (v == deletemusic) {
                        deletemusic(getLayoutPosition());
                    }
                }
            }
                private void deletemusic(int position) {
                    mMusicList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mMusicList.size());
                }

        }

    }

