package districtdigital.agency.votingPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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

import districtdigital.agency.MainActivity;
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

    public static MainFragment mInstance = null;


    private String currentCategory="Culture";

    //Question adapters
    private customViewPager mViewPager;
    private viewPagerAdapter mViewPagerAdapter;
    private ArrayList<viewPagerModel> mContents;
    public  View view;



    private int btnCounter = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        tapBarMenu = view.findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });


        mInstance = this;

        initiateVotingView("Culture");

        //View settin'
        bCulture = view.findViewById(R.id.goToCulture);
        bCulture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("Culture");
            }
        });


        bHighTech = view.findViewById(R.id.goToHighTech);
        bHighTech.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("High Tech");
            }
        });

        bPolitics = view.findViewById(R.id.goToPolitics);
        bPolitics.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("bPolitics");
            }
        });

        bEconomics = view.findViewById(R.id.goToEconomics);
        bEconomics.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("bEconomics");
            }
        });


        bSocial = view.findViewById(R.id.goToSocial);
        bSocial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("bSocial");
            }
        });


        bSport = view.findViewById(R.id.goToSport);
        bSport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("bSport");
            }
        });


        bArt = view.findViewById(R.id.goToArt);
        bArt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateVotingView("bArt");
            }
        });


















        return view;
    }





    private void makeToast( String s) {
        Toast.makeText(this.getActivity(), s, Toast.LENGTH_SHORT).show();
    }




    //initiate questions/answers view
    private void initiateVotingView(String category)
    {
        Log.d("current Category: ",currentCategory);


        if (currentCategory.contentEquals("category"))
        {
            makeToast("you are already there dumbass !");
        }
        else
        {

            currentCategory=category;


        //Main fragment voting area
        mViewPager =(customViewPager) view.findViewById(R.id.votingArea);
        mContents = new ArrayList<>();

        /*set Question content here, handlers are in the adapter*/


        String questionContents[]= {"question 1", "question 2","question 3", "question 4"};
        String poweredBy[]= {"powered By question 1", "powered By question 2","powered By question 3", "powered By question 4"};
        String answer1[]= {"answer1 ef", "answer1 zefgzeg","zegzegezg", "NULL"};
        String answer2[]= {"answer2 question 1", "answer2 question 2","answer2 question 3", "answer2 question 4"};
        String answer3[]= {"azfazfazf", "zegzegzeg","NULL", "NULL"};
        String answer4[]= {"azfazfazfazfazzaf", "ezgzeg","NULL", "NULL"};


        for (int i = 0; i< questionContents.length; i++)
        {

            viewPagerModel viewPagerModel  = new viewPagerModel();
            viewPagerModel.setQuestionContent(category+category+category+category+category+"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim");
            viewPagerModel.setPoweredBy(poweredBy[i]);
            viewPagerModel.setAnswer1(answer1[i]);
            viewPagerModel.setAnswer2(answer2[i]);
            viewPagerModel.setAnswer3(answer3[i]);
            viewPagerModel.setAnswer4(answer4[i]);

            mContents.add(viewPagerModel);

        }


        mViewPagerAdapter = new viewPagerAdapter(mContents,this.getContext(),mViewPager);
        mViewPager.setPagingEnabled(false);
        mViewPager.setAdapter(mViewPagerAdapter);

        }

    }







}