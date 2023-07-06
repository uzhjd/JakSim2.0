package com.twinkle.JakSim.model.service.account;

import com.twinkle.JakSim.model.dao.account.FileDao;
import com.twinkle.JakSim.model.dto.account.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileDao fileDao;
    public boolean updateProfileImage(MultipartFile file, String username) throws Exception{
        UserImage userImage = new UserImage();

        String projectPath = System.getProperty("user.dir") + "/JakSim/src/main/resources/static/image/profiles";
        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        userImage.setUser_id(username);
        userImage.setPath("/image/profiles/" + fileName);

        return fileDao.create(userImage) > 0;
    }
}
