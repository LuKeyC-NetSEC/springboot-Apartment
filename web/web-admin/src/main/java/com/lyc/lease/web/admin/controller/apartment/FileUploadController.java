package com.lyc.lease.web.admin.controller.apartment;


import com.lyc.lease.common.result.Result;
import com.lyc.lease.web.admin.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;


@Tag(name = "文件管理")
@RequestMapping("/admin/file")
@RestController
public class FileUploadController {

    @Autowired
    FileService fileService;

    /**
     * 上传文件
     *
     * @param file 用户上传的文件
     * @return Result<String>
     */
    @Operation(summary = "上传文件")
    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile file) {
//        InputStream stream = file.getInputStream();
        String url = fileService.upload(file);
        return Result.ok(url);
    }

}
