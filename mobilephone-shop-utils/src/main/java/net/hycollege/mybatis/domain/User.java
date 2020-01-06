package net.hycollege.mybatis.domain;

public class User extends BaseDomain {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.uid
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.username
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.recieve
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String recieve;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.phone
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.sex
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.birthday
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    private String birthday;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.uid
     *
     * @return the value of t_user.uid
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.uid
     *
     * @param uid the value for t_user.uid
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.username
     *
     * @return the value of t_user.username
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.username
     *
     * @param username the value for t_user.username
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.recieve
     *
     * @return the value of t_user.recieve
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getRecieve() {
        return recieve;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.recieve
     *
     * @param recieve the value for t_user.recieve
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setRecieve(String recieve) {
        this.recieve = recieve == null ? null : recieve.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.phone
     *
     * @return the value of t_user.phone
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.phone
     *
     * @param phone the value for t_user.phone
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.sex
     *
     * @return the value of t_user.sex
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.sex
     *
     * @param sex the value for t_user.sex
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.birthday
     *
     * @return the value of t_user.birthday
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.birthday
     *
     * @param birthday the value for t_user.birthday
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }
}