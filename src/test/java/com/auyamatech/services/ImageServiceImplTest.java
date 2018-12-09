package com.auyamatech.services;

import com.auyamatech.domain.Recipe;
import com.auyamatech.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceImplTest {
    ImageService imageService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void testSaveImage() throws IOException {
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Auyamatech.com".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //when
        imageService.saveImage(id, multipartFile);

        //then
        verify(recipeRepository).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}
