package com.example.ballonpopup;

 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.MotionEvent;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;

 import androidx.annotation.NonNull;
 import androidx.core.content.ContextCompat;
 import androidx.databinding.DataBindingUtil;
 import androidx.recyclerview.widget.RecyclerView;

 import com.example.ballonpopup.databinding.SongRecBinding;
 import com.skydoves.balloon.Balloon;
 import com.skydoves.balloon.BalloonAnimation;
 import com.skydoves.balloon.OnBalloonClickListener;
 import com.skydoves.balloon.OnBalloonOutsideTouchListener;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.AdapterInner> {
    private SongRecBinding songRecBinding;
    private Context context;

    public SongsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterInner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        songRecBinding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.song_rec,parent,false);
         return new AdapterInner(songRecBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterInner holder, int position) {
        //Don't use the below commented approach (Which uses DataBinding to select the option image)
            //This may produce unexpected behaviours.

     /* songRecBinding.optionsImg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              showOptionPopup();
          }
      });*/

     holder.options.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             //Show the popup on option img click
             showOptionPopup(holder.options);
         }
     });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    private void showOptionPopup(ImageView options)
    {
        final Balloon balloon = new Balloon.Builder(context)
                .setLayout(R.layout.options_popup)
                .setPadding(7)
                .setCornerRadius(4f)
                .setBackgroundColor
                (ContextCompat.getColor(context,R.color.white))
                .setBalloonAnimation(BalloonAnimation.CIRCULAR)
                .setArrowVisible(false)
                .build();
        balloon.showAlignBottom(options);//songRecBinding.optionsImg

        //This method may rise the build time error
              //which is : cannot access Function 1
              //If you face so do the following steps
                  //Just go to Tools > Kotlin > Configure Kotlin in Project >
                  // Android with Gradle and choose your Java module with Single module radio button selected then
                  // select your version and OK.
        balloon.setOnBalloonClickListener(view->{
            //perform your task you want, on click on the balloon dialog
                balloon.dismiss();
        });

        balloon.setOnBalloonOutsideTouchListener(new OnBalloonOutsideTouchListener() {
            @Override
            public void onBalloonOutsideTouch(View view, MotionEvent motionEvent) {
                balloon.dismiss();
            }
        });
    }

   class AdapterInner extends RecyclerView.ViewHolder {
        private ImageView options;
       public AdapterInner(@NonNull View itemView) {
           super(itemView);
           options=itemView.findViewById(R.id.optionsImg);
       }
   }
}
