package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.ju23.typespeeder.classesFromDB.Tasks;
import se.ju23.typespeeder.classesFromDB.TasksRepo;
import se.ju23.typespeeder.gameLogic.Challenge;
import se.ju23.typespeeder.gameLogic.TypeSpeederGamePlay;
import se.ju23.typespeeder.userInterfaces.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    @Mock
    private TasksRepo tasksRepoMock;

    @InjectMocks
    private Challenge challenge;


    public void tasksRepoSetup() {
        // Skapa ett exempel på en uppgift som du vill returnera från mocken
        Tasks expectedTask = new Tasks();
        expectedTask.setTaskId(1);
        expectedTask.setTaskName("Example Task");

        // Ställ in beteendet för findById-metoden i tasksRepoMock
        when(tasksRepoMock.findById(1)).thenReturn(Optional.of(expectedTask));
    }
    @Test
    public void testStartChallengePerformance() {
        Challenge challenge = new Challenge();
        long startTime = System.nanoTime();
        challenge.startChallenge();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testLettersToTypePerformance() {
        tasksRepoSetup();
        //Challenge challenge = new Challenge();
        long startTime = System.nanoTime();
        challenge.lettersToType();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
