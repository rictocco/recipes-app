package com.auyamatech.controllers;

import com.auyamatech.commands.RecipeCommand;
import com.auyamatech.services.ImageService;
import com.auyamatech.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imagefile")MultipartFile file) {
        imageService.saveImage(Long.valueOf(recipeId), file);
        return "redirect:/recipe/"+recipeId+"/show";
    }

    @GetMapping("recipe/{recipeId}/recipeimage")
    public void renderImageFromDb(@PathVariable String recipeId, HttpServletResponse response) throws Exception {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(recipeId));

        byte[] byteArray = new byte[command.getImage().length];
        int i = 0;
        for (Byte primByte : command.getImage()) {
            byteArray[i++] = primByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
