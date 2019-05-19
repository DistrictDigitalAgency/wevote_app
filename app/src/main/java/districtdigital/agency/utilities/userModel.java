package districtdigital.agency.utilities;

public class userModel {
    String   id,fullName,mailAddress,age,address,city,walletAmount,phoneNumber,sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(String walletAmount) {
        this.walletAmount = walletAmount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public userModel() {
    }

    public userModel(String id, String fullName, String mailAddress, String age, String address, String city, String walletAmount, String phoneNumber, String sex) {
        this.id = id;
        this.fullName = fullName;
        this.mailAddress = mailAddress;
        this.age = age;
        this.address = address;
        this.city = city;
        this.walletAmount = walletAmount;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
}
