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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private String key;

    @Transactional
    public int CreateMember(UserDto member){
        UserDto userDto = new UserDto();

        userDto.setId(member.getId());
        userDto.setPw(passwordEncoder.encode(member.getPw()));
        userDto.setName(member.getName());
        userDto.setGender(member.getGender());
        userDto.setTel(member.getTel());
        userDto.setQuestion(member.getQuestion());
        userDto.setAnswer(member.getAnswer());
        userDto.setBirth(member.getBirth());
        userDto.setEmail(member.getEmail());
        userDto.setRole(member.getRole());

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

    public String validateEmail(String email) {
        createKey();

        try{
            sendEmail(email);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this.key;
    }

    private void createKey() {
        //숫자 0-9, 문자 A-Z
        int [] numList = IntStream.concat(
                IntStream.rangeClosed(65, 90),
                IntStream.rangeClosed(48, 57)
        ).toArray();

        List<Integer> indexList = IntStream.range(0, numList.length).boxed().collect(Collectors.toList());
        Collections.shuffle(indexList);
        //특정 문자가 지나치게 중복되어 이를 해결하고자 전체 요소를 다시 섞음

        this.key = indexList.stream().limit(6).map(i -> String.format("%c", numList[i])).collect(Collectors.joining());
    }

    private MimeMessage createMailForm(String email) throws MessagingException, UnsupportedEncodingException{
        String sender = "lsd4026@naver.com";
        String title = "[작심득근] 회원가입 이메일 인증";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);
        message.setFrom(sender);
        message.setText(String.format("이메일 인증번호: "+ this.key), "utf-8", "html");

        return message;
    }

    private void sendEmail(String toMail) throws MessagingException, UnsupportedEncodingException{
        MimeMessage emailForm = createMailForm(toMail);
        emailSender.send(emailForm);
    }
}
