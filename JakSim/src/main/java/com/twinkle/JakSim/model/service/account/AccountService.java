package com.twinkle.JakSim.model.service.account;

import com.twinkle.JakSim.model.dao.account.UserDao;
import com.twinkle.JakSim.model.dto.account.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private StringBuilder key;

    @Transactional
    public int CreateMember(HashMap<Object, String> member){
        System.out.println(" " +  member);

        UserDto userDto = new UserDto();

        userDto.setId(member.get("id"));
        userDto.setPw(passwordEncoder.encode(member.get("pw")));
        userDto.setName(member.get("name"));
        userDto.setGender(Integer.parseInt(member.get("gender")));
        userDto.setTel(member.get("tel"));
        userDto.setQuestion(Integer.parseInt(member.get("question")));
        userDto.setAnswer(member.get("answer"));
        userDto.setBirth(member.get("birth"));
        userDto.setRole(Integer.parseInt(member.get("role")));

        //정상
        return userDao.insertMember(userDto);
    }

    public UserDto findByUsername(String id) {
        return userDao.findByUserId(id);
    }

    public UserDto findByTel(String tel) {
        return userDao.findByTel(tel);
    }

    public int update(String user_id, String pw) {
        return userDao.updatePassword(user_id, passwordEncoder.encode(pw));
    }

    public boolean checkPassword(String username, String pw) {
        return passwordEncoder.matches(pw, userDao.findByUserId(username).getPw());
    }

    public int delete(String id) {
        return userDao.deleteUser(id);
    }

    public int validateEmail(String email) {
        int result = 0;
        createKey();

        try{
            result = sendEmail(email);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void createKey() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        int [] numList = IntStream.concat(
                IntStream.rangeClosed(48, 57),
                IntStream.rangeClosed(65, 90)
        ).toArray();

        for(int i=0; i<6; i++){
            result.append(String.format("%c", numList[random.nextInt(numList.length)]));
        }
        this.key = result;
    }

    private MimeMessage createMailForm(String email) throws MessagingException, UnsupportedEncodingException{
        String sender = "lsd4026@naver.com";
        String title = "[작심득근] 이메일 인증번호입니다.";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);
        message.setFrom(sender);
        message.setText(String.format("이메일 인증번호: "+ this.key), "utf-8", "html");

        return message;
    }

    private int sendEmail(String toMail) throws MessagingException, UnsupportedEncodingException{
        MimeMessage emailForm = createMailForm(toMail);
        emailSender.send(emailForm);
        return 1;
    }
}
