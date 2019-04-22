package districtdigital.agency.votingPackage;

import android.widget.Button;

public class viewPagerModel {
    public String questionContent;
    public String poweredBy;
    public String answer1,answer2,answer3,answer4;
    public int coinAllowed;


    public viewPagerModel(String questionContent, String poweredBy, String answer1, String answer2, String answer3, String answer4, int coinAllowed) {
        this.questionContent = questionContent;
        this.poweredBy = poweredBy;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.coinAllowed = coinAllowed;
    }

    public viewPagerModel() {
    }


    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getPoweredBy() {
        return poweredBy;
    }

    public void setPoweredBy(String poweredBy) {
        this.poweredBy = poweredBy;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getCoinAllowed() {
        return coinAllowed;
    }

    public void setCoinAllowed(int coinAllowed) {
        this.coinAllowed = coinAllowed;
    }
}
