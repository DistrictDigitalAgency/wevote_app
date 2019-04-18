package districtdigital.agency.votingPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import districtdigital.agency.R;

public class viewPagerAdapter extends PagerAdapter {
    private List<viewPagerModel> contents;
    private Context context;

    public viewPagerAdapter(List<viewPagerModel> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }


    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.voting_contents,container,false);
        container.addView(view);


        TextView questionContent = (TextView) view.findViewById(R.id.questionContent);
        questionContent.setText(contents.get(position).getQuestionContent());

        TextView poweredBy  = (TextView) view.findViewById(R.id.poweredBy);
        poweredBy.setText(contents.get(position).getPoweredBy());


        TextView answer1  = (TextView) view.findViewById(R.id.answer1);
        answer1.setText(contents.get(position).getAnswer1());

        TextView answer2  = (TextView) view.findViewById(R.id.answer2);
        answer2.setText(contents.get(position).getAnswer2());

        TextView answer3  = (TextView) view.findViewById(R.id.answer3);
        answer3.setText(contents.get(position).getAnswer3());

        TextView answer4  = (TextView) view.findViewById(R.id.answer4);
        answer4.setText(contents.get(position).getAnswer4());





        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }



    public void destroyAllItem(@NonNull ViewGroup container) {
        container.removeAllViews();
    }


}


