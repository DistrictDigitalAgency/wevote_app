package districtdigital.agency.votingPackage;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;
import com.dd.morphingbutton.impl.LinearProgressButton;

import java.util.List;

import districtdigital.agency.MainActivity;
import districtdigital.agency.R;
import districtdigital.agency.utilities.ProgressGenerator;
import districtdigital.agency.utilities.customViewPager;

public class viewPagerAdapter extends PagerAdapter {
    private List<viewPagerModel> contents;
    private Context context;
    private customViewPager mViewPager;

    private int nextFragment;
    private int btnCounter = 1;

    private ViewGroup transitionContainer;
    private  TextView votedSuccess;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    public viewPagerAdapter(List<viewPagerModel> contents, Context context, customViewPager mViewPager) {
        this.contents = contents;
        this.context = context;
        this.mViewPager=mViewPager;
    }


    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.voting_contents,container,false);
        container.addView(view);

        transitionContainer = (ViewGroup) view.findViewById(R.id.transitions_container);
        votedSuccess = (TextView) view.findViewById(R.id.text);



        TextView questionContent = (TextView) view.findViewById(R.id.questionContent);
        questionContent.setText(contents.get(position).getQuestionContent());

        TextView poweredBy  = (TextView) view.findViewById(R.id.poweredBy);
        poweredBy.setText(contents.get(position).getPoweredBy());


        final Button answer1  = (Button) view.findViewById(R.id.answer1);
        answer1.setText(contents.get(position).getAnswer1());

        Button answer2  = (Button) view.findViewById(R.id.answer2);
        answer2.setText(contents.get(position).getAnswer2());


        if (contents.get(position).getAnswer3().matches("NULL"))
        {
            Button answer3  = (Button) view.findViewById(R.id.answer3);
            answer3.setVisibility(view.INVISIBLE);
        }
        else
        {
            Button answer3  = (Button) view.findViewById(R.id.answer3);
            answer3.setText(contents.get(position).getAnswer3());
        }


        if (contents.get(position).getAnswer4().matches("NULL"))
        {
            Button answer4  = (Button) view.findViewById(R.id.answer4);
            answer4.setVisibility(view.INVISIBLE);
        }
        else
        {
            Button answer4  = (Button) view.findViewById(R.id.answer4);
            answer4.setText(contents.get(position).getAnswer4());
        }



        ViewPager viewPager = (ViewPager) MainFragment.mInstance.view.findViewById(R.id.votingArea);
         nextFragment = viewPager.getCurrentItem() + 1;



        answer1.setOnClickListener(new View.OnClickListener()
        {
            boolean visible;


            @Override
            public void onClick(View v)
            {
                //mViewPager.setCurrentItem(nextFragment,true);


                TransitionManager.beginDelayedTransition(transitionContainer);
                //visible = !visible;
                votedSuccess.setVisibility(View.VISIBLE);


            }
        });





        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }



    public void destroyAllItem(@NonNull ViewGroup container) {
        container.removeAllViews();
    }





    private void answerButtonClicked(final LinearProgressButton btnMorph) {
        if (btnCounter == 0) {
            btnCounter++;
            morphToSquare(btnMorph, 10000);
        } else if (btnCounter == 1) {
            btnCounter = 0;
            simulateProgress(btnMorph);
        }
    }


    private void simulateProgress(@NonNull final LinearProgressButton button) {
        int progressColor = R.color.mb_purple;
        int color = R.color.mb_gray;
        int progressCornerRadius = R.dimen.mb_corner_radius_4;
        int width = R.dimen.mb_width_200;
        int height = R.dimen.mb_height_8;
        int duration = R.integer.mb_animation;

        ProgressGenerator generator = new ProgressGenerator(new ProgressGenerator.OnCompleteListener() {
            @Override
            public void onComplete() {
                morphToSuccess(button);
                button.unblockTouch();
            }
        });
        button.blockTouch(); // prevent user from clicking while button is in progress
        button.morphToProgress(color, progressColor, progressCornerRadius, width, height, duration);
        generator.start(button);
    }


    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(R.dimen.mb_corner_radius_2)
                .width(R.dimen.mb_width_100)
                .height(R.dimen.mb_height_56)
                .color(R.color.mb_blue)
                .colorPressed(R.color.mb_blue_dark)
                .text("test");
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(R.integer.mb_animation)
                .cornerRadius(R.dimen.mb_height_56)
                .width(R.dimen.mb_height_56)
                .height(R.dimen.mb_height_56)
                .color(R.color.mb_green)
                .colorPressed(R.color.mb_green_dark)
                .icon(R.drawable.ic_add);
        btnMorph.morph(circle);
    }

    private void morphToFailure(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(R.dimen.mb_height_56)
                .width(R.dimen.mb_height_56)
                .height(R.dimen.mb_height_56)
                .color(R.color.mb_red)
                .colorPressed(R.color.mb_red_dark)
                .icon(R.drawable.ic_art);
        btnMorph.morph(circle);
    }


}


