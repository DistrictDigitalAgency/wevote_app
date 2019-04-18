package districtdigital.agency.votingPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;
import com.dd.morphingbutton.impl.LinearProgressButton;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;

import districtdigital.agency.R;
import districtdigital.agency.utilities.ProgressGenerator;
import districtdigital.agency.utilities.customViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private TapBarMenu tapBarMenu;
    private ArrayList<String> mQuestions;
    private ArrayAdapter<String> questionsAdapter;
    private Button  bCulture,bHighTech,bPolitics,bEconomics,bSocial,bSport,bArt;



    //Question adapters
    private customViewPager mViewPager;
    private viewPagerAdapter mViewPagerAdapter;
    private ArrayList<viewPagerModel> mContents;




    private int btnCounter = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tapBarMenu = view.findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });


        //View settin'
        bCulture = view.findViewById(R.id.goToCulture);
        bHighTech = view.findViewById(R.id.goToHighTech);
        bPolitics = view.findViewById(R.id.goToPolitics);
        bEconomics = view.findViewById(R.id.goToEconomics);
        bSocial = view.findViewById(R.id.goToSocial);
        bSport = view.findViewById(R.id.goToSport);
        bArt = view.findViewById(R.id.goToArt);



        mViewPager =(customViewPager) view.findViewById(R.id.votingViewPager);
        mContents = new ArrayList<>();

        String questionContents[]= {"question 1", "question 2","question 3", "question 4"};
        String poweredBy[]= {"powered By question 1", "powered By question 2","powered By question 3", "powered By question 4"};
        String answer1[]= {"answer1 ef", "answer1 zefgzeg","answer1 zegzeg", "answer1 zzgz"};
        String answer2[]= {"answer2 question 1", "answer2 question 2","answer2 question 3", "answer2 question 4"};
        String answer3[]= {"answer3 By question 1", "answer3 By question 2","answer3 question 3", "answer3 By question 4"};
        String answer4[]= {"answer4 By question 1", "answer4 By question 2","answer4 By question 3", "answer4 By question 4"};


        for (int i = 0; i< questionContents.length; i++)
        {
            viewPagerModel viewPagerModel  = new viewPagerModel();
            viewPagerModel.setQuestionContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim");
            viewPagerModel.setPoweredBy(poweredBy[i]);
            viewPagerModel.setAnswer1(answer1[i]);
            viewPagerModel.setAnswer2(answer2[i]);
            viewPagerModel.setAnswer3(answer3[i]);
            viewPagerModel.setAnswer4(answer4[i]);

            mContents.add(viewPagerModel);
        }

        final LinearProgressButton btnAnswer1 = (LinearProgressButton ) view.findViewById(R.id.answer1);
        LinearProgressButton btnAnswer2 = (LinearProgressButton ) view.findViewById(R.id.answer2);
        LinearProgressButton btnAnswer3 = (LinearProgressButton ) view.findViewById(R.id.answer3);
        LinearProgressButton btnAnswer4 = (LinearProgressButton ) view.findViewById(R.id.answer4);

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answerButtonClicked(btnAnswer1);
                    }
                });









        mViewPagerAdapter = new viewPagerAdapter(mContents,this.getContext());
        mViewPager.setPagingEnabled(false);
        mViewPager.setAdapter(mViewPagerAdapter);







        return view;
    }


    private void answerButtonClicked(final LinearProgressButton btnMorph) {
        if (btnCounter == 0) {
            btnCounter++;
            morphToSquare(btnMorph, 500);
        } else if (btnCounter == 1) {
            btnCounter = 0;
            simulateProgress1(btnMorph);
        }
    }





    private void setViewByMenuButton(Button bLocal, String sLocal)
    {

    }



    private void makeToast(FragmentActivity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }





    private void simulateProgress1(@NonNull final LinearProgressButton button) {
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
                .text(getString(R.string.mb_button));
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