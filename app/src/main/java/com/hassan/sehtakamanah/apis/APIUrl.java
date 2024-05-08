package com.hassan.sehtakamanah.apis;

public interface APIUrl {

    String SIGN_IN = "http://10.0.2.2/sehtak/checkUserLogin.php/";

    String SIGN_UP = "http://10.0.2.2/sehtak/insertNewUser.php/";

    String UPDATE_INFO = "http://10.0.2.2/sehtak/updateUserInfo.php/";

    String DELETE_USER = "http://10.0.2.2/sehtak/deleteUserAccount.php/";

    String GET_QUES = "http://10.0.2.2/sehtak/getQuestions.php/";

    String SEND_RESULT = "http://10.0.2.2/sehtak/sendUserResult.php/";

}
