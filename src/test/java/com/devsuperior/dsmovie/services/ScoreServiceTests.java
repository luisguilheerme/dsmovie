package com.devsuperior.dsmovie.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {

	@InjectMocks
	private ScoreService service;

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private ScoreRepository repository;

	@Mock
	private UserService userService;

	private Long existingMovieId, nonExistingMovieId;
	private ScoreEntity score;
	private ScoreDTO scoreDTO;
	private MovieDTO movieDTO;
	private UserEntity client;
	private MovieEntity movie;

	@BeforeEach
	void setUp() throws Exception {
		existingMovieId = 1L;
		nonExistingMovieId = 2L;

		client = UserFactory.createUserEntity();		
		movie = MovieFactory.createMovieEntity();
		score = ScoreFactory.createScoreEntity();
		scoreDTO = ScoreFactory.createScoreDTO();
		movieDTO = MovieFactory.createMovieDTO();

		Mockito.when(movieRepository.findById(existingMovieId)).thenReturn(Optional.of(movie));
		Mockito.when(movieRepository.findById(nonExistingMovieId)).thenReturn(Optional.empty());

		Mockito.when(repository.saveAndFlush(any())).thenReturn(score);
		
		Mockito.when(movieRepository.save(any())).thenReturn(movie);

	}

	@Test
	public void saveScoreShouldReturnMovieDTO() {

		Mockito.when(userService.authenticated()).thenReturn(client);		

		movieDTO = service.saveScore(scoreDTO);

		Assertions.assertNotNull(movieDTO);
	}

	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {

		Mockito.when(userService.authenticated()).thenReturn(client);

		movie.setId(nonExistingMovieId);
		score.setMovie(movie);
		scoreDTO = new ScoreDTO(score);

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			movieDTO = service.saveScore(scoreDTO);
		});

	}
}